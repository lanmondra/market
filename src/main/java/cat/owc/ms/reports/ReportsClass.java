package cat.owc.ms.reports;

import cat.owc.ms.reports.exceptions.MusicException;
import fcbq.common.FcbqClass;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ReportsClass extends FcbqClass {

	public void notifyError(String errorCode,HttpStatus httpStatus) throws MusicException {
		throw new MusicException(errorCode, httpStatus);
	}
	
	public void notifyError(String errorCode) throws MusicException {
		throw new MusicException(errorCode, HttpStatus.BAD_REQUEST);
	}
	
	public void notifyError(String errorCode, Object[] args) throws MusicException {
		throw new MusicException(errorCode, args, HttpStatus.BAD_REQUEST);
	}	

	public void notifyError(String errorCode, Object[] args, HttpStatus httpStatus) throws MusicException {
		throw new MusicException(errorCode, args, httpStatus);
	}

	public void notifyError(String errorCode, Object[] args, HttpStatus httpStatus, Map<String, String> extraInfo) throws MusicException {
		MusicException reportsException =  new MusicException(errorCode, args, httpStatus);
		reportsException.extraInfo(extraInfo);
		throw reportsException;
	}

}
