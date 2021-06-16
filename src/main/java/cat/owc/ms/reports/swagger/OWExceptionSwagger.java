package cat.owc.ms.reports.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

//Objecte creat només a efectes de poder documentar el missatge de retorn en cas d'error
@ApiModel(value="ErrorResponse", description="<h5><a id='modelErrorResponse'></a></h5>En caso de error junto con el http status code correspondiente, se retorna este objeto con datos adiccionales")
@Data
public class OWExceptionSwagger {


    @ApiModelProperty(value = "Codigo de error asociado al error producido. Los posible valores se definen en cada endpoint")
    private String code;

    @ApiModelProperty(value = "Http status code")
    private HttpStatus status;

    @ApiModelProperty(value = "Mensaje con la causa del error en el lenguaje especificado en la llamada")
    private String message;

    @ApiModelProperty(value = "Información adiccional asociada al error producido")
    private Map<String, String> extraInfo;

    // Per evitar instancies
    private OWExceptionSwagger() {

    }
}
