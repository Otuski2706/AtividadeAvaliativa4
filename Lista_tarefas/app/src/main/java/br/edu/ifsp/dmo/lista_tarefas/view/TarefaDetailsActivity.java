package br.edu.ifsp.dmo.lista_tarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmo.lista_tarefas.R;
import br.edu.ifsp.dmo.lista_tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.lista_tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.lista_tarefas.presenter.TarefaDetailsPresenter;
import br.edu.ifsp.dmo.lista_tarefas.utils.Constant;

public class TarefaDetailsActivity extends AppCompatActivity implements View.OnClickListener, TarefaDetailsMVP.View{

    private MainMVP.View view;
    private TarefaDetailsMVP.Presenter presenter;
    private TextView tarefaText;
    private TextView descText;
    private Button returnButton;

    public String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_details);

        findViews();
    }

    @Override
    public void onClick(View v) {
        if(v == returnButton){
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
        }
    }

    private void findViews(){
        tarefaText = findViewById(R.id.textview_nome);
        descText = findViewById(R.id.textview_desc);
        returnButton = findViewById(R.id.btn_limited);
    }

    @Override
    protected void onStart() {
        super.onStart();
        nome = presenter.showTarefa();
        tarefaText.setText(String.format(nome));
    }

    @Override
    public void updateUI(String nome, String descricao) {
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
    }

    @Override
    public void close() {
    }
}