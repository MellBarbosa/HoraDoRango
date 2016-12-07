package com.projeto.horadorango;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Bairro;
import com.projeto.horadorango.model.Cidade;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.EnderecoRequest;
import com.projeto.horadorango.model.Usuario;


import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnderecoActivity extends AppCompatActivity  {

    public static final String EXTRA_ID = "_ID";

    private Realm realm;
    private Endereco endereco;

    private EditText edtEndereco;
    private Spinner spBairro;
    private EditText edtCep;
    private EditText edtNumero;
    private EditText edtComplemento;
    private Spinner spCidade;
    private EditText edtReferencia;
    private Button btGravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cadastro Endereço");
        }

        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        spBairro = (Spinner) findViewById(R.id.spBairro);
        edtCep = (EditText) findViewById(R.id.edtCep);
        edtNumero = (EditText) findViewById(R.id.edtNumero);
        edtComplemento = (EditText) findViewById(R.id.edtComplemento);
        spCidade = (Spinner) findViewById(R.id.spCidade);
        edtReferencia = (EditText) findViewById(R.id.edtReferencia);
        btGravar = (Button)findViewById(R.id.btGravar);

        btGravar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });

        realm = Realm.getDefaultInstance();

        ArrayAdapter bairroAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Realm.getDefaultInstance().where(Bairro.class).findAll());
        spBairro.setAdapter(bairroAdapter);

        ArrayAdapter cidadeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, realm.where(Cidade.class).findAll());
        spCidade.setAdapter(cidadeAdapter);

        final long id;

        if (getIntent().getExtras() == null)
            id = 0;
        else
            id = getIntent().getExtras().getLong(EXTRA_ID, 0);

        if (id > 0) {
            endereco = realm.where(Endereco.class).equalTo("id", id).findFirst();
            edtEndereco.setText(endereco.getEndereco());
            edtNumero.setText(endereco.getNumero());
            edtComplemento.setText(endereco.getComplemento());
            edtCep.setText(endereco.getCep());
            edtReferencia.setText(endereco.getPonto_referencia());

            for (int i = 0; i < spBairro.getAdapter().getCount(); i++) {
                Bairro bairro = (Bairro) spBairro.getAdapter().getItem(i);
                if (bairro.getId() == endereco.getBairro().getId())
                    spBairro.setSelection(i);
            }

            for (int i = 0; i < spCidade.getAdapter().getCount(); i++) {
                Cidade cidade = (Cidade) spCidade.getAdapter().getItem(i);
                if (cidade.getId() == endereco.getCidade().getId())
                    spCidade.setSelection(i);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    public void save() {
        if (edtEndereco.getText().toString().trim().isEmpty()) {
            showMessage("Endereço vazio");
            return;
        }

        if (edtNumero.getText().toString().trim().isEmpty()) {
            showMessage("Número vazio");
            return;
        }

        if (edtCep.getText().toString().trim().isEmpty()) {
            showMessage("Cep vazio");
            return;
        }
        Usuario usuario = realm.where(Usuario.class).findFirst();

        EnderecoRequest request = new EnderecoRequest();
        request.setUsuario_id(usuario.getId());
        request.setEndereco(edtEndereco.getText().toString());
        request.setNumero(Integer.parseInt(edtNumero.getText().toString()));
        request.setCep(edtCep.getText().toString());
        request.setComplemento(edtComplemento.getText().toString());
        request.setBairro_id(((Bairro) spBairro.getSelectedItem()).getId());
        request.setCidade_id(((Cidade) spCidade.getSelectedItem()).getId());
        request.setPonto_referencia(edtReferencia.getText().toString());

        ApiComunicator.get().salvarEndereco(request).enqueue(new Callback<EnderecoRequest>() {
            @Override
            public void onResponse(Call<EnderecoRequest> call, Response<EnderecoRequest> response) {

                salvarLocal(response.body());
            }

            @Override
            public void onFailure(Call<EnderecoRequest> call, Throwable t) {

            }
        });
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void salvarLocal(EnderecoRequest enderecoRequest){

        Cidade cidade = realm.where(Cidade.class).equalTo("id", enderecoRequest.getCidade_id()).findFirst();
        Bairro bairro = realm.where(Bairro.class).equalTo("id", enderecoRequest.getBairro_id()).findFirst();
        Usuario usuario = realm.where(Usuario.class).findFirst();

        realm.beginTransaction();

        endereco = new Endereco();

        endereco.setEndereco(enderecoRequest.getEndereco())
                .setNumero(String.valueOf(enderecoRequest.getNumero()))
                .setCep(enderecoRequest.getCep())
                .setComplemento(enderecoRequest.getComplemento())
                .setBairro(bairro)
                .setCidade(cidade)
                .setPonto_referencia(enderecoRequest.getPonto_referencia())
                .setUsuario(usuario)
                .setId(enderecoRequest.getId());

        realm.copyToRealmOrUpdate(endereco);
        realm.commitTransaction();

        finish();

    }


}
