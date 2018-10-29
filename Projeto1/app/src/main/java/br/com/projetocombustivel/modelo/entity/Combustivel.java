package br.com.projetocombustivel.modelo.entity;

public abstract class Combustivel {

    public String descricao;

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
