//package cat.owc.ms.reports.controllers.auth.manager;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.InformerTypeDTO;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.services.interfaces.IInformerTypeService;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth/manager/informer-types")
//@Api(tags = {"manager/informer-types"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerInformerTypeController extends AbstractController {
//
//
//    private final IInformerTypeService iInformerTypeService;
//
//    public ManagerInformerTypeController(IInformerTypeService iInformerTypeService) {
//
//        this.iInformerTypeService = iInformerTypeService;
//    }
//
//
//
//
//    /**
//     * Devuelve los  tipos de informador
//     * @return
//     */
//    @ApiOperation(value = "Devuelve tipos de informador ")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Los tipoa de informador"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | Los permissos del usuario no permiten el acceso |\n"),
//    })
//    @GetMapping()
//    @PreAuthorize("@permissionHandler.canEdit()")
//    public ResponseEntity<List<InformerTypeDTO>> findAll() {
//
//        return ResponseEntity.ok(iInformerTypeService.findAll());
//    }
//}
