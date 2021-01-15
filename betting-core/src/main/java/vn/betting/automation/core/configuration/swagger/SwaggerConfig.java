package vn.betting.automation.core.configuration.swagger;

import com.fasterxml.classmate.TypeResolver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket ticketInAppApi() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new ParameterBuilder().name("apiKey").description("Api Key")
        .modelRef(new ModelRef("string")).parameterType("header").required(true)
        .defaultValue("0xsqZRenO4").build());

    TypeResolver typeResolver = new TypeResolver();
    return new Docket(DocumentationType.SPRING_WEB).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build().pathMapping("/")
        .directModelSubstitute(LocalDate.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class)
        .alternateTypeRules(new AlternateTypeRule(
            typeResolver.resolve(DeferredResult.class,
                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
            typeResolver.resolve(WildcardType.class)))
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET,
            Arrays.asList(new ResponseMessageBuilder().code(200).message("200 OK")
                .responseModel(new ModelRef("Error")).build()))
        .enableUrlTemplating(false).globalOperationParameters(parameters)
        .tags(new Tag("ZaloPay Issue Catcher Services", "All apis relate to ZaloPay Issue Catcher"));
  }
}
