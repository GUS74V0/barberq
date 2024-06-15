package com.br.barberq.barberq.controller;

import com.br.barberq.barberq.model.Barbeiro;
import com.br.barberq.barberq.service.BarbeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/barbeiros")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping("/cadastrar")
    public String cadastrarBarbeiro() {
        return "redirect:/barbeiro.html";
    }

    @PostMapping
    public ResponseEntity<?> criarBarbeiro(@RequestBody Barbeiro barbeiro) {
        try {
            Barbeiro salvo = barbeiroService.save(barbeiro);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Erro ao cadastrar barbeiro"));
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> verificarDuplicidade(@RequestBody Barbeiro barbeiro) {
        boolean exists = barbeiroService.existsByEmailAndBarbeariaId(barbeiro.getEmail(),
                barbeiro.getBarbearia().getId());
        return ResponseEntity.ok().body(Collections.singletonMap("exists", exists));
    }

    @GetMapping("/solicitacoes")
    public ResponseEntity<List<Barbeiro>> listarSolicitacoes() {
        List<Barbeiro> solicitacoes = barbeiroService.listarSolicitacoesPendentes();
        return ResponseEntity.ok(solicitacoes);
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<?> aprovarSolicitacao(@PathVariable Long id) {
        try {
            Barbeiro barbeiro = barbeiroService.aprovarSolicitacao(id);
            return ResponseEntity.ok(barbeiro);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione este log para verificar a exceção
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Erro ao aprovar solicitação"));
        }
    }

    @PostMapping("/rejeitar/{id}")
    public ResponseEntity<?> rejeitarSolicitacao(@PathVariable Long id) {
        try {
            Barbeiro barbeiro = barbeiroService.rejeitarSolicitacao(id);
            return ResponseEntity.ok(barbeiro);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione este log para verificar a exceção
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Erro ao rejeitar solicitação"));
        }
    }

    @GetMapping("/aprovar")
    public String paginaAprovacao() {
        return "redirect:/approval.html";
    }

    @GetMapping("/{id}/barbearia")
    public ResponseEntity<Long> buscarIdBarbeariaPorIdBarbeiro(@PathVariable Long id) {
        try {
            Long barbeariaId = barbeiroService.findBarbeariaIdByBarbeiroId(id);
            return ResponseEntity.ok(barbeariaId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar o id da barbearia", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBarbeiroDetails(@PathVariable Long id) {
        try {
            Barbeiro barbeiro = barbeiroService.findById(id);
            if (barbeiro != null) {
                Map<String, String> barbeiroDetails = new HashMap<>();
                barbeiroDetails.put("nome", barbeiro.getNome());
                barbeiroDetails.put("email", barbeiro.getEmail());
                return ResponseEntity.ok(barbeiroDetails);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Erro ao buscar detalhes do barbeiro"));
        }
    }

}