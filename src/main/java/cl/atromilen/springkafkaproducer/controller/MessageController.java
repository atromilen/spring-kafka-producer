package cl.atromilen.springkafkaproducer.controller;

import cl.atromilen.springkafkaproducer.event.MessageForEmailing;
import cl.atromilen.springkafkaproducer.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/messages")
public class MessageController {

    private KafkaProducerService producerService;

    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/save")
    public void saveMessage(@RequestBody MessageForEmailing message){
        producerService.send(message);
    }

}
