package com.icaropaixao.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icaropaixao.helpdesk.domain.Chamado;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeAbertura = LocalDate.now() ;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeFechamento;
    @NotNull(message = "O campo PRIORIDADE é obrigatório!")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS é obrigatório!")
    private Integer status;
    @NotNull(message = "O campo TÍTULO é obrigatório!")
    private String titulo;
    @NotNull(message = "O campo OBSERVAÇÕES é obrigatório!")
    private String observacoes;

    @NotNull(message = "O campo TÉCNICO é obrigatório!")
    private Integer tecnico;

    @NotNull(message = "O campo CLIENTE é obrigatório!")
    private Integer cliente;

    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataDeAbertura = obj.getDataDeAbertura();
        this.dataDeFechamento = obj.getDataDeFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getCliente().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    // GETS and setss
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
    public Integer getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
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
    public Integer getTecnico() {
        return tecnico;
    }
    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }
    public Integer getCliente() {
        return cliente;
    }
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
    public String getNomeTecnico() {
        return nomeTecnico;
    }
    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }



}
