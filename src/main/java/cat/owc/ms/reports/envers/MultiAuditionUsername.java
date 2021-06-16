package cat.owc.ms.reports.envers;

import cat.owc.owcauthenticationutils.dtos.JWTUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MultiAuditionUsername extends AuditionUsername {

	@Override
	public String get() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null && auth.getPrincipal() != null) {
			JWTUser user = (JWTUser) auth.getPrincipal();
			return user.getUsername();
		}
		
		return null;
	}

}
