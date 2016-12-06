package com.projeto.horadorango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.horadorango.adapter.CarrinhoAdapter;
import com.projeto.horadorango.model.PedidoItem;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

    public static final String EXTRA_IDENT = "EXTRA_IDENT";

    private ListView ProdutosListView;
    private TextView tvAdicionarItems;
    private TextView tvTotal;
    private Button btSelecionarEndereco;
    private List<PedidoItem> pedidoItem;
    private CarrinhoAdapter carrinhoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Meu Carrinho");
        }

        ProdutosListView = (ListView) findViewById(R.id.ProdutosListView);
        tvAdicionarItems = (TextView)findViewById(R.id.tvAdicionarItems);
        tvTotal = (TextView)findViewById(R.id.tvTotal);
        btSelecionarEndereco = (Button)findViewById(R.id.btSelecionarEndereco);

        btSelecionarEndereco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SelecionaEndereco();
            }
        });

        tvAdicionarItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdicionaItems();
            }
        });

        pedidoItem = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_IDENT));

        carrinhoAdapter = new CarrinhoAdapter(this, pedidoItem);
        ProdutosListView.setAdapter(carrinhoAdapter);

        double total = 0;
        for (PedidoItem item : pedidoItem) {
            total += item.getProduto().getValor() * item.getQuantidade();
        }
        tvTotal.setText(String.format("R$ %s", total));


    }



    public void AdicionaItems(){
       finish();
    }

    public void SelecionaEndereco(){
        Intent u = new Intent(this, ListaEnderecosActivity.class);
        startActivity(u);
    }


}
