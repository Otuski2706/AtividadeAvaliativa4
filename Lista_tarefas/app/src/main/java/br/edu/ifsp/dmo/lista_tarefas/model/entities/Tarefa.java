package br.edu.ifsp.dmo.lista_tarefas.model.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    private String nome;
    private String descricao;
    private boolean prioridade;
    private List<Tag> tags;

    private void init(){
        tags = new ArrayList<>();
    }

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        init();
    }

    public Tarefa(String nome, String descricao, boolean prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        init();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPrioridade() {
        return prioridade;
    }

    public void setPrioridade(boolean prioridade) {
        this.prioridade = prioridade;
    }

    public List<Tag> getTags(){
        return tags;
    }


    @NonNull
    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
