//package cat.owc.ms.reports.controllers.auth.user;
//
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.controllers.IAbstractControllerErrorCode;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.PollDTO;
//import cat.owc.ms.reports.entity.enumeration.Portal;
//import cat.owc.ms.reports.services.IPollServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IPollService;
//import cat.owc.ms.reports.swagger.OWExceptionSwagger;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.ms.reports.utils.ReportUtils;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth/polls")
//@Api(tags = {"polls"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class PollController extends AbstractController {
//
//    private final IPollService pollService;
//
//    public PollController(IPollService pollService) {
//            this.pollService = pollService;
//    }
//
//
//    /**
//     * Comprueba si un usuario tiene encuestas activas pendientes de contestar
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Comprueba si el usuario tiene encuestas activas pendientes de contestar. " +
//            "El header de origen de petición determina el tipo de encuestas que se evaluan (las de clubs, arbitros...)")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "En caso que tenga enquestas activas pendientes de contestar"),
//            @ApiResponse(code = 400, message = "El formulario no se puede recuperar por alguno de los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n",
//                    response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En caso de que no tenga ninguna encuesta activa pendiente de contestar", response = OWExceptionSwagger.class)
//    })
//    @GetMapping("/check-pending")
//    public ResponseEntity<Void> checkPendingPolls(@AuthenticationPrincipal JWTUser user) {
//
//        String userUuid = getUserUuid(user);
//        Portal portal = ReportUtils.getPortal();
//
//        if (pollService.hasPendingAnswerPolls(ClientContextHolder.getFederation(), userUuid, ReportUtils.getInformerType(),portal)) {
//            return ResponseEntity.ok().build();
//        }
//        else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//
//    /**
//     * Devuelve las encuestas activas o no activas (segun parametro del usuario)
//     * @param user
//     * @param active
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de encuestas asignadas al usuario. Según se indique en la petición " +
//            "devuelve las que esten activas o las finalizadas" +
//            "El header de origen de petición determina el tipo de encuestas que se evaluan (las de clubs, arbitros...)",
//            response = PollDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de encuestas solicitada"),
//            @ApiResponse(code = 400, message = "El formulario no se puede recuperar por alguno de los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n",
//                    response = OWExceptionSwagger.class),
//    })
//    @GetMapping
//    public ResponseEntity<List<PollDTO>> getPolls(@AuthenticationPrincipal JWTUser user,
//                                             @ApiParam(value = "Indica si devuelve las encuestas activas o las ya finalizadas") @RequestParam(required = false, defaultValue = "true") Boolean active) {
//    	
//    	Portal portal =ReportUtils.getPortal();
//    	System.out.println(portal.getName());
//      //  String userUuid = getUserUuid(user);
//        String userUuid = user.getId();
//        return ResponseEntity.ok(pollService.findPolls(ClientContextHolder.getFederation(), userUuid, ReportUtils.getInformerType(), active,portal));
//    }
//
//
//
//
//    /**
//     * Retorna el formulario de la encuesta indicada para el usuario. Si ya se ha contestado, se informan también las respuestas
//     * @param user - jwt del usuario
//     * @param pollUuid - uuid de la encuesta
//     * @return - el form
//     */
//    @ApiOperation(value = "Devuelve el formulario con las preguntas y respuestas (si ya se ha contestado) de esa encuesta correspondiente para el usuario ",
//            response = FormDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El formulario correspondiente al usuario para la encuesta indicada"),
//            @ApiResponse(code = 400, message = "El formulario no se puede recuperar por alguno de los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 403, message = "Acceso prohibido a los formularios de la encuesta por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n", response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_FOUND + " | No existe una encuesta con el uuid indicado |\n" +
//                    "| " + IPollServiceErrorCode.FORM_NOT_FOUND_FOR_USER + " | El usuario no tiene disponible esta encuesta para contestar |\n", response = OWExceptionSwagger.class)
//    })
//    @GetMapping("{pollUuid}/form")
//    public ResponseEntity<FormDTO> getForm(@AuthenticationPrincipal JWTUser user,
//                                           @ApiParam(value = "UUID de la encuesta a contestar", required = true) @PathVariable String pollUuid) {
//
//        String userUuid = getUserUuid(user);
//        return ResponseEntity.ok(pollService.findFormDTO(pollUuid, userUuid, true));
//    }
//
//
//
//
//
//
//
//
//}
