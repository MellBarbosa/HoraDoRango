package com.projeto.horadorango.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.horadorango.Model.Endereco;
import com.projeto.horadorango.R;

import java.util.ArrayList;

/**
 * Created by Mell on 27/10/2016.
 */

public class EnderecoAdapter extends BaseAdapter {

    private ArrayList<Endereco> listaEnderecos;
    private Context contexto;

    public EnderecoAdapter(Context c, ArrayList<Endereco> ed){
        contexto = c;
        listaEnderecos = ed;
    }

    @Override
    public int getCount() {
        return listaEnderecos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEnderecos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaEnderecos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        EnderecoAdapter.EnderecoHolder holder = null;
        if (row == null){

            LayoutInflater Inflater = LayoutInflater.from(contexto);
            row = Inflater.inflate(R.layout.activity_endereco_adapter, parent, false);

            holder = new EnderecoAdapter.EnderecoHolder();
            holder.tvEndereco = (TextView)row.findViewById(R.id.tvEndereco);
            holder.tvBairro = (TextView)row.findViewById(R.id.tvBairro);
            holder.tvNumero = (TextView)row.findViewById(R.id.tvNumero);
            holder.tvComplemento = (TextView)row.findViewById(R.id.tvComplemento);
            row.setTag(holder);
        }
        else{
            holder = (EnderecoAdapter.EnderecoHolder)row.getTag();
        }
        Endereco e = listaEnderecos.get(position);
        //preenchendo o textview
        holder.tvEndereco.setText(e.getEndereco());
        holder.tvBairro.setText(e.getBairro().getDescricao());
        holder.tvNumero.setText(e.getNumero());
        holder.tvComplemento.setText(e.getComplemento());
        return row;
    }

    class EnderecoHolder{
        TextView tvEndereco, tvBairro, tvNumero, tvComplemento;
    }
}
