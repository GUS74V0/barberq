package com.br.barberq.barberq.model;

import com.br.barberq.barberq.repository.ClienteRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Auth {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Cliente authenticate(String email, String password) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(email);
        if (optionalCliente.isPresent() && passwordEncoder.matches(password, optionalCliente.get().getPassword())) {
            return optionalCliente.get();
        }
        return null;
    }
}
