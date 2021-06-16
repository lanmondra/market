package cat.owc.ms.reports.entity.converters;

import cat.owc.ms.reports.entity.enumeration.ReportTemplateStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ReportTemplateStatusConverter implements AttributeConverter<ReportTemplateStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ReportTemplateStatus enumerator) {
            if (enumerator == null) {
                return null;
            }
            return enumerator.getCode();
        }

        @Override
        public ReportTemplateStatus convertToEntityAttribute(Integer code) {
         return toEnumValue(code);
        }



    // Convierte a FomrStatus segun los posibles tipos que pueden venir de la base de datos
    public static ReportTemplateStatus toEnumValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof ReportTemplateStatus) {
            return (ReportTemplateStatus)value;
        }

        if (value instanceof String) {
            return ReportTemplateStatus.valueOf((String)value);
        }

        Integer intValue;
        if (value instanceof Byte) {
            intValue = ((Byte) value).intValue();
        }
        else if (value instanceof Integer) {
            intValue = (Integer)value;
        }
        else {
            return null;
        }

        return Stream.of(ReportTemplateStatus.values())
                .filter(c -> c.getCode().equals(intValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
