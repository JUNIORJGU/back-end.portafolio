package com.gorazer.mail_service.controller;

import com.gorazer.mail_service.dto.ContactRequest;
import com.gorazer.mail_service.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://juniorjgu.dev")
public class ContactController {
	
	@Autowired
    private EmailService emailService;
	
    @PostMapping("/contact")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody ContactRequest request) {
        

        if (request.getHoneypot() != null && !request.getHoneypot().isBlank()) {

            return ResponseEntity.ok("Mensaje recibido"); 
        }


        try {
            emailService.sendSimpleEmail(
                "juniorjguosrs@gmail.com", 
                "Nuevo contacto: " + request.getName(), 
                "De: " + request.getEmail() + "\n\nMensaje: " + request.getMessage()
            );
            return ResponseEntity.ok("Email enviado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo");
        }
	}
  }