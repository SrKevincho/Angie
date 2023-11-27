package com.parcial.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketTemplateController {

    @GetMapping("/ticket/create")
    public String showCreateTicketForm() {
        return "ticket/create-ticket"; // Nombre de la vista para crear tickets
    }

    // Otros métodos para mostrar formularios de actualización, detalles, etc.
}

