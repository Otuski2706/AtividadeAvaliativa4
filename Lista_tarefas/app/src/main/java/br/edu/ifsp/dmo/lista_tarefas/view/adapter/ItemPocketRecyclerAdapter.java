package br.edu.ifsp.dmo.lista_tarefas.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmo.lista_tarefas.R;
import br.edu.ifsp.dmo.lista_tarefas.model.dao.ITarefaDao;
import br.edu.ifsp.dmo.lista_tarefas.model.dao.TarefaDaoSingleton;
import br.edu.ifsp.dmo.lista_tarefas.model.entities.Tarefa;
import br.edu.ifsp.dmo.lista_tarefas.mvp.MainMVP;
import br.edu.ifsp.dmo.lista_tarefas.mvp.TarefaDetailsMVP;
import br.edu.ifsp.dmo.lista_tarefas.presenter.TarefaDetailsPresenter;
import br.edu.ifsp.dmo.lista_tarefas.view.RecyclerViewItemClickListener;

public class ItemPocketRecyclerAdapter extends RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder>{

    private Context context;
    private MainMVP.Presenter presenter;
    private TarefaDaoSingleton dao;
    public String s;
    private TarefaDetailsPresenter presenter2;
    private List<Tarefa> data;
    private static RecyclerViewItemClickListener clickListener;


    public ItemPocketRecyclerAdapter(Context context, List<Tarefa> data, MainMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarefa article = data.get(position);
        holder.titleTextView.setText(article.getNome());

        holder.editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editClick(article);
            }
        });

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    deleteClick(article);
                }
            }
        });

        if(article.isPrioridade()){
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.teal_200));
        }else{
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.gray));
        }
        holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartClick(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    private void heartClick(Tarefa article){
        presenter.favoriteArticle(article);
        notifyDataSetChanged();
    }

    private void editClick(Tarefa article){
        presenter.openDetails2(article);
        notifyDataSetChanged();
    }

    private void deleteClick(Tarefa article){
        presenter.deleteTask(article);;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTextView;
        public ImageView favoriteImageView;
        public ImageView editImageView;

        public ImageView deleteImageView;

        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_nome_listitem);
            favoriteImageView = itemView.findViewById(R.id.image_favorite_listitem);
            editImageView = itemView.findViewById(R.id.image_edit_listitem);
            deleteImageView = itemView.findViewById(R.id.image_delete_listitem);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
