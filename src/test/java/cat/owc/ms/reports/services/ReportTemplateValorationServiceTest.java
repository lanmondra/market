//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.ReportTemplateDTO;
//import cat.owc.ms.reports.dto.ReportTemplateValorationDTO;
//import cat.owc.ms.reports.exceptions.MusicException;
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class ReportTemplateValorationServiceTest extends AbstractTest {
//
//	
//	@Autowired
//	private ReportTemplateValorationService rtValorationService ;
//	
//	@Autowired
//	private ReportTemplateService reportTemplateService;
//	
//
//	
//	@Transactional
//	@Test
//	public void getAllValorations() {
//	ReportTemplateDTO reportTemplateDTO=	reportTemplateService.findReportTemplateByUuidDTO("CCCC");
//	
//	List<ReportTemplateValorationDTO> rtValorartionList= rtValorationService.findAllByReportTemplateDTO(reportTemplateDTO.getUuid());
//		
//	assertThat(rtValorartionList.size() ).as("si es diferente de null devulve datos").isNotNull();
//	}
//	
//	@Test
//	@Transactional
//	public void deleteReportTemaplateValorationTest() {
//		String VALORARTION_UUID="vAAAA";		
//	 ReportTemplateValorationDTO reportTemplateValoration=	rtValorationService.findDTO(VALORARTION_UUID);	
//	 rtValorationService.delete(reportTemplateValoration.getUuid());
//		
// 
//	 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//	 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.NOT_FOUND),
//             " Expected error code " + IReportTemplateValorationServiceErrorCode.NOT_FOUND);
//   assertThatExceptionOfType(MusicException.class)
//           .isThrownBy(() -> rtValorationService.findDTO(VALORARTION_UUID))
//           .has(errorCode);
//	 
//	}
//	
//	@Transactional
//	@Test
//	public void valorationNotFoundTest() {
//		String VALORATION_UUID="INEXISTENTE";
//		
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.NOT_FOUND),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.NOT_FOUND);
//
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.findDTO(VALORATION_UUID))
//	           .has(errorCode);
//	
//	}
//	
//	@Transactional
//	@Test
//	public void addValorationTest() {
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		
//		ReportTemplateValorationDTO valorationDTOResult =rtValorationService.add(valorationDTO);
//		
//		assertThat(valorationDTOResult).isNotNull();
//	}
//	
//	@Transactional
//	@Test
//	public void addValorationReportIdNulltest() {
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setReportTemplateId(00000);
//			
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_NOT_FOUND),
//	             " Expected error code " + IReportTemplateServiceErrorCode.RT_NOT_FOUND);
//
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);
//	}
//	
//	
//	private ReportTemplateValorationDTO complitRTvalorationDTO() {
//		
//		ReportTemplateValorationDTO rtv = new ReportTemplateValorationDTO();
//		rtv.setName("valoracion ");
//		rtv.setMinGrade(55);
//		rtv.setMaxGrade(78);
//		rtv.setReportTemplateId(3);
//				
//		return rtv;
//	
//	}
//	
//	@Test
//	@Transactional
//	public void addValorationMissingName() {
//		
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setName(null);
//		
//		
//		
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.MISSING_NAME),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.MISSING_NAME);
//
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);
//		
//	}
//	
//	@Test
//	@Transactional
//	public void addValorationMissingTemplateId() {
//		
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setReportTemplateId(null);
//						
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.MISSING_RT_ID),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.MISSING_RT_ID);
//
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);
//		
//	}
//	
//	@Test
//	@Transactional
//	public void addValorationMissingMaxGlade() {
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setMaxGrade(null);
//						
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.MISSING_MAX_GRADE),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.MISSING_MAX_GRADE);
//
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);
//	}
//	
//	@Test
//	@Transactional
//	public void addValorationMissingMinGlade() {
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setMinGrade(null);
//						
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.MISSING_MIN_GRADE),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.MISSING_MIN_GRADE);
//
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);		
//		
//	}
//	
//	@Test
//	@Transactional
//	public void addValorationMinGradeOverMaxTest() {
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setMinGrade(80);
//		valorationDTO.setMaxGrade(70);
//		
//						
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.MIN_GRADE_OVER_MAX),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.MIN_GRADE_OVER_MAX);
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);			
//		
//	}
//	
//	@Test
//	@Transactional
//	public void addvalorationSameValorationsTest() {
//		
//		ReportTemplateValorationDTO valorationDTO= complitRTvalorationDTO();
//		valorationDTO.setMinGrade(60);
//		valorationDTO.setMaxGrade(70);
//		
//
//		 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//		 reportsException.getCode().equals(IReportTemplateValorationServiceErrorCode.DUPLICATED_VALUE),
//	             " Expected error code " + IReportTemplateValorationServiceErrorCode.DUPLICATED_VALUE);
//	   assertThatExceptionOfType(MusicException.class)
//	           .isThrownBy(() -> rtValorationService.add(valorationDTO))
//	           .has(errorCode);
//		
//	
//	}
//	
//	@Transactional
//	@Test
//	public void getReportTemplateValorationByTemaplateId() {
//	ReportTemplateDTO reportTemplateDTO=	reportTemplateService.findReportTemplateByUuidFilledDTO("CCCC");
//	String valoration = rtValorationService.getValorationForGrade(reportTemplateDTO.getId(), 99);
//	assertThat(valoration).as("Debe ser null").isNull();
//
//	}
//	
//	
//		
//}
