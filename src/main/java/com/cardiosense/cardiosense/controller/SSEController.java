package com.cardiosense.cardiosense.controller;

import com.cardiosense.cardiosense.model.SSEEntity;
import com.cardiosense.cardiosense.service.SSEService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class SSEController {
    // Server-Sent Events -> Is a server push technology enabling a client to receive automatic
    // updates from a server via HTTP connection.

    private final SSEService SSEService;
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/sse")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().name("ping").data("keep-alive"));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }, 0, 10, TimeUnit.SECONDS);

        return emitter;
    }


    @PostMapping("/sse")
    public void saveMessage(@RequestBody SSEEntity message) {
        SSEService.saveMessage(message);
        for (SseEmitter emitter : emitters) {
            try {
                emitter
                        .send(SseEmitter.event()
                                .name("newMessage")
                                .data(message.getMessage()));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }

    @DeleteMapping("/sse")
    public void deleteAllMessages() {
        SSEService.deleteAllMessages();
    }
}
