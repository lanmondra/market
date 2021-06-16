//package cat.owc.ms.reports.controllers;
//
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.dto.CampaignDTO;
//import cat.owc.ms.reports.entity.Campaign;
//import cat.owc.ms.reports.mapper.CampaignMapper;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.repository.CampaignRepository;
//import cat.owc.ms.reports.services.ICampaignServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.ICampaignService;
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
//public class ManagerCampaignControllerTest extends AbstractControllersTest {
//
//
//    private static final String BASE_PATH = "/auth/manager/campaigns";
//
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//    @Autowired
//    private ICampaignService campaignService;
//
//
//    @Autowired
//    private CampaignRepository campaignRepository;
//
//    @Autowired
//    private CampaignMapper campaignMapper;
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
//    public void getCampaignsListUserCat1Test() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$.[0].id").value(1));
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void getCampaignsListUserNoCat() throws Exception {
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
//    public void getCampaignUserCat1Test() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1)))
//                //.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1));
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void getCampaignsUserNoCat() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_ACCESS));
//    }
//
//
//
//
//
//    @Test
//    @Transactional
//    public void addCategoryAdminTest() throws Exception {
//
//        // Si da error de missing, ha pasado la validación de permisos que es el objetivo del test
//        CampaignDTO campaignDTO = new CampaignDTO();
//
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error").value(ICampaignServiceErrorCode.MISSING_NAME));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addCategoryNoAdminTest() throws Exception {
//
//        CampaignDTO campaignDTO = new CampaignDTO();
//        campaignDTO.setReportTemplateId(1);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN_CLUB))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
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
//    public void updateCategoryAdminTest() throws Exception {
//
//        Campaign campaign = campaignRepository.getOne(1);
//        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_REPORTS_CAT_1))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateCategoryNoAdminTest() throws Exception {
//
//        Campaign campaign = campaignRepository.getOne(1);
//        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT));
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
//        restMockMvc.perform(delete(BASE_PATH + "/1111")
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
//        restMockMvc.perform(delete(BASE_PATH + "/1111")
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
