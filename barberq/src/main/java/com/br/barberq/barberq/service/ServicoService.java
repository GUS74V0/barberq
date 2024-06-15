package com.br.barberq.barberq.service;

import com.br.barberq.barberq.model.Servico;
import com.br.barberq.barberq.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico salvarServico(Servico servico) {
        servico.setStatus("PENDENTE");
        return servicoRepository.save(servico);
    }

    public Servico atualizarStatus(Long id, String status) {
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        servico.setStatus(status);
        return servicoRepository.save(servico);
    }

    public boolean existsByDescricao(String descricao) {
        return servicoRepository.existsByDescricao(descricao);
    }

    public List<Servico> listarServicosPendentes() {
        return servicoRepository.findByStatus("PENDENTE");
    }
}
