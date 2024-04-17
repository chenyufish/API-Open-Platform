package com.fishman.fishmanapi_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FishmanApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishmanApiGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("tobaidu", r -> r.path("/baidu")
                        .uri("http://www.baidu.com"))
                .route("togithub", r -> r.host("/github")
                        .uri("http://www.github.cn"))
                .build();
    }
}
