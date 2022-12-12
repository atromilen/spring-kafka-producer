package cl.atromilen.springkafkaproducer.event;

import lombok.Data;

import java.time.Instant;

@Data
public class Event<T> {
    private final Instant date = Instant.now();
    private T data;
}
