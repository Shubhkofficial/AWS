package com.shubham.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AWSCloudUtil {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    private AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }
    @Bean
    public AmazonS3 awsS3ClientBuilder() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(Regions.US_EAST_2).build();
        return s3Client;
    }
}

// @Configuration
// public class AwsSQSConfig {

// @Value("${cloud.aws.region.static}")
// private String region;

// @Value("${cloud.aws.credentials.access-key}")
// private String accessKey;

// @Value("${cloud.aws.credentials.secret-key}")
// private String secret;

// @Bean
// public QueueMessagingTemplate queueMessagingTemplate() {
// return new QueueMessagingTemplate(amazonSQSAsync());
// }

// @Primary
// @Bean
// public AmazonSQSAsync amazonSQSAsync() {
// return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1)
// .withCredentials(new AWSStaticCredentialsProvider(new
// BasicAWSCredentials(accessKey, secret)))
// .build();
// }
// }
