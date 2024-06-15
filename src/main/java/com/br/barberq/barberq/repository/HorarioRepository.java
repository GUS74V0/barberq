package com.br.barberq.barberq.repository;

import com.br.barberq.barberq.model.Horario;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

List<Horario> findAllByDataAndDisponivel(LocalDate data, boolean disponivel);
}
