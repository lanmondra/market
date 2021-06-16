package cat.owc.ms.reports.excel;

import cat.owc.ms.reports.exceptions.IMusicExceptionCode;

public interface IExcelGeneratorErrorCode extends IMusicExceptionCode {

	String EXCEL_GENERATOR_ERROR_CODE = ERROR + EXCEL_GENERATOR + "001";						// 1005001
	
	//-------------------------------------------------------------------------------------------------

	String EXCEL_GENERATION_ERROR = EXCEL_GENERATOR_ERROR_CODE + "201";								// 1005001201

}
