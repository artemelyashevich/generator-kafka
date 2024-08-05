package com.elyashevich.analyser_microservices.repository;

import com.elyashevich.analyser_microservices.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}
