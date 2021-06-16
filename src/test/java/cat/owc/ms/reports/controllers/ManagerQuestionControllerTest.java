//package cat.owc.ms.reports.controllers;
//
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.services.BlockService;
//import cat.owc.ms.reports.services.BlockServiceTest;
//import cat.owc.ms.reports.services.QuestionService;
//import cat.owc.ms.reports.services.QuestionServiceTest;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//public class ManagerQuestionControllerTest extends AbstractControllersTest {
//
//
//    private static final String BASE_PATH = "/auth/manager/questions";
//
//    private static final Integer BLOCK_NO_ANSWERS_ID = 4;
//    private static final Integer TEST_FORM_ID = 7;
//    private static final Integer POLL_NO_ANSWERS = 3;
//
//
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//    @Autowired
//    private FormRepository formRepository;
//
//    @Autowired
//    private BlockService blockService;
//
//    @Autowired
//    private QuestionService questionService;
//
//
//
//    private MockMvc restMockMvc;
//
//
//
//    @Before
//    public void setup() {
//
//        MockitoAnnotations.initMocks(this);
//        this.restMockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .addFilter(springSecurityFilterChain)
//                .build();
//    }
//
//
//
//    // PRUEBAS CONTROL ACCESO ALTA PREGUNTA
//    @Test
//    @Transactional
//    public void canAddPollQuestionAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(POLL_NO_ANSWERS, null);
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(blockDTO.getId());
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAddReportQuestionAdminTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(BLOCK_NO_ANSWERS_ID);
//
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAddReportQuestionEditPermissionTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAddPolQuestionlNoAdminTest() throws Exception {
//        // Crear un bloque nuevo en una encuesta sin forms
//        BlockDTO blockDTO = addTestBlock(POLL_NO_ANSWERS, null);
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(blockDTO.getId());
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAddReportQuestionNoEditPermissionTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_NO_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//
//    // PRUEBAS CONTROL ACCESO MODIFICACION PREGUNTA
//
//    private BlockDTO addTestBlock(Integer pollId, Integer reportId) {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(pollId, reportId);
//        return  blockService.add(blockDTO);
//    }
//
//
//    private QuestionDTO addTestQuestion(Integer blockId) {
//        QuestionDTO questionDTO = QuestionServiceTest.createQuestionDTO(blockId);
//        return questionService.add(questionDTO);
//    }
//
//    private void setFormPending(Integer formId) {
//        // Forzar el estado del form asociado al report para passar el test
//        Form form = formRepository.findById(formId).get();
//        form.setStatus(FormStatus.PENDING);
//        formRepository.saveAndFlush(form);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void canUpdatePollQuestionAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(POLL_NO_ANSWERS, null);
//        QuestionDTO questionDTO = addTestQuestion(blockDTO.getId());
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canUpdateReportQuestionAdminTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = addTestQuestion(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canUpdateReportQuestionEditPermissionTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = addTestQuestion(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canUpdatePollQuestionNoAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(POLL_NO_ANSWERS, null);
//        QuestionDTO questionDTO = addTestQuestion(blockDTO.getId());
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    @Transactional
//    public void canUpdateReportQuestionNoEditPermissionTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = addTestQuestion(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_NO_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//
//    // PRUEBAS CONTROL ACCESO BORRADO PREGUNTA
//
//    @Test
//    @Transactional
//    public void canDeletePollQuestionAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(POLL_NO_ANSWERS, null);
//        QuestionDTO questionDTO = addTestQuestion(blockDTO.getId());
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + questionDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canDeleteReportQuestionAdminTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = addTestQuestion(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + questionDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canDeleteReportQuestionEditPermissionTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = addTestQuestion(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + questionDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canDeletePollQuestionNoAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(POLL_NO_ANSWERS, null);
//        QuestionDTO questionDTO = addTestQuestion(blockDTO.getId());
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + questionDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    @Transactional
//    public void canDeleteReportQuestionNoEditPermissionTest() throws Exception {
//        setFormPending(TEST_FORM_ID);
//        QuestionDTO questionDTO = addTestQuestion(BLOCK_NO_ANSWERS_ID);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + questionDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_NO_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//
//
//}
