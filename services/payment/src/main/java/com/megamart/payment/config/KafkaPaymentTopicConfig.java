package com.megamart.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopicConfig {

    public NewTopic paymentTopic() {
        return TopicBuilder.name("payment-topic").build();
    }

}
