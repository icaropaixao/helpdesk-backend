package com.icaropaixao.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa{

    private List<Chamado> chamados = new ArrayList<>();


    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }


    // gets e sets para a lista de chamados associados ao tecnico
    public List<Chamado> getChamados() {
        return chamados;
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }


}
