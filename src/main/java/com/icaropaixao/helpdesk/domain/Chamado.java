package com.icaropaixao.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icaropaixao.helpdesk.domain.enums.Prioridade;
import com.icaropaixao.helpdesk.domain.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAbertura = LocalDate.now() ; // quando um chamado for aberto ele pega a data do momento atual

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeFechamento;

    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    // ASSOCIAÇÕES  (MUITOSCHAMADOS PARA 1 TECNICO - MANY TO ONE)
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado() {
        super();
    }

    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    // gets sets
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataDeAbertura() {
        return dataDeAbertura;
    }

    public void setDataDeAbertura(LocalDate dataDeAbertura) {
        this.dataDeAbertura = dataDeAbertura;
    }

    public LocalDate getDataDeFechamento() {
        return dataDeFechamento;
    }

    public void setDataDeFechamento(LocalDate dataDeFechamento) {
        this.dataDeFechamento = dataDeFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // hashCode equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(id, chamado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
