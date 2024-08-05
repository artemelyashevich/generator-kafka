package com.elyashevich.generator_microservices.web.controller;

import com.elyashevich.generator_microservices.model.Data;
import com.elyashevich.generator_microservices.model.test.DataTestOptions;
import com.elyashevich.generator_microservices.service.KafkaDataService;
import com.elyashevich.generator_microservices.service.TestDataService;
import com.elyashevich.generator_microservices.web.dto.DataDto;
import com.elyashevich.generator_microservices.web.dto.DataTestOptionsDto;
import com.elyashevich.generator_microservices.web.mapper.DataMapper;
import com.elyashevich.generator_microservices.web.mapper.DataTestOptionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final KafkaDataService kafkaDataService;
    private final TestDataService testDataService;

    private final DataMapper dataMapper;
    private final DataTestOptionsMapper dataTestOptionsMapper;

    @PostMapping("/send")
    public void send(final @RequestBody DataDto dto) {
        final Data data = this.dataMapper.toEntity(dto);
        this.kafkaDataService.send(data);
    }

    @PostMapping("test/send")
    public void testSend(final @RequestBody DataTestOptionsDto dto) {
        final DataTestOptions dataTestOptions = this.dataTestOptionsMapper.toEntity(dto);
        this.testDataService.send(dataTestOptions);
    }
}
