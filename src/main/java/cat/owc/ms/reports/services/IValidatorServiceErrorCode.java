package cat.owc.ms.reports.services;

public interface IValidatorServiceErrorCode extends IAbstractServiceErrorCode {
	
	String FIELD_REQUIRED = SERVICE_VALIDATOR_ERROR_CODE + "301";							// 1002011301
	String NOT_NUMBERS = SERVICE_VALIDATOR_ERROR_CODE + "302";								// 1002011302
	String NOT_LETTERS = SERVICE_VALIDATOR_ERROR_CODE + "303";								// 1002011303
	String NOT_LENGTH = SERVICE_VALIDATOR_ERROR_CODE + "304";								// 1002011304
	String CP_NOT_EXIST = SERVICE_VALIDATOR_ERROR_CODE + "305";								// 1002011305
	String START_NUMBER = SERVICE_VALIDATOR_ERROR_CODE + "306";								// 1002011306
	String AGE_REQUIRED = SERVICE_VALIDATOR_ERROR_CODE + "307";								// 1002011307
	String BAD_EMAIL_FORMAT = SERVICE_VALIDATOR_ERROR_CODE + "308";							// 1002011308
	String BANK_ACCOUNT_BAD_FORMATED = SERVICE_VALIDATOR_ERROR_CODE + "309";				// 1002011309

								
}
