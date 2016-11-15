package com.projeto.horadorango.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.horadorango.Model.Empresa;
import com.projeto.horadorango.R;

import java.util.ArrayList;

public class EmpresaAdapter extends BaseAdapter {

    private ArrayList<Empresa> listaEmpresas;
    private Context contexto;

    public EmpresaAdapter(Context c, ArrayList<Empresa> ep){
        contexto = c;
        listaEmpresas = ep;
    }

    @Override
    public int getCount() {
        return listaEmpresas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEmpresas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaEmpresas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        EmpresaHolder holder = null;
        if (row == null){

            LayoutInflater Inflater = LayoutInflater.from(contexto);
            row = Inflater.inflate(R.layout.activity_empresa_adapter, parent, false);

            holder = new EmpresaHolder();
            holder.tvEmpresa = (TextView)row.findViewById(R.id.tvEmpresa);
            holder.tvHorarioEntrega = (TextView)row.findViewById(R.id.tvHorarioEntrega);
            holder.tvPratoDia = (TextView)row.findViewById(R.id.tvPratoDia);
            row.setTag(holder);
        }
        else{
            holder = (EmpresaHolder)row.getTag();
        }
        Empresa e = listaEmpresas.get(position);
        //preenchendo o textview
        holder.tvEmpresa.setText(e.getNome_fantasia());
  //      holder.tvPratoDia.setText(e.());
        holder.tvHorarioEntrega.setText(e.getHorario_entrega());
        return row;
    }

    class EmpresaHolder{
        TextView tvPratoDia, tvHorarioEntrega, tvEmpresa;
    }

}
