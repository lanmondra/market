
package cat.owc.ms.reports.controllers.publics;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.owc.ms.reports.dto.ProvinceDTO;
import cat.owc.ms.reports.dto.TownDTO;
import cat.owc.ms.reports.services.AbstractService;
import cat.owc.ms.reports.services.interfaces.IProvinceService;
import cat.owc.ms.reports.services.interfaces.ITownService;

@RestController
@RequestMapping("/public/province")
public class ProvinceController extends AbstractService{
	
	
	private final IProvinceService provinceService;
	private final ITownService townService;

	public ProvinceController(IProvinceService provinceService ,
							 ITownService townService) {
		
		this.provinceService = provinceService;
		this.townService = townService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProvinceDTO>> findAll(){
		
		
		return ResponseEntity.ok(provinceService.findAll());
		
	}
	
	@GetMapping("/name")
	public ResponseEntity<ProvinceDTO> findByName(@RequestParam String name){
		
		
		return ResponseEntity.ok(provinceService.findbyName(name));
	}
	
	
	@GetMapping ("/towns")
	public ResponseEntity<List<TownDTO>> findTowns(@RequestParam Integer provinceId){
		
		return ResponseEntity.ok(townService.findByProvinceId(provinceId));
	}
	
	
	
	@GetMapping ("/townProvinceName")
	public ResponseEntity<List<TownDTO>> findTownsProvinceName(@RequestParam String provinceName){
		
		return ResponseEntity.ok(townService.findByProvinceName(provinceName));
	}

}
