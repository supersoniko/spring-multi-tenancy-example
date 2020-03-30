package com.sachareinert.springliquibase.repositories;

import com.sachareinert.springliquibase.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}

