//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.MatchReportTypeDTO;
//import cat.owc.ms.reports.entity.MatchReportType;
//import cat.owc.ms.reports.repository.MatchReportTypeRepository;
//import cat.owc.ms.reports.services.interfaces.IMatchReportTypeService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class MatchReportTypeServiceTest extends AbstractTest {
//
//	@Autowired
//	private IMatchReportTypeService matchReportTypeService;
//
//	@Autowired
//    private MatchReportTypeRepository matchReportTypeRepository;
//
//
//
//	@Test
//    @Transactional
//    public void findReportPermissionsTest() {
//
//	    // Preparar las condiciones de prueba
//        MatchReportType type = matchReportTypeRepository.getOne(1);
//        type.setFederation("fcbq");
//        matchReportTypeRepository.saveAndFlush(type);
//
//        type = matchReportTypeRepository.getOne(2);
//        type.setDeleted(Timestamp.valueOf(LocalDateTime.now()));
//        matchReportTypeRepository.saveAndFlush(type);
//
//        List<MatchReportTypeDTO> matchReportTypes = matchReportTypeService.findAllByFederation("test");
//
//        assertThat(matchReportTypes).as("No debe ser null").isNotNull();
//
//        List<Integer> typeIds = matchReportTypes.stream().map(MatchReportTypeDTO::getId).collect(Collectors.toList());
//        assertThat(typeIds).as("Debe contener los id 3, 4, 5").contains(3, 4, 5);
//
//	}
//
//
//
//
//}
