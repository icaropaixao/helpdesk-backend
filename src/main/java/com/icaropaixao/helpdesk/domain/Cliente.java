package com.icaropaixao.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icaropaixao.helpdesk.domain.dtos.ClienteDTO;
import com.icaropaixao.helpdesk.domain.dtos.TecnicoDTO;
import com.icaropaixao.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    // construtor
    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);

    }
    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();

        //Obtém os perfis do objeto 'obj', transforma cada perfil no seu código,
        // e coleta todos os códigos em um conjunto (Set) para armazenar em 'this.perfis'
        this.perfis = obj.getPerfis().stream().map( x -> x.getCodigo()).collect(Collectors.toSet());

        this.dataCriacao = obj.getDataCriacao();

    }

    // gets e sets para a lista de chamados associados ao cliente
    public List<Chamado> getChamados() {
        return chamados;
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }


}
