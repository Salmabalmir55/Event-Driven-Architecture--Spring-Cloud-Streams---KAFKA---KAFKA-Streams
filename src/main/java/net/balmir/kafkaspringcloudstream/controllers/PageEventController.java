package net.balmir.kafkaspringcloudstream.controllers;


import net.balmir.kafkaspringcloudstream.events.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventController {
    private StreamBridge streamBridge;
    @Autowired
    private InteractiveQueryService interactiveQueryService;

    public PageEventController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("/publish")
    public PageEvent send(@RequestParam String name, @RequestParam String topic){
        if (topic.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le paramètre 'topic' ne peut pas être vide");
        }
        PageEvent event = new PageEvent(name, Math.random()>0.5?"U1":"U2", new Date(), 10L+new Random().nextInt(1000));
        streamBridge.send(topic, event);
        return event;
    }


}
