package com.fishman.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**

 * @Description: 电子邮件配置
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class EmailConfig {
    private String emailFrom = "cy13692810010@163.com";
}
