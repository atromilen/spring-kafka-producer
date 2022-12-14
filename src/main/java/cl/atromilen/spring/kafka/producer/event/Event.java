package cl.atromilen.spring.kafka.producer.event;

import lombok.Data;

import java.time.Instant;

@Data
public class Event<T> {
    private final Instant date = Instant.now();
    private T data;
}
