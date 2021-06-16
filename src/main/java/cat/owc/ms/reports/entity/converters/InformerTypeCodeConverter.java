package cat.owc.ms.reports.entity.converters;

import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;


public class InformerTypeCodeConverter {

    public static InformerTypeCode toEnumValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof InformerTypeCode) {
           return (InformerTypeCode) value;
        }
        else {
            return InformerTypeCode.valueOf((String) value);

        }
    }
}
