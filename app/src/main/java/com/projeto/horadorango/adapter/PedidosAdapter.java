package com.projeto.horadorango.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.horadorango.R;
import com.projeto.horadorango.model.Pedido;
import com.projeto.horadorango.model.PedidoItem;

import java.util.List;

import retrofit2.Callback;

public class PedidosAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Pedido> items;

    public PedidosAdapter(Context context, List<Pedido> items) {
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Pedido getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null){
            row = layoutInflater.inflate(R.layout.activity_pedidos_adapter, parent, false);

            holder = new ViewHolder();
            holder.idTv    = (TextView) row.findViewById(R.id.id_tv);
            holder.statusTv = (TextView) row.findViewById(R.id.status_tv);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Pedido p = getItem(position);
        holder.setPedido(p);

        return row;
    }

    private static class ViewHolder {
        TextView idTv, statusTv;

        void setPedido(Pedido pedido) {
            idTv.setText(pedido.getId());
            statusTv.setText(pedido.getStatus());
        }
    }
}