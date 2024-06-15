package com.br.barberq.barberq.service;

import com.br.barberq.barberq.model.Barbeiro;
import com.br.barberq.barberq.repository.BarbeiroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class BarbeiroService {
    private static final Logger logger = LoggerFactory.getLogger(BarbeiroService.class);


    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private NotificationService notificationService;

    public Barbeiro save(Barbeiro barbeiro) {
        return barbeiroRepository.save(barbeiro);
    }

    public boolean existsByEmailAndBarbeariaId(String email, Long barbeariaId) {
        return barbeiroRepository.existsByEmailAndBarbeariaId(email, barbeariaId);
    }

    public List<Barbeiro> listarSolicitacoesPendentes() {
        return barbeiroRepository.findByStatus("Pendente");
    }

    public Barbeiro aprovarSolicitacao(Long id) {
        Barbeiro barbeiro = barbeiroRepository.findById(id).orElseThrow();
        barbeiro.setStatus("Aprovado");
        Barbeiro atualizado = barbeiroRepository.save(barbeiro);
        enviarEmailAprovacao(barbeiro);
        return atualizado;
    }

    public Barbeiro rejeitarSolicitacao(Long id) {
        Barbeiro barbeiro = barbeiroRepository.findById(id).orElseThrow();
        barbeiro.setStatus("Rejeitado");
        return barbeiroRepository.save(barbeiro);
    }


    private void enviarEmailAprovacao(Barbeiro barbeiro) {
        String to = barbeiro.getEmail();
        String subject = "Aprovação de Cadastro";
        String text = "Olá " + barbeiro.getNome() + ",\n\nSeu cadastro como barbeiro foi aprovado.\n\nAtenciosamente,\nEquipe BarberQ";

        logger.info("Enviando e-mail para: " + to);

        notificationService.sendNotification(to, subject, text);
    }

    public Long findBarbeariaIdByBarbeiroId(Long barbeiroId) {
        Optional<Long> optionalBarbeariaId = barbeiroRepository.findBarbeariaIdByBarbeiroId(barbeiroId);
        return optionalBarbeariaId.orElse(null);
    }

    public Long findBarbeiroIdByEmail(String email) {
        Optional<Barbeiro> barbeiroOptional = barbeiroRepository.findByEmail(email);
        return barbeiroOptional.map(Barbeiro::getId).orElse(null);
    }

    public Barbeiro findById(Long id) {
        Optional<Barbeiro> optionalBarbeiro = barbeiroRepository.findById(id);
        return optionalBarbeiro.orElse(null);
    }
}
