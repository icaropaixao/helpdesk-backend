package com.icaropaixao.helpdesk.domain.enums;

public enum Perfil {

    ADMIN(0,"ROLE_ADMIN"), CLIENTE(1,"ROLE_CLIENTE"), TECNICO(2,"ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    // construtor
    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    // gets sets
    public Integer getCodigo() {
        return codigo;
    }
    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        // para cada (perfil x)  dentro do perfil.Values
        for (Perfil x : Perfil.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }
        // se não achar o perfil solicitado gera a excepetion
        throw new IllegalArgumentException("Perfil invalido");

    }

}
