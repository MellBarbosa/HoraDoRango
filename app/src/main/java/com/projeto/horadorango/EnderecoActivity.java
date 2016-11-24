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

import com.projeto.horadorango.model.Bairro;
import com.projeto.horadorango.model.Cidade;
import com.projeto.horadorango.model.Endereco;


import io.realm.Realm;

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

        realm.beginTransaction();

        if (endereco == null)
            endereco = Endereco.create(realm);

        endereco.setEndereco(edtEndereco.getText().toString())
                .setNumero(edtNumero.getText().toString())
                .setCep(edtCep.getText().toString())
                .setComplemento(edtComplemento.getText().toString())
                .setBairro((Bairro) spBairro.getSelectedItem())
                .setCidade((Cidade) spCidade.getSelectedItem())
                .setPonto_referencia(edtReferencia.getText().toString());

        realm.insertOrUpdate(endereco);
        realm.commitTransaction();

        finish();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
