package cat.owc.ms.reports.swagger;

import cat.owc.ms.reports.dto.AbstractDTO;

public interface SwaggerDefaults {
    String HTTP_401_MESSAGE = "No autorizado";
    String HTTP_403_MESSAGE = "Acceso prohibido";
    String HTTP_404_MESSAGE = "Datos no encontrados";
    String HTTP_500_MESSAGE = "Error interno";
    String DATE_FORMAT = "(Formato " + AbstractDTO.JSON_DATE_FORMAT + ")";
    String DATETIME_FORMAT = "(Formato " + AbstractDTO.JSON_DATETIME_FORMAT + ")";
    String TIMESTAMP_FORMAT = "(Formato " + AbstractDTO.JSON_TIMESTAMP_FORMAT + ")";
}
