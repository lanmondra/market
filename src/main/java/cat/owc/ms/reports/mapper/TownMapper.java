package cat.owc.ms.reports.mapper;

import org.mapstruct.Mapper;

import cat.owc.ms.reports.dto.TownDTO;
import cat.owc.ms.reports.entity.Province;
import cat.owc.ms.reports.entity.Town;

@Mapper(config = IgnoreUnmappedMapperConfig.class, componentModel = "spring" )
public interface TownMapper extends EntityMapper<TownDTO, Town>{
	
	
	default Town fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Town town = new Town();
        town.setId(id);
       return town;
    }

}
