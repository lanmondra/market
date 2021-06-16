//package cat.owc.ms.reports.controllers;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.TestUtil;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.InformerType;
//import cat.owc.ms.reports.entity.Poll;
//import cat.owc.ms.reports.repository.FormRepository;
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
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//public class PollControllerTest extends AbstractTest {
//
//        private static final String BASE_PATH = "/auth/polls";
//
//        private static final String DEFAULT_FEDERATION = "test";
//        private static final LocalDateTime DEFAULT_ACTIVATION_TIME = LocalDateTime.now().minusDays(10);
//        private static final  Boolean DEFAULT_ANONYMOUS = true;
//        private static final  Boolean DEFAULT_CHANGE_ALLOWED = true;
//        private static final  String DEFAULT_DESCRIPTION = "DESCRIPTION";
//        private static final String DEFAULT_INFORMER_CATEGORIES = "1234, 1111";
//        private static final InformerType DEFAULT_INFORMER_TYPE = null;
//        private static final Boolean DEFAULT_LOGIN_REQUIRED = true;
//        private static final String DEFAULT_NAME = "NAME";
//        private static final String DEFAULT_UUID = "ABCD";
//
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
//    private MockMvc restMockMvc;
//
//
//    @Before
//    public void setup() {
//
//        MockitoAnnotations.initMocks(this);
//
//        this.restMockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .addFilter(springSecurityFilterChain).build();
//
//
//
//    }
//
//
//    public static Poll createEntity(EntityManager em) {
//        Poll poll = new Poll();
//        poll.setFederation(DEFAULT_FEDERATION);
//        poll.setEndingTime(null);
//        poll.setActivationTime(DEFAULT_ACTIVATION_TIME);
//        poll.setAnonymous(DEFAULT_ANONYMOUS);
//        poll.setChangeAnswerAllowed(DEFAULT_CHANGE_ALLOWED);
//        poll.setDescription(DEFAULT_DESCRIPTION);
//        poll.setInformerCategories(DEFAULT_INFORMER_CATEGORIES);
//        poll.setInformerType(DEFAULT_INFORMER_TYPE);
//        poll.setLoginRequired(DEFAULT_LOGIN_REQUIRED);
//        poll.setName(DEFAULT_NAME);
//        poll.setUuid(DEFAULT_UUID);
//
//        return poll;
//    }
//
//
//    @Test
//    @Transactional
//    public void checkPendingTest() throws Exception {
//        // Preparar la prova
//        Form form = formRepository.findPollFormByIdFilled(3);
//        form.setInformerUuid("a44e083e-5d77-4ac6-81d2-9c63fc1fce18");
//        formRepository.saveAndFlush(form);
//
//
//        restMockMvc.perform(get(BASE_PATH + "/check-pending")
//                .headers(TestUtil.getTestHeaders(TestUtil.JWT_USER_WITH_POLLS)))
//                .andExpect(status().isOk());
//    }
//
//
//
//
//}
