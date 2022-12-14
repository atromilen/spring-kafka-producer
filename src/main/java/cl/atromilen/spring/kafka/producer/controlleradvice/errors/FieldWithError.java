package cl.atromilen.spring.kafka.producer.controlleradvice.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldWithError {
    private String field;
    private String message;
}
