package cat.owc.ms.reports.services.interfaces;

import java.util.List;

import cat.owc.ms.reports.dto.ProvinceDTO;
import cat.owc.ms.reports.entity.Province;

public interface IProvinceService {
	
	List<ProvinceDTO> findAll();
	
	ProvinceDTO findbyName(String name);

}
