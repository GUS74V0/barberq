package com.br.barberq.barberq.controller;

import com.br.barberq.barberq.model.Horario;
import com.br.barberq.barberq.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> getAllHorariosDisponiveis() {
        List<Horario> horarios = horarioService.findAllHorariosDisponiveis();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Horario>> getHorariosDisponiveisPorData(@RequestParam("data") String dataStr) {
        LocalDate data = LocalDate.parse(dataStr); // Converter String para LocalDate
        List<Horario> horarios = horarioService.findHorariosDisponiveisPorData(data);
        return ResponseEntity.ok(horarios);
    }

    @PutMapping("/{horarioId}")
    public ResponseEntity<Horario> reservarHorario(@PathVariable Long horarioId) {
        Horario horarioReservado = horarioService.reservarHorario(horarioId);
        if (horarioReservado != null) {
            return ResponseEntity.ok(horarioReservado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
