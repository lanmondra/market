//package cat.owc.ms.reports.controllers.auth.manager;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.ReportCategoryDTO;
//import cat.owc.ms.reports.entity.Permission;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.permissions.PermissionHandler;
//import cat.owc.ms.reports.services.IReportCategoryServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IPermissionService;
//import cat.owc.ms.reports.services.interfaces.IReportCategoryService;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth/manager/report-categories")
//@Api(tags = {"manager/report-categories"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerReportCategoryController extends AbstractController {
//
//    private final IReportCategoryService reportCategoryService;
//    private final IPermissionService permissionService;
//    private final PermissionHandler permissionHandler;
//
//    public ManagerReportCategoryController(IReportCategoryService reportCategoryService,
//                                           IPermissionService permissionService,
//                                           PermissionHandler permissionHandler) {
//
//        this.reportCategoryService = reportCategoryService;
//        this.permissionService = permissionService;
//        this.permissionHandler = permissionHandler;
//    }
//
//
//
//    private static final String COMMON_ERROR_CODES_400 = "| codigo | descripcion |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IReportCategoryServiceErrorCode.MISSING_NAME + " | Falta informar el nombre de la categoria |\n";
//
//
//    /**
//     * Devuelve la lista de categorias que administra el usuario
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de categorias que administra el usuario ",
//            response = ReportCategoryDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de categorias solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido :\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permisos para acceder |\n")
//    })
//    @GetMapping
//    @PreAuthorize("@permissionHandler.hasReportCategoryPermissions()")
//    public ResponseEntity<List<ReportCategoryDTO>> getReportCategories(@AuthenticationPrincipal JWTUser user) {
//
//        List<Integer> categoryIds = permissionService.getCategoryIdsForPermissions(permissionHandler.getUserCategoryAuthorities());
//
//        return ResponseEntity.ok(reportCategoryService.findByIdInList(categoryIds));
//    }
//
//
//
//
//    /**
//     * Devuelve la categoria solicitada
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la categoria solicitada", response = ReportCategoryDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La categoria solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a la categoria por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la información |\n" +
//                    "| " + IReportCategoryServiceErrorCode.CATEGORY_NOT_BELONGS_TO_FEDE + " | La categoria no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportCategoryServiceErrorCode.CATEGORY_NOT_FOUND + " | No existe una categoria con el uuid indicado |\n")
//    })
//    @GetMapping("/{categoryUuid}")
//    @PreAuthorize("@permissionHandler.canAccessCategory(#categoryUuid)")
//    public ResponseEntity<ReportCategoryDTO> getReportCategory(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(value = "Uuid de la categoria", required = true) @PathVariable String categoryUuid) {
//
//        return ResponseEntity.ok(reportCategoryService.findOneByUuidDTO(categoryUuid));
//    }
//
//
//
//
//
//    /**
//     * Añade una categoria
//     * @param user
//     * @param reportCategoryDTO
//     * @return
//     */
//    @ApiOperation(value = "Añade una nueva categoria", response = ReportCategoryDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Categoria añadida correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                                              COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la información |\n")
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.hasPermission('" + Permission.ADMIN + "')")
//    public ResponseEntity<ReportCategoryDTO> addCategory(@AuthenticationPrincipal JWTUser user,
//                                           @RequestBody ReportCategoryDTO reportCategoryDTO) {
//
//        return ResponseEntity.ok(reportCategoryService.add(reportCategoryDTO));
//    }
//
//
//
//    /**
//     * Modifica una categoria
//     * @param user
//     * @param reportCategoryDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una categoria", response = ReportCategoryDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Categoria modificada correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                    COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la información |\n" +
//                    "| " + IReportCategoryServiceErrorCode.CATEGORY_NOT_BELONGS_TO_FEDE + " | La categoria no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportCategoryServiceErrorCode.CATEGORY_NOT_FOUND + " | No existe una categoria con el uuid indicado |\n")
//
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canEditCategory(#reportCategoryDTO.getUuid())")
//    public ResponseEntity<ReportCategoryDTO> updateCategory(@AuthenticationPrincipal JWTUser user,
//                                              @ApiParam(required = true) @RequestBody ReportCategoryDTO reportCategoryDTO) {
//
//        return ResponseEntity.ok(reportCategoryService.update(reportCategoryDTO));
//    }
//
//
//
//
//    /**
//     * Elimina una categoria
//     * @param user
//     * @param categoryUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una categoria", response = Void.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Categoria eliminada correctamente"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la información |\n" +
//                    "| " + IReportCategoryServiceErrorCode.CATEGORY_NOT_BELONGS_TO_FEDE + " | La categoria no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportCategoryServiceErrorCode.CATEGORY_NOT_FOUND + " | No existe una categoria con el uuid indicado |\n")
//
//    })
//
//    @DeleteMapping("/{categoryUuid}")
//    @PreAuthorize("@permissionHandler.canEditCategory(#categoryUuid)")
//    public ResponseEntity<Void> deleteCategory(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(required = true) @PathVariable String categoryUuid) {
//
//        reportCategoryService.delete(categoryUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//}
