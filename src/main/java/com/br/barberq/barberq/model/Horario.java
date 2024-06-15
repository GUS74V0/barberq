package com.br.barberq.barberq.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horario;
    private boolean disponivel;
    private Long idServico;
    private long idBarbeiro;
    private long idCliente;
    private long idAgendamento;
    private long idBarbearia;
    private LocalDate data;

    public Horario() {

    }

    // #region Getters e Setters
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public long getIdBarbeiro() {
        return idBarbeiro;
    }

    public void setIdBarbeiro(long idBarbeiro) {
        this.idBarbeiro = idBarbeiro;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public long getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(long idBarbearia) {
        this.idBarbearia = idBarbearia;
    }

    // #endregion Getters e Setters

}