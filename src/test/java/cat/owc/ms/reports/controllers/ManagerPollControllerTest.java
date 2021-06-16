//package cat.owc.ms.reports.controllers;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//
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
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.dto.PollDTO;
//import cat.owc.ms.reports.entity.Poll;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.mapper.PollMapper;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.repository.PollRepository;
//import cat.owc.ms.reports.services.IPollServiceErrorCode;
//import cat.owc.ms.reports.services.PollService;
//import cat.owc.ms.reports.services.PollServiceTest;
//
//
//public class ManagerPollControllerTest extends AbstractControllersTest {
//
//
//    private static final String BASE_PATH = "/auth/manager/polls";
//
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
//
//    @Autowired
//    private PollRepository pollRepository;
//
//    @Autowired
//    private PollService pollService;
//
//    @Autowired
//    private PollMapper pollMapper;
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
//    public void canGetPollsListAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canGetPollsListNoAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAccessPollAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAccessPollNoAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                .andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAccessPollAdminNoFedeTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders("fcbq", TestUtil.JWT_ADMIN)))
//                .andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION));
//
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void canAccessPollFormAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111/form/508952c9-8fa1-11ea-b836-02b7c2952a14")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void canAccessPolFormlNoAdminTest() throws Exception {
//
//        restMockMvc.perform(get(BASE_PATH + "/1111/form/508952c9-8fa1-11ea-b836-02b7c2952a14")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                .andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//    }
//
//
//    @Test
//    @Transactional
//    public void addPollAuthorizedTest() throws Exception {
//    	
//    	Portal portal = Portal.FEDERATED;
//        PollDTO pollDTO = PollServiceTest.createPollDTO(true, true, true, InformerTypeCode.CLUB, portal.getCode());
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(pollDTO)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addPollNoAuthorizedTest() throws Exception {
//
//        PollDTO pollDTO = new PollDTO();
//        pollDTO.setInformerTypeCode(InformerTypeCode.REFEREE);
//
//        restMockMvc.perform(post(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN_CLUB))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(pollDTO)))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//
//    }
//
//
//    @Test
//    @Transactional
//    public void updatePollAdminTest() throws Exception {
//
//        Poll poll = pollRepository.findById(4).get();
//        PollDTO pollDTO = pollMapper.toDto(poll);
//        pollDTO.setName("Updated name");
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(pollDTO)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void updatePollTest() throws Exception {
//
//        PollDTO pollDTO = new PollDTO();
//        pollDTO.setUuid("1111");
//
//        restMockMvc.perform(put(BASE_PATH)
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(pollDTO)))
//                .andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//
//    }
//
//
//    @Test
//    @Transactional
//    public void activatePollAdminTest() throws Exception {
//        Poll poll = pollRepository.findById(1).get();
//        poll.setActivationTime(null);
//        pollRepository.saveAndFlush(poll);
//
//
//        restMockMvc.perform(patch(BASE_PATH + "/1111/activate")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(Arrays.asList("uuid test"))))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void activatePollTest() throws Exception {
//
//
//        restMockMvc.perform(patch(BASE_PATH + "/1111/activate")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(Arrays.asList("uuid test"))))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deletePollAdminTest() throws Exception {
//
//        restMockMvc.perform(delete(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void deletePollTest() throws Exception {
//
//
//        restMockMvc.perform(delete(BASE_PATH + "/1111")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void cloneAuthorizedTest() throws Exception {
//
//        restMockMvc.perform(post(BASE_PATH + "/1111/clone")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_ADMIN)))
//                //.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @Transactional
//    public void cloneNotAuthorizedTest() throws Exception {
//
//
//        restMockMvc.perform(post(BASE_PATH + "/1111/clone")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                //.andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error").value(IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN));
//
//
//    }
//
//
//
//
//
//
//
//
//}
