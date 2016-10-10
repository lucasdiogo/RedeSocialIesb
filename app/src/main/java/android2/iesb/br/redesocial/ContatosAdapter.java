package android2.iesb.br.redesocial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by lucasdiogo on 06/09/16.
 */

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder>{

    private List<Contatos> listaContatos;
    private ContatoOnClickListener contatoOnClickListener;
    private final Context context;

    public ContatosAdapter(List<Contatos> listaContatos, ContatoOnClickListener contatoOnClickListener, Context context) {
        this.listaContatos = listaContatos;
        this.context = context;
        this.contatoOnClickListener = contatoOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contatos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Contatos contatos = listaContatos.get(position);
        holder.nome.setText(contatos.getNome());
        holder.email.setText(contatos.getEmail());
        holder.telefone.setText(contatos.getTelefone());

        if (contatoOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contatoOnClickListener.onClickContato(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nome, email, telefone;

        public MyViewHolder(View view){
            super(view);
            nome = (TextView) view.findViewById(R.id.tvNomeLista);
            email = (TextView) view.findViewById(R.id.tvEmailLista);
            telefone = (TextView) view.findViewById(R.id.tvTelefoneLista);
        }
    }

    public interface ContatoOnClickListener{
        public void onClickContato(View view, int idx);
    }


}
