package cat.owc.ms.reports.entity.converters;

import cat.owc.ms.reports.entity.enumeration.SubjectTypeCode;


public class SubjectTypeCodeConverter {

    public static SubjectTypeCode toEnumValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof SubjectTypeCode) {
           return (SubjectTypeCode) value;
        }
        else {
            return SubjectTypeCode.valueOf((String) value);

        }
    }
}
