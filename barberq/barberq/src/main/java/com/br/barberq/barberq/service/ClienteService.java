package com.br.barberq.barberq.service;

import com.br.barberq.barberq.model.Cliente;
import com.br.barberq.barberq.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente authenticate(String email, String password) {
        Optional<Cliente> optionalCliente = clienteRepository.findByEmail(email);
        if (optionalCliente.isPresent() && optionalCliente.get().getPassword().equals(password)) {
            return optionalCliente.get();
        }
        return null;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // You can add more methods as needed
}
