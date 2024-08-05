package com.elyashevich.generator_microservices.web.mapper;

public interface Mappable<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}
