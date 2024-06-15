package com.br.barberq.barberq.repository;

import com.br.barberq.barberq.model.Barbearia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {

    boolean existsByNomeAndEmailAndRuaAndNumeroAndBairroAndCidadeAndEstadoAndCep(
            String nome, String email, String rua, String numero, String bairro, String cidade, String estado, String cep
    );

    Optional<Barbearia> findByEmail(String email);
}
