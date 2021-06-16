//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.dto.PermissionDTO;
//import cat.owc.ms.reports.dto.ReportCategoryDTO;
//import cat.owc.ms.reports.entity.Permission;
//import cat.owc.ms.reports.entity.ReportCategory;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.PermissionMapper;
//import cat.owc.ms.reports.mapper.ReportCategoryMapper;
//import cat.owc.ms.reports.repository.PermissionRepository;
//import cat.owc.ms.reports.repository.ReportCategoryRepository;
//import cat.owc.ms.reports.services.interfaces.IReportCategoryService;
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
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
//public class ReportCategoryServiceTest extends AbstractTest {
//
//    private static final int ACTION_ADD = 0;
//    private static final int ACTION_UPDATE = 1;
//    private static final int ACTION_DELETE = 2;
//
//    @Autowired
//    private EntityManager entityManager;
//
//	@Autowired
//    private IReportCategoryService reportCategoryService;
//
//	@Autowired
//    private ReportCategoryRepository reportCategoryRepository;
//
//	@Autowired
//    private ReportCategoryMapper reportCategoryMapper;
//
//
//	@Autowired
//    private PermissionRepository permissionRepository;
//
//	@Autowired
//    private PermissionMapper permissionMapper;
//
//
//
//
//
//	@Test
//    @Transactional
//    public void findByUuidTest() {
//        ReportCategoryDTO reportCategoryDTO = reportCategoryService.findOneByUuidDTO("AAAA");
//
//        assertThat(reportCategoryDTO).as("No debe ser null").isNotNull();
//        assertThat(reportCategoryDTO.getId()).as("Debe tener id 1").isEqualTo(1);
//    }
//
//
//    @Test
//    @Transactional
//    public void findAllByIdListTest() {
//        List<ReportCategoryDTO> categories = reportCategoryService.findByIdInList(Arrays.asList(1, 2));
//
//        assertThat(categories).as("No debe ser null").isNotNull();
//        assertThat(categories).as("Debe tener dos elementos").hasSize(2);
//    }
//
//
//    @Test
//    @Transactional
//    public void addCategoryTest() {
//        ReportCategoryDTO reportCategoryDTO = createReportCategory();
//
//        long numCategoryBefore = reportCategoryRepository.count();
//        long numPermissionBefore = permissionRepository.count();
//        reportCategoryDTO = reportCategoryService.add(reportCategoryDTO);
//        long numCategoryAfter = reportCategoryRepository.count();
//        long numPermissionAfter = permissionRepository.count();
//
//        assertThat(numCategoryAfter).as("Debe incrementarse en uno").isEqualTo(numCategoryBefore + 1);
//        assertThat(numPermissionAfter).as("Debe incrementarse en dos").isEqualTo(numPermissionBefore + 2);
//        assertThat(reportCategoryDTO.getId()).as("Debe tener id").isNotNull();
//        assertThat(reportCategoryDTO.getUuid()).as("Debe tener uuid").isNotNull();
//        assertThat(reportCategoryDTO.getFederation()).as("Debe tener federacion").isEqualTo(ClientContextHolder.getFederation());
//
//        // Validar que se han creado los permisos asociados a la categoria
//        String federation = ClientContextHolder.getFederation();
//        String editPermissionName = Permission.getNameForCategory(reportCategoryDTO.getId(), true);
//        String readPermissionName = Permission.getNameForCategory(reportCategoryDTO.getId(), false);
//        Permission editPermission = permissionRepository.findOneByNameAndFederation(editPermissionName, federation);
//        Permission readPermission = permissionRepository.findOneByNameAndFederation(readPermissionName, federation);
//
//        assertThat(editPermission).as("Debe haber un permiso de edicion").isNotNull();
//        assertThat(editPermission.getCanEdit()).as("Debe tener permiso de edicion").isTrue();
//        assertThat(readPermission).as("Debe haber un permiso de lectura").isNotNull();
//        assertThat(readPermission.getCanEdit()).as("No Debe tener permiso de edicion").isFalse();
//
//
//    }
//
//
//    @Test
//    @Transactional
//    public void addCategoryWithErrorsTest() {
//	    ReportCategoryDTO reportCategoryDTO = new ReportCategoryDTO();
//	    reportCategoryDTO.setName(null);
//
//	    checkAddUpdateCategory(reportCategoryDTO, "Falta informar el nombre", IReportCategoryServiceErrorCode.MISSING_NAME, ACTION_ADD);
//
//    }
//
//
//
//    private ReportCategoryDTO createReportCategory() {
//	    ReportCategoryDTO reportCategoryDTO = new ReportCategoryDTO();
//	    reportCategoryDTO.setName("TEST CATEGORY");
//
//	    return reportCategoryDTO;
//    }
//
//
//
//    public PermissionDTO createPermission(String name, Boolean canEdit) {
//        Permission permission = new Permission();
//        permission.setName(name);
//        permission.setCanEdit(canEdit);
//        permission.setFederation(ClientContextHolder.getFederation());
//
//        permission = permissionRepository.saveAndFlush(permission);
//        return permissionMapper.toDto(permission);
//    }
//
//
//
//    private void checkAddUpdateCategory(ReportCategoryDTO reportCategoryDTO, String description, String expectedErrorCode, int action) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    switch (action) {
//                        case ACTION_ADD:
//                            reportCategoryService.add(reportCategoryDTO);
//                            break;
//
//                        case ACTION_UPDATE:
//                            reportCategoryService.update(reportCategoryDTO);
//                            break;
//
//                        case ACTION_DELETE:
//                            reportCategoryService.delete(reportCategoryDTO.getUuid());
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
//    public void updateCategoryTest() {
//	    String newName = "UPDATED NAME";
//	    ReportCategory reportCategory = reportCategoryRepository.getOne(1);
//        ReportCategoryDTO reportCategoryDTO = reportCategoryMapper.toDto(reportCategory);
//        reportCategoryDTO.setName(newName);
//
//
//        long numCategoryBefore = reportCategoryRepository.count();
//        long numPermissionBefore = permissionRepository.count();
//        ReportCategoryDTO updatedReportCategoryDTO = reportCategoryService.update(reportCategoryDTO);
//        long numCategoryAfter = reportCategoryRepository.count();
//        long numPermissionAfter = permissionRepository.count();
//
//        assertThat(numCategoryAfter).as("No debe incrementarse en uno").isEqualTo(numCategoryBefore );
//        assertThat(numPermissionAfter).as("No debe incrementarse el número de permisos").isEqualTo(numPermissionBefore );
//        assertThat(updatedReportCategoryDTO.getId()).as("El id no debe cambiar").isEqualTo(reportCategoryDTO.getId());
//        assertThat(updatedReportCategoryDTO.getUuid()).as("El uuid no debe cambiar").isEqualTo(reportCategoryDTO.getUuid());
//        assertThat(updatedReportCategoryDTO.getName()).as("Debe ser el modificado").isEqualTo(newName);
//
//    }
//
//
//
//
//    @Test
//    @Transactional
//    public void updateCategoryWithErrorsTest() {
//        ReportCategory reportCategory = reportCategoryRepository.getOne(1);
//        ReportCategoryDTO reportCategoryDTO = reportCategoryMapper.toDto(reportCategory);
//        Integer categoryId = reportCategoryDTO.getId();
//
//        reportCategory.setFederation("KKKK");
//        reportCategoryRepository.saveAndFlush(reportCategory);
//        checkAddUpdateCategory(reportCategoryDTO, "La federacion no debe coincidir", IReportCategoryServiceErrorCode.CATEGORY_NOT_BELONGS_TO_FEDE, ACTION_UPDATE);
//
//
//        reportCategory.setFederation(ClientContextHolder.getFederation());
//        reportCategoryRepository.saveAndFlush(reportCategory);
//        reportCategoryDTO.setName(null);
//        checkAddUpdateCategory(reportCategoryDTO, "Falta informar el nombre", IReportCategoryServiceErrorCode.MISSING_NAME, ACTION_UPDATE);
//
//        reportCategoryDTO.setId(9999);
//        checkAddUpdateCategory(reportCategoryDTO, "No debe encontrarlo", IReportCategoryServiceErrorCode.CATEGORY_NOT_FOUND, ACTION_UPDATE);
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteCategoryTest() {
//	    List<Permission> permissions = permissionRepository.findAllByReportCategoryId(1);
//	    reportCategoryService.delete("AAAA");
//
//	    ReportCategory reportCategory = reportCategoryRepository.getOne(1);
//	    assertThat(reportCategory.getDeleted()).as("No debe ser null").isNotNull();
//
//	    permissions.forEach(permission -> entityManager.refresh(permission));
//	    long notDeletedPermissions = permissions.stream().filter(permission -> permission.getDeleted() != null).count();
//	    assertThat(notDeletedPermissions).as("Debe ser cero").isEqualTo(0);
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteCategoryWithErrorsTest() {
//
//        ReportCategory reportCategory = reportCategoryRepository.getOne(1);
//        reportCategory.setFederation("KKKK");
//        reportCategoryRepository.saveAndFlush(reportCategory);
//        ReportCategoryDTO reportCategoryDTO = new ReportCategoryDTO();
//        reportCategoryDTO.setUuid("AAAA");
//        checkAddUpdateCategory(reportCategoryDTO, "La federacion no debe coincidir", IReportCategoryServiceErrorCode.CATEGORY_NOT_BELONGS_TO_FEDE, ACTION_DELETE);
//
//
//        reportCategory.setFederation(ClientContextHolder.getFederation());
//        reportCategoryRepository.saveAndFlush(reportCategory);
//        reportCategoryDTO.setUuid("ZZZZ");
//        checkAddUpdateCategory(reportCategoryDTO, "No debe encontrarlo", IReportCategoryServiceErrorCode.CATEGORY_NOT_FOUND, ACTION_DELETE);
//
//    }
//
//
//
//
//
//}
