package com.br.barberq.barberq.controller;

import com.br.barberq.barberq.model.Barbeiro;
import com.br.barberq.barberq.model.Cliente;
import com.br.barberq.barberq.model.Barbearia;
import com.br.barberq.barberq.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login_barbeiro")
    public ResponseEntity<Map<String, Object>> loginBarbeiro(@RequestBody Map<String, String> body) {
        Optional<Barbeiro> barbeiro = authService.loginBarbeiro(body.get("email"), body.get("senha"));
        if (barbeiro.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", barbeiro.get().getId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas. Verifique o e-mail e a senha."));
    }

    @PostMapping("/login_cliente")
    public ResponseEntity<Map<String, Object>> loginCliente(@RequestBody Map<String, String> body) {
        Optional<Cliente> cliente = authService.loginCliente(body.get("email"), body.get("senha"));
        if (cliente.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", cliente.get().getId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas. Verifique o e-mail e a senha."));
    }

    @PostMapping("/login_barbearia")
    public ResponseEntity<Map<String, Object>> loginBarbearia(@RequestBody Map<String, String> body) {
        Optional<Barbearia> barbearia = authService.loginBarbearia(body.get("email"), body.get("senha"));
        if (barbearia.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", barbearia.get().getId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas. Verifique o e-mail e a senha."));
    }
}
