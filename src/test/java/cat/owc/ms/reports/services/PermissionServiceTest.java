//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.dto.PermissionDTO;
//import cat.owc.ms.reports.entity.Permission;
//import cat.owc.ms.reports.services.interfaces.IPermissionService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class PermissionServiceTest extends AbstractTest {
//	@Autowired
//	private IPermissionService permissionService;
//
//
//
//	@Test
//    @Transactional
//    public void findReportPermissionsTest() {
//		List<PermissionDTO> permissions = permissionService.findReportPermissionsDTO("AAAA");
//
//		List<String> reportPermissions = permissions.stream().map(PermissionDTO::getName).collect(Collectors.toList());
//
//		assertThat(permissions).as("No debe ser nulo").isNotNull();
//		assertThat(permissions).as("No debe ser vacio").isNotEmpty();
//		assertThat(reportPermissions).as("Debe contener los permisos de lectura y escritura de la categoria 1")
//				.containsExactlyInAnyOrder(Permission.getNameForCategory(1, true), Permission.getNameForCategory(1, false));
//
//	}
//
//
//
//
//}
