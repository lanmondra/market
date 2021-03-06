//package cat.owc.ms.reports.controllers.auth.manager;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.ReportTemplateValorationDTO;
//import cat.owc.ms.reports.services.interfaces.IReportTemplateValorationService;
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
//import static cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT;
//import static cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE;
//import static cat.owc.ms.reports.services.IReportTemplateServiceErrorCode.RT_NOT_FOUND;
//import static cat.owc.ms.reports.services.IReportTemplateValorationServiceErrorCode.*;
//
//@RestController
//@RequestMapping("/auth/manager/report-templates/valorations")
//@Api(tags = {"manager/report-templates/valorations"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerReportTemplateValorationsController extends AbstractController {
//
//    private final IReportTemplateValorationService rtValorationService;
//
//    public ManagerReportTemplateValorationsController(IReportTemplateValorationService rtValorationService) {
//        this.rtValorationService = rtValorationService;
//    }
//
//
//
//    private static final String COMMON_ERROR_CODES_400 = "| codigo | descripcion |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + RT_NOT_FOUND + " | Plantilla de informe no encontrada |\n" +
//            "| " + MISSING_NAME + " | Falta informar el nombre de la valoraci??n |\n" +
//            "| " + MISSING_MIN_GRADE + " | Falta informar la nota m??nima |\n" +
//            "| " + MISSING_MAX_GRADE + " | Falta informar la nota m??xima |\n" +
//            "| " + DUPLICATED_VALUE + " | Valor duplicado en otra valoraci??n |\n" +
//            "| " + MIN_GRADE_OVER_MAX + " | M??nimo mayor que el m??ximo |\n";
//
//
//    /**
//     * Devuelve la lista de valoraciones de una plantilla
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de valoraciones de una plantilla de informe ",
//            response = ReportTemplateValorationDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de valoraciones"),
//            @ApiResponse(code = 401, message = "Acceso no permitido :\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federaci??n |\n" +
//                    "| " + NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permisos par acceder a la plantilla |\n")
//    })
//    @GetMapping
//    @PreAuthorize("@permissionHandler.canAccessReport(#reportTemplateUuid)")
//    public ResponseEntity<List<ReportTemplateValorationDTO>> getValorations(@AuthenticationPrincipal JWTUser user,
//                                                                            @ApiParam(required = true) @RequestParam String reportTemplateUuid) {
//
//        return ResponseEntity.ok(rtValorationService.findAllByReportTemplateDTO(reportTemplateUuid));
//    }
//
//
//
//
//    /**
//     * A??ade una valoracion
//     * @param user
//     * @param
//     * @return
//     */
//    @ApiOperation(value = "A??ade una nueva valoraci??n", response = ReportTemplateValorationDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Valoraci??n a??adida correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petici??n:\n\n" +
//                                              COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido :\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federaci??n |\n" +
//                    "| " + NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permisos par acceder a la plantilla |\n")
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.canEditReport(#reportTemplateValorationDTO.reportTemplateId)")
//    public ResponseEntity<ReportTemplateValorationDTO> addValoration(@AuthenticationPrincipal JWTUser user,
//                                                                     @ApiParam(required = true) @RequestBody ReportTemplateValorationDTO valorationDTO) {
//
//        return ResponseEntity.ok(rtValorationService.add(valorationDTO));
//    }
//
//
//
//    /**
//     * Modifica una valoracion
//     * @param user
//     * @param reportTemplateValorationDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una valoraci??n", response = ReportTemplateValorationDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Valoraci??n modificada correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petici??n:\n\n" +
//                    COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido :\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federaci??n |\n" +
//                    "| " + NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permisos par acceder a la plantilla |\n")
//
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canEditReport(#reportTemplateValorationDTO.reportTemplateId)")
//    public ResponseEntity<ReportTemplateValorationDTO> updateValoration(@AuthenticationPrincipal JWTUser user,
//                                                                      @ApiParam(required = true) @RequestBody ReportTemplateValorationDTO reportTemplateValorationDTO) {
//
//        return ResponseEntity.ok(rtValorationService.update(reportTemplateValorationDTO));
//    }
//
//
//
//
//    /**
//     * Elimina una valoraci??n
//     * @param user
//     * @param valorationUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una valoracion")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Valoraci??n eliminada correctamente"),
//
//            @ApiResponse(code = 400, message = "Error en la petici??n:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + NOT_FOUND + " | Valoraci??n no encontrada |\n"),
//
//            @ApiResponse(code = 401, message = "Acceso no permitido :\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federaci??n |\n" +
//                    "| " + NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permisos par acceder a la plantilla |\n")
//    })
//
//    @DeleteMapping("/{valorationUuid}")
//    @PreAuthorize("@permissionHandler.canEditValoration(#valorationUuid)")
//    public ResponseEntity<Void> deleteValoration(@AuthenticationPrincipal JWTUser user,
//                                               @ApiParam(required = true) @PathVariable String valorationUuid) {
//
//        rtValorationService.delete(valorationUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//}
