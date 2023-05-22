package br.edu.ifsp.dmo.lista_tarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmo.lista_tarefas.R;
import br.edu.ifsp.dmo.lista_tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.lista_tarefas.presenter.TarefaDetailsPresenter;

public class TarefaSaveActivity extends AppCompatActivity implements TarefaDetailsMVP.View, View.OnClickListener {

    private TarefaDetailsMVP.Presenter presenter;
    private EditText titleEditText;
    private EditText descEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tarefa);

        presenter = new TarefaDetailsPresenter(this);
        findViews();
        setListener();
        setToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.verifyUpdate();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton){
            presenter.saveArticle(
                    titleEditText.getText().toString(),
                    descEditText.getText().toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateUI(String title, String desc) {

        titleEditText.setText(title);
        descEditText.setText(desc);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }

    private void findViews(){
        titleEditText = findViewById(R.id.edittext_tarefa);
        descEditText = findViewById(R.id.edittext_descricao);
        saveButton = findViewById(R.id.button_save_tarefa);
    }

    private void setListener(){
        saveButton.setOnClickListener(this);
    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}