package com.elyashevich.analyser_microservices.service.impl;

import com.elyashevich.analyser_microservices.config.LocalDateTimeDeserializer;
import com.elyashevich.analyser_microservices.model.Data;
import com.elyashevich.analyser_microservices.service.KafkaDataReceiver;
import com.elyashevich.analyser_microservices.service.KafkaDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaDataReceiverImpl implements KafkaDataReceiver {

    private final KafkaReceiver<String, Object> receiver;
    private final LocalDateTimeDeserializer localDateTimeDeserializer;
    private final KafkaDataService kafkaDataService;

    @PostConstruct
    private void init() {
        this.fetch();
    }

    @Override
    public void fetch() {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(
                        LocalDateTime.class,
                        this.localDateTimeDeserializer).create();
        this.receiver
                .receive()
                .subscribe(r -> {
                    final Data data = gson.fromJson(r.value().toString(), Data.class);
                    this.kafkaDataService.handle(data);
                    r.receiverOffset().acknowledge();
                });
    }
}
