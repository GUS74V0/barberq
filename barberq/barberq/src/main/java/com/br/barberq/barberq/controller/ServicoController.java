package com.br.barberq.barberq.controller;

import com.br.barberq.barberq.model.Servico;
import com.br.barberq.barberq.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Collections;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/cadastrar")
    public String cadastrarServico() {
        return "redirect:/servico.html";
    }

    @PostMapping
    public ResponseEntity<?> criarServico(@RequestBody Servico servico) {
        try {
            if (servicoService.existsByDescricao(servico.getDescricao())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", "Serviço já existe"));
            }
            Servico salvo = servicoService.salvarServico(servico);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Erro ao cadastrar serviço"));
        }
    }

    @GetMapping("/solicitacoes")
    public ResponseEntity<List<Servico>> listarSolicitacoes() {
        List<Servico> solicitacoes = servicoService.listarServicosPendentes();
        return ResponseEntity.ok(solicitacoes);
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<?> aprovarSolicitacao(@PathVariable Long id) {
        try {
            Servico servico = servicoService.atualizarStatus(id, "APROVADO");
            return ResponseEntity.ok(servico);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao aprovar solicitação", e);
        }
    }

    @PostMapping("/rejeitar/{id}")
    public ResponseEntity<?> rejeitarSolicitacao(@PathVariable Long id) {
        try {
            Servico servico = servicoService.atualizarStatus(id, "REJEITADO");
            return ResponseEntity.ok(servico);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao rejeitar solicitação", e);
        }
    }

    @GetMapping("/aprovar")
    public String paginaAprovacao() {
        return "redirect:/approvalServico.html";
    }
}
