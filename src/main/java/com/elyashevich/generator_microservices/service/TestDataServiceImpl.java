package com.elyashevich.generator_microservices.service;

import com.elyashevich.generator_microservices.model.Data;
import com.elyashevich.generator_microservices.model.test.DataTestOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final KafkaDataService kafkaDataService;

    @Override
    public void send(DataTestOptions dataTestOptions) {
        if (dataTestOptions.getMeasurementTypes().length > 0) {
            this.executorService.scheduleAtFixedRate(
                    () -> {
                        final Data data = new Data();
                        data.setSensorId(
                                (long) getRandomNumber(1, 10)
                        );
                        data.setMeasurement(
                                this.getRandomNumber(15, 100)
                        );
                        data.setMeasurementType(
                                getRandomMeasurementType(
                                        dataTestOptions.getMeasurementTypes()
                                )
                        );
                        data.setTimestamp(
                                LocalDateTime.now()
                        );
                        this.kafkaDataService.send(data);
                    },
                    0,
                    dataTestOptions.getDelayInSeconds(),
                    TimeUnit.SECONDS
            );
        }
    }

    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    private Data.MeasurementType getRandomMeasurementType(
            Data.MeasurementType[] measurementTypes
    ) {
        final int randomTypeId = (int) (Math.random() * measurementTypes.length);
        return measurementTypes[randomTypeId];
    }
}
