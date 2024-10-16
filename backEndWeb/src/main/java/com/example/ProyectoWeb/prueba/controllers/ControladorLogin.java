package com.example.ProyectoWeb.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProyectoWeb.service.ServicioLogin;
import com.example.ProyectoWeb.dto.LoginDTO;
import com.example.ProyectoWeb.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.exception.CorreoNoExistenteException;
import com.example.ProyectoWeb.model.RedirectResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/iniciar-sesion")
public class ControladorLogin {
    
    private final ServicioLogin servicioLogin;

    public ControladorLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }
    
    @PostMapping
    public ResponseEntity<?> loginUsuario(@RequestBody LoginDTO loginDTO) {
        try {
            RespuestaLoginDTO login = servicioLogin.loginUser(loginDTO);
            String ruta;

            if (login.isArrendador()) {
                ruta = "/arrendador/" + login.getId();
            } else {
                ruta = "/arrendatario/" + login.getId();
            }
            // Envolver la URL en un objeto JSON
            RedirectResponse response = new RedirectResponse(ruta);
            return ResponseEntity.ok(response); // Retornar el objeto JSON

        } catch (CorreoNoExistenteException e) {
            // Crear un objeto JSON para el mensaje de error
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        
        } catch (Exception e) {
            // Crear un objeto JSON para un error genérico
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Hubo un problema con el inicio de sesión. Por favor, inténtelo de nuevo más tarde.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
