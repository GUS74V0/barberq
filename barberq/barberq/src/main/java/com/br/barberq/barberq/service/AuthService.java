package com.br.barberq.barberq.service;

import com.br.barberq.barberq.model.Barbeiro;
import com.br.barberq.barberq.model.Cliente;
import com.br.barberq.barberq.model.Barbearia;
import com.br.barberq.barberq.repository.BarbeiroRepository;
import com.br.barberq.barberq.repository.ClienteRepository;
import com.br.barberq.barberq.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private BarbeiroRepository BarbeiroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<Barbeiro> loginBarbeiro(String email, String senha) {
        Optional<Barbeiro> Barbeiro = BarbeiroRepository.findByEmail(email);
        if (Barbeiro.isPresent() && passwordEncoder.matches(senha, Barbeiro.get().getSenha())) {
            return Barbeiro;
        }
        return Optional.empty();
    }

    public Optional<Cliente> loginCliente(String email, String senha) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent() && passwordEncoder.matches(senha, cliente.get().getPassword())) {
            return cliente;
        }
        return Optional.empty();
    }

    public Optional<Barbearia> loginBarbearia(String email, String senha) {
        Optional<Barbearia> barbearia = barbeariaRepository.findByEmail(email);
        if (barbearia.isPresent() && passwordEncoder.matches(senha, barbearia.get().getSenha())) {
            return barbearia;
        }
        return Optional.empty();
    }
}
