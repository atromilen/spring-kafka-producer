package cl.atromilen.springkafkaproducer.event;

import lombok.Data;

@Data
public class MessageForEmailing {
    private String email;
    private String messageBody;
}
