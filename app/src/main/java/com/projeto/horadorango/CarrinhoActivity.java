package com.projeto.horadorango;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.projeto.horadorango.adapter.CarrinhoAdapter;
import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.Pedido;
import com.projeto.horadorango.model.PedidoItem;
import com.projeto.horadorango.model.PedidoRequest;
import com.projeto.horadorango.model.Usuario;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
          //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void SelecionaEndereco() {

        Realm realm = Realm.getDefaultInstance();
        Usuario usuario = realm.where(Usuario.class).findFirst();
        final List<Endereco> enderecos = realm.copyFromRealm(realm.where(Endereco.class).equalTo("usuario.id", usuario.getId()).findAll());
        if (enderecos.size() == 0) {
            return;
        }

        new MaterialDialog.Builder(this)
                .title("Selecione endereço entrega")
                .items(enderecos)
                .itemsCallbackSingleChoice(0,new MaterialDialog.ListCallbackSingleChoice(){

                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                        android.util.Log.e("endereco", "log" + enderecos.get(position));
                        finalizarPedido(enderecos.get(position));
                      return true;
                    }
                })
                .positiveText("Selecionar")
                .show();
    }


    public void finalizarPedido(Endereco endereco){

        Intent ca = new Intent(this, FinalizaPedidoActivity.class);
        ca.putExtra(FinalizaPedidoActivity.EXTRA_ITEM, Parcels.wrap(pedidoItem));
        ca.putExtra(FinalizaPedidoActivity.EXTRA_ENDERECO, Parcels.wrap(endereco));
        startActivity(ca);

    }

}
