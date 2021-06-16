//package cat.owc.ms.reports;
//
//import cat.owc.ms.reports.services.PollService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class PollExcelGeneratorTest extends AbstractTest {
//
//
//	@Autowired
//	private PollService pollService;
//	
//
//	
//	@Test
//    @Transactional
//    public void generateDetailedAnswerExcelServicePullTest() {
//		
//		Map<String, Object> map = pollService.generateDetailedAnswerExcel("1111");
//		assertThat(map).isNotNull();
//	}
//	
//
//	@Test
//    @Transactional
//    public void generateAggregatedAnswerExcelTest(){
//		
//		Map<String, Object> map = pollService.generateAggregatedAnswerExcel("1111");
//		assertThat(map).isNotNull();
//	}
//	
//	
//}
