package cat.owc.ms.reports;

import cat.owc.ms.reports.exceptions.MusicException;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportsClassTest extends AbstractTest {

	@Test
	public void notifyErrorTest() {
		String message = "Hello testing world!";
		
		Boolean multiError = false;
		
		try {
			notifyError("0000000000", new String[] {message});
		} catch (MusicException e) {
			multiError = true;
			
			assertThat(e.getMessage()).as("No s'ha passat cap missatge").isNotNull();
			String emsg = e.getMessage().substring(e.getMessage().indexOf(":") + 1);
			assertThat(emsg).as("No s'han passat els paràmetres del missatge").isEqualTo(message);
		}
		
		assertThat(multiError).as("No ha llançat excepció").isTrue();
	}
	
	@Test
	public void notifySimplestErrorTest() {
		
		Boolean multiError = false;
		
		try {
			notifyError("0000000000");
		} catch (MusicException e) {
			multiError = true;
		}
		
		assertThat(multiError).as("No ha llançat excepció").isTrue();
	}
	
	@Test
	public void notifyErrorParametersNullTest() {
		
		Boolean multiError = false;
		
		try {
			notifyError("0000000000", (Object[]) null);
		} catch (MusicException e) {
			multiError = true;
		}
		
		assertThat(multiError).as("No ha llançat excepció").isTrue();
	}
	
	@Test
	public void notifyErrorHttpStatusTest() {
		
		Boolean multiError = false;
		
		try {
			notifyError("0000000000", HttpStatus.CONFLICT);
		} catch (MusicException e) {
			multiError = true;
			assertThat(e.getStatus()).as("No s'ha passat l'status correctament").isEqualTo(HttpStatus.CONFLICT);
		}
		
		assertThat(multiError).as("No ha llançat excepció").isTrue();
	}
	
}
