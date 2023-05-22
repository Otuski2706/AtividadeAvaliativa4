package br.edu.ifsp.dmo.lista_tarefas.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmo.lista_tarefas.model.dao.TarefaDaoSingleton;
import br.edu.ifsp.dmo.lista_tarefas.model.dao.ITarefaDao;
import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.lista_tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.lista_tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.lista_tarefas.utils.Constant;
import br.edu.ifsp.dmo.lista_tarefas.view.TarefaDetailsActivity;
import br.edu.ifsp.dmo.lista_tarefas.view.TarefaSaveActivity;
import br.edu.ifsp.dmo.lista_tarefas.view.RecyclerViewItemClickListener;
import br.edu.ifsp.dmo.lista_tarefas.view.adapter.ItemPocketRecyclerAdapter;

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private ITarefaDao dao;
    Tarefa article;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TarefaDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails1() {
        Intent intent = new Intent(view.getContext(), TarefaSaveActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails1(Tarefa nome) {
        Intent intent = new Intent(view.getContext(), TarefaSaveActivity.class);
        intent.putExtra(Constant.ATTR_NOME, nome.getNome());
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails2(String s) {
        Intent intent = new Intent(view.getContext(), TarefaDetailsActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails2(Tarefa nome) {
        Intent intent = new Intent(view.getContext(), TarefaSaveActivity.class);
        intent.putExtra(Constant.ATTR_NOME, nome.getNome());
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);
        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                article = dao.findAll().get(position);
                openDetails1(article);
            }
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void favoriteArticle(Tarefa nome) {
        nome.setPrioridade(!nome.isPrioridade());
        dao.update(nome.getNome(), nome);
    }

    @Override
    public void deleteTask(Tarefa task) {
        dao.delete(task);
    }
}
