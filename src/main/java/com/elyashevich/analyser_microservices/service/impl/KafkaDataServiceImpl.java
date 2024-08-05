package com.elyashevich.analyser_microservices.service.impl;

import com.elyashevich.analyser_microservices.model.Data;
import com.elyashevich.analyser_microservices.repository.DataRepository;
import com.elyashevich.analyser_microservices.service.KafkaDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final DataRepository dataRepository;

    @Override
    public void handle(Data data) {
        log.info("Data {} was saved", data);
        this.dataRepository.save(data);
    }
}
