package cat.owc.ms.reports.exceptions;

import cat.owc.ms.reports.utils.I18n;
import org.springframework.http.HttpStatus;

import cat.owc.springcommon.exceptions.OWException;
import cat.owc.springcommon.utils.spring.SpringContext;

public class MusicException extends OWException{

	
	public MusicException(HttpStatus status) {
		super(status);
	}


	public MusicException(String code, HttpStatus status) {
		super(SpringContext.getApplicationContext().getBean(I18n.class).getMessage(code), code, status);
	}

	public MusicException(String code, Object[] args, HttpStatus status) {
		super(SpringContext.getApplicationContext().getBean(I18n.class).getMessage(code, args == null? new Object[0] : args), code, status);		
	}

	
	
	
	
}
