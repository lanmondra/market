//package cat.owc.ms.reports.controllers;
//
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.dto.ReportCategoryDTO;
//import cat.owc.ms.reports.entity.ReportCategory;
//import cat.owc.ms.reports.mapper.ReportCategoryMapper;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.repository.ReportCategoryRepository;
//import cat.owc.ms.reports.services.IReportCategoryServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IReportCategoryService;
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
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//public class ManagerReportCategoryControllerTest extends AbstractControllersTest {
//
//
//    private static final String BASE_PATH = "/auth/manager/report-categories";
//
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//    @Autowired
//    private IReportCategoryService  reportCategoryService;
//
//    @Autowired
//    private ReportCategoryRepository reportCategoryRepository;
//
//    @Autowired
//    private ReportCategoryMapper reportCategoryMapper;
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
//    public void getCategoriesListUserCat1Test() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(1))
//                .andExpect(jsonPath("$.[0].id").value(1));
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
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$.[0].id").value(1))
//                .andExpect(jsonPath("$.[1].id").value(2));
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
//    public void canAccessCategoryAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAccessCategoryNoAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                .andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void addCategoryAdminTest() throws Exception {
//
//        // Si da error de missing, ha pasado la validación de permisos que es el objetivo del test
//        ReportCategoryDTO reportCategoryDTO = new ReportCategoryDTO();
//
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportCategoryDTO)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error").value(IReportCategoryServiceErrorCode.MISSING_NAME));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addCategoryNoAdminTest() throws Exception {
//
//        ReportCategoryDTO reportCategoryDTO = new ReportCategoryDTO();
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN_CLUB))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportCategoryDTO)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateCategoryAdminTest() throws Exception {
//
//        ReportCategoryDTO reportCategoryDTO = reportCategoryService.findOneByUuidDTO("AAAA");
//        reportCategoryDTO.setName("UPDATED REPORT CATEGORY");
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportCategoryDTO)))
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateCategoryNoAdminTest() throws Exception {
//
//        ReportCategory reportCategory = reportCategoryRepository.getOne(1);
//        ReportCategoryDTO reportCategoryDTO = reportCategoryMapper.toDto(reportCategory);
//        reportCategoryDTO.setName("UPDATED REPORT CATEGORY");
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(reportCategoryDTO)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void deleteCategoryAdminTest() throws Exception {
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
//    public void deleteCategoryNoAdminTest() throws Exception {
//
//
//        restMockMvc.perform(delete(BASE_PATH + "/AAAA")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//
//
//    }
//
//
//}
