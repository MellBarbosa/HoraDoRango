package com.projeto.horadorango.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.horadorango.Model.Produto;
import com.projeto.horadorango.R;

import java.util.ArrayList;

public class ProdutoAdapter extends BaseAdapter {

    private ArrayList<Produto> listaProdutos;
    private Context contexto;

    public ProdutoAdapter(Context c, ArrayList<Produto> lp){
        contexto = c;
        listaProdutos = lp;
    }
    @Override
    public int getCount() {
        return listaProdutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProdutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaProdutos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ProdutoHolder holder = null;
        if(row == null){
            LayoutInflater Inflater = LayoutInflater.from(contexto);
            row = Inflater.inflate(R.layout.activity_produto_adapter, parent, false);

            holder = new ProdutoHolder();
            holder.tvProduto = (TextView)row.findViewById(R.id.tvProduto);
            row.setTag(holder);
        }
        else {
            holder = (ProdutoHolder) row.getTag();
        }
        Produto p = listaProdutos.get(position);
        holder.tvProduto.setText(p.getDescricao());
        return  row;
    }


    class ProdutoHolder{
        TextView tvProduto;
    }
}
