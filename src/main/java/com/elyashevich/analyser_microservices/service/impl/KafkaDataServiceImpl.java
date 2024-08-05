package com.elyashevich.analyser_microservices.service.impl;

import com.elyashevich.analyser_microservices.model.Data;
import com.elyashevich.analyser_microservices.service.KafkaDataService;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataServiceImpl implements KafkaDataService {

    @Override
    public void handle(Data data) {
        System.out.printf("Data object is receives: %s%n%n", data.toString());
    }
}
