package com.icaropaixao.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icaropaixao.helpdesk.domain.Tecnico;
import com.icaropaixao.helpdesk.domain.enums.Perfil;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico obj) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        // Converte os códigos armazenados no conjunto 'perfis' de volta para objetos do tipo Perfil,
        // utilizando o método 'toEnum', e retorna um conjunto (Set) contendo esses perfis
        return perfis.stream().map( x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfis) {
        this.perfis.add(perfis.getCodigo());

    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
