//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.CampaignDTO;
//import cat.owc.ms.reports.entity.Campaign;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.CampaignMapper;
//import cat.owc.ms.reports.repository.CampaignRepository;
//import cat.owc.ms.reports.services.interfaces.ICampaignService;
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
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class CampaignServiceTest extends AbstractTest {
//
//    private static final int ACTION_ADD = 0;
//    private static final int ACTION_UPDATE = 1;
//    private static final int ACTION_DELETE = 2;
//
//
//    @Autowired
//    private EntityManager entityManager;
//
//	@Autowired
//    private ICampaignService campaignService;
//
//	@Autowired
//    private CampaignRepository campaignRepository;
//
//	@Autowired
//    private CampaignMapper campaignMapper;
//
//
//
//
//	@Test
//    @Transactional
//    public void getCampaignListTest() {
//        List<CampaignDTO> campaigns = campaignService.findByCategoryIdList(Arrays.asList(1));
//
//        assertThat(campaigns).as("No debe ser null").isNotNull();
//        assertThat(campaigns).as("Debe tener dos elementos").hasSize(2);
//    }
//
//
//    @Test
//    @Transactional
//    public void addCampaignTest() {
//       CampaignDTO campaignDTO = createCampaign();
//
//        long numBefore = campaignRepository.count();
//        CampaignDTO newCampaignDTO = campaignService.add(campaignDTO);
//        long numAfter = campaignRepository.count();
//
//        assertThat(numAfter).as("Debe incrementarse en uno").isEqualTo(numBefore + 1);
//        assertThat(newCampaignDTO.getId()).as("Debe tener id").isNotNull();
//        assertThat(newCampaignDTO.getUuid()).as("Debe tener uuid").isNotNull();
//        assertThat(newCampaignDTO.getName()).as("Debe tener el mismo nombre").isEqualTo(campaignDTO.getName());
//
//    }
//
//
//    @Test
//    @Transactional
//    public void addCampaignWithErrorsTest() {
//        CampaignDTO campaignDTO = createCampaign();
//
//        addUpdateCommonValidations(campaignDTO, ACTION_ADD);
//
//    }
//
//
//    private void addUpdateCommonValidations(CampaignDTO campaignDTO, int action) {
//	    campaignDTO.setName(null);
//        checkAddUpdateCategory(campaignDTO, "Nombre es obligatorio", ICampaignServiceErrorCode.MISSING_NAME, action);
//
//        campaignDTO.setName("TEST CAMPAIGN");
//        campaignDTO.setStartDate(null);
//        checkAddUpdateCategory(campaignDTO,  "Fecha inicio campaña es obligatorio",ICampaignServiceErrorCode.MISSING_START_DATE, action);
//
//        campaignDTO.setStartDate(LocalDate.now());
//        campaignDTO.setEndDate(null);
//        checkAddUpdateCategory(campaignDTO,  "Fecha fin campaña es obligatorio",ICampaignServiceErrorCode.MISSING_END_DATE, action);
//
//        campaignDTO.setEndDate(LocalDate.now().plusDays(4));
//        campaignDTO.setReportTemplateId(null);
//        checkAddUpdateCategory(campaignDTO,  "Id plantilla informe es obligatorio",ICampaignServiceErrorCode.MISSING_REPORT_TEMPLATE, action);
//
//    }
//
//
//
//    private CampaignDTO createCampaign() {
//	    CampaignDTO campaignDTO = new CampaignDTO();
//	    campaignDTO.setName("TEST CAMPAIGN");
//	    campaignDTO.setReportTemplateId(1);
//	    campaignDTO.setStartDate(LocalDate.now());
//        campaignDTO.setEndDate(LocalDate.now().plusDays(5));
//
//	    return campaignDTO;
//    }
//
//
//    private void checkAddUpdateCategory(CampaignDTO campaignDTO, String description, String expectedErrorCode, int action) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    switch (action) {
//                        case ACTION_ADD:
//                            campaignService.add(campaignDTO);
//                            break;
//
//                        case ACTION_UPDATE:
//                            campaignService.update(campaignDTO);
//                            break;
//
//                        case ACTION_DELETE:
//                            campaignService.delete(campaignDTO.getUuid());
//                            break;
//                    }
//                })
//                .has(errorCode);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateCampaignTest() {
//	    String newName = "UPDATED NAME";
//	    Campaign campaign = campaignRepository.getOne(1);
//        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
//        campaignDTO.setName(newName);
//
//
//        long numBefore = campaignRepository.count();
//        CampaignDTO updatedReportCategoryDTO = campaignService.update(campaignDTO);
//        long numAfter = campaignRepository.count();
//
//        assertThat(numAfter).as("No debe incrementarse en uno").isEqualTo(numBefore);
//        assertThat(updatedReportCategoryDTO.getId()).as("El id no debe cambiar").isEqualTo(campaignDTO.getId());
//        assertThat(updatedReportCategoryDTO.getUuid()).as("El uuid no debe cambiar").isEqualTo(campaignDTO.getUuid());
//        assertThat(updatedReportCategoryDTO.getName()).as("Debe ser el modificado").isEqualTo(newName);
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void updateCampaignWithErrorsTest() {
//        Campaign campaign = campaignRepository.getOne(1);
//        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
//        //campaignRepository.saveAndFlush(campaign);
//
//
//        addUpdateCommonValidations(campaignDTO, ACTION_UPDATE);
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteCampaignTest() {
//
//
//        campaignService.delete("1111");
//
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND),
//                  " Expected error code " + ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> campaignService.findByUuid("1111"))
//                .has(errorCode);
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteCampaignWithErrorsTest() {
//
//        Campaign campaign = campaignRepository.getOne(1);
//        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
//        campaignDTO.setUuid("kkk");
//        checkAddUpdateCategory(campaignDTO, "No debe encontrarlo", ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND, ACTION_DELETE);
//    }
//
//
//
//
//
//}
