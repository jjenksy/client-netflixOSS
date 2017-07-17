package io.jenksi.john;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by johnjenkins on 7/17/17.
 * Swagger 2 is enabled through the @EnableSwagger2 annotation.
 * After the Docket bean is defined, its select() method returns an instance of ApiSelectorBuilder, which provides a way to control the endpoints exposed by Swagger.
 * Predicates for selection of RequestHandlers can be configured with the help of RequestHandlerSelectors and PathSelectors. Using any() for both will make documentation for your entire API available through Swagger.
 * This configuration is enough to integrate Swagger 2 into existing Spring Boot project. For other Spring projects, some additional tuning is required.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    //    After the Docket bean is defined, its select() method returns an instance of ApiSelectorBuilder, which provides a way to control the endpoints exposed by Swagger.
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/jenksi/**"))//limit the endpoints exposed to swagger to the advertiser controller
                .build();
    }
}
