package cat.owc.ms.reports.services.interfaces;

import java.util.Date;

public interface IValidatorService {

	void validateNotNull(Object target);

	void validateNoNumbers(String target);

	void validateNoLetter(String target);

	void validateLength(String target, Integer lng);

	void validatePostalCode(String target);

	void validatePostalCode(String target, String errorCode);

	void validatePhone(String target);

	void validatePhone(String target, String errorCode);

	void validateMobile(String target);

	void validateMobile(String target, String errorCode);

	void validateBirthDate(Date birthDate, Integer age);

	void validateBirthDate(Date birthDate, Integer age, String errorCode);

	void validateEmail(String email);

	void validateEmail(String email, String errorCode);

	void validateNotNull(Object object, String error);

	void validateNoNumbers(String string, String error);

	void validateNoLetter(String string, String error);

	void validateLength(String string, Integer lng, String errorCode);

	void validateBankAccount(String bankAccount);

	void validateBankAccount(String bankAccount, String errorCode);


}
