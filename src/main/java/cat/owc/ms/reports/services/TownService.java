package cat.owc.ms.reports.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.owc.ms.reports.dto.TownDTO;
import cat.owc.ms.reports.repository.TownRepository;
import cat.owc.ms.reports.services.interfaces.ITownService;

@Service
public class TownService extends AbstractService implements ITownService{
	
	
	private final TownRepository townRepository;
	
	
	
	

	public TownService(TownRepository townRepository) {
		
		this.townRepository = townRepository;
	}





	@Override
	public List<TownDTO> findByProvinceId(Integer provinceId) {
	List<TownDTO > townDTO= townRepository.findByProvinceId(provinceId);
		
		if(townDTO.isEmpty()) {
			notifyError(ITownServiceErrorCode.TOWN_NOT_FOUND);
		}
		
		return townDTO;
	}





	@Override
	public List<TownDTO> findByProvinceName(String provinceName) {
		List<TownDTO > townDTO= townRepository.findByProvinceName(provinceName);
		
		if(townDTO.isEmpty()) {
			notifyError(ITownServiceErrorCode.TOWN_NOT_FOUND);
		}
		
		return townDTO;
	}



	



}
