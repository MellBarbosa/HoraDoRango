package com.projeto.horadorango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.horadorango.model.PedidoItem;

import java.util.List;

public class FinalizaPedidoActivity extends AppCompatActivity {

    private ListView tvItensPedido;
    private TextView tvEndereco;
    private TextView tvBairro;
    private TextView tvNumero;
    private TextView tvComplemento;
    private TextView tvEmpresa;
    private TextView tvTaxaEntrega;
    private TextView tvTotal;
    private List<PedidoItem> pedidoItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_pedido);

        if (getSupportActionBar() != null) {
            //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Finaliza Pedido");
        }

        tvItensPedido = (ListView) findViewById(R.id.ProdutosListView);
        tvEndereco = (TextView) findViewById(R.id.tvEndereco);
        tvBairro = (TextView) findViewById(R.id.tvBairro);
        tvNumero = (TextView) findViewById(R.id.tvNumero);
        tvComplemento = (TextView) findViewById(R.id.tvComplemento);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvTaxaEntrega = (TextView) findViewById(R.id.tvTaxaEntrega);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        double total = 0;
        for (PedidoItem item : pedidoItem) {
            total += item.getProduto().getValor() * item.getQuantidade();
        }
        total = total;//adicionar taxa entrega ao valor
        tvTotal.setText(String.format("R$ %s", total));


    }
}
