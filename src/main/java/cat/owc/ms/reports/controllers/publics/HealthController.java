package cat.owc.ms.reports.controllers.publics;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.owc.ms.reports.OwcReportsApplication;
import cat.owc.ms.reports.entity.Province;
import cat.owc.ms.reports.services.interfaces.IProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"health"})
@RestController
@RequestMapping("/public/health")
@SuppressWarnings("unused")
public class HealthController {
	
	private final IProvinceService provinceService;

	public HealthController(IProvinceService provinceService) {
		
		this.provinceService = provinceService;
	}
	
	

    private static final LocalDateTime START_DATE = LocalDateTime.now();

    @ApiOperation(value = "Devuelve el estado del servicio y la versión")
    @GetMapping
    public Map<String, Object> health() {
        Map<String, Object> data = new HashMap<>();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        data.put("status", "up");
        data.put("version", OwcReportsApplication.VERSION);
        data.put("db_version", OwcReportsApplication.DB_VERSION);
        data.put("start_date", formater.format(START_DATE));
        data.put("response_date", formater.format(LocalDateTime.now()));

        return data;
    }
    

}

