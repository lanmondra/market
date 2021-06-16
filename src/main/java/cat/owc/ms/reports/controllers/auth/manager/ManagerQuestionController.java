//package cat.owc.ms.reports.controllers.auth.manager;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import cat.owc.ms.reports.controllers.AbstractController;
//import cat.owc.ms.reports.dto.AnswerOptionDTO;
//import cat.owc.ms.reports.dto.AnswerTemplateDTO;
//import cat.owc.ms.reports.dto.AnswerTypeDTO;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.permissions.IPermissionHandlerErrorCode;
//import cat.owc.ms.reports.services.IAnswerOptionServiceErrorCode;
//import cat.owc.ms.reports.services.IBlockServiceErrorCode;
//import cat.owc.ms.reports.services.IPollServiceErrorCode;
//import cat.owc.ms.reports.services.IQuestionServiceErrorCode;
//import cat.owc.ms.reports.services.interfaces.IAnswerOptionService;
//import cat.owc.ms.reports.services.interfaces.IAnswerTemplateService;
//import cat.owc.ms.reports.services.interfaces.IAnswerTypeService;
//import cat.owc.ms.reports.services.interfaces.IQuestionService;
//import cat.owc.ms.reports.swagger.SwaggerDefaults;
//import cat.owc.owcauthenticationutils.dtos.JWTUser;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@RestController
//@RequestMapping("/auth/manager/questions")
//@Api(tags = {"manager/questions"})
//@ApiResponses({
//        @ApiResponse(code = 401, message = SwaggerDefaults.HTTP_401_MESSAGE),
//        @ApiResponse(code = 403, message = SwaggerDefaults.HTTP_403_MESSAGE),
//        @ApiResponse(code = 500, message = SwaggerDefaults.HTTP_500_MESSAGE),
//})
//public class ManagerQuestionController extends AbstractController {
//
//
//    private final IQuestionService questionService;
//    private final IAnswerTemplateService answerTemplateService;
//    private final IAnswerTypeService answerTypeService;
//    private final IAnswerOptionService answerOptionService;
//
//    public ManagerQuestionController(IQuestionService questionService,
//                                     IAnswerTemplateService answerTemplateService,
//                                     IAnswerTypeService answerTypeService,
//                                     IAnswerOptionService answerOptionService) {
//        this.questionService = questionService;
//        this.answerTemplateService = answerTemplateService;
//        this.answerTypeService = answerTypeService;
//        this.answerOptionService = answerOptionService;
//    }
//
//
//    private final String ERROR_CODES_400 = "| código | descripción |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | La pregunta no se puede modificar. La encuesta o informe al que pertenece ya tiene respuestas |\n" +
//            "| " + IQuestionServiceErrorCode.MISSING_VALUE + " | Falta informar el texto de la pregunta |\n" +
//            "| " + IQuestionServiceErrorCode.MISSING_HAS_ANSWER + " | Falta informar el indicador de si la pregunta tiene respuesta |\n" +
//            "| " + IQuestionServiceErrorCode.MISSING_MANDATORY + " | Falta informar el indicador de si la pregunta es obligatoria |\n" +
//            "| " + IQuestionServiceErrorCode.MISSING_ORDER_NUM + " | Falta informar el orden de la pregunta dentro del bloque |\n" +
//            "| " + IQuestionServiceErrorCode.MISSING_ANSWER_TYPE + " | Falta informar tipo de respuesta |\n";
//
//
//    private final String ADD_ANSWER_OPTIONS_ERROR_CODES_400 = "| código | descripción |\n" +
//            "| ------ | ----------- |\n" +
//            "| " + IAnswerOptionServiceErrorCode.QUESTION_DOES_NOT_ACCEPT_ANSWER_OPTIONS + " |  Esta pregunta no permite respuestas|\n" +
//            "| " + IAnswerOptionServiceErrorCode.QUESTION_ONLY_ACCEPTS_2_ANSWER_OPTIONS + " | Esta pregunta solo acepta dos opciones de respuesta |\n" +
//            "| " + IAnswerOptionServiceErrorCode.VALUE_MUST_BE_NUMERIC + " | El texto/valor de la pregunta debe ser numerico |\n";
//
//
//
//
//            /**
//             * Devuelve la lista de respuestas predeterminadas. La lista tendrà todas las respuestas comunes y las de la federación especificada
//             * @param user
//             * @return
//             */
//    @ApiOperation(value = "Devuelve la lista respuestas predeterminadas", response = AnswerTemplateDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Devuelve la lista"),
//            @ApiResponse(code = 401, message = "Acceso prohibido al bloque por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permisos de edición\n")
//    })
//    @GetMapping("/answer-templates")	
//    @PreAuthorize("@permissionHandler.canEdit()")
//    public ResponseEntity<List<AnswerTemplateDTO>> getAnswerTemplates(@AuthenticationPrincipal JWTUser user) {
//
//        return ResponseEntity.ok(answerTemplateService.findAllFilledDTO());
//    }
//
//
//
//    /**
//     * Devuelve la lista de tipos de respuesta
//     * @param user
//     * @return
//     */
//    @ApiOperation(value = "Devuelve la lista de tipos de respuesta", response = AnswerTypeDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Devuelve la lista"),
//            @ApiResponse(code = 401, message = "Acceso prohibido al bloque por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.NO_ACCESS + " | El usuario no tiene permisos de edición\n")
//    })
//    @GetMapping("/answer-types")
//    @PreAuthorize("@permissionHandler.canEdit()")
//    public ResponseEntity<List<AnswerTypeDTO>> getAnswerTypes(@AuthenticationPrincipal JWTUser user,
//                                                              @ApiParam(required = true, value = "P o R para obtener los tipos para encuesta o para informe") @RequestParam String type) {
//
//        return ResponseEntity.ok(answerTypeService.findAllDTO(type));
//    }
//
//
//
//
//    /**
//     * Añade una pregunta al bloque
//     * @param user
//     * @param questionDTO
//     * @return
//     */
//    @ApiOperation(value = "Añade una pregunta a un bloque", response = QuestionDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Pregunta añadida"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" + ERROR_CODES_400),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_NOT_FOUND + " | No se ha encontrado el bloque al que incluir la pregunta |\n")
//
//    })
//    @PostMapping
//    @PreAuthorize("@permissionHandler.canEditBlock(#questionDTO.getBlockId())")
//    public ResponseEntity<QuestionDTO> addQuestion(@AuthenticationPrincipal JWTUser user,
//                                            @ApiParam(required = true) @RequestBody QuestionDTO questionDTO) {
//
//        return ResponseEntity.ok(questionService.add(questionDTO));
//    }
//
//
//
//    /**
//     * Modifica una pregunta.
//     * @param user
//     * @param questionDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una pregunta", response = QuestionDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Pregunta modificada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido al bloque por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" + ERROR_CODES_400),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_NOT_FOUND + " | No se ha encontrado el bloque |\n" +
//                    "| " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND + " | No se ha encontrado la pregunta |\n")
//
//    })
//    @PutMapping
//    @PreAuthorize("@permissionHandler.canEditBlock(#questionDTO.getBlockId())")
//    public ResponseEntity<QuestionDTO> updateQuestion(@AuthenticationPrincipal JWTUser user,
//                                               @ApiParam(required = true) @RequestBody QuestionDTO questionDTO) {
//
//        return ResponseEntity.ok(questionService.update(questionDTO));
//    }
//
//
//
//
//    /**
//     * Elimina una pregunta
//     * @param user
//     * @param questionUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una pregunta")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Pregunta eliminada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | La pregunta no se puede modificar. La encuesta o informe al que pertenece ya tiene respuestas |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND + " | No se ha encontrado la pregunta |\n")
//
//    })
//    @DeleteMapping("/{questionUuid}")
//    @PreAuthorize("@permissionHandler.canEditQuestion(#questionUuid)")
//    public ResponseEntity<Void> deleteQuestion(@AuthenticationPrincipal JWTUser user,
//                                               @ApiParam(required = true) @PathVariable String questionUuid) {
//
//        questionService.delete(questionUuid, true);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//
//    /**
//     * Añade una opcion de respuesta a la pregunta
//     * @param user
//     * @param answerOptionDTO
//     * @return
//     */
//    @ApiOperation(value = "Añade una opcion de respuesta a la pregunta", response = AnswerOptionDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Opción de respuesta añadida"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En estos casos\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | La pregunta no se puede modificar. La encuesta o informe al que pertenece ya tiene respuestas |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.MISSING_VALUE + " | Falta informar el texto de la pregunta |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.MISSING_ORDER_NUM + " | Falta informar el orden de la pregunta dentro del bloque |\n" +
//                    ADD_ANSWER_OPTIONS_ERROR_CODES_400),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND + " | No se ha encontrado la pregunta asociada a la respuesta |\n")
//
//    })
//    @PostMapping("/answer-option")
//    @PreAuthorize("@permissionHandler.canEditQuestion(#answerOptionDTO.getQuestionId())")
//    public ResponseEntity<AnswerOptionDTO> addAnswerOption(@AuthenticationPrincipal JWTUser user,
//                                                           @ApiParam(required = true) @RequestBody AnswerOptionDTO answerOptionDTO) {
//
//        return ResponseEntity.ok(answerOptionService.add(answerOptionDTO));
//    }
//
//
//
//
//    /**
//     * Modifica una opcion de respuesta de una pregunta
//     * @param user
//     * @param answerOptionDTO
//     * @return
//     */
//    @ApiOperation(value = "Modifica una opcion de respuesta de una pregunta", response = AnswerOptionDTO.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Opción de respuesta modificada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En estos casos\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | La pregunta no se puede modificar. La encuesta o informe al que pertenece ya tiene respuestas |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.MISSING_VALUE + " | Falta informar el texto de la pregunta |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.MISSING_ORDER_NUM + " | Falta informar el orden de la pregunta dentro del bloque |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND + " | No se ha encontrado la pregunta asociada a la respuesta |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.ANSWER_OPTION_NOT_FOUND + " | No se ha encontrado la opción de respuesta |\n")
//
//    })
//    @PutMapping("/answer-option")
//    @PreAuthorize("@permissionHandler.canEditQuestion(#answerOptionDTO.getQuestionId())")
//    public ResponseEntity<AnswerOptionDTO> updateAnswerOption(@AuthenticationPrincipal JWTUser user,
//                                                              @ApiParam(required = true) @RequestBody AnswerOptionDTO answerOptionDTO) {
//
//  
//    	
//        return ResponseEntity.ok(answerOptionService.update(answerOptionDTO));
//    }
//
//
//
//
//    /**
//     * Elimina una opcion de respuesta de una pregunta
//     * @param user
//     * @param questionUuid
//     * @param answerOptionUuid
//     * @return
//     */
//    @ApiOperation(value = "Elimina una opción de respuesta de una pregunta", response = Void.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Opción eliminada"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | La pregunta no se puede modificar. La encuesta o informe al que pertenece ya tiene respuestas |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND + " | No se ha encontrado la pregunta asociada a la respuesta |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.ANSWER_OPTION_NOT_FOUND + " | No se ha encontrado la opción de respuesta |\n")
//
//    })
//    @DeleteMapping("{questionUuid}/answer-option/{answerOptionUuid}")
//    @PreAuthorize("@permissionHandler.canEditQuestion(#questionUuid)")
//    public ResponseEntity<AnswerOptionDTO> deleteAnswerOption(@AuthenticationPrincipal JWTUser user,
//                                                              @ApiParam(required = true) @PathVariable String questionUuid,
//                                                              @ApiParam(required = true) @PathVariable String answerOptionUuid) {
//
//        answerOptionService.delete(answerOptionUuid, true);
//        return ResponseEntity.ok().build();
//    }
//
//
//
//
//    /**
//     * Añade las opciones de respuesta a la pregunta desde una plantilla
//     * @param user
//     * @param questionUuid
//     * @param answerTemplateUuid
//     * @return
//     */
//    @ApiOperation(value = "Añade las opciones de respuesta a la pregunta desde una plantilla de respuesta", response = AnswerOptionDTO.class, responseContainer = "list")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Opciones de respuesta añadidas"),
//            @ApiResponse(code = 401, message = "Acceso no permitido por estos motivos:\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IPermissionHandlerErrorCode.ADMIN_POLLS_NO_ADMIN + " | Los permisos del usuario no permiten acceder la encuesta " +
//                    "| " + IPermissionHandlerErrorCode.NO_PERMISSION_FOR_REPORT + " | Los permisos del usuario no permiten acceder al informe" +
//                    "| " + IPollServiceErrorCode.POLL_NOT_BELONGS_TO_FEDERATION + " | La encuesta a la que pertenece el bloque no es de la federación" +
//                    "| " + IPermissionHandlerErrorCode.REPORT_NOT_BELONGS_TO_FEDE + " | El informe al que pertenece el bloque no es de la federación" ),
//            @ApiResponse(code = 400, message = "En estos casos\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE + " | La pregunta no se puede modificar. La encuesta o informe al que pertenece ya tiene respuestas |\n" +
//                    "| " + IAnswerOptionServiceErrorCode.NO_TEMPLATE_IF_ANSWER_OPTIONS + " | No se pueden añadir opciones des de plantilla ya que la pregunta ya tiene opciones de respuesta |\n"),
//            @ApiResponse(code = 404, message = "En este caso\n\n" +
//                    "| código | descripción |\n" +
//                    "| ------ | ----------- |\n" +
//                    "| " + IQuestionServiceErrorCode.QUESTION_NOT_FOUND + " | No se ha encontrado la pregunta asociada a la respuesta |\n")
//
//    })
//    @PostMapping("/{questionUuid}/answer-from-template/{answerTemplateUuid}")
//    @PreAuthorize("@permissionHandler.canEditQuestion(#questionUuid)")
//    public ResponseEntity<List<AnswerOptionDTO>> addAnswerFromTemplate(@AuthenticationPrincipal JWTUser user,
//                                                           @ApiParam(required = true) @PathVariable String questionUuid,
//                                                           @ApiParam(required = true) @PathVariable String answerTemplateUuid) {
//
//        List<AnswerOptionDTO> answerOptions = answerOptionService.addAnswerFromTemplate(questionUuid, answerTemplateUuid);
//        return ResponseEntity.ok(answerOptions);
//    }
//
//
//
//
//}
