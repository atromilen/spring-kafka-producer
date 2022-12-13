package cl.atromilen.springkafkaproducer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {

    private final ConfigProperties configProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(
                Map.of(
                        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, configProperties.getBootstrapServers()
                )
        );
    }

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder
                .name(configProperties.getTopicName())
                .partitions(configProperties.getTopicPartitions())
                .replicas(configProperties.getTopicReplicas())
                .build();
    }

}
