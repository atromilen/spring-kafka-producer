package cl.atromilen.springkafkaproducer.controlleradvice;

import cl.atromilen.springkafkaproducer.controlleradvice.errors.ErrorMessage;
import cl.atromilen.springkafkaproducer.controlleradvice.errors.FieldWithError;
import cl.atromilen.springkafkaproducer.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    private static final String LOG_PATTERN_MSG = "ControllerAdvice: {}";
    private static final int ILLEGAL_ARGUMENT_CODE = 2002;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> onMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(LOG_PATTERN_MSG, e.getMessage());
        List<FieldWithError> fieldWithErrorList = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> {
                    var fieldError = (FieldError) objectError;
                    var fieldName = Utils.convertCamelCaseToSnakeCase(fieldError.getField());
                    return new FieldWithError(fieldName, fieldError.getDefaultMessage());
                }).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ErrorMessage(ILLEGAL_ARGUMENT_CODE, fieldWithErrorList));
    }

}
