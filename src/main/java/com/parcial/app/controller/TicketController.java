package com.parcial.app.controller;

import com.parcial.app.entity.Ticket;
import com.parcial.app.repository.TicketRepository;
import com.parcial.app.utilities.ListarTicketPdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
	private ListarTicketPdf listadoTicketPDF;

    @GetMapping("/tickets")
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        model.addAttribute("newTicket", new Ticket());
        return "ticketList";
    }

    @GetMapping("/add-ticket")
    public String showAddTicketForm(Model model) {
        model.addAttribute("newTicket", new Ticket());
        return "ticketForm";
    }

    @PostMapping("/add-ticket")
    public String addTicket( @ModelAttribute("newTicket") Ticket newTicket, BindingResult result) {
        if (result.hasErrors()) {
            return "ticketForm";
        }

        ticketRepository.save(newTicket);
        return "redirect:/tickets";
    }
    
    @GetMapping("/add-tick/{numeroDocumento}")
    public String showAddTickForm(@PathVariable String numeroDocumento,Model model) {
        model.addAttribute("newTicket", new Ticket());
        return "ticketFormCliente" + numeroDocumento;
    }

    @PostMapping("/add-tick/{numeroDocumento}")
    public String addTick( @PathVariable String numeroDocumento,@ModelAttribute("newTicket") Ticket newTicket, BindingResult result) {
        if (result.hasErrors()) {
            return "ticketFormCliente";
        }

        ticketRepository.save(newTicket);
        return "redirect:/resultados-estudiante" + numeroDocumento;
    }

    @GetMapping("/edit-ticket/{id}")
    public String showEditTicketForm(@PathVariable String id, Model model) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            model.addAttribute("editTicket", optionalTicket.get());
            return "editTicketForm";
        } else {
            return "redirect:/tickets";
        }
    }

    @PostMapping("/edit-ticket/{id}")
    public String editTicket(@PathVariable String id, @ModelAttribute("editTicket") Ticket editedTicket, BindingResult result) {
        if (result.hasErrors()) {
            return "editTicketForm";
        }

        editedTicket.setId(id);
        ticketRepository.save(editedTicket);
        return "redirect:/tickets";
    }

    @GetMapping("/delete-ticket/{id}")
    public String deleteTicket(@PathVariable String id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }
    
 // Metodo para generar Pdf
 		@GetMapping("/ticket/pdf")
 		public ModelAndView generarPdf(Model model) {

 			List<Ticket> listadoTickets = ticketRepository.findAll();
 			
 			// Crea el modelo con los datos que deseas pasar a la vista PDF
 			Map<String, Object> model1 = new HashMap<>();
 			model1.put("tickets", listadoTickets);

 			return new ModelAndView(listadoTicketPDF, model1);
 		}
}
