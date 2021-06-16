//package cat.owc.ms.reports.controllers.auth.manager;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.PollDTO;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.excel.IExcelGeneratorErrorCode;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.permissions.PermissionHandler;
//import cat.owc.ms.reports.services.IBlockServiceErrorCode;
//import cat.owc.ms.reports.services.IPollServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IPollService;
//import cat.owc.ms.reports.swagger.OWExceptionSwagger;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.ms.reports.utils.ReportUtils;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@RestController
//@RequestMapping("/auth/manager/polls")
//@Api(tags = {"manager/polls"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerPollController extends AbstractController {
//
//    private final IPollService pollService;
//    private final PermissionHandler permissionHandler;
//
//    public ManagerPollController(IPollService pollService,
//                                 PermissionHandler permissionHandler) {
//            this.pollService = pollService;
//            this.permissionHandler = permissionHandler;
//    }
//
//
//
//    private static final String COMMON_ERROR_CODES_400 = "| codigo | descripcion |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IPollServiceErrorCode.MISSING_NAME + " | Falta informar el nombre de la encuesta |\n" +
//            "| " + IPollServiceErrorCode.MISSING_STATUS + " | Falta informar el estado de la encuesta |\n" +
//            "| " + IPollServiceErrorCode.MISSING_ANONYMUS + " | Falta informar si la encuesta es anonima o no |\n" +
//            "| " + IPollServiceErrorCode.MISSING_LOGIN_REQUIRED + " | Falta informar si la encuesta requiere login para ser contestada |\n" +
//            "| " + IPollServiceErrorCode.MISSING_CHANGE_ALLOWED + " | Falta informar si la encuesta admite cambios una vez enviada |\n" +
//            "| " + IPollServiceErrorCode.MISSING_ENDING_TIME + " | Falta informar el dia de finalización de la encuesta |\n" +
//            "| " + IPollServiceErrorCode.MISSING_INFORMER_TYPE + " | Falta informar el tipo de informador |\n" +
//            "| " + IPollServiceErrorCode.MISSING_INFORMER_TYPE_CATEGORIES + " | Falta informar las categorias de los informadores |\n" +
//            "| " + IPollServiceErrorCode.ACTIVATION_AFTER_ENDING + " | Fecha de activación posterior al final de la encuesta  |\n";
//
//
//    /**
//     * Devuelve la lista de encuestas que administra el usuario
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de encuestas que administra el usuario ",
//            response = PollDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de encuestas solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permisos para acceder a la lista |\n")
//    })
//    @GetMapping
//    @PreAuthorize("@permissionHandler.canGetPollsList()")
//    public ResponseEntity<List<PollDTO>> getPolls(@AuthenticationPrincipal JWTUser user) {
//
//        String federation = ClientContextHolder.getFederation();
//        Portal portal= ReportUtils.getPortal();
//        List<InformerTypeCode> informerTypeCodes = permissionHandler.getPollAdminInformerTypes();
//        return ResponseEntity.ok(pollService.findPollsByInformerTypeDTO(federation, informerTypeCodes, portal));
//    }
//
//
//
//
//    /**
//     * Devuelve la encuesta solicitada
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la encuesta solicitada. En caso de estar activa, devuelve también información de los participantes, " +
//            "el numero de encuestas iniciadas y el número de encuestas finalizadas",
//            response = PollDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La encuesta solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n")
//    })
//    @GetMapping("/{pollUuid}")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<PollDTO> getPoll(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(value = "Uuid de la encuesta", required = true) @PathVariable String pollUuid) {
//
//        return ResponseEntity.ok(pollService.findPollFile(pollUuid));
//    }
//
//
//
//
//
//
//
//    /**
//     * Retorna el formulario de la encuesta indicada para el usuario. Si ya se ha contestado, se informan también las respuestas si la encuesta es anonima
//     * @param user - jwt del usuario
//     * @param pollUuid - uuid de la encuesta
//     * @return - el form
//     */
//    @ApiOperation(value = "Devuelve el formulario correspondiente al usuario con las preguntas y respuestas (si ya se ha contestado) ",
//            response = FormDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El formulario correspondiente al usuario para la encuesta indicada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a los formularios de la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n" +
//                    "| " + IPollServiceErrorCode.FORM_NOT_FOUND_FOR_USER + " | El usuario no tiene disponible esta encuesta para contestar |\n")
//    })
//    @GetMapping("{pollUuid}/form/{userUuid}")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<FormDTO> getForm(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(value = "UUID de la encuesta", required = true) @PathVariable String pollUuid,
//                                           @ApiParam(value = "UUID de del usuario para el que recuperar el formulario", required = true) @PathVariable String userUuid) {
//
//        return ResponseEntity.ok(pollService.findFormDTO(pollUuid, userUuid, false));
//    }
//
//
//    /**
//     * Añade una encuesta
//     * @param user
//     * @param pollDTO
//     * @return
//     */
//    @ApiOperation(value = "Añade una nueva encuesta", response = PollDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Encuesta añadida correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                                              COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido a los formularios de la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n")
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.canAddPoll(#pollDTO.getInformerTypeCode())")
//    public ResponseEntity<PollDTO> addPoll(@AuthenticationPrincipal JWTUser user,
//                                           @RequestBody PollDTO pollDTO) {
//
//        return ResponseEntity.ok(pollService.add(pollDTO));
//    }
//
//
//
//    /**
//     * Modifica una encuesta
//     * @param user
//     * @param pollDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una encuesta", response = PollDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Encuesta modificada correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                    COMMON_ERROR_CODES_400 +
//                    "| " + IPollServiceErrorCode.POLL_HAS_ANSWERS + " | No se puede modificar la encuesta. Ya tiene respuestas |\n"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a los formularios de la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n")
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollDTO.getUuid())")
//    public ResponseEntity<PollDTO> updatePoll(@AuthenticationPrincipal JWTUser user,
//                                              @ApiParam(required = true) @RequestBody PollDTO pollDTO) {
//
//        return ResponseEntity.ok(pollService.update(pollDTO));
//    }
//
//
//
//
//    /**
//     * Elimina una encuesta
//     * @param user
//     * @param pollUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una encuesta")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Encuesta eliminada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a los formularios de la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n")
//    })
//    @DeleteMapping("/{pollUuid}")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<Void> deletePoll(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(required = true) @PathVariable String pollUuid) {
//
//        pollService.delete(pollUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//
//
//    /**
//     * Activa la encuesta para que pueda ser contestada
//     * @param user
//     * @param pollUuid
//     * @return
//     */
//    @ApiOperation(value = "Activa la encuesta para que pueda ser contestada")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Encuesta activada"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petición:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.NOT_ACTIVABLE_MISSING_PARTICIPANTS + " | No se han informado los participantes |\n" +
//                    "| " + IPollServiceErrorCode.NOT_ACTIVABLE_ALREADY_ACTIVE + " | La encuesta ya está activa |\n" +
//                    "| " + IPollServiceErrorCode.NOT_ACTIVABLE_WRONG_ENDING_TIME + " | La fecha de fin es anterior a la activación |\n" +
//                    "| " + IBlockServiceErrorCode.QUESTIONS_CONTAINS_ERRORS + " | Las preguntas y respuestas de la plantilla no estan completas.\n" +
//                                                                                  " En el campo extra info de la respuesta, se devuelve el detalle de los errores.\n" +
//                                                                                  "La clave del map de extra info es <numero de order bloque>-<numero orden pregunta> y el valor la causa del problema |\n"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a los formularios de la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n")
//    })
//    @PatchMapping("/{pollUuid}/activate")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<Void> activatePoll(@AuthenticationPrincipal JWTUser user,
//                                             @ApiParam(required = true) @PathVariable String pollUuid,
//                                             @ApiParam(required = true, value = "Lista de uuid de usuarios que deben contestar") @RequestBody List<String> userUuids) {
//
//        pollService.activate(pollUuid, userUuids);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//
//    /**
//     * Crea una copia completa de la encuesta, copiando tambien los bloques, preguntas, posibles respuestas....
//     * @param user
//     * @param pollUuid
//     * @return
//     */
//    @ApiOperation(value = "Crea una copia completa de la encuesta, copiando tambien los bloques, preguntas, posibles respuestas....", response = PollDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Encuesta clonada. Retorna la nueva encuesta"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n")
//    })
//    @PostMapping("/{pollUuid}/clone")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<PollDTO> clonePoll(@AuthenticationPrincipal JWTUser user,
//                                          @ApiParam(required = true) @PathVariable String pollUuid) {
//
//
//        return ResponseEntity.ok(pollService.clone(pollUuid));
//    }
//
//
//
//
//    @ApiOperation(value = "Devuelve un fichero excel con la respuesta agregada a las preguntas de la encuesta",
//            responseContainer = "array", response = Byte.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El excel como un array de bytes"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n"),
//            @ApiResponse(code = 500, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IExcelGeneratorErrorCode.EXCEL_GENERATION_ERROR + " | Error generando el excel |\n")
//    })
//    @GetMapping("/{pollUuid}/aggregated-excel")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<byte[]> getAggregatedExcel(@AuthenticationPrincipal JWTUser user,
//                                                     @ApiParam(required = true) @PathVariable String pollUuid) {
//
//        log.info("Peticion generacion excel respuestas agregadas para la encuesta con uuid " + pollUuid);
//        Map<String, Object> excelData = pollService.generateAggregatedAnswerExcel(pollUuid);
//
//        String fileName = (String)excelData.get(IPollService.EXCEL_FILE_NAME);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_ENCODING, "UTF-8");
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//
//
//        return ResponseEntity.ok().headers(headers).body((byte []) excelData.get(IPollService.EXCEL_CONTENT));
//    }
//
//
//
//
//
//    @ApiOperation(value = "Devuelve un fichero excel con la respuesta detallada a las preguntas de la encuesta",
//            responseContainer = "array", response = Byte.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El excel como un array de bytes"),
//            @ApiResponse(code = 401, message = "Acceso no permitido a la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | El usuario no tiene permiso para acceder a la encuesta |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n"),
//            @ApiResponse(code = 500, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IExcelGeneratorErrorCode.EXCEL_GENERATION_ERROR + " | Error generando el excel |\n")
//    })
//    @GetMapping("/{pollUuid}/detailed-excel")
//    @PreAuthorize("@permissionHandler.canAccessPoll(#pollUuid)")
//    public ResponseEntity<byte[]> getDetailedExcel(@AuthenticationPrincipal JWTUser user,
//                                                   @ApiParam(required = true) @PathVariable String pollUuid) {
//
//        log.info("Peticion generacion excel respuestas detalladas para la encuesta con uuid " + pollUuid);
//        Map<String, Object> excelData = pollService.generateDetailedAnswerExcel(pollUuid);
//
//        String fileName = (String)excelData.get(IPollService.EXCEL_FILE_NAME);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_ENCODING, "UTF-8");
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//
//
//        return ResponseEntity.ok().headers(headers).body((byte []) excelData.get(IPollService.EXCEL_CONTENT));
//    }
//
//
//
//}
