package cat.owc.ms.reports.services;

import cat.owc.ms.reports.exceptions.IMusicExceptionCode;
import static cat.owc.ms.reports.services.IAbstractServiceErrorCode.SERVICE_PROVINCE_ERROR_CODE;

public interface IProvinceServiceErrorCode extends IMusicExceptionCode{
	
	String PROVINCE_NOT_FOUND=SERVICE_PROVINCE_ERROR_CODE + "001";		//1002001001

}
