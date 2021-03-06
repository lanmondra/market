//package cat.owc.ms.reports.controllers.auth.manager;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.CampaignDTO;
//import cat.owc.ms.reports.excel.IExcelGeneratorErrorCode;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.permissions.PermissionHandler;
//import cat.owc.ms.reports.services.ICampaignServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.ICampaignService;
//import cat.owc.ms.reports.services.interfaces.IPermissionService;
//import cat.owc.ms.reports.services.interfaces.IPollService;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.*;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth/manager/campaigns")
//@Api(tags = {"manager/campaigns"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerCampaignController extends AbstractController {
//
//    private final ICampaignService campaignService;
//    private final IPermissionService permissionService;
//    private final PermissionHandler permissionHandler;
//
//    public ManagerCampaignController(ICampaignService campaignService,
//                                     IPermissionService permissionService,
//                                     PermissionHandler permissionHandler) {
//
//        this.campaignService = campaignService;
//        this.permissionService = permissionService;
//        this.permissionHandler = permissionHandler;
//    }
//
//
//
//    private static final String COMMON_ERROR_CODES_400 = "| codigo | descripcion |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + ICampaignServiceErrorCode.MISSING_NAME + " | Falta informar el nombre de la campa??a |\n" +
//            "| " + ICampaignServiceErrorCode.MISSING_START_DATE + " | Falta informar la fecha de inicio de la campa??a |\n" +
//            "| " + ICampaignServiceErrorCode.MISSING_END_DATE + " | Falta informar la fecha de final de la campa??a |\n" +
//            "| " + ICampaignServiceErrorCode.MISSING_REPORT_TEMPLATE + " | Falta informar la plantilla de informe de la campa??a |\n";
//
//
//    /**
//     * Devuelve la lista de campa??as que administra el usuario
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de campa??as que administra el usuario ",
//            response = CampaignDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de campa??as solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido :\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permisos para acceder |\n")
//    })
//    @GetMapping
//    @PreAuthorize("@permissionHandler.hasReportCategoryPermissions()")
//    public ResponseEntity<List<CampaignDTO>> getCampaigns(@AuthenticationPrincipal JWTUser user) {
//
//        List<Integer> categoryIds = permissionService.getCategoryIdsForPermissions(permissionHandler.getUserCategoryAuthorities());
//
//        return ResponseEntity.ok(campaignService.findByCategoryIdList(categoryIds));
//    }
//
//
//
//
//    /**
//     * Devuelve la campa??a solicitada
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la campa??a solicitada", response = CampaignDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La campa??a solicitada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la informaci??n |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_BELONGS_TO_FEDE + " | La campa??a no pertenece a la federaci??n |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND + " | No existe una campa??a con el uuid indicado |\n")
//    })
//    @GetMapping("/{campaignUuid}")
//    @PreAuthorize("@permissionHandler.canAccessCampaign(#campaignUuid)")
//    public ResponseEntity<CampaignDTO> getCampaign(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(value = "Uuid de la campa??a", required = true) @PathVariable String campaignUuid) {
//
//        return ResponseEntity.ok(campaignService.findByUuid(campaignUuid));
//    }
//
//
//
//
//
//    /**
//     * A??ade una campa??a
//     * @param user
//     * @param campaignDTO
//     * @return
//     */
//    @ApiOperation(value = "A??ade una nueva campa??a", response = CampaignDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Campa??a a??adida correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petici??n:\n\n" +
//                                              COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la informaci??n |\n")
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.canEditCampaign(#campaignDTO)")
//    public ResponseEntity<CampaignDTO> addCampaign(@AuthenticationPrincipal JWTUser user,
//                                                   @RequestBody CampaignDTO campaignDTO) {
//
//        return ResponseEntity.ok(campaignService.add(campaignDTO));
//    }
//
//
//
//    /**
//     * Modifica una campa??a
//     * @param user
//     * @param campaignDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una campa??a", response = CampaignDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Campa??a modificada correctamente"),
//            @ApiResponse(code = 400, message = "Error en los datos de la petici??n:\n\n" +
//                    COMMON_ERROR_CODES_400),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la informaci??n |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_BELONGS_TO_FEDE + " | La campa??a no pertenece a la federaci??n |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND + " | No existe una campa??a con el uuid indicado |\n")
//
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canEditCampaign(#campaignDTO)")
//    public ResponseEntity<CampaignDTO> updateCampaign(@AuthenticationPrincipal JWTUser user,
//                                                      @ApiParam(required = true) @RequestBody CampaignDTO campaignDTO) {
//
//        return ResponseEntity.ok(campaignService.update(campaignDTO));
//    }
//
//
//
//
//    /**
//     * Elimina una campa??a
//     * @param user
//     * @param campaignUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una campa??a", response = Void.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Campa??a eliminada correctamente"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la informaci??n |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_BELONGS_TO_FEDE + " | La campa??a no pertenece a la federaci??n |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND + " | No existe una campa??a con el uuid indicado |\n")
//
//    })
//
//    @DeleteMapping("/{campaignUuid}")
//    @PreAuthorize("@permissionHandler.canEditCampaign(#campaignUuid)")
//    public ResponseEntity<Void> deleteCampaign(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(required = true) @PathVariable String campaignUuid) {
//
//        campaignService.delete(campaignUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//    /**
//     * Genera el excel de respuestas agregadas para la campa??a
//     * @param user
//     * @param campaignUuid
//     * @return
//     */
//    @ApiOperation(value = "Devuelve un fichero excel con la respuesta agregada a las preguntas de todos los formularios asociados a la campa??a",
//            responseContainer = "array", response = Byte.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El excel como un array de bytes"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la campa??a |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_BELONGS_TO_FEDE + " | La campa??a no pertenece a la federaci??n |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " +ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND + " | No existe una campa??a con el uuid indicado |\n"),
//            @ApiResponse(code = 500, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IExcelGeneratorErrorCode.EXCEL_GENERATION_ERROR + " | Error generando el excel |\n")
//    })
//    @GetMapping("/{campaignUuid}/aggregated-excel")
//    @PreAuthorize("@permissionHandler.canAccessCampaign(#campaignUuid)")
//    public ResponseEntity<byte[]> getAggregatedExcel(@AuthenticationPrincipal JWTUser user,
//                                                     @ApiParam(required = true) @PathVariable String campaignUuid) {
//
//        log.info("Peticion generacion excel respuestas agregadas para la campa??a con uuid " + campaignUuid);
//        Map<String, Object> excelData = campaignService.generateAggregatedAnswerExcel(campaignUuid);
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
//    /**
//     * Genera el excel de respuestas detalladas para la campa??a
//     * @param user
//     * @param campaignUuid
//     * @return
//     */
//    @ApiOperation(value = "Devuelve un fichero excel con la respuesta detallada a las preguntas de todos los formularios asociados a la campa??a",
//            responseContainer = "array", response = Byte.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El excel como un array de bytes"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permiso para acceder a la campa??a |\n" +
//                    "| " + ICampaignServiceErrorCode.CAMPAIGN_NOT_BELONGS_TO_FEDE + " | La campa??a no pertenece a la federaci??n |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " +ICampaignServiceErrorCode.CAMPAIGN_NOT_FOUND + " | No existe una campa??a con el uuid indicado |\n"),
//            @ApiResponse(code = 500, message = "En este caso\n\n" +
//                    "| c??digo | descripci??n |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IExcelGeneratorErrorCode.EXCEL_GENERATION_ERROR + " | Error generando el excel |\n")
//    })
//    @GetMapping("/{campaignUuid}/detailed-excel")
//    @PreAuthorize("@permissionHandler.canAccessCampaign(#campaignUuid)")
//
//    public ResponseEntity<byte[]> getDetailedExcel(@AuthenticationPrincipal JWTUser user,
//                                                   @ApiParam(required = true) @PathVariable String campaignUuid) {
//
//        log.info("Peticion generacion excel respuestas detalladas para la campa??a con uuid " + campaignUuid);
//        Map<String, Object> excelData = campaignService.generateDetailedAnswerExcel(campaignUuid);
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
//}
