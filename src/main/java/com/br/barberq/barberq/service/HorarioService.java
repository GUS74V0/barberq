package com.br.barberq.barberq.service;

import com.br.barberq.barberq.model.Horario;
import com.br.barberq.barberq.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> findAllHorariosDisponiveis() {
        return horarioRepository.findAll();
    }

    public List<Horario> findHorariosDisponiveisPorData(LocalDate data) {
        return horarioRepository.findAllByDataAndDisponivel(data, true);
    }

    public Horario reservarHorario(Long horarioId) {
        Optional<Horario> optionalHorario = horarioRepository.findById(horarioId);
        if (optionalHorario.isPresent()) {
            Horario horario = optionalHorario.get();
            if (horario.isDisponivel()) {
                horario.setDisponivel(false);
                return horarioRepository.save(horario);
            } else {
                return null; // Horário já reservado
            }
        } else {
            return null; // Horário não encontrado
        }
    }
}
