package com.parcial.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcial.app.entity.Calificacion;
import com.parcial.app.entity.Estudiante;

public interface CalificacionRepository extends MongoRepository<Calificacion, String>{
	Calificacion findByEstudiante(Estudiante estudiante);
}
