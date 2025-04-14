package com.example.demo.minio;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

/** * @Author: heyuhua * @Date: 2021/1/12 10:42 */
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    /** * endPoint is a URL, domain name, IPv4 or IPv6 address */
    private String endpoint = "localhost";

    /** * Port */
    private int port = 9000;

    /** * AccessKeys are similar to user ids and are used to uniquely identify your account */
    private String accessKey = "tryer";

    /**
     * secretKey是你账户的密码
     */
    private String secretKey = "tryadmin";

    /** * If true, HTTPS is used instead of HTTP. The default is true */
    private Boolean secure = false;

    /** * Default bucket */
    private String bucketName = "mybucket";

    /** * Configure directory */
    private String configDir;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(endpoint, port, secure)
                .credentials(accessKey, secretKey)
                .build();
    }
}
