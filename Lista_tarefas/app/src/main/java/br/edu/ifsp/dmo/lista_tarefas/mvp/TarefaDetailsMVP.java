package br.edu.ifsp.dmo.lista_tarefas.mvp;

import android.os.Bundle;

public interface TarefaDetailsMVP {

    interface View{
        void updateUI(String nome, String descricao);

        Bundle getBundle();

        void showToast(String message);

        void close();

    }

    interface Presenter{
        void deatach();

        void verifyUpdate();

        String showTarefa();

        void saveArticle(String nome, String descricao);

        void deleteArticle(String nome);
    }
}
