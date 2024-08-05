package com.elyashevich.generator_microservices.service;

import com.elyashevich.generator_microservices.model.Data;

public interface KafkaDataService {

    void send(Data data);
}
