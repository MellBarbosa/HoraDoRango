package com.projeto.horadorango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.projeto.horadorango.adapter.CarrinhoAdapter;
import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.Pedido;
import com.projeto.horadorango.model.PedidoItem;
import com.projeto.horadorango.model.PedidoRequest;
import com.projeto.horadorango.model.Usuario;

import org.parceler.Parcels;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalizaPedidoActivity extends AppCompatActivity {

    public static final String EXTRA_ENDERECO = "EXTRA_ENDERECO";
    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    private ListView ItensPedidoListView;
    private TextView tvEndereco;
    private TextView tvBairro;
    private TextView tvNumero;
    private TextView tvComplemento;
    private TextView tvEmpresa;
    private TextView tvTaxaEntrega;
    private TextView tvTotal;
    private List<PedidoItem> pedidoItem;
    private Endereco endereco;
    private Button btEfetuarPedido;
    private Empresa empresa;
    private CarrinhoAdapter carrinhoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_pedido);

        if (getSupportActionBar() != null) {
            //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Finaliza Pedido");
        }

        ItensPedidoListView = (ListView) findViewById(R.id.ItensPedidoListView);
        tvEndereco = (TextView) findViewById(R.id.tvEndereco);
        tvBairro = (TextView) findViewById(R.id.tvBairro);
        tvNumero = (TextView) findViewById(R.id.tvNumero);
        tvComplemento = (TextView) findViewById(R.id.tvComplemento);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvTaxaEntrega = (TextView) findViewById(R.id.tvTaxaEntrega);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btEfetuarPedido = (Button) findViewById(R.id.btEfetuarPedido);

        btEfetuarPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                efetuarPedido();
            }
        });

        pedidoItem = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_ITEM));
        endereco = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_ENDERECO));

        empresa = pedidoItem.get(0).getProduto().getEmpresa();

        tvEndereco.setText(endereco.getEndereco());
        tvBairro.setText(endereco.getBairro().getDescricao());
        tvNumero.setText(endereco.getNumero());
        tvComplemento.setText(endereco.getComplemento());
        tvEmpresa.setText(empresa.getNome_fantasia());
        tvTaxaEntrega.setText("" + empresa.getTaxa_entrega());

        carrinhoAdapter = new CarrinhoAdapter(this, pedidoItem);
        ItensPedidoListView.setAdapter(carrinhoAdapter);


        double total = 0;
        for (PedidoItem item : pedidoItem) {
            total += item.getProduto().getValor() * item.getQuantidade();
        }
        total = total + empresa.getTaxa_entrega();
        tvTotal.setText(String.format("R$ %s", total));


    }


    public void efetuarPedido(){

      final MaterialDialog LoadingMaterialDialog =
                new MaterialDialog.Builder(this)
                .title("Hora do Rango")
                .content("Carregando")
                .progress(true, 0)
                .show();

        Usuario usuario = Realm.getDefaultInstance().where(Usuario.class).findFirst();
        PedidoRequest pedido = new PedidoRequest();
        pedido.setEndereco_id(endereco.getId());
        pedido.setUsuario_id(usuario.getId());
        pedido.setStatus("A");

        pedido.setItens(pedidoItem);

        ApiComunicator.get().enviarPedido(pedido).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                LoadingMaterialDialog.dismiss();
                Intent intent = new Intent(FinalizaPedidoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }


}
