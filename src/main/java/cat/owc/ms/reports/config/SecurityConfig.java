package cat.owc.ms.reports.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import cat.owc.owcauthenticationutils.filters.JWTFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_GET_WHITELIST = {
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/swagger**",
        "/webjars/**",
        "/health/**",
		"/public/**",
	};

	private static final String[] AUTH_POST_WHITELIST = {
		"/public/**",
    };
	
	/**
	 * Configuració dels requests públics
	 */
	@Override
	public void configure(WebSecurity security) {
		security
			.ignoring().antMatchers(HttpMethod.GET, AUTH_GET_WHITELIST)
			.and().ignoring().antMatchers(HttpMethod.POST, AUTH_POST_WHITELIST);
	}
	
	/**
	 * Configuració dels filtres d'autenticació
	 */
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security
			.csrf().disable()
			.cors()
			.and().authorizeRequests().anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new JWTFilter(null, null), BasicAuthenticationFilter.class)
		;
	}

}
