package cl.atromilen.springkafkaproducer.controlleradvice.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorMessage {
    private int errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errorDetails;

    public ErrorMessage(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorMessage(int errorCode, Object errorDetails) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }
}
