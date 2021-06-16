package cat.owc.ms.reports.services;


import cat.owc.ms.reports.exceptions.IMusicExceptionCode;

public interface IAbstractServiceErrorCode extends IMusicExceptionCode {


	
	String SERVICE_ERROR_CODE = ERROR + SERVICE;											// 1002
	
	//-------------------------------------------------------------------------------------------------
	
	String SERVICE_PROVINCE_ERROR_CODE=SERVICE_ERROR_CODE + "001";					//1002001
	String SERVICE_TOWN_ERROR_CODE = SERVICE_ERROR_CODE +"002";						//1002002
	
	
	
	String SERVICE_VALIDATOR_ERROR_CODE = SERVICE_ERROR_CODE + "301";				//1002011

	
	

	

}


