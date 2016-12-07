package com.projeto.horadorango.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.horadorango.model.PedidoItem;
import com.projeto.horadorango.model.Produto;
import com.projeto.horadorango.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

public class ProdutoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<PedidoItem> items;

    public ProdutoAdapter(Context context, OrderedRealmCollection<Produto> produtos) {
        layoutInflater = LayoutInflater.from(context);

        items = new ArrayList<>();

        for (Produto produto: produtos) {
            PedidoItem item = new PedidoItem();
            item.setProduto(Realm.getDefaultInstance().copyFromRealm(produto));
            items.add(item);
        }

        Collections.sort(items, new Comparator<PedidoItem>() {
            public int compare(PedidoItem a, PedidoItem b) {
                return a.getProduto().getCategoria().getDescricao().compareTo(b.getProduto().getCategoria().getDescricao());
            }
        });
    }

    public List<PedidoItem> getProdutosSelecionados() {
        List<PedidoItem> selecionados = new ArrayList<>();

        for (PedidoItem item : items) {
            if (item.getQuantidade() > 0) {
                selecionados.add(item);
            }
        }

        return selecionados;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public PedidoItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getProduto().getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProdutoAdapter.ViewHolder holder;

        if (row == null){
            row = layoutInflater.inflate(R.layout.activity_produto_adapter, parent, false);

            holder = new ProdutoAdapter.ViewHolder();
            holder.tvCategoria = (TextView)row.findViewById(R.id.tvCategoria);
            holder.tvProduto = (TextView)row.findViewById(R.id.tvProduto);
            holder.tvQuantidade = (TextView)row.findViewById(R.id.tvQuantidade);
            holder.ivMais = (ImageView) row.findViewById(R.id.ivMais);
            holder.ivMenos = (ImageView) row.findViewById(R.id.ivMenos);
            holder.tvValor = (TextView) row.findViewById(R.id.valor_tv);
            holder.init();
            row.setTag(holder);
        }
        else{
            holder = (ProdutoAdapter.ViewHolder)row.getTag();
        }

        PedidoItem p = getItem(position);

        holder.setPedidoItem(p);

        if (position == 0 || getItem(position - 1).getProduto().getCategoria().getId() != p.getProduto().getCategoria().getId()) {
            holder.tvCategoria.setVisibility(View.VISIBLE);
        } else {
            holder.tvCategoria.setVisibility(View.GONE);
        }

        return row;
    }

    private static class ViewHolder {
        TextView tvCategoria, tvProduto, tvQuantidade, tvValor;
        ImageView ivMais, ivMenos;

        PedidoItem pedidoItem;

        void init() {
            ivMais.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    pedidoItem.setQuantidade( pedidoItem.getQuantidade() + 1);
                    tvQuantidade.setText("" + pedidoItem.getQuantidade());
                }
            });

            ivMenos.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (pedidoItem.getQuantidade() != 0) {
                        pedidoItem.setQuantidade(pedidoItem.getQuantidade() - 1);
                        tvQuantidade.setText("" + pedidoItem.getQuantidade());
                    }
                }
            });
        }

        void setPedidoItem(PedidoItem pedidoItem) {
            this.pedidoItem = pedidoItem;


            //preenchendo o textview
            tvProduto.setText(pedidoItem.getProduto().getDescricao());
            tvValor.setText("R$" + String.valueOf(pedidoItem.getProduto().getValor()));
            tvCategoria.setText(pedidoItem.getProduto().getCategoria().getDescricao());

            tvQuantidade.setText("" + pedidoItem.getQuantidade());
        }
    }
}
