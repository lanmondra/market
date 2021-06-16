package cat.owc.ms.reports.services;

import cat.owc.ms.reports.exceptions.IMusicExceptionCode;
import static cat.owc.ms.reports.services.IAbstractServiceErrorCode.SERVICE_TOWN_ERROR_CODE;


public interface ITownServiceErrorCode extends IMusicExceptionCode{
	
	String TOWN_NOT_FOUND =SERVICE_TOWN_ERROR_CODE+"001";				//1002002001

}
