package cat.owc.ms.reports.permissions;

import cat.owc.ms.reports.ReportsClass;
import cat.owc.ms.reports.dto.*;

import cat.owc.ms.reports.entity.Permission;
import cat.owc.ms.reports.entity.converters.InformerTypeCodeConverter;
import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
import cat.owc.ms.reports.services.interfaces.*;
import cat.owc.ms.reports.utils.ReportUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("permissionHandler")
public class PermissionHandler  extends ReportsClass {

//	private final IPollService pollService;
//	private final IReportTemplateService reporTemplateService;
//	private final IPermissionService permissionService;
//	private final IBlockService blockService;
//	private final IQuestionService questionService;
//	private final IReportCategoryService reportCategoryService;
//	private final ICampaignService campaignService;
//	private final IFormService formService;
//	private final IReportTemplateValorationService rtValorationService;

//	public PermissionHandler(IPollService pollService,
//							 IReportTemplateService reporTemplateService,
//							 IPermissionService permissionService,
//							 IBlockService blockService,
//							 IQuestionService questionService,
//							 IReportCategoryService reportCategoryService,
//							 ICampaignService campaignService,
//							 IFormService formService,
//							 IReportTemplateValorationService rtValorationService) {
//		this.pollService = pollService;
//		this.reporTemplateService = reporTemplateService;
//		this.permissionService = permissionService;
//		this.blockService = blockService;
//		this.questionService = questionService;
//		this.reportCategoryService = reportCategoryService;
//		this.campaignService = campaignService;
//		this.formService = formService;
//		this.rtValorationService = rtValorationService;
//	}
	public PermissionHandler() {
		
	}
//
//	/**
//	 * 
//	 * @param permission
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public boolean hasPermission(String permission) {
//		List<GrantedAuthority> authorities = getGrantedAuthorities();
//		
//		for(GrantedAuthority ga : authorities) {
//			if(ga.getAuthority().equals(permission)) {
//				return true;
//			}
//		}
//		
//		notifyError(IPermissionHandlerErrorCode.NO_ACCESS, HttpStatus.UNAUTHORIZED);
//		return false;
//	}
//
//
//	private List<GrantedAuthority> getGrantedAuthorities() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		return (List<GrantedAuthority>) auth.getAuthorities();
//	}
//
//
//	/**
//	 * 
//	 * @param permissions
//	 * @return
//	 */
//	public boolean hasPermission(String... permissions) {
//		for(String permission : permissions) {
//			if(hasPermission(permission)) {
//				return true;
//			}
//		}
//
//		notifyError(IPermissionHandlerErrorCode.NO_ACCESS, HttpStatus.UNAUTHORIZED);
//		return false;
//	}
//	
//	/**
//	 * 
//	 * @param role
//	 * @return
//	 */
//	public boolean hasRole(String role) {
//		return false;
//	}
//	
//	/**
//	 * 
//	 * @param roles
//	 * @return
//	 */
//	public boolean hasAnyRole(String... roles) {
//		return false;
//	}
//
//
//	/**
//	 * Retorna si el usuario tiene algun permiso de edición o lectura de categorias de informe
//	 * @return
//	 */
//	public boolean hasReportCategoryPermissions() {
//		List<String> categoryPermissions = getUserCategoryAuthorities();
//		if (categoryPermissions.isEmpty()) {
//			notifyError(IPermissionHandlerErrorCode.NO_ACCESS, HttpStatus.UNAUTHORIZED);
//		}
//
//		return true;
//
//	}
//
//	/**
//	 * Verifica si puede acceder a la lista de encuestas. Para ello debe ser admin.
//	 * Las encuestas a recuperar se determinaran por el tipo de admin que sea.
//	 * @return
//	 */
//	public boolean canGetPollsList() {
//		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
//		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//			if (grantedAuthority.getAuthority().equals(Permission.ADMIN) ||
//				grantedAuthority.getAuthority().startsWith(Permission.POLL_ADMIN_PREFIX)) {
//				return true;
//			}
//		}
//
//		notifyError(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN, HttpStatus.UNAUTHORIZED);
//		return false;
//	}



//	public boolean canAccessPoll(String pollUuid) {
//		PollDTO pollDTO = pollService.findPollByUuidDTO(pollUuid);
//		return canAccessPoll(pollDTO);
//	}
//
//
//	public boolean canAccessPoll(Integer pollId) {
//		PollDTO pollDTO = pollService.findPollByIdDTO(pollId);
//		return canAccessPoll(pollDTO);
//	}

//
//	/**
//	 * Verifica si el usuario tiene acceso a la encuesta.
//	 * Para ello la encuesta debe pertenecer a la federación origen de la llamada y
//	 * el usuario debe ser admin global o admin del tipo de informador asociado a la encuesta
//	 *
//	 * @param pollDTO
//	 * @return
//	 */
//	public boolean canAccessPoll(PollDTO pollDTO) {
//
//		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
//
//		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//			if (grantedAuthority.getAuthority().equals(Permission.ADMIN)) {
//				return true;
//			}
//			else if (grantedAuthority.getAuthority().startsWith(Permission.POLL_ADMIN_PREFIX)) {
//				InformerTypeCode informerTypeCode = getInformerTypeForAuthority(grantedAuthority.getAuthority());
//				if (informerTypeCode.equals(pollDTO.getInformerTypeCode())) {
//					return true;
//				}
//			}
//		}
//		notifyError(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN, HttpStatus.UNAUTHORIZED);
//		return false;
//	}
//
//
//	/**
//	 * Verifica que el usuario sea admin global o si es admin para un tipo de destinatario concreto, el tipo de la encuesta sea el mismo
//	 * @param newPollInformerType
//	 * @return
//	 */
//	public boolean canAddPoll(InformerTypeCode newPollInformerType) {
//
//		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
//
//		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//			if (grantedAuthority.getAuthority().equals(Permission.ADMIN)) {
//				return true;
//			}
//			else if (grantedAuthority.getAuthority().startsWith(Permission.POLL_ADMIN_PREFIX)) {
//				InformerTypeCode informerTypeCode = getInformerTypeForAuthority(grantedAuthority.getAuthority());
//				if (informerTypeCode.equals(newPollInformerType)) {
//					return true;
//				}
//			}
//		}
//		notifyError(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN, HttpStatus.UNAUTHORIZED);
//		return false;
//	}
//
//
//
//
//
//
//	/**
//	 * Devuelve la lista de tipos de informador que gestiona un admin de encuestas
//	 * @return
//	 */
//	public List<InformerTypeCode> getPollAdminInformerTypes() {
//
//		List<InformerTypeCode> informerTypeCodes = new ArrayList<>();
//
//		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
//		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//			if (grantedAuthority.getAuthority().equals(Permission.ADMIN)) {
//				return Arrays.asList(InformerTypeCode.values());
//			}
//			else if (grantedAuthority.getAuthority().startsWith(Permission.POLL_ADMIN_PREFIX)) {
//				informerTypeCodes.add(getInformerTypeForAuthority(grantedAuthority.getAuthority()));
//			}
//		}
//
//		return informerTypeCodes;
//	}
//
//
//	/**
//	 * Devuelve el codigo de tipo de informador asociado a un permiso de admin
//	 * @param authority
//	 * @return
//	 */
//	private InformerTypeCode getInformerTypeForAuthority(String authority) {
//		String informerTypeCode;
//
//		if (authority.startsWith(Permission.POLL_ADMIN_PREFIX)) {
//			informerTypeCode = authority.substring(Permission.POLL_ADMIN_PREFIX.length());
//		}
//		else if (authority.startsWith(Permission.ADMIN_PREFIX)) {
//			informerTypeCode = authority.substring(Permission.ADMIN_PREFIX.length());
//		}
//		else {
//			informerTypeCode = "";
//		}
//		return InformerTypeCodeConverter.toEnumValue(informerTypeCode);
//	}


	/**
	 * Retorna si el usuario puede acceder a un formulario. Para ello, debe poder acceder a la plantilla
	 * @param formUuid
	 * @return
	 */
//	public boolean canDeleteForm(String formUuid) {
//
//		Form form = formService.findFormByUuidFilled(formUuid);
//		return canAccessReport(form.getReportTemplate().getUuid());
//	}


	/**
	 * Retorna si el usuario puede crear, modificar o borrar una plantilla de informe.
	 * Para ello, debe ser admin o tener uno de los permisos de edicion asociados a la categoria a la que pertenece
	 * la plantilla de informe
	 *
	 * @param reportUuid
	 * @return
	 */
//	public boolean canEditReport(String reportUuid) {
//		return canAccessReport(reportUuid, null, true);
//	}
//
//
//	/**
//	 * Retorna si el usuario puede crear, modificar o borrar una plantilla de informe.
//	 * Para ello, debe ser admin o tener uno de los permisos de edicion asociados a la categoria a la que pertenece
//	 * la plantilla de informe
//	 *
//	 * @param reportId
//	 * @return
//	 */
//	public boolean canEditReport(Integer reportId) {
//		return canAccessReport(null, reportId, true);
//	}


//	/**
//	 * Valida que el usuario tenga acceso (de lectura o de escritura) la plantilla de informe
//	 * @param reportUuid
//	 * @return
//	 */
//	public boolean canAccessReport(String reportUuid) {
//
//		return canAccessReport(reportUuid, null, false);
//	}
//
//
//
//	/**
//	 * Valida que el usuario tenga acceso (de lectura o de escritura) la plantilla de informe
//	 * @param reportTemplateId
//	 * @return
//	 */
//	public boolean canAccessReport(Integer reportTemplateId) {
//
//		return canAccessReport(null, reportTemplateId, false);
//	}


//	private boolean canAccessReport(String reportUuid, Integer reportId, boolean forEdit) {
//		List<PermissionDTO> reportPermissions = null;
//
//		if (reportUuid != null) {
//			reportPermissions = permissionService.findReportPermissionsDTO(reportUuid);
//		}
//		else {
//			reportPermissions = permissionService.findReportPermissionsDTO(reportId);
//		}
//
//		// No deberia darse pero por si acaso...
//		if ((reportPermissions == null) || reportPermissions.isEmpty()) {
//			return true;
//		}
//
//		// Validar que el report pertenezca a la federación solicitante
//		if (!ReportUtils.equalsRequestFederation(reportPermissions.get(0).getFederation())) {
//			notifyError(IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE, HttpStatus.UNAUTHORIZED);
//			return false;
//		}
//
//		// Solo se permiten permisos de edicion
//		if (forEdit) {
//			reportPermissions = reportPermissions.stream()
//					.filter(PermissionDTO::getCanEdit)
//					.collect(Collectors.toList());
//		}
//
//		// Recuperar los permisos del usuario
//		List<String> userAuthorities =getGrantedAuthorities().stream()
//										.map(GrantedAuthority::getAuthority)
//										.collect(Collectors.toList());
//
//		boolean isAdmin = userAuthorities.stream()
//				.anyMatch(userAuthority -> userAuthority.equals(Permission.ADMIN));
//
//		if (isAdmin) {
//			return true;
//		}
//
//		// Buscar si el usuario tiene alguno de los permisos del form
//		for (PermissionDTO permissionDTO : reportPermissions) {
//			String authority = userAuthorities.stream()
//					.filter(userAuthority -> userAuthority.equals(permissionDTO.getName()))
//					.findFirst()
//					.orElse(null);
//
//			if ((authority != null)) {
//				return true;
//			}
//		}
//
//		notifyError(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT, HttpStatus.UNAUTHORIZED);
//		return false;
//	}

//
//	public boolean hasAdminPermission() {
//		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
//
//		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//			if (grantedAuthority.getAuthority().equals(Permission.ADMIN) ||
//			    grantedAuthority.getAuthority().startsWith(Permission.ADMIN_PREFIX))
//				return true;
//		}
//
//		return false;
//	}
//
//
//	/**
//	 * Devuelve los permisos del usuario relacionados con categorias
//	 * @return
//	 */
//	public List<String> getUserCategoryAuthorities() {
//		return getGrantedAuthorities().stream()
//				.map(GrantedAuthority::getAuthority)
//				.filter(userAuthority -> userAuthority.startsWith(Permission.REPORT_CAT_EDIT_PREFIX) ||
//										 userAuthority.startsWith(Permission.REPORT_CAT_READ_PREFIX))
//				.collect(Collectors.toList());
//	}
//
//
//	public boolean canEdit() {
//		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
//
//		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
//			if (grantedAuthority.getAuthority().equals(Permission.ADMIN) ||
//			    grantedAuthority.getAuthority().startsWith(Permission.ADMIN_PREFIX) ||
//				grantedAuthority.getAuthority().startsWith(Permission.POLL_ADMIN_PREFIX) ||
//			    grantedAuthority.getAuthority().startsWith(Permission.REPORT_CAT_EDIT_PREFIX)) {
//				return true;
//			}
//		}
//
//		notifyError(IPermissionHandlerErrorCode.NO_ACCESS, HttpStatus.UNAUTHORIZED);
//		return false;
//	}

/*
	public boolean canEditBlock(BlockDTO blockDTO) {
		if (blockDTO.getPollId() != null) {
			return canAccessPoll(blockDTO.getPollId());
		}
		else if (blockDTO.getReportTemplateId() != null) {
			return canAccessReport(null, blockDTO.getReportTemplateId(), true);
		}
		else {
			return false;
		}
	}

	public boolean canEditBlock(String blockUuid) {
		BlockDTO blockDTO = blockService.findBlockByUuidDTO(blockUuid);
		return canEditBlock(blockDTO);
	}


	public boolean canEditBlock(Integer blockId) {
		BlockDTO blockDTO = blockService.findBlockByIdDTO(blockId);
		return canEditBlock(blockDTO);
	}


	public boolean canEditQuestion(String questionUuid) {
		QuestionDTO questionDTO = questionService.findQuestionDTO(questionUuid);
		return canEditBlock(questionDTO.getBlockId());
	}


	public boolean canEditQuestion(Integer questionId) {
		QuestionDTO questionDTO = questionService.findQuestionDTO(questionId);
		return canEditBlock(questionDTO.getBlockId());
	}


	public boolean canAccessCategory(String categoryUuid) {
		ReportCategoryDTO reportCategoryDTO = reportCategoryService.findOneByUuidDTO(categoryUuid);

		return hasPermission(Permission.getNameForCategory(reportCategoryDTO.getId(), true),
							 Permission.getNameForCategory(reportCategoryDTO.getId(), false));
	}


	public boolean canEditCategory(String categoryUuid) {
		ReportCategoryDTO reportCategoryDTO = reportCategoryService.findOneByUuidDTO(categoryUuid);

		return hasPermission(Permission.getNameForCategory(reportCategoryDTO.getId(), true));
	}


	public boolean canEditCategory(Integer categoryId) { ;

		return hasPermission(Permission.getNameForCategory(categoryId, true));
	}


	public boolean canAccessCampaign(String campaignUuid) {
		CampaignDTO campaignDTO = campaignService.findByUuid(campaignUuid);
		return hasPermission(Permission.getNameForCategory(campaignDTO.getReportTemplateCategoryId(), false));

	}


	public boolean canEditCampaign(String campaignUuid) {
		CampaignDTO campaignDTO = campaignService.findByUuid(campaignUuid);
		return hasPermission(Permission.getNameForCategory(campaignDTO.getReportTemplateCategoryId(), true));
	}


	public boolean canEditCampaign(CampaignDTO campaignDTO) {
		return canAccessReport(null, campaignDTO.getReportTemplateId(), true);

	}


	public boolean canEditValoration(String valorationUiud) {
		ReportTemplateValorationDTO valorationDTO = rtValorationService.findDTO(valorationUiud);

		return canEditReport(valorationDTO.getReportTemplateId());
	}
	*/

}
