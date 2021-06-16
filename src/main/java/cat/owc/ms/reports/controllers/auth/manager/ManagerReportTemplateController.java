//package cat.owc.ms.reports.controllers.auth.manager;
//
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.MatchReportTypeDTO;
//import cat.owc.ms.reports.dto.ReportTemplateColorsDTO;
//import cat.owc.ms.reports.dto.ReportTemplateDTO;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.entity.enumeration.ReportTemplateStatus;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.permissions.PermissionHandler;
//import cat.owc.ms.reports.services.IBlockServiceErrorCode;
//import cat.owc.ms.reports.services.IFormServiceErrorCode;
//import cat.owc.ms.reports.services.IReportTemplateServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.*;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.ms.reports.utils.ReportUtils;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth/manager/report-templates")
//@Api(tags = {"manager/report-templates"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerReportTemplateController extends AbstractController {
//
//    private final IReportTemplateService reportTemplateService;
//    private final IPermissionService permissionService;
//    private final PermissionHandler permissionHandler;
//    private final IMatchReportTypeService matchReportTypeService;
//    private final IFormService formService;
//    private final IReportTemplateColorsService reportTemplateColorsService;
//
//    public ManagerReportTemplateController(IReportTemplateService reportTemplateService,
//                                           IPermissionService permissionService,
//                                           PermissionHandler permissionHandler,
//                                           IMatchReportTypeService matchReportTypeService,
//                                           IFormService formService ,
//                                           IReportTemplateColorsService reportTemplateColorsService) {
//            this.reportTemplateService = reportTemplateService;
//            this.permissionService = permissionService;
//            this.permissionHandler = permissionHandler;
//            this.matchReportTypeService = matchReportTypeService;
//            this.formService = formService;
//            this.reportTemplateColorsService = reportTemplateColorsService;
//    }
//
//
//    private static final String COMMON_ERROR_CODES_400 = "| codigo | descripcion |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_NAME + " | Falta informar el nombre de la plantilla |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_IS_VISIBLE + " | Falta informar si la plantilla es visible o no |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_SHOW_REFEREE + " | Falta informar si se debe mostrar al árbitro o no |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_PORTAL + " | Falta informar el portal la que pertenece la plantilla |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_ALLOW_NOT_DESIGNATED + " | Falta informar si se permite para no designados |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_IS_MATCH_REPORT + " | Falta informar si es una plantilla de informe de partido |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_INFORMER_TYPE + " | Falta informar el tipo de informador |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_SUBJECT_TYPE + " | Falta informar el tipo de informado  |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_REPORT_CATEGORY + " | Falta informar la categoria el informe  |\n" +
//            "| " + IReportTemplateServiceErrorCode.MISSING_MATCH_REPORT_TYPE + " | Falta informar el tipo de informe de partido  |\n" +
//            "| " + IReportTemplateServiceErrorCode.RT_PORTAL_NOT_ALLOWED + " | El portal indicado no existe  |\n" +
//            "| " + IReportTemplateServiceErrorCode.RT_WITH_NAME_EXISTS + " | Ya existe un informe con ese nombre  |\n";
//    //add portal para usarlo en el servicio
//
//    /**
//     * Devuelve la lista de plantillas de informe que administra el usuario
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de plantillas de informe que administra el usuario. De cada plantilla solo devuelve los datos basicos." +
//            "Para acceder a la información detallada de bloques, preguntas,... debe recuperarse cada una de las plantillas de manera individual " +
//            "Devuelve todas las plantillas, solo las visibles o solo las no visibles en función del parametro (null, true, false)",
//            response = ReportTemplateDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de plantillas de informe solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder  |\n")
//    })
//    @GetMapping
//    @PreAuthorize("@permissionHandler.hasReportCategoryPermissions()")
//    public ResponseEntity<List<ReportTemplateDTO>> getReportTemplates(@AuthenticationPrincipal JWTUser user,
//                                                                      @ApiParam @RequestParam(required = false) ReportTemplateStatus status,                                                                 
//                                                                 @ApiParam @RequestParam(required = false) Boolean onlyVisible) {
//   
//   
//        Portal portal=	ReportUtils.getPortal();
//    	
//        List<Integer> categoryIds = permissionService.getCategoryIdsForPermissions(permissionHandler.getUserCategoryAuthorities());
//        return ResponseEntity.ok(reportTemplateService.findByCategoryIdListDTO(categoryIds, status, onlyVisible, portal));
//    }
//
//
//
//    /**
//     * Devuelve la plantilla de informe solicitada
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la plantilla de informe solicitada",
//            response = ReportTemplateDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La plantilla solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la feeración |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.RT_NOT_FOUND + " | No existe la plantilla indicada |\n")
//    })
//    @GetMapping("/{reportTemplateUuid}")
//    @PreAuthorize("@permissionHandler.canAccessReport(#reportTemplateUuid)")
//    public ResponseEntity<ReportTemplateDTO> getReportTemplate(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(value = "Uuid de la plantilla", required = true) @PathVariable String reportTemplateUuid) {
//
//        return ResponseEntity.ok(reportTemplateService.findReportTemplateByUuidFilledDTO(reportTemplateUuid));
//    }
//
//
//
//
//    /**
//     * Añade una plantilla d'informe
//     * @param user
//     * @param reportTemplateDTO
//     * @return
//     */
//    @ApiOperation(value = "Añade una nueva plantilla de informe", response = ReportTemplateDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Plantilla añadida correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                                              COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para modificación en la categoria de informe  |\n")
//
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.canEditCategory(#reportTemplateDTO.getReportCategoryId())")
//    public ResponseEntity<ReportTemplateDTO> addReportTemplate(@AuthenticationPrincipal JWTUser user,
//                                                     @RequestBody ReportTemplateDTO reportTemplateDTO) {
//        return ResponseEntity.ok(reportTemplateService.add(reportTemplateDTO));
//    }
//
//
//
//    /**
//     * Modifica una plantilla de formulari
//     * @param user
//     * @param reportTemplateDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una plantilla de formulario", response = ReportTemplateDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Plantilla modificada correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                    COMMON_ERROR_CODES_400 +
//                    "| " + IReportTemplateServiceErrorCode.RT_HAS_ANSWERS + " | No se puede modificar la plantilla. Ya tiene respuestas |\n"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.RT_NOT_FOUND + " | No existe la plantilla indicada |\n")
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canEditReport(#reportTemplateDTO.getUuid())")
//    public ResponseEntity<ReportTemplateDTO> updateReportTemplate(@AuthenticationPrincipal JWTUser user,
//                                                                  @ApiParam(required = true) @RequestBody ReportTemplateDTO reportTemplateDTO) {
//
//        return ResponseEntity.ok(reportTemplateService.update(reportTemplateDTO));
//    }
//
////
////
////
//    /**
//     * Elimina una plantilla de informe
//     * @param user
//     * @param reportTemplateUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una plantilla de informe")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Plantilla eliminada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.RT_NOT_FOUND + " | No existe la plantilla indicada |\n")
//    })
//    @DeleteMapping("/{reportTemplateUuid}")
//    @PreAuthorize("@permissionHandler.canEditReport(#reportTemplateUuid)")
//    public ResponseEntity<Void> deleteReportTemplate(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(required = true) @PathVariable String reportTemplateUuid) {
//
//        reportTemplateService.delete(reportTemplateUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//
//
//    /**
//     * Activa la plantilla para que pueda ser asignada a usuarios para completar informes
//     * @param user
//     * @param reportTemplateUuid
//     * @return
//     */
//    @ApiOperation(value = "Activa la plantilla para que pueda ser asignada a usuarios para completar informes")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Plantilla activada"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                    "| " + IReportTemplateServiceErrorCode.NOT_ACTIVABLE_ALREADY_ACTIVE + " | La plantilla ya está cerrada |\n" +
//                    "| " + IBlockServiceErrorCode.QUESTIONS_CONTAINS_ERRORS + " | Las preguntas y respuestas de la plantilla no estan completas.\n" +
//                                                                                 " En el campo extra info de la respuesta, se devuelve el detalle de los errores.\n" +
//                                                                                 "La clave del map de extra info es <numero de order bloque>-<numero orden pregunta> y el valor la causa del problema |\n"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.RT_NOT_FOUND + " | No existe la plantilla indicada |\n")
//
//    })
//    @PatchMapping("/{reportTemplateUuid}/activate")
//    @PreAuthorize("@permissionHandler.canEditReport(#reportTemplateUuid)")
//    public ResponseEntity<Void> activateReportTemplate(@AuthenticationPrincipal JWTUser user,
//                                             @ApiParam(required = true) @PathVariable String reportTemplateUuid) {
//
//
//        reportTemplateService.activate(reportTemplateUuid);
//        return null;
//    }
//
//
//
//
//    /**
//     * Crea una copia completa de la plantilla, copiando tambien los bloques, preguntas, posibles respuestas....
//     * @param user
//     * @param reportTemplateUuid
//     * @return
//     */
//    @ApiOperation(value = "Crea una copia completa de la plantilla, copiando tambien los bloques, preguntas, posibles respuestas....", response = ReportTemplateDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Plantilla clonada. Retorna la nueva plantilla"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.RT_NOT_FOUND + " | No existe la plantilla indicada |\n")
//    })
//    @PostMapping("/{reportTemplateUuid}/clone")
//    @PreAuthorize("@permissionHandler.canEditReport(#reportTemplateUuid)")
//    public ResponseEntity<ReportTemplateDTO> cloneReportTemplate(@AuthenticationPrincipal JWTUser user,
//                                                    @ApiParam(required = true) @PathVariable String reportTemplateUuid) {
//
//        return ResponseEntity.ok(reportTemplateService.clone(reportTemplateUuid));
//    }
//
//
//
//    /**
//     * Asigna un informe a un usuario para que pueda completarlo. Crea el formulario para ese informador e informado y si
//     * en la petición se pasan respuestas automàticas a preguntas del formulario, una vez creado, se le asignan esas respuestas
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Asigna un informe a un usuario para que pueda completarlo. Crea el formulario para ese informador e informado y si " +
//            "en la petición se pasan respuestas automàticas a preguntas del formulario, una vez creado, se le asignan esas respuestas",
//                  response = FormDTO.class,
//                  notes = "El map de automaticAnswer contiene el valor para determinadas preguntas del formulario que se pueden llenar automaticamente. " +
//                          "Actualmente, estan configuradas estas respuestas automaticas\n\n" +
//                          "| código | descripción |\n" +
//                          "| ------ | ----------- |\n" +
//                          "| refereeNumber     | Número de colegiado |\n" +
//                          "| refereeName       | Nombre de colegiado |\n" +
//                          "| secondRefereerName | Nombre segundo colegiado |\n" +
//                          "| informerName      | Nombre del informador |\n" +
//                          "| refereeCategory   | Categoria del colegiado |\n" +
//                          "| matchNumber       | Número de partido |\n" +
//                          "| matchCategory     | Categoria del partido |\n" +
//                          "| matchCompetition  | Competición  |\n" +
//                          "| localTeam         | Equipo local |\n" +
//                          "| visitorTeam       | Equip visitante |\n" +
//                          "| matchDay          | Dia de partido |\n" +
//                          "| matchTime         | Hora de partido |\n" +
//                          "| matchTown         | Localidad donde se juega el partido |\n" +
//                          "| localPoints       | Tanteo equipo local |\n" +
//                          "| visitorPoints     | Tanteo equipo visitante |\n")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Formulario creado. Devuelve el formulario"),
//            @ApiResponse(code = 400, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.NOT_ASSIGNABLE_STILL_EDITABLE + " | No se puede asignar la plantilla. El formulario no esta cerrado  |\n"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IReportTemplateServiceErrorCode.RT_NOT_FOUND + " | No existe la plantilla indicada |\n")
//    })
//    @PostMapping("/{reportTemplateUuid}/assign-to/{informerUuid}/for/{subjectUuid}")
//    @PreAuthorize("@permissionHandler.canAccessReport(#reportTemplateUuid)")
//    public ResponseEntity<FormDTO> assignReport(@AuthenticationPrincipal JWTUser user,
//                                                @ApiParam(value = "uuid de la plantilla", required = true) @PathVariable String reportTemplateUuid,
//                                                @ApiParam(value = "uuid del informador", required = true) @PathVariable String informerUuid,
//                                                @ApiParam(value = "uuid del informado", required = true) @PathVariable String subjectUuid,
//                                                @ApiParam(value = "Valores de las respuestas generadas automaticamente") @RequestBody Map<String, String> automaticAnswers) {
//
//        return ResponseEntity.ok(reportTemplateService.assignReportForm(reportTemplateUuid, informerUuid, subjectUuid, automaticAnswers));
//    }
//
//
//
//
//    @ApiOperation(value = "Elimina el informe del usuario")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Formulario eliminado"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso para acceder  |\n" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | La plantilla no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IFormServiceErrorCode.FORM_NOT_FOUND + " | No existe el formulario |\n")
//    })
//    @DeleteMapping("/unassign-form/{formUuid}")
//    @PreAuthorize("@permissionHandler.canDeleteForm(#formUuid)")
//    public ResponseEntity<Void> unassignReport(@AuthenticationPrincipal JWTUser user,
//                                                  @ApiParam(value = "uuid del formulario", required = true) @PathVariable String formUuid) {
//
//        formService.deleteForm(formUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//
//
//    /**
//     * Devuelve la lista de tipos de informe de partido
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de tipos de informe de partido.",
//            response = MatchReportTypeDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de tipos de informe de partido"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder  |\n")
//    })
//    @GetMapping("/match-report-types")
//    @PreAuthorize("@permissionHandler.hasReportCategoryPermissions()")
//    public ResponseEntity<List<MatchReportTypeDTO>> geMatchReportTypes(@AuthenticationPrincipal JWTUser user) {
//
//
//        return ResponseEntity.ok(matchReportTypeService.findAllByFederation(ClientContextHolder.getFederation()));
//    }
//
//
//
//    /**
//     * Devuelve la lista de codigos de portal
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de códigos de portal.",
//            response = Portal.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de códigos de portal"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder  |\n")
//    })
//    @GetMapping("/portal-codes")
//    @PreAuthorize("@permissionHandler.hasReportCategoryPermissions()")
//    public ResponseEntity<List<Portal>> getPortalCodes(@AuthenticationPrincipal JWTUser user) {
//
//        return ResponseEntity.ok(Arrays.asList(Portal.values()));
//    }
//    
//    @ApiOperation(value = "Devuelve la lista de colores dada un federación.",
//            response = ReportTemplateColorsDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista devulta correctamente ")
//       
//    })       
//          
//   
//    @GetMapping("/colors")
//    public ResponseEntity<List<ReportTemplateColorsDTO>> getColorByFede(){
//    	
//
//    	return ResponseEntity.ok(reportTemplateColorsService.findByFederation(ClientContextHolder.getFederation()));
//    	
//    }
//
//
//}
