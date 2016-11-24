package com.projeto.horadorango.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.R;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by Mell on 27/10/2016.
 */

public class EnderecoAdapter extends RealmBaseAdapter<Endereco> implements ListAdapter {

    private  LayoutInflater layoutInflater;

    public EnderecoAdapter(Context context, OrderedRealmCollection<Endereco> realmResults){
        super(context, realmResults);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null){
            row = layoutInflater.inflate(R.layout.activity_endereco_adapter, parent, false);

            holder = new ViewHolder();
            holder.tvEndereco = (TextView)row.findViewById(R.id.tvEndereco);
            holder.tvBairro = (TextView)row.findViewById(R.id.tvBairro);
            holder.tvNumero = (TextView)row.findViewById(R.id.tvNumero);
            holder.tvComplemento = (TextView)row.findViewById(R.id.tvComplemento);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder)row.getTag();
        }

        Endereco e = getItem(position);
        //preenchendo o textview
        holder.tvEndereco.setText(e.getEndereco());
        holder.tvBairro.setText(e.getBairro().getDescricao());
        holder.tvNumero.setText(e.getNumero());
        holder.tvComplemento.setText(e.getComplemento());
        return row;
    }

    class ViewHolder{
        TextView tvEndereco, tvBairro, tvNumero, tvComplemento;
    }
}
