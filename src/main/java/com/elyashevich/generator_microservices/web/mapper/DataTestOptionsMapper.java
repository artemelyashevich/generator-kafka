package com.elyashevich.generator_microservices.web.mapper;

import com.elyashevich.generator_microservices.model.test.DataTestOptions;
import com.elyashevich.generator_microservices.web.dto.DataTestOptionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
