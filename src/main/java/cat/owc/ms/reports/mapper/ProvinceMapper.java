package cat.owc.ms.reports.mapper;

import org.mapstruct.Mapper;

import cat.owc.ms.reports.dto.ProvinceDTO;

import cat.owc.ms.reports.entity.Province;


@Mapper(config = IgnoreUnmappedMapperConfig.class, componentModel = "spring" )
public interface ProvinceMapper extends  EntityMapper<ProvinceDTO, Province>{
	
	default Province fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Province province = new Province();
        province.setId(id);
       return province;
    }

}
