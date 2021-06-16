//package cat.owc.ms.reports.controllers;
//
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.services.BlockService;
//import cat.owc.ms.reports.services.BlockServiceTest;
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
//public class ManagerBlockControllerTest extends AbstractControllersTest {
//
//
//    private static final String BASE_PATH = "/auth/manager/blocks";
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
//    // PRUEBAS CONTROL ACCESO ALTA BLOQUE
//    @Test
//    @Transactional
//    public void canAddPollBlockAdminTest() throws Exception {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(3, null);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAddReportBlockAdminTest() throws Exception {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(null, 1);
//
//        // Forzar el estado del form asociado al report para passar el test
//        Form form = formRepository.findById(7).get();
//        form.setStatus(FormStatus.PENDING);
//        formRepository.saveAndFlush(form);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAddReportBlockEditPermissionTest() throws Exception {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(null, 1);
//
//        // Forzar el estado del form asociado al report para passar el test
//        Form form = formRepository.findById(7).get();
//        form.setStatus(FormStatus.PENDING);
//        formRepository.saveAndFlush(form);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAddPollNoAdminTest() throws Exception {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(3, null);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAddReportBlockNoEditPermissionTest() throws Exception {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(null, 1);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_NO_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//
//
//
//    // PRUEBAS CONTROL ACCESO MODIFICACION BLOQUE
//
//    private BlockDTO addTestBlock(Integer pollId, Integer reportId) {
//        BlockDTO blockDTO = BlockServiceTest.createBlockDTO(pollId, reportId);
//        return  blockService.add(blockDTO);
//
//    }
//
//    private void setFormPending(Integer formId) {
//        // Forzar el estado del form asociado al report para passar el test
//        Form form = formRepository.getOne(formId);
//        form.setStatus(FormStatus.PENDING);
//        formRepository.saveAndFlush(form);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void canUpdatePollBlockAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(3, null);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canUpdateReportBlockAdminTest() throws Exception {
//        setFormPending(7);
//
//        BlockDTO blockDTO = addTestBlock(null, 1);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canUpdateReportBlockEditPermissionTest() throws Exception {
//        setFormPending(7);
//
//        BlockDTO blockDTO = addTestBlock(null, 1);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canUpdatePollNoAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(3, null);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    @Transactional
//    public void canUpdateReportBlockNoEditPermissionTest() throws Exception {
//        setFormPending(7);
//
//        BlockDTO blockDTO = addTestBlock(null, 1);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_NO_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(blockDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//
//    // PRUEBAS CONTROL ACCESO BORRADO BLOQUE
//
//    @Test
//    @Transactional
//    public void canDeletePollBlockAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(3, null);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + blockDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canDeleteReportBlockAdminTest() throws Exception {
//        setFormPending(7);
//
//        BlockDTO blockDTO = addTestBlock(null, 1);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + blockDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canDeleteReportBlockEditPermissionTest() throws Exception {
//        setFormPending(7);
//
//        BlockDTO blockDTO = addTestBlock(null, 1);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + blockDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canDeletePollNoAdminTest() throws Exception {
//        BlockDTO blockDTO = addTestBlock(3, null);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + blockDTO.getUuid())
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    @Transactional
//    public void canDeleteReportBlockNoEditPermissionTest() throws Exception {
//        setFormPending(7);
//
//        BlockDTO blockDTO = addTestBlock(null, 1);
//
//        restMockMvc.perform(delete(BASE_PATH + "/" + blockDTO.getUuid())
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
