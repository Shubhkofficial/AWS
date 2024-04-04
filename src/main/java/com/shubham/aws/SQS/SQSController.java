package com.shubham.aws.SQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    private String sqlurl = "https://sqs.us-east-2.amazonaws.com/771224448224/MySpring";

    @GetMapping("/send/{msg}")
    public void pubMsg(@PathVariable String msg) {
        queueMessagingTemplate.send(sqlurl, MessageBuilder.withPayload(msg == null ? "" : msg).build());
    }

    @SqsListener(value = "MySpring", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void subscribeMsg(String msg) {
        System.out.println(msg);
    }
}
