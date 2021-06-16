//package cat.owc.ms.reports.services;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.AnswerDTO;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.entity.Answer;
//import cat.owc.ms.reports.entity.Block;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.Poll;
//import cat.owc.ms.reports.entity.ReportTemplate;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.PollStatus;
//import cat.owc.ms.reports.entity.enumeration.ReportTemplateStatus;
//import cat.owc.ms.reports.entity.enumeration.SubjectTypeCode;
//import cat.owc.ms.reports.entity.enumeration.UserTypes;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.BlockMapper;
//import cat.owc.ms.reports.mapper.FormMapper;
//import cat.owc.ms.reports.mapper.QuestionMapper;
//import cat.owc.ms.reports.repository.BlockRepository;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.repository.PollRepository;
//import cat.owc.ms.reports.repository.QuestionRepository;
//import cat.owc.ms.reports.repository.ReportTemplateRepository;
//import cat.owc.ms.reports.repository.SubjectTypeRepository;
//import cat.owc.ms.reports.services.interfaces.IFormService;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class FormServiceTest extends AbstractTest {
//	@Autowired
//	private EntityManager entityManager;
//
//	@Autowired
//    private IFormService formService;
//
//	@Autowired
//	private FormRepository formRepository;
//
//	@Autowired
//    private PollRepository pollRepository;
//
//	@Autowired
//	private ReportTemplateRepository reportTemplateRepository;
//
//	@Autowired
//	private SubjectTypeRepository subjectTypeRepository;
//	
//	@Autowired
//	private FormMapper formMapper;
//	
//	@Autowired
//	private QuestionMapper questionMapper;
//	
//	@Autowired
//	private QuestionService questionService;
//	
//	@Autowired
//	private QuestionRepository questionRepository;
//	
//	@Autowired
//	private ReportTemplateService  reportTemplateService;
//		
//	@Autowired
//	private  BlockRepository  blockRepository ;
//	
//	@Autowired
//	private  BlockMapper  blockMapper ;
//
//
//
//	@Test
//    @Transactional
//    public void getPollFormTest() {
//	    Poll poll = pollRepository.findById(1).get();
//	    FormDTO formDTO = formService.findPollFormForUserFilled(poll.getUuid(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", true);
//
//	    assertThat(formDTO).as("Debe tener valor").isNotNull();
//	    assertThat(formDTO.getId()).as("Debe ser el id 5").isEqualTo(5);
//    }
//
//    @Test
//	@Transactional
//	public void addAnswerAnswerFormNotFoundError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.FORM_NOT_FOUND),
//												        	"Expected error code " + IFormServiceErrorCode.FORM_NOT_FOUND);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("ABCD", "508ad6df-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//	@Test
//	@Transactional
//	public void addAnswerUserNotAllowedError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.USER_NOT_ALLOWED_TO_FILL_FORM),
//														        "Expected error code " + IFormServiceErrorCode.USER_NOT_ALLOWED_TO_FILL_FORM);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("5555", "ABCD", answerDTO))
//				.has(errorCode);
//	}
//
//
//	@Test
//	@Transactional
//	public void addAnswerFormEndedError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//
//		Form form = formRepository.findPollFormByIdFilled(5);
//		form.setStatus(FormStatus.ENDED);
//		formRepository.saveAndFlush(form);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.FORM_ENDED),
//												     "Expected error code " + IFormServiceErrorCode.FORM_ENDED);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("5555", "508ad6df-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerFormAnswerError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);		// Deberia ser 5 para coincidir con el del form
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.INCORRECT_ANSWER_FOR_FORM),
//											              "Expected error code " + IFormServiceErrorCode.INCORRECT_ANSWER_FOR_FORM);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("3333", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//	@Test
//	@Transactional
//	public void addAnswerWrongReportFederationError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(7);
//
//		ReportTemplate reportTemplate = reportTemplateRepository.findById(1).get();
//		reportTemplate.setFederation("fcbq");
//		reportTemplateRepository.saveAndFlush(reportTemplate);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.REPORT_NOT_BELONGS_TO_FEDERATION),
//											         "Expected error code " + IFormServiceErrorCode.REPORT_NOT_BELONGS_TO_FEDERATION);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("7777", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerWrongReportStatusError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(7);
//
//		ReportTemplate reportTemplate = reportTemplateRepository.findById(1).get();
//		reportTemplate.setStatus(ReportTemplateStatus.EDITABLE);
//		reportTemplateRepository.saveAndFlush(reportTemplate);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.REPORT_NOT_READY),
//													  "Expected error code " + IFormServiceErrorCode.REPORT_NOT_READY);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("7777", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerWrongPollFederationError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setFederation("fcbq");
//		pollRepository.saveAndFlush(poll);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION),
//											          "Expected error code " + IFormServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerWrongPollStatusError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.POLL_NOT_READY),
//												        "Expected error code " + IFormServiceErrorCode.POLL_NOT_READY);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerPollNotActiveError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		poll.setActivationTime(LocalDateTime.now().plusDays(2));
//		pollRepository.saveAndFlush(poll);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.POLL_NOT_ACTIVE),
//												     "Expected error code " + IFormServiceErrorCode.POLL_NOT_ACTIVE);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//	@Test
//	@Transactional
//	public void addAnswerPollEndedError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		poll.setEndingTime(LocalDateTime.now().minusDays(2));
//		pollRepository.saveAndFlush(poll);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.POLL_ENDED),
//												        "Expected error code " + IFormServiceErrorCode.POLL_ENDED);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerQuestionNotFoundError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//		answerDTO.setQuestionId(15);		// Encuesta 1 tiene id pregunta de 1 a 12
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.QUESTION_NOT_IN_POLL),
//												        "Expected error code " + IFormServiceErrorCode.QUESTION_NOT_IN_POLL);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerNumericOutOfRangeError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//		answerDTO.setQuestionId(4);		// Pregunta 4 es numerica
//		answerDTO.setValue("12");
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.ANSWER_VALUE_OUT_OF_RANGE),
//														"Expected error code " + IFormServiceErrorCode.ANSWER_VALUE_OUT_OF_RANGE);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addAnswerOptionNotOfQuestionError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//		answerDTO.setQuestionId(3);		// Pregunta 3 es multiple. Las respuestas posibles son las 6, 7, 8
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(9));
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.SELECTED_ANSWER_NOT_VALID),
//												        "Expected error code " + IFormServiceErrorCode.SELECTED_ANSWER_NOT_VALID);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//	@Test
//	@Transactional
//	public void addAnswerMoreThanOneOptionError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(1);
//		answerDTO.setQuestionId(1);		// Pregunta 1 es binaria, solo puede tener una opcion
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(1,2));
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.MORE_THAN_ONE_OPTION),
//											         "Expected error code " + IFormServiceErrorCode.MORE_THAN_ONE_OPTION);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void updateAnswerUuidNotMatchError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setUuid("ABCD");
//		answerDTO.setFormId(1);
//		answerDTO.setQuestionId(1);		// Pregunta 1 es binaria, solo puede tener una opcion
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(1));
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.WRONG_ANSWER_UUID),
//														"Expected error code " + IFormServiceErrorCode.WRONG_ANSWER_UUID);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.updateAnswer("1111", "EFGH", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void updateAnswerNotFoundError() {
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setUuid("ABCD");
//		answerDTO.setFormId(1);
//		answerDTO.setQuestionId(1);		// Pregunta 1 es binaria, solo puede tener una opcion
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(1));
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.ANSWER_NOT_FOUND),
//												     "Expected error code " + IFormServiceErrorCode.ANSWER_NOT_FOUND);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.updateAnswer("5555", "ABCD", "508ad6df-8fa1-11ea-b836-02b7c2952a14", answerDTO))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void closeFormAlreadyClosedError() {
//		// Forzar status cerrado y ver que lanza excepción al cerrar
//		Form form = formRepository.findById(5).get();
//		form.setStatus(FormStatus.ENDED);
//		formRepository.saveAndFlush(form);
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.FORM_ALREADY_CLOSED),
//												     "Expected error code " + IFormServiceErrorCode.FORM_ALREADY_CLOSED);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.formCompleted("5555",  "508ad6df-8fa1-11ea-b836-02b7c2952a14"))
//				.has(errorCode);
//	}
//
//
//
//	@Test
//	@Transactional
//	public void closeFormMissingQuestionsError() {
//
//		Poll poll = pollRepository.findById(1).get();
//		poll.setStatus(PollStatus.NOT_EDITABLE);
//		pollRepository.saveAndFlush(poll);
//
//		Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IFormServiceErrorCode.QUESTIONS_NOT_ANSWERED),
//				                                     "Expected error code " + IFormServiceErrorCode.QUESTIONS_NOT_ANSWERED);
//
//		assertThatExceptionOfType(MusicException.class)
//				.isThrownBy(() -> formService.formCompleted("1111",  "508952c9-8fa1-11ea-b836-02b7c2952a14"))
//				.has(errorCode);
//	}
//
//
//	@Test
//	@Transactional
//	public void addBinaryAnswerTest() {
//		Form form = formRepository.findById(1).get();
//		addOptionsAnswer(1, 1, Arrays.asList(1));
//
//		// Refrescar el form para que actualice con los datos salvados (por algun motivo ejecutar de nuevo la query no lo recarga...)
//		entityManager.refresh(form);
//
//		checkExcludingOption(form, 1, Arrays.asList(1));
//
//
//	}
//
//
//	private AnswerDTO addOptionsAnswer(Integer formId, Integer questionId, List<Integer> selectedOptionIds) {
//		// Añadir respuesta al form 1
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(formId);
//		answerDTO.setQuestionId(questionId);
//		answerDTO.setSelectedAnswerOptions(selectedOptionIds);
//
//		return formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//	}
//
//
//	private void checkExcludingOption(Form form, Integer questionId, List<Integer> expectedSelectedOptionIds) {
//		Answer answer = form.getAnswers().stream()
//				.filter(formAnswer -> formAnswer.getQuestion().getId().equals(questionId))
//				.findFirst()
//				.orElse(null);
//
//
//		assertThat(answer).as("Debe tener valor").isNotNull();
//		assertThat(answer.getSelectedAnswerOptions())
//				.as("Debe tener {} elementos", expectedSelectedOptionIds.size())
//				.hasSize(expectedSelectedOptionIds.size());
//
//		List<Integer> selectedOptionIds = answer.getSelectedAnswerOptions().stream()
//											.map(selectedOption -> selectedOption.getSelectedAnswerOptionId().getAnswerOptionId())
//											.collect(Collectors.toList());
//		assertThat(selectedOptionIds)
//				.as("Debe tener las opciones de respuesta {} " + expectedSelectedOptionIds)
//				.containsAll(expectedSelectedOptionIds);
//
//	}
//
//
//	@Test
//	@Transactional
//	public void updateBinaryAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addOptionsAnswer(1, 1, Arrays.asList(1));
//		entityManager.refresh(form);
//
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(2));
//		formService.updateAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//		entityManager.refresh(form);
//		checkExcludingOption(form, 1, Arrays.asList(2));
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addExcludingAnswerTest() {
//		Form form = formRepository.findById(1).get();
//		addOptionsAnswer(1, 2, Arrays.asList(3));
//
//		// Refrescar el form para que actualice con los datos salvados (por algun motivo ejecutar de nuevo la query no lo recarga...)
//		entityManager.refresh(form);
//
//		checkExcludingOption(form, 2, Arrays.asList(3));
//	}
//
//
//
//	@Test
//	@Transactional
//	public void updateExcludingAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addOptionsAnswer(1, 2, Arrays.asList(3));
//		entityManager.refresh(form);
//
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(5));
//		formService.updateAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//		entityManager.refresh(form);
//		checkExcludingOption(form, 2, Arrays.asList(5));
//
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addExcludingColorAnswerTest() {
//		Form form = formRepository.findById(1).get();
//		addOptionsAnswer(1, 5, Arrays.asList(12));
//
//		// Refrescar el form para que actualice con los datos salvados (por algun motivo ejecutar de nuevo la query no lo recarga...)
//		entityManager.refresh(form);
//
//		checkExcludingOption(form, 5, Arrays.asList(12));
//	}
//
//
//
//	@Test
//	@Transactional
//	public void updateExcludingColorAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addOptionsAnswer(1, 5, Arrays.asList(12));
//		entityManager.refresh(form);
//
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(14));
//		formService.updateAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//		entityManager.refresh(form);
//		checkExcludingOption(form, 5, Arrays.asList(14));
//
//	}
//
//
//
//	@Test
//	@Transactional
//	public void addMultipleAnswerTest() {
//		Form form = formRepository.findById(1).get();
//		addOptionsAnswer(1, 3, Arrays.asList(6, 7));
//
//		// Refrescar el form para que actualice con los datos salvados (por algun motivo ejecutar de nuevo la query no lo recarga...)
//		entityManager.refresh(form);
//
//		checkExcludingOption(form, 3, Arrays.asList(6, 7));
//	}
//
//
//
//	@Test
//	@Transactional
//	public void updateMultipleAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addOptionsAnswer(1, 3, Arrays.asList(6, 7));
//		entityManager.refresh(form);
//
//		answerDTO.setSelectedAnswerOptions(Arrays.asList(7, 8));
//		formService.updateAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//		entityManager.refresh(form);
//		checkExcludingOption(form, 3, Arrays.asList(7, 8));
//
//	}
//
//
//
//
//	@Test
//	@Transactional
//	public void addNumericAnswerTest() {
//		Form form = formRepository.findById(1).get();
//		addValueAnswer(1, 4, "3");
//
//		// Refrescar el form para que actualice con los datos salvados (por algun motivo ejecutar de nuevo la query no lo recarga...)
//		entityManager.refresh(form);
//
//		checkValueOption(form, 4, "3");
//
//	}
//
//
//	private AnswerDTO addValueAnswer(Integer formId, Integer questionId, String value) {
//		// Añadir respuesta al form 1
//		AnswerDTO answerDTO = new AnswerDTO();
//		answerDTO.setFormId(formId);
//		answerDTO.setQuestionId(questionId);
//		answerDTO.setValue(value);
//
//		return formService.addAnswer("1111", "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//	}
//
//
//
//	private void checkValueOption(Form form, Integer questionId, String expectedValue) {
//		Answer answer = form.getAnswers().stream()
//				.filter(formAnswer -> formAnswer.getQuestion().getId().equals(questionId))
//				.findFirst()
//				.orElse(null);
//
//		assertThat(answer).as("Debe tener valor").isNotNull();
//		assertThat(answer.getSelectedAnswerOptions())
//				.as("Debe tener 0 opciones seleccionadas")
//				.isNullOrEmpty();
//		assertThat(answer.getValue()).as("Debe tener el valor {}", expectedValue).isEqualTo(expectedValue);
//	}
//
//
//	@Test
//	@Transactional
//	public void updateNumericAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addValueAnswer(1, 4, "3");
//		entityManager.refresh(form);
//
//		answerDTO.setValue("9");
//		formService.updateAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//		entityManager.refresh(form);
//		checkValueOption(form, 4, "9");
//
//	}
//
//
//	@Test
//	@Transactional
//	public void addOpenAnswerTest() {
//		Form form = formRepository.findById(1).get();
//		addValueAnswer(1, 6, "Una bonica parrafada");
//
//		// Refrescar el form para que actualice con los datos salvados (por algun motivo ejecutar de nuevo la query no lo recarga...)
//		entityManager.refresh(form);
//
//		checkValueOption(form, 6, "Una bonica parrafada");
//
//	}
//
//
//	@Test
//	@Transactional
//	public void updateOpenAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addValueAnswer(1, 6, "Una bonica parrafada");
//		entityManager.refresh(form);
//
//		answerDTO.setValue("Una bonica parrafada modificada");
//		formService.updateAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14", answerDTO);
//
//		entityManager.refresh(form);
//		checkValueOption(form, 6, "Una bonica parrafada modificada");
//
//	}
//
//
//
//	@Test
//	@Transactional
//	public void deleteAnswerTest() {
//
//		Form form = formRepository.findById(1).get();
//		AnswerDTO answerDTO = addOptionsAnswer(1, 1, Arrays.asList(1));
//		entityManager.refresh(form);
//
//		formService.deleteAnswer("1111", answerDTO.getUuid(), "508952c9-8fa1-11ea-b836-02b7c2952a14");
//		entityManager.flush();
//		entityManager.refresh(form);
//		assertThat(form.getAnswers()).as("Debe estar vacio").isNullOrEmpty();
//
//	}
//
//
//	@Test
//	@Transactional
//	public void formCompletedTest() {
//
//		// Probar el caso encuesta que permite modificacion
//		Form form = formRepository.findById(5).get();
//		form.setStatus(FormStatus.STARTED);
//		formRepository.saveAndFlush(form);
//
//
//		formService.formCompleted("5555", "508ad6df-8fa1-11ea-b836-02b7c2952a14");
//		entityManager.flush();
//		entityManager.refresh(form);
//
//		assertThat(form.getStatus()).as("Debe quedar cerrado").isEqualTo(FormStatus.ENDED_MODIFIABLE);
//
//
//		// Probar el caso encuesta que no permite modificacion
//		Poll poll = pollRepository.findById(1).get();
//		poll.setChangeAnswerAllowed(false);
//		pollRepository.saveAndFlush(poll);
//
//		formService.formCompleted("5555", "508ad6df-8fa1-11ea-b836-02b7c2952a14");
//		entityManager.flush();
//		entityManager.refresh(form);
//
//		assertThat(form.getStatus()).as("Debe quedar cerrado").isEqualTo(FormStatus.ENDED);
//
//	}
//
//
//	@Test
//	@Transactional
//	public void formForSubjectTest() {
//
//		LocalDate fromDate = LocalDate.of(2020,5, 1);
//		LocalDate toDate = LocalDate.of(2020, 8, 1);
//		// Validar que devuelve todos los elementos
//		List<FormDTO> forms = formService.findAllReportFormsBySubjectUuid("a44e083e-5d77-4ac6-81d2-9c63fc1fce18", SubjectTypeCode.REFEREE, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("Debe tener 4 elementos").hasSize(4);
//
//		// Validar que aplica bien el filtro por fecha
//		toDate = LocalDate.of(2020, 6, 1);
//		forms = formService.findAllReportFormsBySubjectUuid("a44e083e-5d77-4ac6-81d2-9c63fc1fce18", SubjectTypeCode.REFEREE, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("Debe tener 2 elementos").hasSize(2);
//
//
//		// Validar que aplica bien el filtro por subject type
//		toDate = LocalDate.of(2020, 8, 1);
//		ReportTemplate reportTemplate = reportTemplateRepository.getOne(1);
//		reportTemplate.setSubjectType(subjectTypeRepository.findByCode(SubjectTypeCode.CLUB));
//		reportTemplateRepository.saveAndFlush(reportTemplate);
//
//
//		forms = formService.findAllReportFormsBySubjectUuid("a44e083e-5d77-4ac6-81d2-9c63fc1fce18", SubjectTypeCode.REFEREE,fromDate, toDate);
//		FormDTO formDTO7 = forms.stream().filter(formDTO -> formDTO.getId() == 7).findFirst().orElse(null);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("Debe tener 3 elementos").hasSize(3);
//		assertThat(formDTO7).as("Debe ser null").isNull();
//	}
//
//
//	@Test
//	@Transactional
//	public void formForInformerTest() {
//		// Per verificar que no agafa els de les enquestes
//		Poll poll = pollRepository.getOne(3);
//		poll.getInformerType().setCode(InformerTypeCode.REFEREE);
//		poll = pollRepository.saveAndFlush(poll);
//		entityManager.refresh(poll);
//
//		LocalDate fromDate = LocalDate.of(2020,1, 1);
//		LocalDate toDate = LocalDate.of(2020, 4, 1);
//
//		List<FormDTO> forms = formService.findAllReportFormsByInformerUuid("508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.REFEREE, true,fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("Debe tener 1 elemento").hasSize(1);
//
//		// Verificar que aplica bien el filtro por fecha
//		fromDate = LocalDate.of(2019,1, 1);
//		toDate = LocalDate.of(2019, 4, 1);
//		forms = formService.findAllReportFormsByInformerUuid("508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.REFEREE, true,fromDate, toDate);
//		assertThat(forms).as("No debe tener elementos").isEmpty();
//
//		// Verificar que aplica bien el filtro de pendientes
//		fromDate = LocalDate.of(2020,1, 1);
//		toDate = LocalDate.of(2020, 4, 1);
//		Form form = formRepository.getOne(10);
//		form.setStatus(FormStatus.ENDED);
//		formRepository.saveAndFlush(form);
//
//		forms = formService.findAllReportFormsByInformerUuid("508ad6df-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.REFEREE, false, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("Debe tener 1 elementos").hasSize(1);
//		assertThat(forms.get(0).getId()).as("Debe ser el 10").isEqualTo(10);
//
//	}
//
//
//	@Test
//	@Transactional
//	public void getFormsByInformerTypeAndCategoryInTest() {
//		List<Integer> categoryIds = Arrays.asList(1, 2);
//		LocalDate fromDate = LocalDate.of(2020,1, 1);
//		LocalDate toDate = LocalDate.of(2020, 4, 1);
//
//
//		List<FormDTO> forms = formService.findAllReportFormsByInformerTypeAndCategoryIn(InformerTypeCode.REFEREE, categoryIds, true, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("No debe estar vacio").isNotEmpty();
//
//		List<Integer> formIds = forms.stream().map(FormDTO::getId).collect(Collectors.toList());
//		assertThat(formIds).as("Debe contener los forms 7, 8, 9, 10").containsExactly(7, 8, 9, 10);
//
//		Form form7 = formRepository.getOne(7);
//		form7.setStatus(FormStatus.ENDED);
//		formRepository.saveAndFlush(form7);
//
//
//		// Validar que filtra bien per status
//		forms = formService.findAllReportFormsByInformerTypeAndCategoryIn(InformerTypeCode.REFEREE, categoryIds, true, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("No debe estar vacio").isNotEmpty();
//
//		formIds = forms.stream().map(FormDTO::getId).collect(Collectors.toList());
//		assertThat(formIds).as("Debe contener los forms 8, 9, 10").containsExactly(8, 9, 10);
//
//
//		// Validar que valida bien por fecha
//		Form form9 = formRepository.getOne(9);
//		form9.setAssigned(LocalDateTime.now());
//		formRepository.saveAndFlush(form9);
//
//		forms = formService.findAllReportFormsByInformerTypeAndCategoryIn(InformerTypeCode.REFEREE, categoryIds, true, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("No debe estar vacio").isNotEmpty();
//
//		formIds = forms.stream().map(FormDTO::getId).collect(Collectors.toList());
//		assertThat(formIds).as("Debe contener los forms 8, 10").containsExactly(8, 10);
//
//
//		// Validar que filtra por categoria
//		// Asignamos un report template a categoria 2, buscar por categoria 1 y ver que no lo devuelve
//		ReportTemplate reportTemplate = reportTemplateRepository.getOne(4);
//		Form form10 = formRepository.getOne(10);
//		form10.setReportTemplate(reportTemplate);
//		formRepository.saveAndFlush(form10);
//
//		categoryIds = Arrays.asList(1);
//		forms = formService.findAllReportFormsByInformerTypeAndCategoryIn(InformerTypeCode.REFEREE, categoryIds, true, fromDate, toDate);
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("No debe estar vacio").isNotEmpty();
//
//		formIds = forms.stream().map(FormDTO::getId).collect(Collectors.toList());
//		assertThat(formIds).as("Debe contener los forms 8").containsExactly(8);
//
//	}
//
//
//
//	@Test
//	@Transactional
//	public void findAllReportFormsFromListTest() {
//
//		List<String> uuids = Arrays.asList("7777", "8888");
//		Map<String, FormDTO> forms = formService.findAllReportFormsFromList(uuids);
//
//		assertThat(forms).as("No debe ser null").isNotNull();
//		assertThat(forms).as("Debe tener dos elementos").hasSize(2);
//		assertThat(forms.keySet()).as("Debe tener los uuid").containsExactlyInAnyOrder(uuids.get(0), uuids.get(1));
//
//	}
//
//	@Test
//	@Transactional
//	public void testValoration() {
//		String FORM_UUID = "FORM9";
//		String USER_UUID ="508ad6df-8fa1-11ea-b836-02b7c2952a14";
//		
//		FormDTO formDTO=	formService. findReportFormForUserFilled(FORM_UUID,USER_UUID , UserTypes.INFORMER);
//		
//		assertThat(formDTO.getValoration()).isNotNull();	
//	}
//
//	@Test
//	@Transactional
//	public void numercValorationTest() {
//		String FORM_UUID = "FORM9";
//		String USER_UUID ="508ad6df-8fa1-11ea-b836-02b7c2952a14";
//		Form form= formService.findFormByUuidFilled(FORM_UUID);
//
//		 // llamamos a valorar
//		 FormDTO formDTOFind = formService.findReportFormForUserFilled(form.getUuid(),USER_UUID, UserTypes.INFORMER);			
//			assertThat(formDTOFind.getValoration()).as("si es diferente de null ya nos vale ").isNotNull();		
//		
//	}
//
//	@Test
//	@Transactional
//	public void blockNulltest() {
//		String FORM_UUID = "FORM9";
//		String USER_UUID ="508ad6df-8fa1-11ea-b836-02b7c2952a14";
//		Form form= formService.findFormByUuidFilled(FORM_UUID);	
//		FormDTO formDTO = formMapper.reportFormToDto(form); 
//		 
//		
//		
//		 for(BlockDTO blocks : formDTO.getBlocks()) {
//			blocks.setWeight(null);
//			Block block= blockMapper.toEntity(blocks);
//			blockRepository.saveAndFlush(block);
//			
//		 }
//
//		 FormDTO formDTO2 = formService. findReportFormForUserFilled(form.getUuid(), USER_UUID, UserTypes.INFORMER);
//		 assertThat(formDTO2.getGrade()).as("Nota debe ser null").isNull();
//		 
//
//		
//	}
//	
//	@Test
//	@Transactional
//	public void questionNulltest() {
//		String FORM_UUID = "FORM9";
//		String USER_UUID ="508ad6df-8fa1-11ea-b836-02b7c2952a14";
//		Form form= formService.findFormByUuidFilled(FORM_UUID);
//		
//		// ponemos a null el weight de los blocks
//		 FormDTO formDTO = formMapper.reportFormToDto(form); 
//		 for(BlockDTO blocks : formDTO.getBlocks()) {
//			 if(blocks.getWeight() != null) {
//				 for(QuestionDTO questions : blocks.getQuestions()) {
//					 questions.setWeight(null);
//					 questionRepository.saveAndFlush(questionMapper.toEntity(questions));
//				 }
//				 
//			 }
//		 }
//
//		 FormDTO formDTO2 = formService.findReportFormForUserFilled(form.getUuid(), USER_UUID, UserTypes.INFORMER);
//		 assertThat(formDTO2.getGrade()).as("No debe ser null").isNotNull();		 
//	}
//	
//	@
//	Transactional
//	@Test
//	public void validateGradeReportTemplateNotHasPunctuation() {	
//		String FORM_UUID = "FORM9";
//		String REPORT_TEMPLATE_UUID="EEEE";
//		String USER_UUID ="508ad6df-8fa1-11ea-b836-02b7c2952a14";
//		Form form= formService.findFormByUuidFilled(FORM_UUID);
//		
//		ReportTemplate reportTemplate =reportTemplateRepository.findOneByUuidFilled(REPORT_TEMPLATE_UUID);
//		reportTemplate.setHasPunctuation(false);
//		reportTemplateRepository.saveAndFlush(reportTemplate);
//		
//		FormDTO formDTO2 = formService. findReportFormForUserFilled(form.getUuid(), USER_UUID, UserTypes.INFORMER);
//		 assertThat(formDTO2.getValoration()).as("Nota debe ser null").isNull();
//		
//	}
//	@Transactional
//	@Test
//	public void validationGradeFormNotEditable() {
//		String FORM_UUID = "FORM9";
//		
//		String USER_UUID ="508ad6df-8fa1-11ea-b836-02b7c2952a14";
//		
//	Form form= formService.findFormByUuidFilled(FORM_UUID);
//		form.setStatus(FormStatus.ENDED);
//		formRepository.saveAndFlush(form);
//		
//		FormDTO formDTO2 = formService. findReportFormForUserFilled(form.getUuid(), USER_UUID, UserTypes.INFORMER);
//		assertThat(formDTO2.getValoration()).as("Nota debe ser null").isNull();
//		
//		
//	}
//
//}
