package cat.owc.ms.reports.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TownDTO extends AbstractDTO {
	
	
	private Integer id;
	
	
	private String name;

}
