//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.AnswerOptionDTO;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.entity.AnswerOption;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.Question;
//import cat.owc.ms.reports.entity.enumeration.AnswerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.AnswerOptionMapper;
//import cat.owc.ms.reports.repository.AnswerOptionRepository;
//import cat.owc.ms.reports.repository.AnswerTypeRepository;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.repository.QuestionRepository;
//import cat.owc.ms.reports.services.interfaces.IAnswerOptionService;
//import cat.owc.ms.reports.services.interfaces.IQuestionService;
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class AnswerOptionServiceTest extends AbstractTest {
//
//    private static final Integer BLOCK_NO_ANSWERS_ID = 4;
//    private static final Integer TEST_QUESTION_ID = 19;
//    private static final String TEST_QUESTION_UUID = "SSS";
//    private static final Integer TEST_OPTION_ID = 43;
//    private static final String TEST_OPTION_UUID = "1YYY";
//    private static final Integer TEST_FORM_ID = 7;
//
//    @Autowired
//    private EntityManager entityManager;
//
//	@Autowired
//    private IAnswerOptionService answerOptionService;
//
//	@Autowired
//    private AnswerOptionRepository answerOptionRepository;
//
//	@Autowired
//    private AnswerOptionMapper answerOptionMapper;
//
//	@Autowired
//    private FormRepository formRepository;
//
//    @Autowired
//    private IQuestionService questionService;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private AnswerTypeRepository answerTypeRepository;
//
//
//
//
//    @Test
//    @Transactional
//    public void addAnswerOptionOkTest() {
//
//        QuestionDTO questionDTO = createQuestionForTest(BLOCK_NO_ANSWERS_ID);
//        AnswerOptionDTO answerOptionDTO = createAnswerOptionDTO(questionDTO.getId());
//
//        long numOptionsBefore = answerOptionRepository.count();
//
//        answerOptionDTO = answerOptionService.add(answerOptionDTO);
//
//        long numOptionsAfter = answerOptionRepository.count();
//
//        assertThat(numOptionsAfter).as("Debe incrementarse en uno el numero de opciones").isEqualTo(numOptionsBefore + 1);
//        assertThat(answerOptionDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(answerOptionDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(answerOptionDTO.getValue()).as("El nombre debe ser Hola").isEqualTo("Hola");
//    }
//
//    @Test
//    @Transactional
//    public void addOptionNokTest() {
//        doAddUpdateValidations(false);
//    }
//
//
//    @Test
//    @Transactional
//    public  void addOptionNumOptionsValidations() {
//        AnswerOption answerOption = answerOptionRepository.getOne(TEST_OPTION_ID);
//        AnswerOptionDTO answerOptionDTO = answerOptionMapper.toDto(answerOption);
//        Question question = questionRepository.findByUuid(TEST_QUESTION_UUID);
//
//        log.info("Validar que si pregunta es binaria, no deja añadir mas opciones");
//        checkAddUpdate(answerOptionDTO, "Binaria solo permite dos opciones", IAnswerOptionServiceErrorCode.QUESTION_ONLY_ACCEPTS_2_ANSWER_OPTIONS , false);
//
//        log.info("Validar que si es numerica solo admite dos opciones");
//        question.setAnswerType(answerTypeRepository.findByCode(AnswerTypeCode.NUMERIC));
//        questionRepository.saveAndFlush(question);
//        checkAddUpdate(answerOptionDTO, "Numerica solo permite dos opciones", IAnswerOptionServiceErrorCode.QUESTION_ONLY_ACCEPTS_2_ANSWER_OPTIONS , false);
//
//        log.info("Validar que si es open no admite opciones");
//        question.setAnswerType(answerTypeRepository.findByCode(AnswerTypeCode.OPEN));
//        questionRepository.saveAndFlush(question);
//        checkAddUpdate(answerOptionDTO, "Open no permite opciones", IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS , false);
//
//
//        log.info("Validar que pregunta no admite respuesta da error");
//        question.setAnswerType(answerTypeRepository.findByCode(AnswerTypeCode.BINARY));
//        question.setHasAnswer(false);
//        questionRepository.saveAndFlush(question);
//        checkAddUpdate(answerOptionDTO, "Has answer false no permite opciones", IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS , false);
//
//        log.info("Validar que si es link no admite opciones");
//        question.setAnswerType(answerTypeRepository.findByCode(AnswerTypeCode.LINK));
//        questionRepository.saveAndFlush(question);
//        checkAddUpdate(answerOptionDTO, "Open no permite opciones", IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS , false);
//    }
//    
//   
//
//
//    private void doAddUpdateValidations(boolean update) {
//        AnswerOption answerOption = answerOptionRepository.getOne(TEST_OPTION_ID);
//        AnswerOptionDTO answerOptionDTO = answerOptionMapper.toDto(answerOption);
//
//        if (!update) {
//            // Test de add, borramos la respuesta para que pase la validación del número de respuestas ya existentes
//            answerOptionRepository.deleteById(TEST_OPTION_ID);
//        }
//
//
//        // Setear estado del form a pendiente para que deje pasar el primer filtro
//        setFormPending(TEST_FORM_ID);
//
//        log.info("Validar error si falta value");
//        answerOptionDTO.setValue(null);
//        checkAddUpdate(answerOptionDTO, "Falta informar value", IAnswerOptionServiceErrorCode.MISSING_VALUE , update);
//
//        log.info("Validar error si falta order num");
//        answerOptionDTO.setValue("hola");
//        answerOptionDTO.setOrderNum(null);
//        checkAddUpdate(answerOptionDTO, "Falta informar order num", IAnswerOptionServiceErrorCode.MISSING_ORDER_NUM , update);
//
//        // Fijar tipo de pregunta a numeric para validar que el valor debe ser numerico en este caso
//        Question question = questionRepository.findByUuid(TEST_QUESTION_UUID);
//        question.setAnswerType(answerTypeRepository.findByCode(AnswerTypeCode.NUMERIC));
//        questionRepository.saveAndFlush(question);
//
//        answerOptionDTO.setValue("hola");
//        answerOptionDTO.setOrderNum(1);
//        checkAddUpdate(answerOptionDTO, "El valor debe ser numerico", IAnswerOptionServiceErrorCode.VALUE_MUST_BE_NUMERIC , update);
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void updateOptionOkTest() {
//        setFormPending(TEST_FORM_ID);
//
//        AnswerOption answerOption = answerOptionRepository.getOne(TEST_OPTION_ID);
//        AnswerOptionDTO answerOptionDTO = answerOptionMapper.toDto(answerOption);
//
//        answerOptionDTO.setValue("new value");
//        answerOptionDTO.setOrderNum(99);
//
//        AnswerOptionDTO updatedAnswerOptionDTO = answerOptionService.update(answerOptionDTO);
//
//        assertThat(updatedAnswerOptionDTO.getValue()).as("El value debe haber cambiado").isEqualTo("new value");
//        assertThat(updatedAnswerOptionDTO.getOrderNum()).as("Order num debe haber cambiado" ).isEqualTo(99);
//        assertThat(updatedAnswerOptionDTO.getUuid()).as("Uuid no debe haber cambiaod").isEqualTo(answerOptionDTO.getUuid());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateOptionNokTest() {
//
//        doAddUpdateValidations(true);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateOptionNotFoundTest() {
//
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IAnswerOptionServiceErrorCode.ANSWER_OPTION_NOT_FOUND),
//                " Expected error code " + IAnswerOptionServiceErrorCode.ANSWER_OPTION_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    AnswerOptionDTO answerOptionDTO = new AnswerOptionDTO();
//                    answerOptionDTO.setId(9999);
//                    answerOptionService.update(answerOptionDTO);
//                })
//                .has(errorCode);    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteOptionOkTest() {
//        setFormPending(TEST_FORM_ID);
//
//        answerOptionService.delete(TEST_OPTION_UUID, true);
//        AnswerOption answerOption = answerOptionRepository.getOne(TEST_OPTION_ID);
//
//        assertThat(answerOption.getDeleted()).as("Deleted debe estar informado").isNotNull();
//    }
//
//
//    @Test
//    @Transactional
//    public void deleteOptionNotFoundTest() {
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals( IAnswerOptionServiceErrorCode.ANSWER_OPTION_NOT_FOUND),
//                 " Expected error code " +  IAnswerOptionServiceErrorCode.ANSWER_OPTION_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    answerOptionService.delete("LALALALA", true);
//                })
//                .has(errorCode);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteOptionInUseFoundTest() {
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE),
//                " Expected error code " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    answerOptionService.delete(TEST_OPTION_UUID, true);
//                })
//                .has(errorCode);
//    }
//
//
//
//    private QuestionDTO createQuestionForTest(Integer blockId) {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(BLOCK_NO_ANSWERS_ID);
//        questionDTO = questionService.add(questionDTO);
//
//        // Apaño para que recarge bien la entity con todos los campos
//        Question question = questionRepository.getOne(questionDTO.getId());
//        entityManager.refresh(question);
//        return questionDTO;
//
//    }
//
//    public static AnswerOptionDTO createAnswerOptionDTO(Integer questionId) {
//
//        AnswerOptionDTO answerOptionDTO = new AnswerOptionDTO();
//        answerOptionDTO.setValue("Hola");
//        answerOptionDTO.setQuestionId(questionId);
//        answerOptionDTO.setOrderNum(1);
//
//        return answerOptionDTO;
//
//    }
//
//
//    private void checkAddUpdate(AnswerOptionDTO answerOptionDTO, String description, String expectedErrorCode, Boolean update) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    if (update) {
//                        answerOptionService.update(answerOptionDTO);
//                    }
//                    else {
//                        answerOptionService.add(answerOptionDTO);
//                    }
//                })
//                .has(errorCode);
//    }
//
//
//    public void setFormPending(Integer formId) {
//        // Forzar el estado del form asociado al report para passar el test
//        Form form = formRepository.findById(formId).get();
//        form.setStatus(FormStatus.PENDING);
//        formRepository.saveAndFlush(form);
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void addAnswerFromTemplateTest() {
//        QuestionDTO questionDTO = createQuestionForTest(BLOCK_NO_ANSWERS_ID);
//        answerOptionService.addAnswerFromTemplate(questionDTO.getUuid(), "1");
//
//        Question question = questionRepository.findQuestionFilled(questionDTO.getId());
//        entityManager.refresh(question);
//
//        assertThat(question.getAnswerOptions()).as("No debe ser null").isNotNull();
//        assertThat(question.getAnswerOptions()).as("Debe tener 2 elementos").hasSize(2);
//    }
//
//
//    @Test
//    @Transactional
//    public void addAnswerFromTemplateNofIfBlockInUseTest() {
//        Question question = questionRepository.findByUuid(TEST_QUESTION_UUID);
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE),
//                " Expected error code " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    answerOptionService.addAnswerFromTemplate(question.getUuid(), "1");
//                })
//                .has(errorCode);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void addAnswerFromTemplateNofIfQuestionHasAnswersTest() {
//        setFormPending(TEST_FORM_ID);
//        Question question = questionRepository.findByUuid(TEST_QUESTION_UUID);
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IAnswerOptionServiceErrorCode.NO_TEMPLATE_IF_ANSWER_OPTIONS),
//                " Expected error code " + IAnswerOptionServiceErrorCode.NO_TEMPLATE_IF_ANSWER_OPTIONS);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    answerOptionService.addAnswerFromTemplate(question.getUuid(), "1");
//                })
//                .has(errorCode);
//    }
//
//    @Test
//    @Transactional
//    public void editAnswerOptionWithAnswertest() {
//    	AnswerOption answerOption = answerOptionRepository.getOne(TEST_OPTION_ID);
//    	answerOption.setColor("red");
//    	answerOption.setDescription("esta es nueva descripcion de prueba ");
//    	AnswerOptionDTO answerOptionDTO = answerOptionMapper.toDto(answerOption);
//    	AnswerOptionDTO answerOptionDTOUpdated= answerOptionService.update(answerOptionDTO);
//    	
//    	assertThat(answerOptionDTOUpdated).as("si no es null es por que devuelve algo").isNotNull();
//    }
//
//
//
//}
