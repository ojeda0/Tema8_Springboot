package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.JuegoEntity;

public interface JuegoRepository extends JpaRepository<JuegoEntity, Long> {

}
