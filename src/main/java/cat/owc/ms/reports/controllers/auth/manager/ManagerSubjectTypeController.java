//package cat.owc.ms.reports.controllers.auth.manager;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.SubjectTypeDTO;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.services.interfaces.ISubjectTypeService;
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
//@RequestMapping("/auth/manager/subject-types")
//@Api(tags = {"manager/subject-types"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerSubjectTypeController extends AbstractController {
//
//
//    private final ISubjectTypeService subjectTypeService;
//
//    public ManagerSubjectTypeController(ISubjectTypeService subjectTypeService) {
//
//        this.subjectTypeService = subjectTypeService;
//    }
//
//
//
//
//    /**
//     * Devuelve los posibes tipos de sujeto de un informe
//     * @return
//     */
//    @ApiOperation(value = "Devuelve los posibles tipos de sujeto de informe ")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Los tipos de sujeto"),
//            @ApiResponse(code = 403, message = "Acceso prohibido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | Los permissos del usuario no permiten el acceso |\n"),
//    })
//    @GetMapping()
//    @PreAuthorize("@permissionHandler.canEdit()")
//    public ResponseEntity<List<SubjectTypeDTO>> findAll() {
//
//        return ResponseEntity.ok(subjectTypeService.findAll());
//    }
//}
