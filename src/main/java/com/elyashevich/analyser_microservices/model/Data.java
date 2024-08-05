package com.elyashevich.analyser_microservices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    private Long sensorId;

    private LocalDateTime timestamp;

    private Double measurement;

    private MeasurementType measurementType;

    public enum MeasurementType {
        TEMPERATURE, VOLTAGE, POWER
    }
}
