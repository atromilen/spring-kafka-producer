package cl.atromilen.springkafkaproducer.controller;

import cl.atromilen.springkafkaproducer.event.MessageForEmailing;
import cl.atromilen.springkafkaproducer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaProducerService producerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@Valid @RequestBody MessageForEmailing message){
        producerService.send(message);
    }

}
