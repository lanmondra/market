//package cat.owc.ms.reports.services;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.ReportTemplateColorsDTO;
//import cat.owc.ms.reports.services.interfaces.IReportTemplateColorsService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class ReportTemplateColorsTets extends AbstractTest {
//	
//	
//	@Autowired
//	private  IReportTemplateColorsService reportTemplateColorsService;
//	
//
//	@Transactional
//	@Test
//	public void getColorsByFederation() {
//		
//		String FEDERATION ="AAAA";
//				
//	List<ReportTemplateColorsDTO> listForAAAA= reportTemplateColorsService.findByFederation(FEDERATION);
//
//	assertThat(listForAAAA.size()).as("hay 5 en la base de datos para esta prueba ").isEqualTo(5);
//	
//	}
//	
//}
