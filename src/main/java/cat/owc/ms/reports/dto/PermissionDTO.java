package cat.owc.ms.reports.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Definición de un permiso en la aplicación")
public class PermissionDTO extends AbstractDTO {

    @ApiModelProperty(value = "id del permiso")
    private Integer id;

    @JsonIgnore
    private String federation;

    @ApiModelProperty(value = "Nombre del permiso")
    private String name;

    @ApiModelProperty(value = "Indica si es un permiso que permite modificación o solo lectura")
    private Boolean canEdit;


    @ApiModelProperty(value = "Uuid de la categoria de informe a la que está asociada el permiso")
    private String reportCategoryUuid;

    @ApiModelProperty(value = "Nombre de la categoria de informe a la que está asociada el permiso")
    private String reportCategoryName;

    @JsonIgnore
    private Timestamp deleted;


    public void setCanEdit(Object canEdit) {
        this.canEdit = toBoolean(canEdit);
    }

}
