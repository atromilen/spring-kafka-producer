package cl.atromilen.springkafkaproducer.integratedtests;

import cl.atromilen.springkafkaproducer.event.MessageForEmailing;
import cl.atromilen.springkafkaproducer.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port:29092"})
@ActiveProfiles("test")
class KafkaProducerServiceTest {

    @Autowired
    private KafkaProducerService producer;

    @Test
    void whenAMessageIsProducedWithSuccess_inKafkaProducerService_thenProcessFinishNormally() throws InterruptedException {
        var messageToSend = getMessageForEmailing();
        assertDoesNotThrow(() -> producer.send(getMessageForEmailing()));
    }

    private MessageForEmailing getMessageForEmailing() {
        MessageForEmailing message = new MessageForEmailing();
        message.setEmail("mockemail@mock.mock");
        message.setMessageBody("A mock message body");
        return message;
    }

}
