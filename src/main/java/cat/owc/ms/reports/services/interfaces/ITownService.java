package cat.owc.ms.reports.services.interfaces;

import java.util.List;

import cat.owc.ms.reports.dto.TownDTO;

public interface ITownService {
	
	
	List<TownDTO > findByProvinceId(Integer provinceId);
	
	List<TownDTO > findByProvinceName(String provinceName);

}
