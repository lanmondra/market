//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.Question;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.QuestionMapper;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.repository.QuestionRepository;
//import cat.owc.ms.reports.services.interfaces.IQuestionService;
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class QuestionServiceTest extends AbstractTest {
//
//    private static final Integer BLOCK_NO_ANSWERS_ID = 4;
//    private static final Integer TEST_QUESTION_ID = 19;
//    private static final String TEST_QUESTION_UUID = "SSS";
//    private static final Integer TEST_FORM_ID = 7;
//
//	@Autowired
//    private IQuestionService questionService;
//
//	@Autowired
//    private QuestionRepository questionRepository;
//
//	@Autowired
//    private QuestionMapper questionMapper;
//
//	@Autowired
//    private FormRepository formRepository;
//
//
//
//    @Test
//    @Transactional
//    public void addQuestionOkTest() {
//        setFormPending(TEST_FORM_ID);
//
//        QuestionDTO questionDTO = createQuestionDTO(BLOCK_NO_ANSWERS_ID);
//        long numQuestionsBefore = questionRepository.count();
//
//        questionDTO = questionService.add(questionDTO);
//
//        long numQuestionsAfter = questionRepository.count();
//
//        assertThat(numQuestionsAfter).as("Debe incrementarse en uno el numero de preguntas").isEqualTo(numQuestionsBefore + 1);
//        assertThat(questionDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(questionDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(questionDTO.getValue()).as("El nombre debe ser Texto de la pregunta").isEqualTo("Texto de la pregunta");
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addQuestionNoAnswerOkTest() {
//        // Validar que si no tiene respuesta el tipo de respuesta no es obligatorio
//        setFormPending(TEST_FORM_ID);
//
//        QuestionDTO questionDTO = createQuestionDTO(BLOCK_NO_ANSWERS_ID);
//        questionDTO.setHasAnswer(false);
//        questionDTO.setAnswerTypeId(null);
//        long numQuestionsBefore = questionRepository.count();
//
//        questionDTO = questionService.add(questionDTO);
//
//        long numQuestionsAfter = questionRepository.count();
//
//        assertThat(numQuestionsAfter).as("Debe incrementarse en uno el numero de preguntas").isEqualTo(numQuestionsBefore + 1);
//        assertThat(questionDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(questionDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(questionDTO.getValue()).as("El nombre debe ser Texto de la pregunta").isEqualTo("Texto de la pregunta");
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addQuestionNokTest() {
//        doAddUpdateValidations(false);
//    }
//
//
//    private void doAddUpdateValidations(boolean update) {
//        Question question = questionRepository.getOne(TEST_QUESTION_ID);
//        QuestionDTO questionDTO = questionMapper.toDto(question);
//
//        log.info("Validar que error si bloque tiene respuestas");
//        questionDTO.setHasAnswer(!questionDTO.getHasAnswer());
//        checkAddUpdateQuestion(questionDTO, "Bloque tiene respuestas", IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE , update);
//
//        // Setear estado del form a pendiente para que deje pasar el primer filtro
//        setFormPending(TEST_FORM_ID);
//
//        log.info("Validar error si falta value");
//        questionDTO.setValue(null);
//        checkAddUpdateQuestion(questionDTO, "Falta informar value", IQuestionServiceErrorCode.MISSING_VALUE , update);
//
//        log.info("Validar error si falta has answer");
//        questionDTO.setValue("AAA");
//        questionDTO.setHasAnswer(null);
//        checkAddUpdateQuestion(questionDTO, "Falta informar has answer", IQuestionServiceErrorCode.MISSING_HAS_ANSWER , update);
//
//        log.info("Validar error si falta mandatory");
//        questionDTO.setHasAnswer(true);
//        questionDTO.setMandatory(null);
//        checkAddUpdateQuestion(questionDTO, "Falta informar mandatory", IQuestionServiceErrorCode.MISSING_MANDATORY , update);
//
//        log.info("Validar error si falta order num");
//        questionDTO.setMandatory(true);
//        questionDTO.setOrderNum(null);
//        checkAddUpdateQuestion(questionDTO, "Falta informar order num", IQuestionServiceErrorCode.MISSING_ORDER_NUM , update);
//
//        log.info("Validar error si falta answerType");
//        questionDTO.setOrderNum(1);
//        questionDTO.setAnswerTypeId(null);
//        checkAddUpdateQuestion(questionDTO, "Falta informar answerType", IQuestionServiceErrorCode.MISSING_ANSWER_TYPE , update);
//
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void updateQuestionOkTest() {
//        setFormPending(TEST_FORM_ID);
//
//        Question question = questionRepository.getOne(TEST_QUESTION_ID);
//        QuestionDTO questionDTO = questionMapper.toDto(question);
//
//        questionDTO.setValue("new value");
//        questionDTO.setOrderNum(99);
//
//        QuestionDTO updatedQuestionDTO = questionService.update(questionDTO);
//
//        assertThat(updatedQuestionDTO.getValue()).as("El value debe haber cambiado").isEqualTo("new value");
//        assertThat(updatedQuestionDTO.getOrderNum()).as("Order num debe haber cambiado" ).isEqualTo(99);
//        assertThat(updatedQuestionDTO.getUuid()).as("Uuid no debe haber cambiaod").isEqualTo(questionDTO.getUuid());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateQuestionNokTest() {
//
//        doAddUpdateValidations(true);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateQuestionNotFoundTest() {
//
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IQuestionServiceErrorCode.QUESTION_NOT_FOUND),
//                " Expected error code " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    QuestionDTO questionDTO = new QuestionDTO();
//                    questionDTO.setId(9999);
//                    questionService.update(questionDTO);
//                })
//                .has(errorCode);    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteQuestionOkTest() {
//        setFormPending(TEST_FORM_ID);
//
//        questionService.delete(TEST_QUESTION_UUID, true);
//        Question question = questionRepository.getOne(TEST_QUESTION_ID);
//
//        assertThat(question.getDeleted()).as("Deleted debe estar informado").isNotNull();
//
//        long optionsNotDeleted = question.getAnswerOptions().stream()
//                .filter(answerOption -> answerOption.getDeleted() == null)
//                .count();
//
//        assertThat(optionsNotDeleted).as("Debe ser zero").isEqualTo(0);
//    }
//
//
//    @Test
//    @Transactional
//    public void deleteQuestionNotFoundTest() {
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IQuestionServiceErrorCode.QUESTION_NOT_FOUND),
//                 " Expected error code " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    questionService.delete("LALALALA", true);
//                })
//                .has(errorCode);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteQuestionInUseFoundTest() {
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE),
//                " Expected error code " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    questionService.delete(TEST_QUESTION_UUID, true);
//                })
//                .has(errorCode);
//    }
//
//
//
//    public static QuestionDTO createQuestionDTO(Integer blockId) {
//        QuestionDTO questionDTO = new QuestionDTO();
//        questionDTO.setValue("Texto de la pregunta");
//        questionDTO.setHasAnswer(true);
//        questionDTO.setMandatory(true);
//        questionDTO.setOrderNum(1);
//        questionDTO.setBlockId(blockId);
//        questionDTO.setAnswerTypeId(1);
//
//        return questionDTO;
//
//    }
//
//
//    private void checkAddUpdateQuestion(QuestionDTO questionDTO, String description, String expectedErrorCode, Boolean update) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    if (update) {
//                        questionService.update(questionDTO);
//                    }
//                    else {
//                        questionService.add(questionDTO);
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
//    //--------- nuevos test -----------
//    @Test
//    @Transactional
//    public void editQuestionWithAnswer(){
//    Integer QUESTION_ID =23;
//
//    QuestionDTO questionDTO = questionService.findQuestionDTO(QUESTION_ID);
//    questionDTO.setValue("refereeNumber");
//   
//    
//    questionService.update(questionDTO);
//     	
//    	
//    }
//    
//    @Test
//    @Transactional
//    public void editQuestionWithAnswerDateNotCanChange(){
//    Integer QUESTION_ID =23;
//    QuestionDTO questionDTO = questionService.findQuestionDTO(QUESTION_ID);
//    questionDTO.setBlockId(1);
//    
//    Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//    reportsException.getCode().equals(IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE),
//            " Expected error code " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE);
//
//   assertThatExceptionOfType(MusicException.class)
//           .isThrownBy(() -> {
//        	   questionService.update(questionDTO);
//           })
//           .has(errorCode);
//  	
//    }
//
//
//}
