//package cat.owc.ms.reports.services;
//
//import static cat.owc.ms.reports.services.IPollServiceErrorCode.FORM_NOT_FOUND_FOR_USER;
//import static cat.owc.ms.reports.services.IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.assertj.core.api.Fail.fail;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
//import javax.transaction.Transactional;
//
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.PollDTO;
//import cat.owc.ms.reports.entity.AnswerOption;
//import cat.owc.ms.reports.entity.Block;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.InformerType;
//import cat.owc.ms.reports.entity.Poll;
//import cat.owc.ms.reports.entity.Question;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.PollStatus;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.PollMapper;
//import cat.owc.ms.reports.repository.AnswerOptionRepository;
//import cat.owc.ms.reports.repository.AnswerTypeRepository;
//import cat.owc.ms.reports.repository.BlockRepository;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.repository.InformerTypeRepository;
//import cat.owc.ms.reports.repository.PollRepository;
//import cat.owc.ms.reports.repository.QuestionRepository;
//import cat.owc.ms.reports.services.interfaces.IPollService;
//import cat.owc.ms.reports.utils.I18n;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class PollServiceTest extends AbstractTest {
//
//	@Autowired
//    private IPollService pollService;
//
//	@Autowired
//    private PollRepository pollRepository;
//
//	@Autowired
//    private FormRepository formRepository;
//
//	@Autowired
//    private QuestionRepository questionRepository;
//
//	@Autowired
//    private AnswerOptionRepository answerOptionRepository;
//
//	@Autowired
//    private AnswerTypeRepository answerTypeRepository;
//
//	@Autowired
//    private I18n i18n;
//
//	@Autowired
//    private BlockRepository blockRepository;
//	
//	@Autowired
//	private PollMapper pollMapper;
//
//	@Autowired InformerTypeRepository informerTypeRepository;
//
//	@Test
//    @Transactional
//    public void hasPendingFormsTest() {
//		Portal portal= Portal.REFEREE;
//        Boolean hasPending = pollService.hasPendingAnswerPolls(ClientContextHolder.getFederation(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB, portal);
//
//        assertThat(hasPending).as("Debe ser true").isTrue();
//    }
//
//
//    @Test
//    @Transactional
//    public void getActivePollsTest() {
//    	Portal portal = null;
//	    // Caso usuario con 4 encuestas, pero una de arbitros y una no activa
//	    List<PollDTO> polls = pollService.findPolls(ClientContextHolder.getFederation(), "508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB, true, portal);
//        List<Integer> pollIds = polls.stream().map(pollDTO -> {
//            return pollDTO.getId();
//        }).collect(Collectors.toList());
//
//	    assertThat(polls).as("No debe ser null").isNotNull();
//        assertThat(pollIds).as("Debe contener las encuestas 1 y 2").contains(1, 2);
//
//
//        // Caso usuario con dos encuestas activas, una iniciada y la otra finalizada
//        polls = pollService.findPolls(ClientContextHolder.getFederation(), "508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB, true, portal);
//        pollIds = polls.stream().map(PollDTO::getId).collect(Collectors.toList());
//
//        assertThat(polls).as("No debe ser null").isNotNull();
//        assertThat(pollIds).as("Debe contener las encuestas 1 y 2").contains(1, 2);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void getNonActivePollsTest() {
//    	Portal portal = null;
//        // Caso usuario con 4 encuestas, una no activa
//        List<PollDTO> polls = pollService.findPolls(ClientContextHolder.getFederation(), "508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB, false,portal);
//        List<Integer> pollIds = polls.stream().map(PollDTO::getId).collect(Collectors.toList());
//
//        assertThat(polls).as("No debe ser null").isNotNull();
//        assertThat(polls).as("debe contener un elemento").hasSize(1);
//        assertThat(pollIds).as("Debe contener la encuesta 4").contains(4);
//
//
//        // Caso usuario sin encuestas no activas
//        polls = pollService.findPolls(ClientContextHolder.getFederation(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB, false, portal);
//        pollIds = polls.stream().map(pollDTO -> pollDTO.getId()).collect(Collectors.toList());
//
//        assertThat(polls).as("Debe ser null").isNullOrEmpty();
//    }
//    
//    @Transactional
//    @Test
//    public void getPollPortalNotNull() {
//    	
//    	Portal portal = Portal.CLUB;
//    	
//    	Poll poll = pollRepository.getOne(1);
//    	poll.setPortal(portal.getCode());
//    	pollRepository.saveAndFlush(poll);
//    	
//    	 List<PollDTO>  polls = pollService.findPolls(ClientContextHolder.getFederation(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB, false, portal);
//    	
//    	 assertThat(polls.size()).isNotNull();
//    	 
//    }
//
//
//    @Test
//    @Transactional
//    public void getFormNoFederationTest() {
//
//        try {
//            Poll poll = pollRepository.getOne(1);
//            poll.setFederation("fcbq");
//            pollRepository.saveAndFlush(poll);
//
//            FormDTO formDTO = pollService.findFormDTO(poll.getUuid(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", true);
//            fail("Debe lanzar excepción");
//        }
//        catch(MusicException e) {
//            assertThat(e.getCode()).as("El codigo de error debe ser " + POLL_NOT_BELONGS_TO_FEDERATION);
//            assertThat(e.getStatus()).as("Debe ser http status unauthorized").isEqualTo(HttpStatus.UNAUTHORIZED);
//        }
//        catch(Exception e) {
//            fail("La excepcion debe ser de tipo ReportsException");
//        }
//
//
//    }
//
//
//    @Test
//    @Transactional
//    public void getFormNotFoundForUserTest() {
//
//        try {
//            Poll poll = pollRepository.getOne(1);
//            FormDTO formDTO = pollService.findFormDTO(poll.getUuid(), "ABCD", true);
//            fail("Debe lanzar excepción");
//        }
//        catch(MusicException e) {
//            assertThat(e.getCode()).as("El codigo de error debe ser " + FORM_NOT_FOUND_FOR_USER);
//            assertThat(e.getStatus()).as("Debe ser http status unauthorized").isEqualTo(HttpStatus.NOT_FOUND);
//        }
//        catch(Exception e) {
//            fail("La excepcion debe ser de tipo ReportsException");
//        }
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void getFormOkForUserTest() {
//        Poll poll = pollRepository.getOne(1);
//        FormDTO formDTO = pollService.findFormDTO(poll.getUuid(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", true);
//
//        assertThat(formDTO).as("Debe tener valor").isNotNull();
//        assertThat(formDTO.getId()).as("Debe ser el id 5").isEqualTo(5);
//        assertThat(formHasAnswers(formDTO)).as("Debe tener respuestas").isTrue();
//    }
//
//
//    @Test
//    @Transactional
//    public void getFormOkForAdminWithAnswersTest() {
//	    // Para admin, para que el formulario devuelva las respuestas la encuesta debe ser anonima
//        Poll poll = pollRepository.getOne(1);
//        poll.setAnonymous(true);
//        pollRepository.saveAndFlush(poll);
//
//        FormDTO formDTO = pollService.findFormDTO(poll.getUuid(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", false);
//
//        assertThat(formDTO).as("Debe tener valor").isNotNull();
//        assertThat(formDTO.getId()).as("Debe ser el id 5").isEqualTo(5);
//        assertThat(formHasAnswers(formDTO)).as("Debe tener respuestas").isTrue();
//    }
//
//
//    @Test
//    @Transactional
//    public void getFormOkForAdminWithoutAnswersTest() {
//        // Para admin, para que el formulario no devuelva las respuestas la encuesta no debe ser anonima
//        Poll poll = pollRepository.getOne(1);
//        poll.setAnonymous(false);
//        pollRepository.saveAndFlush(poll);
//
//        FormDTO formDTO = pollService.findFormDTO(poll.getUuid(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", false);
//
//        assertThat(formDTO).as("Debe tener valor").isNotNull();
//        assertThat(formDTO.getId()).as("Debe ser el id 5").isEqualTo(5);
//        assertThat(formHasAnswers(formDTO)).as("No debe tener respuestas").isFalse();
//    }
//
//
//    private Boolean formHasAnswers(FormDTO formDTO) {
//        AtomicBoolean hasAnswers = new AtomicBoolean(false);
//
//	    formDTO.getBlocks().forEach(blockDTO -> {
//            long numQuestionsAnswered = blockDTO.getQuestions().stream()
//                                            .filter(questionDTO -> questionDTO.getAnswer() != null)
//                                            .count();
//            hasAnswers.set(numQuestionsAnswered > 0);
//        });
//
//	    return hasAnswers.get();
//    }
//
//
//    @Test
//    @Transactional
//    public void findPollByUuidFilledDTOTest() {
//	    PollDTO pollDTO = pollService.findPollByUuidFilledDTO("1111");
//
//        assertThat(pollDTO).as("Debe tener valor").isNotNull();
//        assertThat(pollDTO.getId()).as("Debe ser el id 1").isEqualTo(1);
//    }
//
//
//    @Test
//    @Transactional
//    public void findPollByUuidFilledDTONotFoundTest() {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IPollServiceErrorCode.POLL_NOT_FOUND),
//                "Expected error code " + IPollServiceErrorCode.POLL_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> pollService.findPollByUuidFilledDTO("ABCD"))
//                .has(errorCode);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void getPollByUuidDTOTest() {
//        PollDTO pollDTO = pollService.findPollByUuidDTO("1111");
//
//        assertThat(pollDTO).as("Debe tener valor").isNotNull();
//        assertThat(pollDTO.getId()).as("Debe ser el id 1").isEqualTo(1);
//    }
//
//
//    @Test
//    @Transactional
//    public void getPollByUuidDTONotFoundTest() {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IPollServiceErrorCode.POLL_NOT_FOUND),
//                "Expected error code " + IPollServiceErrorCode.POLL_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> pollService.findPollByUuidDTO("ABCD"))
//                .has(errorCode);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void findPollFileNoAnswersTest() {
//        //Modificar datos de prueba para que no haya una encuesta finalizada
//        Form form5 = formRepository.getOne(5);
//        form5.setStatus(FormStatus.STARTED);
//        formRepository.saveAndFlush(form5);
//
//
//        PollDTO pollDTO = pollService.findPollFile("1111");
//
//	    assertThat(pollDTO).as("No debe ser null").isNotNull();
//	    assertThat(pollDTO.getId()).as("El id debe ser 1").isEqualTo(1);
//
//	    // Verificar lista participantes
//        List<String> participantsUuid = pollDTO.getParticipants();
//        assertThat(participantsUuid).as("No debe ser null").isNotNull();
//        assertThat(participantsUuid).as("Debe tener datos").isNotEmpty();
//        assertThat(participantsUuid).as("Debe tener 2 elementos").hasSize(2);
//
//        // Verificar numero encuestas iniciadas (los forms 1 y 5 deben estar iniciados)
//        assertThat(pollDTO.getNumStartedPolls()).as("Debe tener dos encuestas iniciadas").isEqualTo(2);
//
//        // Verificar numero encuestas finalizadas
//        assertThat(pollDTO.getNumAnsweredPolls()).as("No debe haber ninguna encuesta finalizada").isEqualTo(0);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void findPollFileWithAnswersTest() {
//
//        PollDTO pollDTO = pollService.findPollFile("1111");
//
//        assertThat(pollDTO).as("No debe ser null").isNotNull();
//        assertThat(pollDTO.getId()).as("El id debe ser 1").isEqualTo(1);
//
//        // Verificar lista participantes
//        List<String> participantsUuid = pollDTO.getParticipants();
//        assertThat(participantsUuid).as("No debe ser null").isNotNull();
//        assertThat(participantsUuid).as("Debe tener datos").isNotEmpty();
//        assertThat(participantsUuid).as("Debe tener 2 elementos").hasSize(2);
//
//        // Verificar numero encuestas iniciadas
//        assertThat(pollDTO.getNumStartedPolls()).as("Debe tener dos encuestas iniciadas").isEqualTo(2);
//
//        // Verificar numero encuestas finalizadas
//        assertThat(pollDTO.getNumAnsweredPolls()).as("Debe haber una encuesta finalizada").isEqualTo(1);
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void finPollsByInformerTypeTest() {
//    	
//    	Portal portal = null;
//	    List<PollDTO> pollsDTO = pollService.findPollsByInformerTypeDTO("test", Arrays.asList(InformerTypeCode.CLUB, InformerTypeCode.REFEREE),portal);
//
//	    assertThat(pollsDTO).as("No debe ser null").isNotNull();
//        assertThat(pollsDTO).as("No debe estar vacio").isNotEmpty();
//
//        List<Integer> pollIds = pollsDTO.stream().map(pollDTO -> pollDTO.getId()).collect(Collectors.toList());
//        assertThat(pollIds).as("Debe tener elementos de 1 a 4").contains(1, 2, 3, 4);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void finPollsOnlyClubTest() {
//    	Portal portal = null;
//        List<PollDTO> pollsDTO = pollService.findPollsByInformerTypeDTO("test", Arrays.asList(InformerTypeCode.CLUB), portal);
//
//        assertThat(pollsDTO).as("No debe ser null").isNotNull();
//        assertThat(pollsDTO).as("No debe estar vacio").isNotEmpty();
//
//        List<Integer> pollIds = pollsDTO.stream().map(pollDTO -> pollDTO.getId()).collect(Collectors.toList());
//        assertThat(pollIds).as("Debe tener elementos 1, 2, 4").contains(1, 2, 4);
//        assertThat(pollIds).as("No debe tener elemento 3").doesNotContain(3);
//
//
//    }
//
//
//    @Test
//    @Transactional
//    public void findPollsNotDeletedTest() {
//    	Portal portal = null;
//	    Poll poll = pollRepository.getOne(1);
//	    poll.setDeleted(Timestamp.valueOf(LocalDateTime.now()));
//	    pollRepository.saveAndFlush(poll);
//
//        List<PollDTO> pollsDTO = pollService.findPollsByInformerTypeDTO("test", Arrays.asList(InformerTypeCode.CLUB),portal);
//
//        assertThat(pollsDTO).as("No debe ser null").isNotNull();
//        assertThat(pollsDTO).as("No debe estar vacio").isNotEmpty();
//
//        List<Integer> pollIds = pollsDTO.stream().map(pollDTO -> pollDTO.getId()).collect(Collectors.toList());
//        assertThat(pollIds).as("Debe tener elementos de 2 y 4").contains(2, 4);
//        assertThat(pollIds).as("No debe tener elemento 1 (baja) y 3 (arbitros)").doesNotContain(1, 3);
//    }
//    //-----------------------nuevos test--------------------
//    @Transactional
//    @Test
//    public void findPollPortalNotNullTets() {
//
//    	 List<PollDTO> pollsDTO = pollService.findPollsByInformerTypeDTO("test", Arrays.asList(InformerTypeCode.CLUB), Portal.REFEREE);
//    	
//    	 assertThat(pollsDTO).as("No debe ser null").isNotNull();
//         assertThat(pollsDTO).as("No debe estar vacio").isNotEmpty();
//         assertThat(pollsDTO.size()).as("Debe haber 3 elementos").isEqualTo(3);
//    }
//   
//    
//    @Transactional
//    @Test
//    public void updateNotPortaInformedTest () {
//    	
//    	Poll poll = pollRepository.getOne(1);
//    	poll.setPortal(null);
//    	PollDTO pollDTO= pollMapper.toDto(poll);
//    	
//   	
//    	 Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IPollServiceErrorCode.MISSING_PORTAL),
//                 "Expected error code " + IPollServiceErrorCode.MISSING_PORTAL);
//
//         assertThatExceptionOfType(MusicException.class)
//                 .isThrownBy(() -> pollService.update(pollDTO))
//                 .has(errorCode);
//    	
//    }
//    
//    @Transactional
//    @Test
//    public void addNotportalInformed() {
//    	
//    }
//    
//    //--------------------------------------------------------
//
//    @Test
//    @Transactional
//    public void addPollOkTest() {
//    	Portal portal = Portal.FEDERATED;
//	    PollDTO pollDTO = createPollDTO(true, true, true, InformerTypeCode.CLUB, portal.getCode());
//	    long numPollsBefore = pollRepository.count();
//
//	    pollDTO = pollService.add(pollDTO);
//
//	    long numPollsAfter = pollRepository.count();
//	    assertThat(numPollsAfter).as("Debe incrementarse en uno el numero de encuestas").isEqualTo(numPollsBefore + 1);
//	    assertThat(pollDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(pollDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(pollDTO.getStatus()).as("Estatus debe ser editable").isEqualTo(PollStatus.EDITABLE);
//        assertThat(pollDTO.getFederation()).as("Federacion debe ser test").isEqualTo("test");
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addPollFederatedNoCategoriesOk() {
//	    // Validar que si el informerType es federado, las categorias no son obligatorias
//    	Portal portal = Portal.FEDERATED;
//    	
//        PollDTO pollDTO = createPollDTO(true, true, true, InformerTypeCode.FEDERATED, portal.getCode());
//        InformerType informerType = informerTypeRepository.findByCode(InformerTypeCode.FEDERATED);
//        pollDTO.setInformerTypeId(informerType.getId());
//        pollDTO.setInformerCategories(null);
//        long numPollsBefore = pollRepository.count();
//
//        pollDTO = pollService.add(pollDTO);
//
//        long numPollsAfter = pollRepository.count();
//        assertThat(numPollsAfter).as("Debe incrementarse en uno el numero de encuestas").isEqualTo(numPollsBefore + 1);
//        assertThat(pollDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(pollDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(pollDTO.getStatus()).as("Estatus debe ser editable").isEqualTo(PollStatus.EDITABLE);
//        assertThat(pollDTO.getFederation()).as("Federacion debe ser test").isEqualTo("test");
//    }
//
//
//    @Test
//    @Transactional
//    public void addPollNokTest() {
//        addUpdateCommonValidations(false);
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void updatePollOkTest() {
//	    String testName = "Testing update";
//
//        PollDTO pollDTO = pollService.findPollByUuidDTO("4444");
//        pollDTO.setName(testName);
//        pollDTO.setAnonymous(true);
//        pollDTO.setLoginRequired(false);
//        pollDTO.setPortal(Portal.CLUB.getCode());
//        pollDTO = pollService.add(pollDTO);
//
//        pollDTO.setPortal(Portal.CLUB.getCode());
//        pollDTO = pollService.update(pollDTO);
//
//        // Asegurar que salva
//        pollRepository.flush();
//
//        // Recuperar i validar
//        Poll poll = pollRepository.getOne(4);
//
//        assertThat(poll.getName()).as("El nombre debe ser " + testName).isEqualTo(testName);
//        assertThat(poll.getAnonymous()).as("Anonymous debe ser true").isTrue();
//        assertThat(poll.getLoginRequired()).as("Login required debe ser false").isFalse();
//        assertThat(poll.getLastActionUser()).as("Debe tener el valor por defecto").isEqualTo("no se pudo recuperar");
//    }
//
//
//    @Test
//    @Transactional
//    public void updatePollNokTest() {
//
//	    addUpdateCommonValidations(true);
//    }
//
//
//    @Test
//    @Transactional
//    public void updatePollWithAnswersTest() {
//        PollDTO pollDTO = pollService.findPollByUuidDTO("1111");
//        pollDTO.setName("test name");
//        pollDTO.setPortal(Portal.FEDERATED.getCode());
//        checkAddUpdatePoll(pollDTO, "Para actualizar no debe tener respuestas", IPollServiceErrorCode.POLL_HAS_ANSWERS, true);
//    }
//
//
//
//    public static PollDTO createPollDTO(Boolean anonynous, Boolean loginRequired, Boolean changeAllowed, InformerTypeCode informerTypeCode, Integer portal) {
//	    PollDTO pollDTO = new PollDTO();
//	    pollDTO.setName("Nombre poll test");
//	    pollDTO.setEndingTime(LocalDateTime.now().plusDays(30));
//	    pollDTO.setAnonymous(anonynous);
//	    pollDTO.setDescription("La descripcion de la encuesta de prueba");
//	    pollDTO.setLoginRequired(loginRequired);
//	    pollDTO.setChangeAnswerAllowed(changeAllowed);
//	    pollDTO.setInformerTypeId(2);
//	    pollDTO.setInformerCategories("CAT1, CAT2");
//	    pollDTO.setPortal(portal);
//	    
//	    return pollDTO;
//
//    }
//
//    private void addUpdateCommonValidations(Boolean update) {
//    	Portal portal = Portal.CLUB;
//        PollDTO pollDTO = createPollDTO(true, true, true, InformerTypeCode.CLUB, portal.getCode());
//
//        log.info("Test nombre obilgatorio");
//        pollDTO.setName(null);
//        checkAddUpdatePoll(pollDTO, "Name es obligatorio", IPollServiceErrorCode.MISSING_NAME, update);
//
//        pollDTO.setName("Poll test 1");
//        if (update) {
//            log.info("Test status obilgatorio");
//            pollDTO.setStatus(null);
//            checkAddUpdatePoll(pollDTO, "Status es obligatorio", IPollServiceErrorCode.MISSING_STATUS, update);
//        }
//
//        log.info("Test anonymous obilgatorio");
//        pollDTO.setStatus(PollStatus.EDITABLE);
//        pollDTO.setAnonymous(null);
//        checkAddUpdatePoll(pollDTO, "Anonymus es obligatorio", IPollServiceErrorCode.MISSING_ANONYMUS, update);
//
//        log.info("Test login required obilgatorio");
//        pollDTO.setAnonymous(true);
//        pollDTO.setLoginRequired(null);
//        checkAddUpdatePoll(pollDTO, "Login required es obligatorio", IPollServiceErrorCode.MISSING_LOGIN_REQUIRED, update);
//
//        log.info("Test change answer obilgatorio");
//        pollDTO.setLoginRequired(true);
//        pollDTO.setChangeAnswerAllowed(null);
//        checkAddUpdatePoll(pollDTO, "Change allowed es obligatorio", IPollServiceErrorCode.MISSING_CHANGE_ALLOWED, update);
//
//        log.info("Test ending time obilgatorio");
//        pollDTO.setChangeAnswerAllowed(true);
//        pollDTO.setEndingTime(null);
//        checkAddUpdatePoll(pollDTO, "Ending time es obligatorio", IPollServiceErrorCode.MISSING_ENDING_TIME, update);
//
//        log.info("Test informer type obilgatorio");
//        pollDTO.setEndingTime(LocalDateTime.now().plusDays(30));
//        pollDTO.setInformerTypeId(null);
//        checkAddUpdatePoll(pollDTO, "Informer type es obligatorio", IPollServiceErrorCode.MISSING_INFORMER_TYPE, update);
//
//        log.info("Test informer type categories obilgatorio");
//        pollDTO.setInformerTypeId(2);
//        pollDTO.setInformerCategories(null);
//        checkAddUpdatePoll(pollDTO, "Informer type es obligatorio", IPollServiceErrorCode.MISSING_INFORMER_TYPE_CATEGORIES, update);
//
//
//        pollDTO.setInformerCategories("CAT1, CAT2");
//        if (update) {
//            log.info("Test fecha activacion");
//            pollDTO.setActivationTime(LocalDateTime.now());
//            pollDTO.setEndingTime(LocalDateTime.now().minusDays(2));
//            checkAddUpdatePoll(pollDTO, "Activacion anterior a final", IPollServiceErrorCode.ACTIVATION_AFTER_ENDING, update);
//        }
//    }
//
//
//    private void checkAddUpdatePoll(PollDTO pollDTO, String description, String expectedErrorCode, Boolean update) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    if (update) {
//                        pollService.update(pollDTO);
//                    }
//                    else {
//                        pollService.add(pollDTO);
//                    }
//                })
//                .has(errorCode);
//    }
//
//
//    @Test
//    @Transactional
//    public void activationOkTest() {
//	    try {
// 	        String uuidTestUser = "uuid prueba activacion";
//
//            Poll poll = pollRepository.getOne(1);
//
//            // Preparar estado de la prueba
//            poll.setActivationTime(null);
//            poll.setEndingTime(LocalDateTime.now().plusDays(30));
//            pollRepository.saveAndFlush(poll);
//
//
//            pollService.activate("1111", Arrays.asList(uuidTestUser));
//
//            poll = pollRepository.getOne(1);
//            assertThat(poll.getActivationTime()).as("Activation time debe estar informado").isNotNull();
//            assertThat(poll.getStatus()).as("Debe ser no editable").isEqualTo(PollStatus.NOT_EDITABLE);
//
//            Form form = formRepository.findPollFormForUserFilled("1111", uuidTestUser);
//            assertThat(form).as("Debe haber creado el form").isNotNull();
//            assertThat(form.getStatus()).as("Debe estar pendiente").isEqualTo(FormStatus.PENDING);
//        }
//	    catch(Exception e) {
//	        fail("No debe lanzar excepcion ", e);
//        }
//    }
//
//    @Test
//    @Transactional
//    public void activationNOkTest() {
//        String uuidTestUser = "uuid prueba activacion";
//
//        Poll poll = pollRepository.getOne(1);
//
//
//        // Validar hay participantes
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IPollServiceErrorCode.NOT_ACTIVABLE_MISSING_PARTICIPANTS),
//                " Codigo error esperado " + IPollServiceErrorCode.NOT_ACTIVABLE_MISSING_PARTICIPANTS);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> pollService.activate("1111", null))
//                .has(errorCode);
//
//
//        // Validar no activado
//        errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IPollServiceErrorCode.NOT_ACTIVABLE_ALREADY_ACTIVE),
//                " Codigo error esperado " + IPollServiceErrorCode.NOT_ACTIVABLE_ALREADY_ACTIVE);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() ->             pollService.activate("1111", Arrays.asList(uuidTestUser)))
//                .has(errorCode);
//
//
//
//
//        // Validar fechas activacion y fin
//        poll.setActivationTime(null);
//        poll.setEndingTime(LocalDateTime.now().minusDays(10));
//        pollRepository.saveAndFlush(poll);
//        errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IPollServiceErrorCode.NOT_ACTIVABLE_WRONG_ENDING_TIME),
//                " Codigo error esperado " + IPollServiceErrorCode.NOT_ACTIVABLE_WRONG_ENDING_TIME);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() ->             pollService.activate("1111", Arrays.asList(uuidTestUser)))
//                .has(errorCode);
//
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void activationNOkQuestionWithAnswersTest() {
//
//        Poll poll = pollRepository.getOne(1);
//        poll.setActivationTime(null);
//        pollRepository.saveAndFlush(poll);
//
//        log.info("Validar que si la pregunta no admite respuestas, no tenga respuestas");
//        Question question = questionRepository.getOne(1);
//        question.setHasAnswer(false);
//        questionRepository.saveAndFlush(question);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS);
//
//
//        log.info("Validar que si la pregunta es de tipo open no debe tener respuestas");
//        question = questionRepository.findByUuid("AAA");
//        question.setHasAnswer(true);
//        question.setAnswerType(answerTypeRepository.getOne(6));
//        questionRepository.saveAndFlush(question);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void activationNOkQuestionWithMoreThan2AnswersTest() {
//
//        Poll poll = pollRepository.getOne(1);
//        poll.setActivationTime(null);
//        pollRepository.saveAndFlush(poll);
//
//        log.info("Validar que si la pregunta es binaria, solo tenga dos respuestas");
//        Question question = questionRepository.findByUuid("BBB");   // Esta pregunta tiene 3 opciones de respuesta
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
//        Poll poll = pollRepository.getOne(1);
//        poll.setActivationTime(null);
//        pollRepository.saveAndFlush(poll);
//
//        log.info("Validar que si la pregunta es numeric los valores de respuesta sean numericos");
//        AnswerOption answerOption = answerOptionRepository.getOne(9);
//        answerOption.setValue("AAA");
//        answerOptionRepository.saveAndFlush(answerOption);
//
//        executeActivationWrongQuestions(IAnswerOptionServiceErrorCode.VALUE_MUST_BE_NUMERIC);
//
//
//        log.info("Validar que si la pregunta es numeric, solo tenga dos respuestas");
//        answerOption.setValue("1");
//        answerOptionRepository.saveAndFlush(answerOption);
//        answerOption = answerOptionRepository.getOne(10);
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
//                .isThrownBy(() -> pollService.activate("1111", Arrays.asList(uuidTestUser)))
//                .has(errorCode)
//                .has(errorCause);
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void deleteTest() {
//	    try {
//            pollService.delete("1111");
//
//            Poll poll = pollRepository.getOne(1);
//
//            assertThat(poll.getDeleted()).as("Debe estar informado").isNotNull();
//
//            // Validar que se han borrado todos los bloques
//            Block blockNotDeleted = poll.getBlocks().stream()
//                    .filter(block -> block.getDeleted() == null)
//                    .findFirst()
//                    .orElse(null);
//
//            assertThat(blockNotDeleted).as("No debe quedar ningun bloque sin borrar").isNull();
//
//
//            // Validar que se han borrado todas las preguntas
//            Set<Question> questions = new HashSet<>();
//            poll.getBlocks().forEach(block -> questions.addAll(block.getQuestions()));
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
//        }
//	    catch(Exception e) {
//	        fail("No debe lanzar excepcion ", e);
//        }
//    }
//
//
//    @Test
//    @Transactional
//    public void cloneTest() {
//        Poll originalPoll = pollRepository.getOne(1);
//
//        long numBlocksBefore = blockRepository.count();
//        long numQuestionsBefore = questionRepository.count();
//
//        PollDTO cloendPoll = pollService.clone(originalPoll.getUuid());
//
//        long numBlocksAfter = blockRepository.count();
//        long numQuestionsAfter = questionRepository.count();
//
//
//        assertThat(numBlocksAfter)
//                .as("Debe incrementarse en el num de bloques del original")
//                .isEqualTo(numBlocksBefore + originalPoll.getBlocks().size());
//
//        assertThat(cloendPoll.getId()).as("Id Debe ser distinto").isNotEqualTo(originalPoll.getId());
//        assertThat(cloendPoll.getUuid()).as("Uuid Debe ser distinto").isNotEqualTo(originalPoll.getUuid());
//
//        // Verificar que se copian todas las preguntas
//        AtomicInteger numQuestionsOriginal = new AtomicInteger(0);
//        originalPoll.getBlocks().forEach(block -> numQuestionsOriginal.addAndGet(block.getQuestions().size()));
//
//        assertThat(numQuestionsAfter)
//                .as("Debe inrementarse en tantas preguntas tiene el original")
//                .isEqualTo(numQuestionsBefore + numQuestionsOriginal.get());
//
//
//
//    }
//
//
//
//}
