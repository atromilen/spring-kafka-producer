package cl.atromilen.spring.kafka.producer.event;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageForEmailing {
    @NotBlank(message = "email is required and can't be null nor empty.")
    private String email;
    @NotBlank(message = "message_body is required and can't be null nor empty.")
    private String messageBody;
}
