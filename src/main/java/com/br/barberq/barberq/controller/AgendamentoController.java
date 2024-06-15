package com.br.barberq.barberq.controller;

import com.br.barberq.barberq.model.Agendamento;
import com.br.barberq.barberq.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.findAllAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getAgendamentoById(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.findById(id);
        if (agendamento != null) {
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento createdAgendamento = agendamentoService.save(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        Agendamento currentAgendamento = agendamentoService.findById(id);
        if (currentAgendamento != null) {
            agendamento.setId(id);
            Agendamento updatedAgendamento = agendamentoService.save(agendamento);
            return ResponseEntity.ok(updatedAgendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.findById(id);
        if (agendamento != null) {
            agendamentoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}

