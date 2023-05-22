package br.edu.ifsp.dmo.lista_tarefas.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsp.dmo.lista_tarefas.R;
import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.lista_tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.lista_tarefas.view.TarefaDetailsActivity;

public class ItemPocketAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private MainMVP.Presenter presenter;

    public ItemPocketAdapter(Context context, List<Tarefa> data, MainMVP.Presenter presenter){
        super(context, R.layout.listview_item, data);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.nomeTextView = convertView.findViewById(R.id.text_nome_listitem);
            holder.favoriteImageView = convertView.findViewById(R.id.image_favorite_listitem);
            holder.editImageView = convertView.findViewById(R.id.image_edit_listitem);
            holder.deleteImageView = convertView.findViewById(R.id.image_delete_listitem);

            holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    starClick(position);
                }
            });

            holder.editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), TarefaDetailsActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

            holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), TarefaDetailsActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Tarefa nome = (Tarefa) getItem(position);
        holder.nomeTextView.setText(nome.getNome());

        if(nome.isPrioridade()){
            holder.favoriteImageView.setColorFilter(getContext().getColor(R.color.teal_200));
        }else{
            holder.favoriteImageView.setColorFilter(getContext().getColor(R.color.gray));
        }

        return convertView;
    }

    private void starClick(int position){
        Tarefa nome = (Tarefa) getItem(position);
        presenter.favoriteArticle(nome);
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        public TextView nomeTextView;
        public ImageView favoriteImageView;
        public ImageView editImageView;
        public ImageView deleteImageView;
    }
}
