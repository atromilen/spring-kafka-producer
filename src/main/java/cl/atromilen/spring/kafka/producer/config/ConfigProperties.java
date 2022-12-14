package cl.atromilen.spring.kafka.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.producer")
@Data
public class ConfigProperties {
    private String bootstrapServers;
    private String topicName;
    private Integer topicPartitions;
    private Integer topicReplicas;
}
