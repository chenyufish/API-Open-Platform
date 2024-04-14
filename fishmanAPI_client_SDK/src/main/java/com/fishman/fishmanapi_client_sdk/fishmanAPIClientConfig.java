package com.fishman.fishmanapi_client_sdk;

import com.fishman.fishmanapi_client_sdk.client.FishmanAPIClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * fishmanAPI客户端配置
 *
 */
@Configuration
@ConfigurationProperties("fishman.client")
@Data
@ComponentScan
public class fishmanAPIClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public FishmanAPIClient fishmanAPIClient() {
        return new FishmanAPIClient(accessKey, secretKey);
    }

}
