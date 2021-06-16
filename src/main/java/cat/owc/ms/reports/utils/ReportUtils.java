package cat.owc.ms.reports.utils;

import cat.owc.ms.reports.ContextInfo;
import cat.owc.ms.reports.config.ClientContextHolder;
import cat.owc.ms.reports.entity.converters.PortalConverter;
import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
import cat.owc.ms.reports.entity.enumeration.Portal;
import cat.owc.owcauthenticationutils.dtos.JWTUser;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ReportUtils {

	private static Environment environment;


	public ReportUtils(Environment env) {
		environment = env;
	}


	/**
	 * Returns the current environment profile
	 * @return
	 */
	public static String getActiveProfile() {
		List<String> profiles = Arrays.asList(environment.getActiveProfiles());
		return profiles.get(0);
	}


	/**
	 * Retorna el uuid del usuario en función del origen de la petición establecida en el contexto
	 * @param user
	 * @return
	 */
	public static String getUuid(JWTUser user) {
		if (ClientContextHolder.getCurrentContext() == null) {
			return user.getUuid(JWTUser.USER_UUID);
		}

		switch (ClientContextHolder.getRequestOrigin()) {
			case ContextInfo.ORIGIN_REFEREE: return user.getUuid(JWTUser.REFEREE_UUID);
			case ContextInfo.ORIGIN_FEDERATED: return user.getUuid(JWTUser.FEDERATED_UUID);
			default: return user.getUuid(JWTUser.USER_UUID);
		}
	}

	/**
	 * Retorna el tipo de informador en función del origen de la petición. El tipo de informador corresponde al perfil desde donde el usuario
	 * va a ejectuar las encuestas o informes
	 * @return
	 */
	public static InformerTypeCode getInformerType() {
		if (ClientContextHolder.getCurrentContext() == null) {
			return InformerTypeCode.CLUB;
		}

		switch (ClientContextHolder.getRequestOrigin()) {
			case ContextInfo.ORIGIN_REFEREE: return InformerTypeCode.REFEREE;
			case ContextInfo.ORIGIN_FEDERATED: return InformerTypeCode.FEDERATED;
			default: return InformerTypeCode.CLUB;
		}

	}




	/**
	 * Retorna el portal correspondiente al origen de la petición
	 * @return
	 */
	public static Portal getPortal() {
		if (ClientContextHolder.getCurrentContext() == null) {
			return null;
		}

		return PortalConverter.toEnumValue(ClientContextHolder.getRequestOrigin());
	}


	/**
	 * Verifica si la federacion indicada coincide con la federación origen de la petición
	 * @param federation
	 * @return
	 */
	public static Boolean equalsRequestFederation(String federation) {
		if ((federation == null) || (ClientContextHolder.getCurrentContext() == null)) {
			return false;
		}

		return federation.equalsIgnoreCase(ClientContextHolder.getFederation());
	}




	public static boolean isTestProfile() {
		return getActiveProfile().contains("test");
	}



}
