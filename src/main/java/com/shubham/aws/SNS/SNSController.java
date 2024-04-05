package com.shubham.aws.SNS;

import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SNSController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    private static final String TOPIC_ARN = "arn:aws:sns:us-east-2:771224448224:MySNSTopic";

    @GetMapping("/addSubscription/{email}")
    public String addSubscription(@PathVariable String email) {

        SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN, "email", email);
        amazonSNSClient.subscribe(subscribeRequest);
        return "Subscribed, pls verify email";
    }

    @GetMapping("/sendNotification")
    public String sendNotif() {
        // This will send to all the subscribers of this topic
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, "Hey", "Sub");
        amazonSNSClient.publish(publishRequest);
        return "Sent!";
    }

}
