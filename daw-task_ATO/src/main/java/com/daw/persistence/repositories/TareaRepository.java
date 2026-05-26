package com.daw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.TareaEntity;
import com.daw.persistence.entities.enums.EstadoTarea;

public interface TareaRepository extends JpaRepository<TareaEntity, Long> {

	//Filtrar por estado
	List<TareaEntity> findByEstado(EstadoTarea estado);
	
	//Filtrar por fecha anterior a la de hoy (vencidas)
	List<TareaEntity> findByFechaVencimientoBefore(LocalDate fecha);
	
	//Filtrar por fecha mayor o igual a la de hoy (no vencidas)
	List<TareaEntity> findByFechaVencimientoGreaterThanEqual(LocalDate fecha);
	
	//Filtrar por título (que contenga el String del título ignorando mayus/minus)
	List<TareaEntity> findByTituloContainingIgnoreCase(String titulo);
	
}
