package com.elyashevich.generator_microservices.web.dto;

import com.elyashevich.generator_microservices.model.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DataTestOptionsDto {

    private int delayInSeconds;

    private Data.MeasurementType[] measurementTypes;
}
