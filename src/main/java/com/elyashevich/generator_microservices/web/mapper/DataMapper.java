package com.elyashevich.generator_microservices.web.mapper;

import com.elyashevich.generator_microservices.model.Data;
import com.elyashevich.generator_microservices.web.dto.DataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {
}
