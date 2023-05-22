package br.edu.ifsp.dmo.lista_tarefas.presenter;

import android.os.Bundle;

import br.edu.ifsp.dmo.lista_tarefas.model.dao.TarefaDaoSingleton;
import br.edu.ifsp.dmo.lista_tarefas.model.dao.ITarefaDao;
import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.lista_tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.lista_tarefas.utils.Constant;

public class TarefaDetailsPresenter implements TarefaDetailsMVP.Presenter {

    private TarefaDetailsMVP.View view;
    private Tarefa tarefa;
    private ITarefaDao dao;

    public TarefaDetailsPresenter(TarefaDetailsMVP.View view) {
        this.view = view;
        tarefa = null;
        dao = TarefaDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void verifyUpdate() {
        String nome;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            nome = bundle.getString(Constant.ATTR_NOME);
            tarefa = dao.findByTitle(nome);
            view.updateUI(tarefa.getNome(), tarefa.getDescricao());
        }
    }

    @Override
    public String showTarefa() {
        String nome;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            nome = bundle.getString(Constant.ATTR_NOME);
            return nome;
        }
        return "oi";
    }

    @Override
    public void saveArticle(String nome, String descricao) {

        if(tarefa == null){
            tarefa = new Tarefa(nome, descricao);
            dao.create(tarefa);
            view.showToast("Nova Tarefa adicionada com sucesso.");
            view.close();
        }else{
            String oldNome = tarefa.getNome();
            Tarefa newNome = new Tarefa(nome, descricao, tarefa.isPrioridade());
            if(dao.update(oldNome, newNome)){
                view.showToast("Tarefa atualizada com sucesso.");
                view.close();
            }else{
                view.showToast("Erro ao atualizar o tarefa.");
            }
        }
    }

    @Override
    public void deleteArticle(String nome) {

        if(tarefa != null){
            dao.delete(tarefa);
            view.showToast("Tarefa deletada com sucesso.");
            view.close();
        }
    }
}
