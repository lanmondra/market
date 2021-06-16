package cat.owc.ms.reports.entity.converters;

import cat.owc.ms.reports.entity.enumeration.PollStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PollStatusConverter implements AttributeConverter<PollStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PollStatus enumerator) {
            if (enumerator == null) {
                return null;
            }
            return enumerator.getCode();
        }

        @Override
        public PollStatus convertToEntityAttribute(Integer code) {
            return toEnumValue(code);
        }


    // Convierte a FomrStatus segun los posibles tipos que pueden venir de la base de datos
    public static PollStatus toEnumValue(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof PollStatus) {
            return (PollStatus)value;
        }

        if (value instanceof String) {
            return PollStatus.valueOf((String)value);
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

        return Stream.of(PollStatus.values())
                .filter(c -> c.getCode().equals(intValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
