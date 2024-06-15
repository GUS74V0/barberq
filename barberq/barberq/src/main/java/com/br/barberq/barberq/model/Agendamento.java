package com.br.barberq.barberq.model;

import jakarta.persistence.*;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_horario")
    private Long idHorario;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_barbeiro")
    private Long idbarbeiro;

    @Column(name = "id_barbearia")
    private Long idBarbearia;

    @Column(name = "id_servico")
    private Long idServico;

    private String data;

    // Getters e Setters

    // Relacionamento com Horario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_horario", referencedColumnName = "id", insertable = false, updatable = false)
    private Horario horario;

    // Relacionamento com Barbearia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barbearia", referencedColumnName = "id", insertable = false, updatable = false)
    private Barbearia barbearia;

    // Relacionamento com barbeiro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barbeiro", referencedColumnName = "id", insertable = false, updatable = false)
    private Barbeiro barbeiro;

    // Relacionamento com Servico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servico", referencedColumnName = "id", insertable = false, updatable = false)
    private Servico servico;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdbarbeiro() {
        return idbarbeiro;
    }

    public void setIdbarbeiro(Long idbarbeiro) {
        this.idbarbeiro = idbarbeiro;
    }

    public Long getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(Long idBarbearia) {
        this.idBarbearia = idBarbearia;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // toString, equals, hashCode - opcional, mas recomendado

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", idHorario=" + idHorario +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", idCliente=" + idCliente +
                ", idbarbeiro=" + idbarbeiro +
                ", idBarbearia=" + idBarbearia +
                ", idServico=" + idServico +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Agendamento that = (Agendamento) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
