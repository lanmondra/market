package cat.owc.ms.reports.repository;


import java.util.List;

import cat.owc.ms.reports.dto.TownDTO;

public interface TownRepositoryNative {

	
	List<TownDTO> findByProvinceId(Integer provinceId);
	
	List<TownDTO> findByProvinceName (String provinceName);
	
	
}
