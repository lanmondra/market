package cat.owc.ms.reports.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cat.owc.ms.reports.dto.ProvinceDTO;
import cat.owc.ms.reports.entity.Province;
import cat.owc.ms.reports.mapper.ProvinceMapper;
import cat.owc.ms.reports.repository.ProvinceRepository;
import cat.owc.ms.reports.services.interfaces.IProvinceService;

@Service
@Transactional
public class ProvinceService extends AbstractService implements IProvinceService {
	
	private final ProvinceRepository provinceRepository;
	private final ProvinceMapper provinceMapper;
	
	

	public ProvinceService(ProvinceRepository provinceRepository ,
			ProvinceMapper provinceMapper) {
		this.provinceRepository = provinceRepository;
		this.provinceMapper = provinceMapper;
	}



	@Override
	public List<ProvinceDTO> findAll() {
		log.info("se va a mostrar todos las provincias");
		List<Province> allProvinces= provinceRepository.findAll();
		return provinceMapper.toDto(allProvinces);
	}



	@Override
	public ProvinceDTO findbyName(String name) {

		Optional<Province> optionalProvince= provinceRepository.findByName(name);
		
		if(!optionalProvince.isPresent()) {
			notifyError(IProvinceServiceErrorCode.PROVINCE_NOT_FOUND);
		}
		return provinceMapper.toDto(optionalProvince.get()) ;
	}

	
	
}
