//package cat.owc.ms.reports.controllers.auth.user;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.controllers.IAbstractControllerErrorCode;
//import cat.owc.ms.reports.dto.AnswerDTO;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.entity.enumeration.InformerTypeCode;
//import cat.owc.ms.reports.entity.enumeration.SubjectTypeCode;
//import cat.owc.ms.reports.entity.enumeration.UserTypes;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.permissions.PermissionHandler;
//import cat.owc.ms.reports.services.IFormServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IFormService;
//import cat.owc.ms.reports.services.interfaces.IPermissionService;
//import cat.owc.ms.reports.swagger.OWExceptionSwagger;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.*;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth/forms")
//@Api(tags = {"forms"})
//public class FormController extends AbstractController {
//
//
//    private final IFormService formService;
//    private final PermissionHandler permissionHandler;
//    private final IPermissionService permissionService;
//
//    public FormController(IFormService formService,
//                          PermissionHandler permissionHandler,
//                          IPermissionService permissionService) {
//        this.formService = formService;
//        this.permissionHandler = permissionHandler;
//        this.permissionService = permissionService;
//    }
//
//
//    private static final String ERROR_CODES_400 = "| codigo | descripcion |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n" +
//            "| " + IFormServiceErrorCode.POLL_NOT_ACTIVE + " | La encuesta todavia no está activa |\n" +
//            "| " + IFormServiceErrorCode.POLL_ENDED + " | Ha finalizado el plazo para contestar la encuesta |\n" +
//            "| " + IFormServiceErrorCode.FORM_ENDED + " | El formulario ya se ha marcado como finalizado y no puede ser contestado |\n" +
//            "| " + IFormServiceErrorCode.INCORRECT_ANSWER_FOR_FORM + " | El formulario de la respuesta no coicide que el que se ha de modificar |\n" +
//            "| " + IFormServiceErrorCode.REPORT_NOT_READY + " | El informe no está listo para ser contestado |\n" +
//            "| " + IFormServiceErrorCode.POLL_NOT_READY + " | La encuesta no está lista para ser contestada |\n" +
//            "| " + IFormServiceErrorCode.QUESTION_NOT_IN_POLL + " | La pregunta respondida no pertenece a la enquesta |\n" +
//            "| " + IFormServiceErrorCode.QUESTION_NOT_IN_REPORT + " | La pregunta respondida no pertenece al informe |\n";
//
//
//    private final String ERROR_CODES_403 = "| código | descripción |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IFormServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta no pertenece a la federación |\n" +
//            "| " + IFormServiceErrorCode.REPORT_NOT_BELONGS_TO_FEDERATION + " | El informe no pertenece a la federación |\n" +
//            "| " + IFormServiceErrorCode.USER_NOT_ALLOWED_TO_FILL_FORM + " | El usuario no está autorizado a contestar |\n";
//
//
//
//
//    /**
//     * Añade las respuestas a un formulario
//     * @param user - jwt de usuario
//     * @param formUuid - uuid del form
//     */
//    @ApiOperation(value = "Añade la respuesta a una pregunta del formulario", response = AnswerDTO.class)
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Respuesta añadida correctamente"),
//        @ApiResponse(code = 400, message = "No se puede modificar la respuesta por alguno de estos motivos:\n\n" +
//                ERROR_CODES_400 +
//                "| " + IFormServiceErrorCode.MORE_THAN_ONE_OPTION + " | Se ha seleccionado mas de una opción de respuesta y la pregunta no lo permite |\n" +
//                "| " + IFormServiceErrorCode.QUESTION_ALREADY_ANSWERED + " | La pregunta ya ha sido contestada |\n" +
//                "| " + IFormServiceErrorCode.ANSWER_VALUE_OUT_OF_RANGE + " | El valor de la respuesta está fuera del rango de valores permitidos |\n" +
//                "| " + IFormServiceErrorCode.SELECTED_ANSWER_NOT_VALID + " | La respuesta seleccionada no está entre las posibles opciones de respuesta de la pregunta |\n",
//                response = OWExceptionSwagger.class),
//        @ApiResponse(code = 403, message = "Acceso prohibido a los formularios de la encuesta por estos motivos:\n\n" + ERROR_CODES_403, response = OWExceptionSwagger.class),
//        @ApiResponse(code = 404, message = "En este caso\n\n" +
//                "| código | descripción |\n" +
//                "| ------ | ----------- |\n" +
//                "| " + IFormServiceErrorCode.FORM_NOT_FOUND + " | No se ha encontrado el formulario a modificar |\n", response = OWExceptionSwagger.class)
//    })
//    @PostMapping("/{formUuid}/answer")
//    public ResponseEntity<AnswerDTO> addAnswer(@AuthenticationPrincipal JWTUser user,
//                                               @ApiParam(value = "Uuid del form donde añadir las respuestas", required = true) @PathVariable String formUuid,
//                                               @ApiParam(value = "Datos de la respuesta", required = true) @RequestBody AnswerDTO answerDTO) {
//
//        String userUuid = getUserUuid(user);
//        return ResponseEntity.ok(formService.addAnswer(formUuid, userUuid, answerDTO));
//
//    }
//
//
//
//    /**
//     * Modifica la respuesta a una pregunta del formulario
//     * @param user - jwt de usuario
//     * @param formUuid - uuid del form
//     * @param answerDTO - Datos de la respuesta
//     * @return - La respuesta modificada
//     */
//
//    @ApiOperation(value = "Modifica la respuesta a una pregunta del formulario", response = AnswerDTO.class)
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Respuesta modificada correctamente"),
//        @ApiResponse(code = 400, message = "No se puede modificar la respuesta por alguno de estos motivos:\n\n" +
//                ERROR_CODES_400 +
//                "| " + IFormServiceErrorCode.MORE_THAN_ONE_OPTION + " | Se ha seleccionado mas de una opción de respuesta y la pregunta no lo permite |\n" +
//                "| " + IFormServiceErrorCode.ANSWER_VALUE_OUT_OF_RANGE + " | El valor de la respuesta está fuera del rango de valores permitidos |\n" +
//                "| " + IFormServiceErrorCode.SELECTED_ANSWER_NOT_VALID + " | La respuesta seleccionada no está entre las posibles opciones de respuesta de la pregunta |\n" +
//                "| " + IFormServiceErrorCode.WRONG_ANSWER_UUID + " | Uuid de la respuesta a modificar incorrecto |\n",
//                response = OWExceptionSwagger.class),
//        @ApiResponse(code = 403, message = "Acceso prohibido a los formularios de la encuesta por estos motivos:\n\n" + ERROR_CODES_403, response = OWExceptionSwagger.class),
//        @ApiResponse(code = 404, message = "En este caso\n\n" +
//                "| código | descripción |\n" +
//                "| ------ | ----------- |\n" +
//                "| " + IFormServiceErrorCode.ANSWER_NOT_FOUND + " | No se ha encontrado la respuesta a modificar |\n" +
//                "| " + IFormServiceErrorCode.FORM_NOT_FOUND + " | No se ha encontrado el formulario a modificar |\n", response = OWExceptionSwagger.class)
//    })
//    @PutMapping("/{formUuid}/answer/{answerUuid}")
//    public ResponseEntity<AnswerDTO> updateResponse(@AuthenticationPrincipal JWTUser user,
//                                                    @ApiParam(value ="Uuid del form al que añadir la respuesta", required = true) @PathVariable String formUuid,
//                                                    @ApiParam(value ="Uuid del la respuesta a modificar", required = true) @PathVariable String answerUuid,
//                                                    @ApiParam(value = "Los datos de la respuesta", required= true) @RequestBody AnswerDTO answerDTO) {
//
//        String userUuid = getUserUuid(user);
//        return ResponseEntity.ok(formService.updateAnswer(formUuid, answerUuid, userUuid, answerDTO));
//    }
//
//
//
//
//    /**
//     * Elimna la respuesta a una pregunta del formulario
//     * @param user - jwt de usuario
//     * @param formUuid - uuid del form
//     * @param answerUuid - Uuid de la respuesta a eliminar
//     */
//
//    @ApiOperation(value = "Elimina la respuesta a una pregunta del formulario")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Respuesta eliminada correctamente"),
//        @ApiResponse(code = 400, message = "No se puede modificar la respuesta por alguno de estos motivos:\n\n" +
//                "| codigo | descripcion |\n" +
//                "| ------ | ----------- |\n" +
//                ERROR_CODES_400,
//                response = OWExceptionSwagger.class),
//        @ApiResponse(code = 403, message = "Acceso prohibido a los formularios de la encuesta por estos motivos:\n\n" + ERROR_CODES_403, response = OWExceptionSwagger.class),
//        @ApiResponse(code = 404, message = "En este caso\n\n" +
//                "| código | descripción |\n" +
//                "| ------ | ----------- |\n" +
//                "| " + IFormServiceErrorCode.ANSWER_NOT_FOUND + " | No se ha encontrado la respuesta a modificar |\n" +
//                "| " + IFormServiceErrorCode.FORM_NOT_FOUND + " | No se ha encontrado el formulario a modificar |\n", response = OWExceptionSwagger.class)
//    })
//    @DeleteMapping("/{formUuid}/answer/{answerUuid}")
//    public ResponseEntity<Void> deleteResponse(@AuthenticationPrincipal JWTUser user,
//                                               @ApiParam(value ="Uuid del form al que elimianr la respuesta", required = true) @PathVariable String formUuid,
//                                               @ApiParam(value ="Uuid del la respuesta a eliminar", required = true) @PathVariable String answerUuid) {
//
//        String userUuid = getUserUuid(user);
//        formService.deleteAnswer(formUuid, answerUuid, userUuid);
//        return ResponseEntity.ok().build();
//    }
//
//
//    /**
//     * Marca el formulario como completado
//     * @param user
//     * @param formUuid
//     */
//    @ApiOperation(value = "Marca el formulario como completado")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Formulario completado correctamente"),
//            @ApiResponse(code = 400, message = "No se puede cerrar el formulario por alguno de estos motivos:\n\n" +
//                    "| codigo | descripcion |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n" +
//                    "| " + IFormServiceErrorCode.POLL_NOT_ACTIVE + " | La encuesta todavia no está activa |\n" +
//                    "| " + IFormServiceErrorCode.POLL_ENDED + " | Ha finalizado el plazo para contestar la encuesta |\n" +
//                    "| " + IFormServiceErrorCode.FORM_ENDED + " | El formulario ya se ha marcado como finalizado |\n" +
//                    "| " + IFormServiceErrorCode.REPORT_NOT_READY + " | El informe no está listo para ser contestado |\n" +
//                    "| " + IFormServiceErrorCode.POLL_NOT_READY + " | La encuesta no está lista para ser contestada |\n" +
//                    "| " + IFormServiceErrorCode.QUESTIONS_NOT_ANSWERED + " | Faltan preguntas por contestar |\n" +
//                    "| " + IFormServiceErrorCode.FORM_ALREADY_CLOSED + " | La encuesta no está lista para ser contestada |\n",
//                    response = OWExceptionSwagger.class),
//            @ApiResponse(code = 403, message = "Acceso prohibido a los formularios de la encuesta por estos motivos:\n\n" + ERROR_CODES_403, response = OWExceptionSwagger.class),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IFormServiceErrorCode.FORM_NOT_FOUND + " | No se ha encontrado el formulario a modificar |\n", response = OWExceptionSwagger.class)
//    })
//    @PutMapping("/{formUuid}/completed")
//    public ResponseEntity<Void> formCompleted(@AuthenticationPrincipal JWTUser user,
//                              @ApiParam(value ="Uuid del form a marcar como completad", required = true) @PathVariable String formUuid) {
//
//        String userUuid = getUserUuid(user);
//        formService.formCompleted(formUuid, userUuid);
//        return ResponseEntity.ok().build();
//
//    }
//
//
//
//    /**
//     * Devuelve los formularios de informe realizados sobre el usuario
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuele la lista de formularios de informe realizados sobre el usuario entre las fechas indicadas.\n" +
//            "Segun el parametro, devuelve los informes donde se evalua al sujeto de una manera determinada (como árbitro o como federado....).\n " +
//            "Devuelve solo información del form, sin datos de las preguntas y respuestas. Para obtenerlas se debe consultar el form individualmente",
//            response = FormDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de informes"),
//            @ApiResponse(code = 400, message = "No se puede recuperar por los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n")
//    })
//    @GetMapping("/on-me")
//    public ResponseEntity<List<FormDTO>> getReportFormsForSubject(@AuthenticationPrincipal JWTUser user,
//                                                                  @ApiParam(required = true) @RequestParam SubjectTypeCode subjectTypeCode,
//                                                                  @ApiParam(required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//                                                                  @ApiParam(required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
//
//        String userUuid = getUserUuid(user);
//        return ResponseEntity.ok(formService.findAllReportFormsBySubjectUuid(userUuid, subjectTypeCode, fromDate, toDate));
//    }
//
//
//
//
//    /**
//     * Devuelve un mapa indexado por uuid de los formularios solicitados
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve un map indexado por uuid de formulario con los datos de los formularios " +
//            "Devuelve solo información del form, sin datos de las preguntas y respuestas. Para obtenerlas se debe consultar el form individualmente",
//            response = FormDTO.class, responseContainer = "map")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "El maoa de formularios"),
//            @ApiResponse(code = 400, message = "No se puede recuperar por los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n")
//    })
//    @GetMapping("/from-list")
//    public ResponseEntity<Map<String, FormDTO>> getReportFormsFromList(@AuthenticationPrincipal JWTUser user,
//                                                                       @ApiParam(required = true) @RequestParam List<String> formUuids) {
//
//        return ResponseEntity.ok(formService.findAllReportFormsFromList(formUuids));
//    }
//
//
//
//
//    /**
//     * Devuelve los formularios de informe que debe completar o completados del usuario
//     * @param user
//     * @param pending
//     * @return
//     */
//    @ApiOperation(value = "Devuelve los formularios de informe que debe completar o completados del usuario entre las fechas indicadas.\n" +
//            "Segun el parametro de entrada de la petición, se devuelven los informe de ese tipo \n" +
//            "Devuelve solo información del form, sin datos de las preguntas y respuestas. Para obtenerlas se debe consultar el form individualmente",
//            response = FormDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de formularios"),
//            @ApiResponse(code = 400, message = "El formulario no se puede recuperar por alguno de los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n",
//                    response = OWExceptionSwagger.class),
//    })
//    @GetMapping("/assigned")
//    public ResponseEntity<List<FormDTO>> getReportFormsForInformer(@AuthenticationPrincipal JWTUser user,
//                                                             @ApiParam(required = true) @RequestParam InformerTypeCode informerTypeCode,
//                                                             @ApiParam(value = "Indica si devuelve los formularios completados o pendientes de completar") @RequestParam(required = false, defaultValue = "true") Boolean pending,
//                                                             @ApiParam(required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//                                                             @ApiParam(required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
//
//        String userUuid = getUserUuid(user);
//        return ResponseEntity.ok(formService.findAllReportFormsByInformerUuid(userUuid, informerTypeCode, pending,fromDate, toDate));
//    }
//
//
//
//    /**
//     * Devuelve los formularios de las categorias que administra el usuario
//     * @param user
//     * @param pending
//     * @return
//     */
//    @ApiOperation(value = "Devuelve los formularios de informe de las categorias que administra el usuario entre las fechas indicadas.\n" +
//            "Segun el parametro de entrada de la petición, se devuelven los informe de ese tipo (pendientes o completados) \n" +
//            "Devuelve solo información del form, sin datos de las preguntas y respuestas. Para obtenerlas se debe consultar el form individualmente",
//            response = FormDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de formularios"),
//            @ApiResponse(code = 400, message = "El formulario no se puede recuperar por alguno de los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n",
//                    response = OWExceptionSwagger.class),
//    })
//    @GetMapping("/supervised")
//    public ResponseEntity<List<FormDTO>> getReportFormsForSupervisor(@AuthenticationPrincipal JWTUser user,
//                                                             @ApiParam(required = true) @RequestParam InformerTypeCode informerTypeCode,
//                                                             @ApiParam(value = "Indica si devuelve los formularios completados o pendientes de completar") @RequestParam(required = false, defaultValue = "true") Boolean pending,
//                                                             @ApiParam(required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//                                                             @ApiParam(required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
//
//        List<Integer> categoryIds = permissionService.getCategoryIdsForPermissions(permissionHandler.getUserCategoryAuthorities());
//
//        return ResponseEntity.ok(formService.findAllReportFormsByInformerTypeAndCategoryIn(informerTypeCode, categoryIds, pending, fromDate, toDate));
//    }
//
//
//
//
//
//
//
//    /**
//     * Devuelve el formulario de informe indicado
//     * @param user
//     * @param formUuid
//     * @param userType
//     * @return
//     */
//    @ApiOperation(value = "Devuelve toda la información de un formulario (incluyendo preguntas y respuestas). Se controla el acceso al mismo de la siguiente manera:\n" +
//            " * tipo usuario = subject --> deja acceder si usuario es el sujeto del informe \n" +
//            " * tipo usuario = informer --> deja acceder si el usuario es el informador del informe\n" +
//            " * tipo usuario = supervisor --> deja acceder si el usuario tiene permiso para acceder a la categoria del informe",
//            response = FormDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "La lista de encuestas solicitada"),
//            @ApiResponse(code = 400, message = "El formulario no se puede recuperar por alguno de los siguientes motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IAbstractControllerErrorCode.NO_USER_UUID + " | Uuid del usuario no informado |\n",
//                    response = OWExceptionSwagger.class),
//            @ApiResponse(code = 401, message = "Acceso no autorizado a los formularios de la encuesta por estos motivoss:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IFormServiceErrorCode.USER_NOT_ALLOWED_TO_GET_FORM + " | Acceso no permitido al form |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | El usuario no tiene permiso de acceso a la categoria del form |\n")
//    })
//    @GetMapping("/{formUuid}")
//    public ResponseEntity<FormDTO> getReportForm(@AuthenticationPrincipal JWTUser user,
//                                                 @ApiParam(required = true) @PathVariable String formUuid,
//                                                 @ApiParam(required = true, value = "En condicion de que el usuario quiere ver el formulario") @RequestParam UserTypes userType) {
//
//        String userUuid = getUserUuid(user);
//        return ResponseEntity.ok(formService.findReportFormForUserFilled(formUuid, userUuid, userType));
//    }
//
//}
