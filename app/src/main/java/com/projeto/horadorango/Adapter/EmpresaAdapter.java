package com.projeto.horadorango.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.R;
import com.projeto.horadorango.model.Produto;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmCollection;
import io.realm.RealmList;

public class EmpresaAdapter extends RealmBaseAdapter<Produto> implements ListAdapter {

    private LayoutInflater layoutInflater;

    public EmpresaAdapter(Context context, OrderedRealmCollection<Produto> realmResults){
        super(context, realmResults);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;

        if (row == null){

            row = layoutInflater.inflate(R.layout.activity_empresa_adapter, parent, false);

            holder = new ViewHolder();
            holder.tvEmpresa = (TextView)row.findViewById(R.id.tvEmpresa);
            holder.tvHorarioEntrega = (TextView)row.findViewById(R.id.tvHorarioEntrega);
            holder.tvPratoDia = (TextView)row.findViewById(R.id.tvPratoDia);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder)row.getTag();
        }
        Produto produto = getItem(position);

        holder.tvEmpresa.setText(produto.getEmpresa().getNome_fantasia());
        holder.tvHorarioEntrega.setText(produto.getEmpresa().getHorario_entrega());
        holder.tvPratoDia.setText(produto.getDescricao());

        return row;
    }

    class ViewHolder{
        TextView tvEmpresa, tvHorarioEntrega, tvPratoDia ;
    }

}
