package cat.owc.ms.reports.entity.converters;

import cat.owc.ms.reports.entity.enumeration.AnswerTypeCode;


public class AnswerTypeCodeConverter  {

    public static AnswerTypeCode toEnumValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof AnswerTypeCode) {
           return (AnswerTypeCode) value;
        }
        else {
            return AnswerTypeCode.valueOf((String) value);

        }
    }
}
