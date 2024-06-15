package com.br.barberq.barberq.service;

import com.br.barberq.barberq.model.Barbearia;
import com.br.barberq.barberq.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public Barbearia save(Barbearia barbearia) {
        return barbeariaRepository.save(barbearia);
    }

    public boolean existsByFields(String nome, String email, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        return barbeariaRepository.existsByNomeAndEmailAndRuaAndNumeroAndBairroAndCidadeAndEstadoAndCep(
                nome, email, rua, numero, bairro, cidade, estado, cep
        );
    }

    public List<Barbearia> findAll() {
        return barbeariaRepository.findAll();
    }

    public Optional<Barbearia> findById(Long id) {
        return barbeariaRepository.findById(id);
    }
}
