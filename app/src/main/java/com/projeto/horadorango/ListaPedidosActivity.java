package com.projeto.horadorango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto.horadorango.adapter.EnderecoAdapter;
import com.projeto.horadorango.adapter.PedidosAdapter;
import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Pedido;
import com.projeto.horadorango.model.PedidoRequest;
import com.projeto.horadorango.model.Usuario;
import com.projeto.horadorango.util.RealmUtil;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPedidosActivity extends AppCompatActivity {

    private PedidosAdapter pedidosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Meus Pedidos");
        }

        ListView listView = (ListView) findViewById(R.id.PedidosListView);

        pedidosAdapter = new PedidosAdapter(ListaPedidosActivity.this, Collections.<Pedido>emptyList());
        listView.setAdapter(pedidosAdapter);

        Realm realm = Realm.getDefaultInstance();
        int id = realm.where(Usuario.class).findFirst().getId();

        ApiComunicator.get().listaPedidos(id).enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                pedidosAdapter.setItems(response.body());
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(ListaPedidosActivity.this, "Não foi possível carregar os pedidos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
