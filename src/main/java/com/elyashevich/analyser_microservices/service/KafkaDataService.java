package com.elyashevich.analyser_microservices.service;

import com.elyashevich.analyser_microservices.model.Data;

public interface KafkaDataService {

    void handle(Data data);
}
