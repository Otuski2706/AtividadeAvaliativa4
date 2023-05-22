package br.edu.ifsp.dmo.lista_tarefas.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;

public interface MainMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();

        void openDetails1();

        void openDetails1(Tarefa article);

        void openDetails2(String s);

        void openDetails2(Tarefa nome);

        void populateList(RecyclerView recyclerView);

        void favoriteArticle(Tarefa article);

        void deleteTask(Tarefa task);
    }
}
