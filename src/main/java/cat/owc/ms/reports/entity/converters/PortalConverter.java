package cat.owc.ms.reports.entity.converters;


import cat.owc.ms.reports.entity.enumeration.Portal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PortalConverter implements AttributeConverter<Portal, Integer> {

        @Override
        public Integer convertToDatabaseColumn(Portal enumerator) {
            if (enumerator == null) {
                return null;
            }
            return enumerator.getCode();
        }

        @Override
        public Portal convertToEntityAttribute(Integer code) {
            return toEnumValue(code);
        }


        // Convierte a Portal segun los posibles tipos que pueden venir de la base de datos
        public static Portal toEnumValue(Object value) {
            if (value == null) {
                return null;
            }

            if (value instanceof Portal) {
                return (Portal)value;
            }

            if (value instanceof String) {
                return Portal.valueOf((String)value);
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

            return Stream.of(Portal.values())
                    .filter(c -> c.getCode().equals(intValue))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
}
