package net.balmir.kafkaspringcloudstream.controllers;


import net.balmir.kafkaspringcloudstream.events.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageEventController {
    private StreamBridge streamBridge;
    @Autowired
    private InteractiveQueryService interactiveQueryService;

    public PageEventController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

}
