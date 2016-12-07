package com.projeto.horadorango;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.projeto.horadorango.adapter.EnderecoAdapter;
import com.projeto.horadorango.adapter.PedidosAdapter;
import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Pedido;
import com.projeto.horadorango.model.PedidoRequest;
import com.projeto.horadorango.model.Usuario;
import com.projeto.horadorango.util.RealmUtil;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPedidosActivity extends AppCompatActivity {

    private Realm realm;
    private PedidosAdapter pedidosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Meus Pedidos");
        }

        final ListView PedidosListView = (ListView) findViewById(R.id.PedidosListView);

        int id = Realm.getDefaultInstance().where(Usuario.class).findFirst().getId();

        ApiComunicator.get().listaPedidos(id).enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                android.util.Log.e("Lista", "" + response.body());

                realm = Realm.getDefaultInstance();
                int id = realm.where(Usuario.class).findFirst().getId();
          //      pedidosAdapter = new PedidosAdapter(this, realm.where(Pedido.class).findAll());
                PedidosListView.setAdapter(pedidosAdapter);
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {

            }
        });
    }
}
