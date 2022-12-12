package cl.atromilen.springkafkaproducer.service;

import cl.atromilen.springkafkaproducer.config.ConfigProperties;
import cl.atromilen.springkafkaproducer.event.Event;
import cl.atromilen.springkafkaproducer.event.MessageForEmailing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Event<?>> kafkaTemplate;
    private final ConfigProperties properties;

    public KafkaProducerService(KafkaTemplate<String, Event<?>> kafkaTemplate, ConfigProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    public void send(MessageForEmailing message) throws RuntimeException{
        Event<MessageForEmailing> event = new Event<>();
        event.setData(message);

        var key = "msg-" + UUID.randomUUID();

        kafkaTemplate
                .send(properties.getTopicName(), key, event)
                .addCallback(this::onSuccess, this::onFailure);
    }

    private void onSuccess(SendResult<String, Event<?>> result) {
        LOGGER.info("Message has been written successfully to topic {} into partition {} with key {}.",
                result.getRecordMetadata().topic(),
                result.getRecordMetadata().partition(),
                result.getProducerRecord().key());
    }

    private void onFailure(Throwable ex) {
        LOGGER.error("Failure sending message to Kafka topic {}", properties.getTopicName(), ex);
        throw new RuntimeException(ex.getMessage(), ex);
    }
}
