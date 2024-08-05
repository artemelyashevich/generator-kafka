package com.elyashevich.generator_microservices.service;

import com.elyashevich.generator_microservices.model.test.DataTestOptions;

public interface TestDataService {
    void send(DataTestOptions dataTestOptions);
}
