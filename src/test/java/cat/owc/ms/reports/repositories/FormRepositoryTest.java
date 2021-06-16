//package cat.owc.ms.reports.repositories;
//
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.repository.FormRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = { OwcReportsApplication.class })
//@Transactional
//public class FormRepositoryTest extends AbstractTest {
//
//    @Autowired
//    private FormRepository formRepository;
//
//    @Test
//    public void countActiveFormsTest() {
//    	Portal portal = Portal.REFEREE;
//    	
//        // Verificar que no devuelve ni encuestas acabadas ni de otro tipo de informer
//        Integer active = formRepository.countActivePollForms(ClientContextHolder.getFederation(), "508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB , portal);
//        assertThat(active).as("Debe tener 2").isEqualTo(2);
//
//        // Verificar que no devuelve formulareios acabados
//        active = formRepository.countActivePollForms(ClientContextHolder.getFederation(), "508ad6df-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB , portal);
//        assertThat(active).as("Debe tener 1").isEqualTo(1);
//
//
//        // Verificar que no devuelve los del portal que no toca
//        portal = Portal.CLUB;
//        active = formRepository.countActivePollForms(ClientContextHolder.getFederation(), "508952c9-8fa1-11ea-b836-02b7c2952a14", InformerTypeCode.CLUB , portal);
//        assertThat(active).as("Debe ser 0").isEqualTo(0);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void findAllPollFormsInStatusTest() {
//
//        // Poner varios estados para probar
//        //Form testForm = formRepository.getOne(1);
//        //testForm.setStatus(FormStatus.ENDED);
//        //formRepository.saveAndFlush(testForm);
//
//        List<FormStatus> formStatus = Arrays.asList(FormStatus.ENDED, FormStatus.STARTED);
//        List<Form> forms = formRepository.findAllPollFormsWithStatusFilled(1, formStatus);
//
//        List<Integer> formsIds = forms.stream().map(form -> form.getId()).collect(Collectors.toList());
//
//        assertThat(forms).as("No debe ser null").isNotNull();
//        assertThat(formsIds).as("Debe tener los forms 1 y 5").containsExactlyInAnyOrder(1, 5);
//
//
//
//        formStatus = Arrays.asList(FormStatus.ENDED);
//        forms = formRepository.findAllPollFormsWithStatusFilled(1, formStatus);
//
//        formsIds = forms.stream().map(form -> form.getId()).collect(Collectors.toList());
//
//        assertThat(forms).as("No debe ser null").isNotNull();
//        assertThat(formsIds).as("Debe tener los forms 5").containsExactlyInAnyOrder(5);
//    }
//
//    @Test
//    @Transactional
//    public void findFormByUuidDTOTest() {
//    	
//    	FormDTO form = formRepository.findFormByUuidDTO("1111");
//    	assertThat(form).isNotNull();
//    }
//
//
//}
