package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Agregada esta importación
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.app.entity.Calificacion;
import com.parcial.app.entity.Estudiante;
import com.parcial.app.exception.NotFoundException;
import com.parcial.app.repository.CalificacionRepository;
import com.parcial.app.repository.EstudianteRepository;

@Controller // Asegúrate de agregar la anotación @Controller
@RequestMapping("/calificaciones")
public class CalificacionTemplateController {
	@Autowired
	private CalificacionRepository calificacionRepository;
	
	@Autowired
	private EstudianteRepository estudianteRepository;

	@GetMapping("/")
	public String calificacionListTemplate(Model model) {
		model.addAttribute("calificaciones", calificacionRepository.findAll());
		return "calificaciones-list";
	}
	

	@GetMapping("/new")
	public String calificacionesNewTemplate(Model model, Model estudianteModel) {
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		estudianteModel.addAttribute("estudiantes", estudiantes);
		model.addAttribute("calificacion", new Calificacion());
		return "calificaciones-form";
	}

	@GetMapping("/edit/{id}")
	public String calificacionEditTemplate(@PathVariable("id") String id, Model model, Model estudianteModel) {
		model.addAttribute("calificacion",
				calificacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Calificación no encontrado")));
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		estudianteModel.addAttribute("estudiantes", estudiantes);
		return "calificaciones-form";
	}

	@PostMapping("/save")
	public String calificacionesSaveProcess(@ModelAttribute("calificacion") Calificacion Calificacion) {
		if (Calificacion.getId().isEmpty()) {
			Calificacion.setId(null);
		}
		calificacionRepository.save(Calificacion);
		return "redirect:/calificaciones/";
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String calificacionesDeleteProcess(@PathVariable("id") String id) {
		calificacionRepository.deleteById(id);
		return "redirect:/calificaciones/";
	}
	
	@GetMapping("/borrar/{id}")
	public String estudiantesBorrarProcess(@PathVariable("id") String id) {
		calificacionRepository.deleteById(id);
		return "redirect:/calificaciones/niveles";
	}
}
