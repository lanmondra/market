//package cat.owc.ms.reports.controllers;
//
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.dto.ReportTemplateDTO;
//import cat.owc.ms.reports.entity.ReportTemplate;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.mapper.ReportTemplateMapper;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.repository.ReportTemplateRepository;
//import cat.owc.ms.reports.services.ReportTemplateServiceTest;
//import cat.owc.ms.reports.services.interfaces.IReportTemplateService;
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
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//public class ManagerReportTemplateControllerTest extends AbstractControllersTest {
//
//
//    private static final String BASE_PATH = "/auth/manager/report-templates";
//
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//    @Autowired
//    private IReportTemplateService reportTemplateService;
//
//    @Autowired
//    private ReportTemplateRepository reportTemplateRepository;
//
//    @Autowired
//    private ReportTemplateMapper reportTemplateMapper;
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
//    @Test
//    @Transactional
//    public void getReportsListUserCat1Test() throws Exception {
//
//        Integer [] expectedReportIds = {1, 2};
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(3))
//                .andExpect(jsonPath("$.[0].id").value(1))
//                .andExpect(jsonPath("$.[1].id").value(2));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void getCategoriesListUserCat1_2Test() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1_2)))
//                //.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(5));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void getCategoriesListUserNoCat() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void getReportTemplateUserCat() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void getReportTemplateUserNoCat() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT));
//    }
//
//
//    @Test
//    @Transactional
//    public void addReportTemplateAuthorizedTest() throws Exception {
//
//        ReportTemplateDTO reportTemplateDTO = ReportTemplateServiceTest.createReportTemplateDTO(true, true, Portal.REFEREE, true, true, 1, 1, 1);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addReportTemplateNoAuthorizedTest() throws Exception {
//
//        ReportTemplateDTO reportTemplateDTO = ReportTemplateServiceTest.createReportTemplateDTO(true, true, Portal.REFEREE, true, true, 1, 1, 1);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN_CLUB))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//
//
//    }
//
//
//    @Test
//    @Transactional
//    public void updateReportTemplateAuthorized() throws Exception {
//
//        ReportTemplate reportTemplate = reportTemplateRepository.getOne(2);
//        ReportTemplateDTO reportTemplateDTO = reportTemplateMapper.toDto(reportTemplate);
//        reportTemplate.setAllowNotDesignated(false);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void updateReportTemplateNotAuthorized() throws Exception {
//
//        ReportTemplate reportTemplate = reportTemplateRepository.getOne(2);
//        ReportTemplateDTO reportTemplateDTO = reportTemplateMapper.toDto(reportTemplate);
//        reportTemplate.setAllowNotDesignated(false);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN_CLUB))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportTemplateDTO)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT));
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteReportTemplateAutorizedTest() throws Exception {
//
//        restMockMvc.perform(delete(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void deleteReportTemplateNotAutorizedTest() throws Exception {
//
//
//        restMockMvc.perform(delete(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1_READ)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT));
//
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void activateReportTemplateAutorizedTest() throws Exception {
//
//        restMockMvc.perform(patch(BASE_PATH + "/AAAA/activate")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void activateReportTemplateNotAutorizedTest() throws Exception {
//
//
//        restMockMvc.perform(patch(BASE_PATH + "/AAAA/activate")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1_READ)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT));
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void cloneReportTemplateAutorizedTest() throws Exception {
//
//        restMockMvc.perform(post(BASE_PATH + "/AAAA/clone")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void cloneReportTemplateNotAutorizedTest() throws Exception {
//
//
//        restMockMvc.perform(post(BASE_PATH + "/AAAA/clone")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1_READ)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT));
//    }
//
//
//
//}
