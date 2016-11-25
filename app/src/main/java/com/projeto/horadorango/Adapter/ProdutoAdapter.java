package com.projeto.horadorango.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projeto.horadorango.model.Produto;
import com.projeto.horadorango.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;


public class ProdutoAdapter extends RealmBaseAdapter<Produto> implements ListAdapter {

    private  LayoutInflater layoutInflater;

    public ProdutoAdapter(Context context, OrderedRealmCollection<Produto> realmResults){
        super(context, realmResults);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProdutoAdapter.ViewHolder holder;

        if (row == null){
            row = layoutInflater.inflate(R.layout.activity_endereco_adapter, parent, false);

            holder = new ProdutoAdapter.ViewHolder();
            holder.tvCategoria = (TextView)row.findViewById(R.id.tvCategoria);
            holder.tvProduto = (TextView)row.findViewById(R.id.tvProduto);
            holder.tvQuantidade = (TextView)row.findViewById(R.id.tvQuantidade);

            row.setTag(holder);
        }
        else{
            holder = (ProdutoAdapter.ViewHolder)row.getTag();
        }

        Produto p = getItem(position);
        //preenchendo o textview
        holder.tvProduto.setText(p.getDescricao());
    //    holder.tvQuantidade.setText(0);
        holder.tvCategoria.setText(p.getCategoria().getDescricao());
        if (position > 0 && getItem(position - 1).getCategoria() != p.getCategoria() ) {
            holder.tvCategoria.setText(p.getCategoria().getDescricao());
            holder.tvCategoria.setVisibility(View.VISIBLE);
        } else {
            holder.tvCategoria.setVisibility(View.GONE);
        }
        return row;
    }

    class ViewHolder{
        TextView tvCategoria, tvProduto, tvQuantidade;
    }
}
