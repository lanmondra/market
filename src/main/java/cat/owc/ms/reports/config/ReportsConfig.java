package cat.owc.ms.reports.config;

import cat.owc.owcauthenticationutils.utils.OWCAuthenticationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class ReportsConfig {
	
	/**
	 * Definim singleton pel component OWCAuthenticationUtils
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	@Bean
	public OWCAuthenticationUtils authenticationUtils() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		return new OWCAuthenticationUtils(new ClassPathResource("rsa/public_key.pem").getInputStream());
	}
	
}
