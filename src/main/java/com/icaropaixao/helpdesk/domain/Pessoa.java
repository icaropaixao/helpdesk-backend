package com.icaropaixao.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icaropaixao.helpdesk.domain.enums.Perfil;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public  abstract class Pessoa  implements Serializable {
    private static final long serialVersionUID = 1L;

    // TIPO PROTECED SIGINIFICA QUE AS CLASSES QUE HERDAREM (PESSOA) PODERAM USAR AS VARIAVEIS

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected String nome;

    @CPF
    @Column(unique=true) // coluna UNICA no banco
    protected String cpf;

    @Column(unique=true)
    protected String email;

    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)//quando buscar os elemntos traz os usuarios juntos
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfis = new HashSet<>(); // pessoa pode ser do tipo Tecnico ou Admin, por isso criar uma lista

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now(); // pega a data atual de criacao do usuario

    // constructors
    public Pessoa() {
        super();
        // quando um perfil for criado ele precisa ser no minimo do tipo CLIENTE
        addPerfil(Perfil.CLIENTE);
    }
    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    // gets and sets
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public Set<Perfil> getPerfis() {
        // 'perfis' é provavelmente um Set ou List de inteiros que representam códigos de perfis (ex: 1 = ADMIN, 2 = CLIENTE, etc.)
        // Aqui, estamos convertendo cada código inteiro do set 'perfis' em um objeto do tipo Perfil (enum)
        // Usamos stream() para percorrer todos os elementos
        // Usamos map() para aplicar a função 'Perfil.toEnum(x)' em cada elemento x (converter o número para o enum correspondente)
        // Por fim, usamos collect(Collectors.toSet()) para reunir os resultados novamente em um Set<Perfil> e retorná-lo
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {return dataCriacao;}

    public void setDataCriacao(LocalDate dataCriacao) {this.dataCriacao = dataCriacao;}

    // hashCode e equal para fazer comparações pelo CPF ou ID
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }


}
