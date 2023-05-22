package br.edu.ifsp.dmo.lista_tarefas.model.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tag;
import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.lista_tarefas.utils.Constant;

public class TarefaDaoSingleton implements ITarefaDao {
    private static TarefaDaoSingleton instance = null;
    private List<Tarefa> dataset;
    private Context context;
    private SharedPreferences sharedPreferences;

    private TarefaDaoSingleton() {
        this.context = context;
        dataset = new ArrayList<>();
    }

    public static TarefaDaoSingleton getInstance(){
        if(instance == null)
            instance = new TarefaDaoSingleton();
        return instance;
    }

    @Override
    public void create(Tarefa nome) {
        if(nome != null){
            Tarefa inDataset = dataset.stream()
                    .filter(user1 -> user1.getNome().equals(user1.getNome()))
                    .findAny()
                    .orElse(null);

            dataset.add(nome);
        }
    }

    @Override
    public boolean update(String oldNome, Tarefa nome) {
        Tarefa inDataset;
        inDataset = dataset.stream()
                .filter(tarefa1 -> tarefa1.getNome().equals(oldNome))
                .findAny()
                .orElse(null);
        if(inDataset != null){
            inDataset.setNome(nome.getNome());
            inDataset.setPrioridade(nome.isPrioridade());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Tarefa nome) {
        return dataset.remove(nome);
    }

    @Override
    public Tarefa findByTitle(String nome) {
        return dataset.stream()
                .filter(article -> article.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Tarefa> findByTag(Tag tag) {
        List<Tarefa> selection = new ArrayList<>();
        for(Tarefa a : dataset){
            for(Tag t : a.getTags()){
                if(t.getTagName().equals(tag.getTagName())){
                    selection.add(a);
                }
            }
        }
        return selection;
    }

    @Override
    public List<Tarefa> findAll() {
        return dataset;
    }

}
