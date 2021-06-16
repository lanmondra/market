package cat.owc.ms.reports.permissions;

import cat.owc.ms.reports.exceptions.IMusicExceptionCode;

public interface IPermissionHandlerErrorCode extends IMusicExceptionCode {

	String PERMISSION_HANDLER_ERROR_CODE = ERROR + PERMISSION_HANDLER + "001";						// 1004001
	
	//-------------------------------------------------------------------------------------------------

	String ADMIN_POLLS_NO_ADMIN = PERMISSION_HANDLER_ERROR_CODE + "001";					// 1004001001
	String NO_PERMISSION_FOR_REPORT = PERMISSION_HANDLER_ERROR_CODE + "002";				// 1004001002
	String REPORT_NOT_BELONGS_TO_FEDE = PERMISSION_HANDLER_ERROR_CODE + "003";				// 1004001003
	String NO_ACCESS = PERMISSION_HANDLER_ERROR_CODE + "004";								// 1004001004

}
