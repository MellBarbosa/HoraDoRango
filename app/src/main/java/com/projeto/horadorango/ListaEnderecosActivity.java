package com.projeto.horadorango;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projeto.horadorango.adapter.EnderecoAdapter;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.Usuario;

import io.realm.Realm;

    public class ListaEnderecosActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

        private Realm realm;
        private EnderecoAdapter enderecoAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_enderecos);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Meus Endereços");
            }

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(this);

            ListView enderecoListView = (ListView) findViewById(R.id.endereco_listview);
            enderecoListView.setOnItemClickListener(this);

            realm = Realm.getDefaultInstance();
            int id = realm.where(Usuario.class).findFirst().getId();
            enderecoAdapter = new EnderecoAdapter(this, realm.where(Endereco.class).equalTo("usuario.id", id).findAll());
            enderecoListView.setAdapter(enderecoAdapter);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            realm.close();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab:
                    Intent ed = new Intent(this, EnderecoActivity.class);
                    startActivity(ed);
                    break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(this, EnderecoActivity.class);
            intent.putExtra(EnderecoActivity.EXTRA_ID, enderecoAdapter.getItem(position).getId());
            startActivity(intent);
        }
    }

