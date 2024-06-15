package com.br.barberq.barberq.repository;

import com.br.barberq.barberq.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    boolean existsByDescricao(String descricao);
    List<Servico> findByStatus(String status);
}
