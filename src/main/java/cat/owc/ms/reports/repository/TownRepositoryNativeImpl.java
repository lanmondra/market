package cat.owc.ms.reports.repository;

import java.util.List;

import cat.owc.ms.reports.dto.TownDTO;
import cat.owc.ms.reports.entity.Town;

public class TownRepositoryNativeImpl  extends AbstractRepository<Town, Integer> implements TownRepositoryNative{
	
	String TOWN_FIELD="t.id AS id, "+
					  "t.name AS name ";

	@Override
	public List<TownDTO> findByProvinceId(Integer provinceId) {
		String sql = "SELECT "+ TOWN_FIELD +
				"FROM town t "+
				"LEFT JOIN province p ON p.id = t.province_id "+
				"WHERE t.province_id = "+provinceId +"";

	return listDTO(sql, TownDTO.class);
	}

	@Override
	public List<TownDTO> findByProvinceName(String provinceName) {
		String sql = "SELECT "+ TOWN_FIELD +
				"FROM town t "+
				"LEFT JOIN province p ON p.id = t.province_id "+
				"WHERE p.name = '"+provinceName +"'";
		
		return listDTO(sql, TownDTO.class);
	}
	


}



