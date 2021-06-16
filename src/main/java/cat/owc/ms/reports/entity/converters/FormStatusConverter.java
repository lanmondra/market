package cat.owc.ms.reports.entity.converters;


import cat.owc.ms.reports.entity.enumeration.FormStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class FormStatusConverter implements AttributeConverter<FormStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(FormStatus enumerator) {
            if (enumerator == null) {
                return null;
            }
            return enumerator.getCode();
        }

        @Override
        public FormStatus convertToEntityAttribute(Integer code) {
            return toEnumValue(code);
        }


        // Convierte a FomrStatus segun los posibles tipos que pueden venir de la base de datos
        public static FormStatus toEnumValue(Object value) {
            if (value == null) {
                return null;
            }

            if (value instanceof FormStatus) {
                return (FormStatus)value;
            }

            if (value instanceof String) {
                return FormStatus.valueOf((String)value);
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

            return Stream.of(FormStatus.values())
                    .filter(c -> c.getCode().equals(intValue))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
}
