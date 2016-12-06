package com.projeto.horadorango.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.horadorango.R;
import com.projeto.horadorango.model.PedidoItem;

import java.util.List;
/**
 * Created by Mell on 05/12/2016.
 */

public class CarrinhoAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<PedidoItem> items;

    public CarrinhoAdapter(Context context, List<PedidoItem> items) {
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
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
        ViewHolder holder;

        if (row == null){
            row = layoutInflater.inflate(R.layout.item_carrinho, parent, false);

            holder = new ViewHolder();
            holder.nameTv    = (TextView) row.findViewById(R.id.name_tv);
            holder.priceUnTv = (TextView) row.findViewById(R.id.price_un_tv);
            holder.quantTv   = (TextView) row.findViewById(R.id.quant_tv);
            holder.totalTv   = (TextView) row.findViewById(R.id.total_tv);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        PedidoItem p = getItem(position);
        holder.setPedidoItem(p);

        return row;
    }

    private static class ViewHolder {
        TextView nameTv, priceUnTv, quantTv, totalTv;

        void setPedidoItem(PedidoItem pedidoItem) {
            double priceUn = pedidoItem.getProduto().getValor();
            double total = priceUn * pedidoItem.getQuantidade();

            nameTv.setText(pedidoItem.getProduto().getDescricao());
            priceUnTv.setText(String.format("R$ %s", priceUn));
            quantTv.setText("" + pedidoItem.getQuantidade());
            totalTv.setText(String.format("R$ %s", total));
        }
    }
}