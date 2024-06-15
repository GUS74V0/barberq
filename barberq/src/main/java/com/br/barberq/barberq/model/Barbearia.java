package com.br.barberq.barberq.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Barbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Rua é obrigatória")
    private String rua;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    @OneToMany(mappedBy = "barbearia")
    @JsonManagedReference
    private List<Barbeiro> barbeiros;

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Senha é obrigatória") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "Senha é obrigatória") String senha) {
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Email é obrigatório") @Email(message = "Email deve ser válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email é obrigatório") @Email(message = "Email deve ser válido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Rua é obrigatória") String getRua() {
        return rua;
    }

    public void setRua(@NotBlank(message = "Rua é obrigatória") String rua) {
        this.rua = rua;
    }

    public @NotBlank(message = "Número é obrigatório") String getNumero() {
        return numero;
    }

    public void setNumero(@NotBlank(message = "Número é obrigatório") String numero) {
        this.numero = numero;
    }

    public @NotBlank(message = "Bairro é obrigatório") String getBairro() {
        return bairro;
    }

    public void setBairro(@NotBlank(message = "Bairro é obrigatório") String bairro) {
        this.bairro = bairro;
    }

    public @NotBlank(message = "Cidade é obrigatória") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank(message = "Cidade é obrigatória") String cidade) {
        this.cidade = cidade;
    }

    public @NotBlank(message = "Estado é obrigatório") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "Estado é obrigatório") String estado) {
        this.estado = estado;
    }

    public @NotBlank(message = "CEP é obrigatório") String getCep() {
        return cep;
    }

    public void setCep(@NotBlank(message = "CEP é obrigatório") String cep) {
        this.cep = cep;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    public void setBarbeiros(List<Barbeiro> barbeiros) {
        this.barbeiros = barbeiros;
    }
}
