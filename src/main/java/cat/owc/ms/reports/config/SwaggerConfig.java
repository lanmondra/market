package cat.owc.ms.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
@Profile("!test")
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customImplementation(){
        Set<String> contentTypes = new HashSet<>();
        contentTypes.add("application/json");

        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(apiKey());

        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(securityContext());


        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .consumes(contentTypes)
                .produces(contentTypes)
                .securitySchemes(securitySchemes)
                .securityContexts(securityContexts)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cat.owc.ms.reports.controllers"))
                .build()
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
                .directModelSubstitute(java.sql.Timestamp.class, java.util.Date.class)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .tags(new Tag("polls", "Operaciones sobre encuestas para usuarios finales"),
                      new Tag("health", "Operaciones sobre el estado del servicio") ,
                      new Tag("forms", "Operaciones sobre formularios de encuesta o informe"),
                      new Tag("manager/polls", "Operaciones de administración de encuestas"),
                      new Tag("manager/report-templates", "Operaciones de gestión de plantillas de informe"),
                      new Tag("manager/blocks", "Operaciones de gestión de bloques"),
                      new Tag("manager/questions", "Operaciones de gestión de preguntas de encuesta"),
                      new Tag("manager/informer-types", "Operaciones de gestión de tipos de informadores"),
                      new Tag("manager/subject-types", "Operaciones de gestión de los tipos de sujetos de informes"),
                      new Tag("manager/permissions", "Operaciones sobre permisos de usuario"),
                      new Tag("manager/report-categories", "Operaciones sobre categorias de informe"),
                      new Tag("manager/campaigns", "Operaciones sobre campañas de informe"),
                      new Tag("manager/report-templates/valorations", "Operaciones sobre valoraciones de informes")


                );
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Reports Service")
                .description(getDescription())
                .license(" ")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("Optimal Way", "https://www.optimalwayconsulting.cat/", "suport@optimalwayconsulting.com"))
                .build();
    }


    private String getDescription() {

        return "API REST para la generación y gestión de informes y encuestas\n\n" +
                "Para el acceso se requiere un token JWT incluido en el header Authorization de la petición, con el formato **Bearer <jwt_token>**\n\n" +
                "El header de las peticiones debe incluir tres parametros mas:\n\n" +
                " * federation -> Con el identificador de la federación que genera la petición (fcbq, fbclm, ...)\n\n" +
                " * X-App-Origin -> Con el portal origen de la petición. Este portal determina que informes o encuestas puede ver un usuario. Valores posibles referee, club, federated, others\n\n" +
                " * Accept-Language -> Con el idioma en que se devolveran los mensajes de error\n\n\n" +
                " ## RESPUESTA EN CASO DE ERROR\n\n " +
                "En caso de respuesta con error (http status <> 2xx), la respuesta sera un objeto tipo <a href='#modelErrorResponse'>ErrorResponse</a>.\n " +
                "Los valores posibles del código de error se definen en cada endpoint\n\n";
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/auth/*.*"))
                .build();
        //return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> defaultAuthMethod = new ArrayList<>();
        defaultAuthMethod.add(new SecurityReference("Bearer", new AuthorizationScope[0]));

        return defaultAuthMethod;

    }



}
