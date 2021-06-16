//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.dto.ReportTemplateDTO;
//import cat.owc.ms.reports.entity.*;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.entity.enumeration.ReportTemplateStatus;
//import cat.owc.ms.reports.entity.enumeration.UserTypes;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.BlockMapper;
//import cat.owc.ms.reports.mapper.QuestionMapper;
//import cat.owc.ms.reports.mapper.ReportTemplateMapper;
//import cat.owc.ms.reports.repository.*;
//import cat.owc.ms.reports.services.interfaces.IReportTemplateService;
//import cat.owc.ms.reports.utils.I18n;
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.assertj.core.api.Fail.fail;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class ReportTemplateServiceTest extends AbstractTest {
//
//    @Autowired
//    private IReportTemplateService reportTemplateService;
//
//    @Autowired
//    private ReportTemplateRepository reportTemplateRepository;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private I18n i18n;
//
//    @Autowired
//    private AnswerTypeRepository answerTypeRepository;
//
//    @Autowired
//    private AnswerOptionRepository answerOptionRepository;
//
//    @Autowired
//    private BlockRepository blockRepository;
//
//    @Autowired
//    private FormService formService;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Autowired
//    private FormRepository formRepository;
//    
//    @Autowired
//    private  QuestionService  questionService ;
//    
//    @Autowired
//    private QuestionMapper questionMapper;
//    
//    @Autowired
//    private ReportTemplateMapper reportTemplateMapper ;
//    
//    @Autowired
//    private BlockService blockService;
//    
//    @Autowired
//    private BlockMapper blockMapper ;
//
//    @Test
//    @Transactional
//    public void getReportTemplateByUuidDTOTest() {
//        ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//
//        assertThat(reportTemplateDTO).as("Debe tener valor").isNotNull();
//        assertThat(reportTemplateDTO.getId()).as("Debe ser el id 1").isEqualTo(1);
//    }
//
//
//    @Test
//    @Transactional
//    public void getReportTemplateByUuidDTONotFoundTest() {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_NOT_FOUND),
//                "Expected error code " + IReportTemplateServiceErrorCode.RT_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.findReportTemplateByUuidDTO("ABCD"))
//                .has(errorCode);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void getReportTemplateByUuidFilledDTOTest() {
//        ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidFilledDTO("AAAA");
//
//        assertThat(reportTemplateDTO).as("Debe tener valor").isNotNull();
//        assertThat(reportTemplateDTO.getId()).as("Debe ser el id 1").isEqualTo(1);
//        assertThat(reportTemplateDTO.getBlocks()).as("Debe tener bloques").isNotNull();
//    }
//
//
//    @Test
//    @Transactional
//    public void getReportTemplateByUuidFilledDTONotFoundTest() {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_NOT_FOUND),
//                "Expected error code " + IReportTemplateServiceErrorCode.RT_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.findReportTemplateByUuidFilledDTO("ABCD"))
//                .has(errorCode);
//    }
//
//
//    @Test
//    @Transactional
//    public void getReportTemplateByUuidFilledDTONotFedeTest() {
//        ReportTemplate reportTemplate = reportTemplateRepository.getOne(1);
//        reportTemplate.setFederation("aaaa");
//        reportTemplateRepository.saveAndFlush(reportTemplate);
//
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_NOT_BELONGS_TO_FEDERATION),
//                "Expected error code " + IReportTemplateServiceErrorCode.RT_NOT_BELONGS_TO_FEDERATION);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.findReportTemplateByUuidFilledDTO("AAAA"))
//                .has(errorCode);
//    }
//
//
//    @Test
//    @Transactional
//    public void addReportTemplateOkTest() {
//        ReportTemplateDTO reportTemplateDTO = createReportTemplateDTO(true, true, Portal.REFEREE, true, true, 1, 1, 1);
//
//        long numReportssBefore = reportTemplateRepository.count();
//
//        reportTemplateDTO = reportTemplateService.add(reportTemplateDTO);
//
//        long numReportsAfter = reportTemplateRepository.count();
//        assertThat(numReportsAfter).as("Debe incrementarse en uno el numero de plantillas").isEqualTo(numReportssBefore + 1);
//        assertThat(reportTemplateDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(reportTemplateDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(reportTemplateDTO.getStatus()).as("Estatus debe ser editable").isEqualTo(ReportTemplateStatus.EDITABLE);
//        assertThat(reportTemplateDTO.getFederation()).as("Federacion debe ser test").isEqualTo("test");
//    }
//
//
//    @Test
//    @Transactional
//    public void addReportTemplateNokTest() {
//        addUpdateCommonValidations(false);
//    }
//
//
//    @Test
//    @Transactional
//    public void addReportTemplateNoMatchReportTest() {
//        ReportTemplateDTO reportTemplateDTO = createReportTemplateDTO(true, true, Portal.REFEREE, true, false, 1, 1, 1);
//        reportTemplateDTO.setMatchReportTypeId(null);
//
//        try {
//            reportTemplateService.add(reportTemplateDTO);
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion ", e);
//        }
//    }
//
//
//    @Test
//    @Transactional
//    public void updateReportTemplateOkTest() {
//        String testName = "Testing update";
//
//        ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("BBBB");
//        reportTemplateDTO.setName(testName);
//        reportTemplateDTO.setIsVisible(false);
//        reportTemplateDTO.setAllowNotDesignated(false);
//
//        reportTemplateService.update(reportTemplateDTO);
//
//        // Asegurar que salva
//        reportTemplateRepository.flush();
//
//        // Recuperar i validar
//        ReportTemplate reportTemplate = reportTemplateRepository.getOne(2);
//
//        assertThat(reportTemplate.getName()).as("El nombre debe ser " + testName).isEqualTo(testName);
//        assertThat(reportTemplate.getIsVisible()).as("IsVisible debe ser false").isFalse();
//        assertThat(reportTemplate.getAllowNotDesignated()).as("Allow not designated debe ser false").isFalse();
//        assertThat(reportTemplate.getLastActionUser()).as("Debe tener el valor por defecto").isEqualTo("no se pudo recuperar");
//    }
//
//
//    @Test
//    @Transactional
//    public void updateReportTemplateNokTest() {
//
//        addUpdateCommonValidations(true);
//    }
//
//
//    @Test
//    @Transactional
//    public void updateReportTemplateNoMatchReportTest() {
//        ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("BBBB");
//        reportTemplateDTO.setIsMatchReport(false);
//        reportTemplateDTO.setMatchReportTypeId(null);
//
//        try {
//            reportTemplateService.update(reportTemplateDTO);
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion ", e);
//        }
//    }
//
//
//    @Test
//    @Transactional
//    public void updateReportTemplateWithAnswersTest() {
//        ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//        reportTemplateDTO.setName(reportTemplateDTO.getName() + "MODIF");
//        checkAddUpdateReportTemplate(reportTemplateDTO, "Para actualizar no debe tener respuestas", IReportTemplateServiceErrorCode.RT_HAS_ANSWERS, true);
//    }
//
//
//    private void addUpdateCommonValidations(Boolean update) {
//        ReportTemplateDTO reportTemplateDTO = createReportTemplateDTO(true, true, Portal.REFEREE, true, true, 1, 1, 1);
//
//
//        log.info("Test nombre obilgatorio");
//        reportTemplateDTO.setName(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "Name es obligatorio", IReportTemplateServiceErrorCode.MISSING_NAME, update);
//
//        reportTemplateDTO.setName("Test name");
//        if (update) {
//            log.info("Test is visible obilgatorio");
//            reportTemplateDTO.setIsVisible(null);
//            checkAddUpdateReportTemplate(reportTemplateDTO, "isVisible es obligatorio", IReportTemplateServiceErrorCode.MISSING_IS_VISIBLE, update);
//        }
//
//        log.info("Test showReferee obilgatorio");
//        reportTemplateDTO.setIsVisible(true);
//        reportTemplateDTO.setShowReferee(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "showReferee es obligatorio", IReportTemplateServiceErrorCode.MISSING_SHOW_REFEREE, update);
//
//        log.info("Test portal obilgatorio");
//        reportTemplateDTO.setShowReferee(true);
//        reportTemplateDTO.setPortal(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "Portal es obligatorio", IReportTemplateServiceErrorCode.MISSING_PORTAL, update);
//
//        log.info("Test allowNotDesignated obilgatorio");
//        reportTemplateDTO.setPortal(Portal.REFEREE);
//        reportTemplateDTO.setAllowNotDesignated(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "allowNotDesignated es obligatorio", IReportTemplateServiceErrorCode.MISSING_ALLOW_NOT_DESIGNATED, update);
//
//        log.info("Test isMatchReport obilgatorio");
//        reportTemplateDTO.setAllowNotDesignated(true);
//        reportTemplateDTO.setIsMatchReport(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "isMatchRepot es obligatorio", IReportTemplateServiceErrorCode.MISSING_IS_MATCH_REPORT, update);
//
//        log.info("Test matchReportType obilgatorio");
//        reportTemplateDTO.setIsMatchReport(true);
//        reportTemplateDTO.setMatchReportTypeId(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "matchReportType es obligatorio", IReportTemplateServiceErrorCode.MISSING_MATCH_REPORT_TYPE, update);
//
//        log.info("Test informer type obilgatorio");
//        reportTemplateDTO.setMatchReportTypeId(1);
//        reportTemplateDTO.setInformerTypeId(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "Informer type es obligatorio", IReportTemplateServiceErrorCode.MISSING_INFORMER_TYPE, update);
//
//        log.info("Test subjectType obilgatorio");
//        reportTemplateDTO.setInformerTypeId(1);
//        reportTemplateDTO.setSubjectTypeId(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "Subject type es obligatorio", IReportTemplateServiceErrorCode.MISSING_SUBJECT_TYPE, update);
//
//        log.info("Test reportTypeCategory obilgatorio");
//        reportTemplateDTO.setSubjectTypeId(1);
//        reportTemplateDTO.setReportCategoryId(null);
//        checkAddUpdateReportTemplate(reportTemplateDTO, "Category es obligatorio", IReportTemplateServiceErrorCode.MISSING_REPORT_CATEGORY, update);
//
//
//        log.info("Test nombre duplicado");
//        reportTemplateDTO.setReportCategoryId(1);
//        reportTemplateDTO.setName("PLANTILLA INFORME 1");
//        checkAddUpdateReportTemplate(reportTemplateDTO, "El nombre no puede estar duplicado", IReportTemplateServiceErrorCode.RT_WITH_NAME_EXISTS, update);
//
//    }
//
//
//    private void checkAddUpdateReportTemplate(ReportTemplateDTO reportTemplateDTO, String description, String expectedErrorCode, Boolean update) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    if (update) {
//                        reportTemplateService.update(reportTemplateDTO);
//                    } else {
//                        reportTemplateService.add(reportTemplateDTO);
//                    }
//                })
//                .has(errorCode);
//    }
//
//
//    public static ReportTemplateDTO createReportTemplateDTO(boolean isVisible, boolean showReferee, Portal portal, boolean allowNotDesignated, boolean isMatchReport, Integer informerTypeId, Integer subjectTypeId, Integer categoryId) {
//        ReportTemplateDTO reportTemplateDTO = new ReportTemplateDTO();
//        reportTemplateDTO.setName("RT TEST NAME");
//        reportTemplateDTO.setIsVisible(isVisible);
//        reportTemplateDTO.setShowReferee(showReferee);
//        reportTemplateDTO.setPortal(portal);
//        reportTemplateDTO.setAllowNotDesignated(allowNotDesignated);
//        reportTemplateDTO.setIsMatchReport(isMatchReport);
//        reportTemplateDTO.setInformerTypeId(informerTypeId);
//        reportTemplateDTO.setSubjectTypeId(subjectTypeId);
//        reportTemplateDTO.setReportCategoryId(categoryId);
//        reportTemplateDTO.setHasPunctuation(false);
//
//        if (isMatchReport) {
//            reportTemplateDTO.setMatchReportTypeId(1);
//        }
//
//        return reportTemplateDTO;
//    }
//
//
//    @Test
//    @Transactional
//    public void deleteTest() {
//        try {
//            reportTemplateService.delete("AAAA");
//
//            ReportTemplate reportTemplate = reportTemplateRepository.getOne(1);
//
//            assertThat(reportTemplate.getDeleted()).as("Debe estar informado").isNotNull();
//
//            // Validar que se han borrado todos los bloques
//            Block blockNotDeleted = reportTemplate.getBlocks().stream()
//                    .filter(block -> block.getDeleted() == null)
//                    .findFirst()
//                    .orElse(null);
//
//            assertThat(blockNotDeleted).as("No debe quedar ningun bloque sin borrar").isNull();
//
//
//            // Validar que se han borrado todas las preguntas
//            Set<Question> questions = new HashSet<>();
//            reportTemplate.getBlocks().forEach(block -> questions.addAll(block.getQuestions()));
//
//            Question questionNotDeleted = questions.stream()
//                    .filter(question -> question.getDeleted() == null)
//                    .findFirst()
//                    .orElse(null);
//
//            assertThat(questionNotDeleted).as("No debe quedar ningna pregunta sin borrar").isNull();
//
//
//            // Validar que se han borrado todas las opciones de respuesta
//            Set<AnswerOption> answerOptions = new HashSet<>();
//            questions.forEach(question -> answerOptions.addAll(question.getAnswerOptions()));
//
//            AnswerOption answerOptionNotDeleted = answerOptions.stream()
//                    .filter(answerOption -> answerOption.getDeleted() == null)
//                    .findFirst()
//                    .orElse(null);
//
//            assertThat(answerOptionNotDeleted).as("No debe quedar ningna opcion de respuesta sin borrar").isNull();
//
//
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion ", e);
//        }
//    }
//
//
//    @Test
//    @Transactional
//    public void deleteNonExistingReportTemplateTest() {
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_NOT_FOUND),
//                " Expected error code " + IReportTemplateServiceErrorCode.RT_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    reportTemplateService.delete("KKKK");
//                })
//                .has(errorCode);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void activationOkTest() {
//        try {
//            String uuidTestUser = "uuid prueba activacion";
//
//            reportTemplateService.activate("AAAA");
//
//            ReportTemplate reportTemplate = reportTemplateRepository.getOne(1);
//            assertThat(reportTemplate.getStatus()).as("Debe ser no editable").isEqualTo(ReportTemplateStatus.NOT_EDITABLE);
//
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion ", e);
//        }
//    }
//
//    @Test
//    @Transactional
//    public void activationNOkTest() {
//        String uuidTestUser = "uuid prueba activacion";
//
//        ReportTemplate reportTemplate = reportTemplateRepository.getOne(1);
//        reportTemplate.setStatus(ReportTemplateStatus.NOT_EDITABLE);
//        reportTemplateRepository.saveAndFlush(reportTemplate);
//
//
//        // Validar no activado
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IReportTemplateServiceErrorCode.NOT_ACTIVABLE_ALREADY_ACTIVE),
//                " Codigo error esperado " + IReportTemplateServiceErrorCode.NOT_ACTIVABLE_ALREADY_ACTIVE);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.activate("AAAA"))
//                .has(errorCode);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void activationNOkQuestionWithAnswersTest() {
//
//
//        log.info("Validar que si la pregunta no admite respuestas, no tenga respuestas");
//        Question question = questionRepository.findByUuid("XXX");
//        question.setHasAnswer(false);
//        questionRepository.saveAndFlush(question);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS);
//
//
//        log.info("Validar que si la pregunta es de tipo open no debe tener respuestas");
//        question.setHasAnswer(true);
//        question.setAnswerType(answerTypeRepository.getOne(6));
//        questionRepository.saveAndFlush(question);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS);
//    }
//
//
//    @Test
//    @Transactional
//    public void activationNOkQuestionWithMoreThan2AnswersTest() {
//
//
//        log.info("Validar que si la pregunta es binaria, solo tenga dos respuestas");
//        Question question = questionRepository.findByUuid("XXX");   // Esta pregunta tiene 3 opciones de respuesta
//        question.setAnswerType(answerTypeRepository.getOne(1)); // Le ponemos tipo binary para que pete el test
//        questionRepository.saveAndFlush(question);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.QUESTION_ONLY_ACCEPTS_2_ANSWER_OPTIONS);
//
//
//        log.info("Validar que si la pregunta es numeric, solo tenga dos respuestas");
//        question.setAnswerType(answerTypeRepository.getOne(4));     // La ponemos numeric para que pete el test
//        questionRepository.saveAndFlush(question);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.QUESTION_ONLY_ACCEPTS_2_ANSWER_OPTIONS);
//    }
//
//
//    @Test
//    @Transactional
//    public void activationNOkQuestionNumericWithoutNumericValuesTest() {
//
//
//        log.info("Validar que si la pregunta es numeric los valores de respuesta sean numericos");
//        AnswerOption answerOption = answerOptionRepository.getOne(51);
//        answerOption.setValue("AAA");
//        answerOptionRepository.saveAndFlush(answerOption);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.VALUE_MUST_BE_NUMERIC);
//
//
//        answerOption.setValue("1");
//        answerOptionRepository.saveAndFlush(answerOption);
//        answerOption = answerOptionRepository.getOne(52);
//        answerOption.setValue("AAA");
//        answerOptionRepository.saveAndFlush(answerOption);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.VALUE_MUST_BE_NUMERIC);
//    }
//
//
//    private void executeActivationWrongQuestions(String expectedErrorMessageCode) {
//        String uuidTestUser = "uuid prueba activacion";
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IBlockServiceErrorCode.QUESTIONS_CONTAINS_ERRORS),
//                " Codigo error esperado " + IBlockServiceErrorCode.QUESTIONS_CONTAINS_ERRORS);
//
//        // Busca en la informacion extra del error la causa que debe devolver
//        Condition<MusicException> errorCause = new Condition<>(reportsException -> {
//            Map<String, String> extraInfo = reportsException.getExtraInfo();
//            String errorMessage = i18n.getMessage(expectedErrorMessageCode);
//            Collection<String> errorMessages = extraInfo.values();
//            long numErrors = errorMessages.stream().filter(message -> message.contains(errorMessage)).count();
//            return numErrors != 0;
//        }, "La causa del error no es correcta");
//
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.activate("AAAA"))
//                .has(errorCode)
//                .has(errorCause);
//    }
//
//
//    @Test
//    @Transactional
//    public void cloneTest() {
//        ReportTemplate originalReportTemplate = reportTemplateRepository.getOne(1);
//    	
//        long numBlocksBefore = blockRepository.count();
//        long numQuestionsBefore = questionRepository.count();
//
//        ReportTemplateDTO clonedReportTemplate = reportTemplateService.clone(originalReportTemplate.getUuid());
//
//        long numBlocksAfter = blockRepository.count();
//        long numQuestionsAfter = questionRepository.count();
//
//
//        assertThat(numBlocksAfter)
//                .as("Debe incrementarse en el num de bloques del original")
//                .isEqualTo(numBlocksBefore + originalReportTemplate.getBlocks().size());
//
//        assertThat(clonedReportTemplate.getId()).as("Id Debe ser distinto").isNotEqualTo(originalReportTemplate.getId());
//        assertThat(clonedReportTemplate.getUuid()).as("Uuid Debe ser distinto").isNotEqualTo(originalReportTemplate.getUuid());
//
//        // Verificar que se copian todas las preguntas
//        AtomicInteger numQuestionsOriginal = new AtomicInteger(0);
//        originalReportTemplate.getBlocks().forEach(block -> numQuestionsOriginal.addAndGet(block.getQuestions().size()));
//
//        assertThat(numQuestionsAfter)
//                .as("Debe inrementarse en tantas preguntas tiene el original")
//                .isEqualTo(numQuestionsBefore + numQuestionsOriginal.get());
//    }
//
//
//    @Test
//    @Transactional
//    public void assignMatchRecordTest() {
//        final String ANSWER_PREFIX = "answer_prefix_";
//        Map<String, String> automaticAnswers = generateAutomaticAnswers(ANSWER_PREFIX);
//
//        try {
//            // Forzar que este cerrado y sea match report para que genere el bloque 0
//            ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("BBBB");
//            reportTemplateDTO.setStatus(ReportTemplateStatus.NOT_EDITABLE);
//            reportTemplateDTO.setIsMatchReport(true);
//            reportTemplateService.update(reportTemplateDTO);
//            reportTemplateRepository.flush();
//            ReportTemplate reportTemplate = reportTemplateRepository.getOne(reportTemplateDTO.getId());
//            entityManager.refresh(reportTemplate);
//
//            reportTemplateService.assignReportForm("BBBB", "INFORMER_UUID", "SUBJECT_UUID", automaticAnswers);
//            formRepository.flush();
//
//            LocalDate from = LocalDate.now();
//            LocalDate to = LocalDate.now();
//            List<FormDTO> forms = formService.findAllReportFormsByInformerUuid("INFORMER_UUID", InformerTypeCode.REFEREE, true, from, to);
//
//            assertThat(forms).as("No debe ser null").isNotNull();
//            assertThat(forms).as("Solo debe haber uno").hasSize(1);
//
//            // Sin el refresh no se entrera que tiene respuestas (cosas de hibernate...)
//            Form form = formRepository.getOne(forms.get(0).getId());
//            entityManager.refresh(form);
//
//            FormDTO formDTO = formService.findReportFormForUserFilled(forms.get(0).getUuid(), "INFORMER_UUID", UserTypes.INFORMER);
//
//
//            BlockDTO block0 = formDTO.getBlocks().stream()
//                    .filter(blockDTO -> blockDTO.getOrderNum().equals(0))
//                    .findFirst()
//                    .orElse(null);
//
//            assertThat(block0).as("Debe tener bloque 0").isNotNull();
//
//            // Verificar que todas las preguntas estan informadas con el valor correcto
//            List<QuestionDTO> questions = block0.getQuestions();
//            for (int i = 0; i < questions.size(); i++) {
//                QuestionDTO questionDTO = questions.get(i);
//                assertThat(questionDTO.getAnswer()).as("Debe tener respuesta").isNotNull();
//                assertThat(questionDTO.getAnswer().getValue()).as("El numero de la respuesta debe conincidir con el numero de orden de la pregunta")
//                        .isEqualTo(ANSWER_PREFIX + questionDTO.getOrderNum());
//            }
//
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion", e);
//        }
//
//    }
//
//
//    private Map<String, String> generateAutomaticAnswers(String answerPrefix) {
//        Map<String, String> automaticAnswers = new HashMap<>();
//
//        automaticAnswers.put("refereeNumber", answerPrefix + "1");
//        automaticAnswers.put("refereeName", answerPrefix + "2");
//        automaticAnswers.put("refereeCategory", answerPrefix + "3");
//        automaticAnswers.put("matchNumber", answerPrefix + "4");
//        automaticAnswers.put("localTeam", answerPrefix + "5");
//        automaticAnswers.put("visitorTeam", answerPrefix + "6");
//        automaticAnswers.put("matchCategory", answerPrefix + "7");
//        automaticAnswers.put("matchGender", answerPrefix + "8");
//        automaticAnswers.put("matchGroup", answerPrefix + "9");
//        automaticAnswers.put("matchDay", answerPrefix + "10");
//        automaticAnswers.put("matchTime", answerPrefix + "11");
//        automaticAnswers.put("matchTown", answerPrefix + "12");
//        automaticAnswers.put("localPoints", answerPrefix + "13");
//        automaticAnswers.put("visitorPoints", answerPrefix + "14");
//
//        return automaticAnswers;
//    }
//
//
//    @Test
//    @Transactional
//    public void assignNotMatchRecordTest() {
//
//        try {
//            // Forzar que este cerrado y no sea match report para que no tenga bloque 0
//            ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("BBBB");
//            reportTemplateDTO.setStatus(ReportTemplateStatus.NOT_EDITABLE);
//            reportTemplateDTO.setIsMatchReport(false);
//            reportTemplateService.update(reportTemplateDTO);
//
//            reportTemplateService.assignReportForm("BBBB", "INFORMER_UUID", "SUBJECT_UUID", null);
//
//            LocalDate from = LocalDate.now();
//            LocalDate to = LocalDate.now();
//            List<FormDTO> forms = formService.findAllReportFormsByInformerUuid("INFORMER_UUID", InformerTypeCode.REFEREE, true, from, to);
//
//            assertThat(forms).as("No debe ser null").isNotNull();
//            assertThat(forms).as("Solo debe haber uno").hasSize(1);
//
//            // Sin el refresh no se entrera que tiene respuestas (cosas de hibernate...)
//            Form form = formRepository.getOne(forms.get(0).getId());
//            entityManager.refresh(form);
//
//            FormDTO formDTO = formService.findReportFormForUserFilled(forms.get(0).getUuid(), "INFORMER_UUID", UserTypes.INFORMER);
//
//            BlockDTO block0 = formDTO.getBlocks().stream()
//                    .filter(blockDTO -> blockDTO.getOrderNum().equals(0))
//                    .findFirst()
//                    .orElse(null);
//
//            assertThat(formDTO.getBlocks()).as("Debe haber 1 bloque").hasSize(1);
//            assertThat(block0).as("No debe haber bloque 0").isNull();
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion", e);
//        }
//
//    }
//
//
//    @Test
//    @Transactional
//    public void assignMatchRecordNotClosedTest() {
//
//        try {
//            // Forzar que no este cerrado para forzar el error
//            ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("BBBB");
//            reportTemplateDTO.setStatus(ReportTemplateStatus.EDITABLE);
//            reportTemplateService.update(reportTemplateDTO);
//
//            Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IReportTemplateServiceErrorCode.NOT_ASSIGNABLE_STILL_EDITABLE),
//                    " Expected error code " + IReportTemplateServiceErrorCode.NOT_ASSIGNABLE_STILL_EDITABLE);
//
//            assertThatExceptionOfType(MusicException.class)
//                    .isThrownBy(() -> reportTemplateService.assignReportForm("BBBB", "INFORMER_UUID", "SUBJECT_UUID", null))
//                    .has(errorCode);
//        } catch (Exception e) {
//            fail("No debe lanzar excepcion", e);
//        }
//    }
//    
//    @Test
//    @Transactional
//    public void getReportTemplatesByCategoryIdsTestportalNull() {
//    	
//    	Portal portal =null;	
//        List<ReportTemplateDTO> reportTemplates = reportTemplateService.findByCategoryIdListDTO(Arrays.asList(1, 2), null, null, portal );
//
//        assertThat(reportTemplates).as("No debe ser null").isNotNull();
//        assertThat(reportTemplates).as("No debe tener 5 elementos").hasSize(5);
//    }
//    
//    @Test
//    @Transactional
//    public void getReportTemplatesByCategoryIdsTestPortalNotNull() {
//    		
//   	Portal portal = Portal.REFEREE;
//    	
//    Integer before=	reportTemplateService.findByCategoryIdListDTO(Arrays.asList(1, 2), null, null, portal ).size();
//    	//cambiamos el valor del portal a uno 
//    ReportTemplate oneReportTemplateFromDB=	reportTemplateRepository.getOne(1);   
//    oneReportTemplateFromDB.setPortal(Portal.CLUB);
//    reportTemplateRepository.saveAndFlush(oneReportTemplateFromDB);
//    
//    Integer after =	reportTemplateService.findByCategoryIdListDTO(Arrays.asList(1, 2), null, null, portal ).size();
//        List<ReportTemplateDTO> reportTemplates = reportTemplateService.findByCategoryIdListDTO(Arrays.asList(1, 2), null, null, portal );
//
//        assertThat(reportTemplates).as("No debe ser null").isNotNull();
//        assertThat(reportTemplates).as("No debe ser null con la cantidad ").hasSize(after);
//        assertThat(reportTemplates).as("No debe ser null con la cantidad ").hasSize(before-1);
//    }
//    
//    @Test
//    @Transactional
//    public void getReportTemplatesByCategoryIdsTestPortal() {
//    		
//    	Portal portal = Portal.REFEREE;
//    	
//    	//cambiamos el valor del portal de todos   
//    List<ReportTemplate > allReportsTemplateFromDB= reportTemplateRepository.findAll();
//    for(ReportTemplate rt : allReportsTemplateFromDB) {
//    rt.setPortal(Portal.CLUB);
//    reportTemplateRepository.saveAndFlush(rt);
//    
//    }   
//        List<ReportTemplateDTO> reportTemplatesList = reportTemplateService.findByCategoryIdListDTO(Arrays.asList(1, 2), null, null, portal );
//
//        assertThat(reportTemplatesList).as("No debe ser null").isNotNull();
//       
//        assertThat(reportTemplatesList).as("debe ser ya que se cambio todo CLUB que es 2 y REFEREE es 0 ").hasSize(0);
//    }
//    
//    @Test
//    @Transactional
//    public void getReportTemplatesByCategoryIdsTestPortalNull() {
//    		
//    	Portal portal = null;
//    	
//    	//cambiamos el valor del portal de todos  esto deberia afectar por que el portal es null
//     
//        List<ReportTemplate > allReportstemplateFromDB= reportTemplateRepository.findAll();
//        for(ReportTemplate rt : allReportstemplateFromDB) {
//            rt.setPortal(Portal.CLUB);
//            reportTemplateRepository.saveAndFlush(rt);
//
//        }
//        List<ReportTemplateDTO> reportTemplatesList = reportTemplateService.findByCategoryIdListDTO(Arrays.asList(1, 2), null, null, portal );
//
//        assertThat(reportTemplatesList).as("No debe ser null").isNotNull();
//
//        assertThat(reportTemplatesList).as("debe de ser 5 por que portal == null en este caso pasa sin hacer caso al parametro portal ").hasSize(5);
//    }
//
//
//
//    
//    @Transactional
//    @Test
//    public void activateBlockWeightIncorrectTest() {
//    	
//    	ReportTemplateDTO reportTemplateDTO=reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//    	
//    	BlockDTO blockDTO =blockRepository.findBlockByUuidDTO("4444");
//    	blockDTO.setWeight(80);
//    	blockDTO.setIsVisibleSubject(true);
//    	blockRepository.save(blockMapper.toEntity(blockDTO));
//    	   	   	
//    	 Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//    	    reportsException.getCode().equals(IBlockServiceErrorCode.AMOUNT_BLOCK_WEIGHT_NOT_CORRECT),
//    	    " Expected error code " + IBlockServiceErrorCode.AMOUNT_BLOCK_WEIGHT_NOT_CORRECT);
//
//    	    assertThatExceptionOfType(MusicException.class)
//                    .isThrownBy(() -> reportTemplateService.activate(reportTemplateDTO.getUuid()))
//    	            .has(errorCode);
//  	
//    }
//    
//    @Test
//    @Transactional
//    public void activateQuestionTest() {
//        ReportTemplateDTO reportTemplateDTO=reportTemplateService.findReportTemplateByUuidFilledDTO("AAAA");
//
//        BlockDTO blockDTO = reportTemplateDTO.getBlocks().get(0);
//
//        blockDTO.getQuestions().forEach(questionDTO -> {
//            questionDTO.setWeight(null);
//            questionRepository.saveAndFlush(questionMapper.toEntity(questionDTO));
//        });
//
//
//
//   	   Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//   	    reportsException.getCode().equals(IBlockServiceErrorCode.NOT_DEFENITE_QUESTION_WEIGHT_ON_BLOCK),
//   	    " Expected error code " + IBlockServiceErrorCode.NOT_DEFENITE_QUESTION_WEIGHT_ON_BLOCK);
//
//   	    assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.activate(reportTemplateDTO.getUuid()))
//   	            .has(errorCode);
//  	   
//    }
//    
//    @Transactional
//    @Test
//    public void activateBlockNullTest() {
//
//        BlockDTO blockDTO = 	blockService.findBlockByUuidDTO("4444");
//        blockDTO.setWeight(null);
//        blockDTO.setIsVisibleSubject(true);
//        blockRepository.saveAndFlush(blockMapper.toEntity(blockDTO));
//    	ReportTemplateDTO reportTemplateDTO=reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//      	
//       Condition<MusicException> errorCode = new Condition<>(reportsException ->
//        reportsException.getCode().equals(IBlockServiceErrorCode.AMOUNT_BLOCK_WEIGHT_NOT_CORRECT),
//        " Expected error code " + IBlockServiceErrorCode.AMOUNT_BLOCK_WEIGHT_NOT_CORRECT);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> reportTemplateService.activate(reportTemplateDTO.getUuid()))
//                .has(errorCode);
//
//    }   
//    
//    @Test
//    @Transactional
//    public void updateReportTemplateWithAnswersCantTets() {
//        ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//        // edito una que se puede y la otra no 
//        reportTemplateDTO.setIsVisible(false);
//        reportTemplateDTO.setName("nuevo nombre ");
//               
//        Condition<MusicException> errorCode = new Condition<>(reportsException ->
//        reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_HAS_ANSWERS),
//        " Expected error code " + IReportTemplateServiceErrorCode.RT_HAS_ANSWERS);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() ->  reportTemplateService.update(reportTemplateDTO))
//                .has(errorCode);
//    
//    }
//    
//    @Transactional
//    @Test
//    public void updateReportTemplateWithAnswerCanEditTest() {
//    	 ReportTemplateDTO reportTemplateDTO = reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//         // edito las 3 opciones que se pueden 
//         reportTemplateDTO.setIsVisible(false);
//         reportTemplateDTO.setShowReferee(false);
//         reportTemplateDTO.setHasPunctuation(false);
//         
//        ReportTemplateDTO reportTemplateDTOReturn= reportTemplateService.update(reportTemplateDTO);
//        assertThat(reportTemplateDTOReturn).as("si no es nul ya nos vale ").isNotNull();
//    	   	
//    }
//    
//    @Transactional
//    @Test
//    public void updateReportTemplateWithAnswersCant() {
//    	
//    ReportTemplateDTO reportTemplateDTO=	reportTemplateService.findReportTemplateByUuidDTO("AAAA");
//    //intentamos editar 3 campos prohibidos
//    reportTemplateDTO.setName("nuevo nombre ");
//    reportTemplateDTO.setPortal(1);
//
//    Condition<MusicException> errorCode = new Condition<>(reportsException ->
//    reportsException.getCode().equals(IReportTemplateServiceErrorCode.RT_HAS_ANSWERS),
//    " Expected error code " + IReportTemplateServiceErrorCode.RT_HAS_ANSWERS);
//
//    assertThatExceptionOfType(MusicException.class)
//            .isThrownBy(() ->  reportTemplateService.update(reportTemplateDTO))
//            .has(errorCode);
//  
// 
//    	
//    }
//    
//    
//    
//}
//
//
