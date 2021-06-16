package cat.owc.ms.reports.envers;

import org.springframework.stereotype.Component;

@Component
public class MultiAuditionOrigin extends AuditionOrigin {

	public static final String AUDITION_ORIGIN = "reports";
	
	public String get() {
		return AUDITION_ORIGIN;
	}
	
}
