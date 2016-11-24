package com.projeto.horadorango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CarrinhoActivity extends AppCompatActivity {

    private ListView ProdutosListView;
    private TextView tvAdicionarItems;
    private TextView tvTotal;
    private Button btSelecionarEndereco;

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

    }

    public void AdicionaItems(){
        Intent i = new Intent(this, PedidoActivity.class);
        startActivity(i);
    }

    public void SelecionaEndereco(){
        Intent u = new Intent(this, ListaEnderecosActivity.class);
        startActivity(u);
    }
}
