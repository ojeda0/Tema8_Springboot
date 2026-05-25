package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.JuegoEntity;
import com.daw.persistence.entities.enums.TipoJuego;

public interface JuegoRepository extends JpaRepository<JuegoEntity, Long> {

	//Filtrar por género
	List<JuegoEntity> findByGeneroContainingIgnoreCase(String genero);
	
	//Filtrar por nombre
	List<JuegoEntity> findByNombreContainingIgnoreCase(String nombre);
	
	//Filtrar por plataforma
	List<JuegoEntity> findByPlataformasContainingIgnoreCase(String plataformas);
	
	//Filtrar por tipo
	List<JuegoEntity> findByTipo(TipoJuego tipo);
	
	//Filtrar por rango de precio
	List<JuegoEntity> findByPrecioBetween(double precioMin, double precioMax);
	
	//Filtrar el top 5 de juegos descargados
	List<JuegoEntity> findTop5ByOrderByDescargasDesc();
	
}
