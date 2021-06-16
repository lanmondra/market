//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.ContextInfo;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.dto.AnswerOptionTemplateDTO;
//import cat.owc.ms.reports.dto.AnswerTemplateDTO;
//import cat.owc.ms.reports.entity.AnswerTemplate;
//import cat.owc.ms.reports.repository.AnswerTemplateRepository;
//import cat.owc.ms.reports.services.interfaces.IAnswerTemplateService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class AnswerTemplateServiceTest extends AbstractTest {
//
//	@Autowired
//	private IAnswerTemplateService answerTemplateService;
//
//	@Autowired
//	private AnswerTemplateRepository answerTemplateRepository;
//
//
//	@Test
//    @Transactional
//    public void findAllTest() {
//		// Asignar catalan ya que una de las plantillas esta solo en catalan
//		ContextInfo contextInfo =  (ContextInfo)ClientContextHolder.getCurrentContext();
//		contextInfo.setLanguage("ca");
//		ClientContextHolder.setCurrentContext(contextInfo);
//
//		// Asignar federacion diferente de ALL a una de las plantillas
//		AnswerTemplate answerTemplate = answerTemplateRepository.getOne(7);
//		answerTemplate.setFederation("test");
//		answerTemplateRepository.saveAndFlush(answerTemplate);
//
//		List<AnswerTemplateDTO> answerTemplates = answerTemplateService.findAllFilledDTO();
//
//		assertThat(answerTemplates).as("No debe ser null").isNotNull();
//		assertThat(answerTemplates).as("Debe tener datos").isNotEmpty();
//
//		Set<AnswerOptionTemplateDTO> answerOptions = new HashSet<>();
//		answerTemplates.forEach(answerTemplateDTO -> answerOptions.addAll(answerTemplateDTO.getAnswerOptions()));
//
//		assertThat(answerOptions).as("No debe ser null").isNotNull();
//		assertThat(answerOptions).as("Debe tener datos").isNotEmpty();
//
//		List<String> fedes = answerTemplates.stream()
//				.map(answerTemplateDTO -> answerTemplateDTO.getFederation())
//				.distinct()
//				.collect(Collectors.toList());
//
//		assertThat(fedes).as("Debe contener ALL y test").containsExactlyInAnyOrder("ALL", "test");
//	}
//
//
//	@Test
//	@Transactional
//	public void getByUuidTest() {
//
//		AnswerTemplateDTO answerTemplateDTO = answerTemplateService.findByUuidDTO("1");
//
//		assertThat(answerTemplateDTO).as("No debe ser null").isNotNull();
//		assertThat(answerTemplateDTO.getAnswerOptions()).as("debe tener dos elementos").hasSize(2);
//	}
//
//
//}
