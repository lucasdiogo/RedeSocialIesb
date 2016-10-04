package android2.iesb.br.redesocial;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by lucasdiogo on 06/09/16.
 */

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder>{

    private List<Contatos> listaContatos;

    public ContatosAdapter (List<Contatos> listaContatos) {
        this.listaContatos = listaContatos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contatos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contatos contatos = listaContatos.get(position);
        holder.nome.setText(contatos.getNome());
        holder.telefone.setText(contatos.getTelefone());
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nome, telefone;

        public MyViewHolder(View view){
            super(view);
            nome = (TextView) view.findViewById(R.id.tvNomeLista);
            telefone = (TextView) view.findViewById(R.id.tvTelefoneLista);
        }
    }
}
