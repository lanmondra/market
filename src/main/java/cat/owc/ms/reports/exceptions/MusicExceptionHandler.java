package cat.owc.ms.reports.exceptions;

import cat.owc.springcommon.exceptions.OWExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings("unused")
public class MusicExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private OWExceptionHandler owExceptionHandler;

	@ExceptionHandler
	public ResponseEntity<Object> handle(Exception exception, WebRequest request) {
		return owExceptionHandler.handleException(exception, request);
	}
	
}
