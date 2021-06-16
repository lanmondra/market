package cat.owc.ms.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"cat.owc.ms.reports.envers", "cat.owc.ms.reports.entity" })
@EnableAutoConfiguration
@ComponentScan(basePackages = {"cat.owc" })
public class OwcReportsApplication {

	public static final String VERSION = "1.0.8";
	public static final String DB_VERSION = "1.0.8";
	public static final String FEDE_HEADER = "federation";
	public static final String REQUEST_ORIGIN_HEADER = "X-App-Origin";
	public static final String LANGUAGE_HEADER = "Accept-Language";



	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OwcReportsApplication.class, args);
	}

}
