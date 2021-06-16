package cat.owc.ms.reports.controllers;

import cat.owc.ms.reports.exceptions.IMusicExceptionCode;

public interface IAbstractControllerErrorCode extends IMusicExceptionCode {

	String CONTROLLER_ERROR_CODE = ERROR + CONTROLLER;												// 1001
	
	//-------------------------------------------------------------------------------------------------

	String CONTROLLER_POLL_ERROR_CODE = CONTROLLER_ERROR_CODE + "001";					// 1001001
	String CONTROLLER_FORM_ERROR_CODE = CONTROLLER_ERROR_CODE + "002";					// 1001002
	String CONTROLLER_ADMIN_POLL_ERROR_CODE = CONTROLLER_ERROR_CODE + "003";			// 1001003
	String CONTROLLER_ADMIN_BLOCK_ERROR_CODE = CONTROLLER_ERROR_CODE + "004";			// 1001004


	//------------ GENERIC ERROR CODES -----------------------------------------------------------------
	String CONTROLLER_ABSTRACT_ERROR_CODE = CONTROLLER_ERROR_CODE + "000";					// 1001000
	String NO_USER_UUID = CONTROLLER_ABSTRACT_ERROR_CODE + "201";							// 1001000201
}
