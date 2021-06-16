//package cat.owc.ms.reports.controllers.auth.manager;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.services.IBlockServiceErrorCode;
//import cat.owc.ms.reports.services.IPollServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IBlockService;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@RestController
//@RequestMapping("/auth/manager/blocks")
//@Api(tags = {"manager/blocks"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerBlockController extends AbstractController {
//
//
//    private final IBlockService blockService;
//
//    public ManagerBlockController(IBlockService blockService) {
//
//        this.blockService = blockService;
//    }
//
//
//
//    /**
//     * Añade un bloque
//     * @param user
//     * @param blockDTO
//     * @return
//     */
//    @ApiOperation(value = "Añade una nuevo bloque", response = BlockDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Bloque añadido correctamente"),
//            @ApiResponse(code = 401, message = "Acceso no permitido al bloque por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | El bloque está en un formulario que ya tiene respuestas |\n" +
//                    "| " + IBlockServiceErrorCode.MISSING_NAME + " | Falta informar el nombre del bloque |\n" +
//                    "| " + IBlockServiceErrorCode.MISSING_ORDER_NUM + " | Falta informrar el numero de orden del bloque |\n")
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.canEditBlock(#blockDTO)")
//    public ResponseEntity<BlockDTO> addBlock(@AuthenticationPrincipal JWTUser user,
//                                             @RequestBody BlockDTO blockDTO) {
//
//        return ResponseEntity.ok(blockService.add(blockDTO));
//    }
//
//
//
//    /**
//     * Modifica un bloque
//     * @param user
//     * @param blockDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica un bloque", response = BlockDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Bloque modificado"),
//            @ApiResponse(code = 401, message = "Acceso no permitido al bloque por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | El bloque está en un formulario que ya tiene respuestas |\n" +
//                    "| " + IBlockServiceErrorCode.MISSING_NAME + " | Falta informar el nombre del bloque |\n" +
//                    "| " + IBlockServiceErrorCode.MISSING_ORDER_NUM + " | Falta informrar el numero de orden del bloque |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_NOT_FOUND + " | No se ha encontrado el bloque |\n")
//
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canEditBlock(#blockDTO)")
//    public ResponseEntity<BlockDTO> updateBlock(@AuthenticationPrincipal JWTUser user,
//                                                @RequestBody BlockDTO blockDTO) {
//
//        return ResponseEntity.ok(blockService.update(blockDTO ));
//    }
//
//
//
//
//    /**
//     * Elimina un bloque
//     * @param user
//     * @param blockUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina un bloque")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Bloque eliminada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido al bloque por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | El bloque está en un formulario que ya tiene respuestas |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_NOT_FOUND + " | No se ha encontrado el bloque |\n")
//    })
//    @DeleteMapping("/{blockUuid}")
//    @PreAuthorize("@permissionHandler.canEditBlock(#blockUuid)")
//    public ResponseEntity<Void> deleteBlock(@AuthenticationPrincipal JWTUser user,
//                                            @ApiParam(required = true) @PathVariable String blockUuid) {
//
//        blockService.delete(blockUuid, true);
//        return ResponseEntity.ok().build();
//    }
//
//
//}
