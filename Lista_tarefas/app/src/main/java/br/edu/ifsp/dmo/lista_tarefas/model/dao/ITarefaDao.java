package br.edu.ifsp.dmo.lista_tarefas.model.dao;

import java.util.List;

import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tag;

public interface ITarefaDao {
    void create(Tarefa nome);

    boolean update(String oldNome, Tarefa nome);

    boolean delete(Tarefa nome);

    Tarefa findByTitle(String nome);

    List<Tarefa> findByTag(Tag tag);

    List<Tarefa> findAll();
}
