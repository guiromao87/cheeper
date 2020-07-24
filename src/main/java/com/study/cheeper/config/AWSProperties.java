package com.study.cheeper.config;

import com.amazonaws.regions.Regions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.s3")
public class AWSProperties {

    private String accessKey;
    private String secretKey;
    private Regions region;

    public String getAccessKey() { return accessKey; }

    public void setAccessKey(String accessKey) { this.accessKey = accessKey; }

    public String getSecretKey() { return secretKey; }

    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public Regions getRegion() { return region; }

    public void setRegion(Regions region) { this.region = region; }
}