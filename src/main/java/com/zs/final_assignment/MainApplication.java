package com.zs.final_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Main class Driver code.
 */
@SpringBootApplication
@Configuration
@EnableSwagger2
public class MainApplication {
    /**
     * Main function.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
//                .apiInfo(metaInfo());
    }
//    private ApiInfo metaInfo() {
//
//        ApiInfo apiInfo = new ApiInfo(
//                "Atividades API REST",
//                "API REST de cadastro de atividades.",
//                "1.0",
//                "Terms of Service",
//                new Contact("JoÃ£o VR", "www.una.br/",
//                        " "),
//                "Apache License Version 2.0",
//                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
//        );
//
//        return apiInfo;
//    }
}
