package com.projeto.horadorango;

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
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Endereco;

import io.realm.Realm;

public class CadEmpresaActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "_ID";

    private Realm realm;
    private Empresa empresa;

    private EditText edtEmpresa;
    private EditText edtHorarioFunc;
    private EditText edttel1;
    private EditText edttel2;
    private EditText edttel3;
    private EditText edtHorarioEntrega;
    private EditText edtTaxaEntrega;
    private Button btGravarEmpresa;
    private Spinner spEndereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_empresa);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cadastro Empresa");
        }

        edtEmpresa = (EditText)findViewById(R.id.edtEmpresa);
        edtHorarioFunc = (EditText)findViewById(R.id.edtHorarioFunc);
        edttel1 = (EditText)findViewById(R.id.edttel1);
        edttel2 = (EditText)findViewById(R.id.edtTel2);
        edttel3 = (EditText)findViewById(R.id.edtTel3);
        edtHorarioEntrega = (EditText)findViewById(R.id.edtHorarioEntrega);
        edtTaxaEntrega = (EditText)findViewById(R.id.edtTaxaEntrega);
        btGravarEmpresa = (Button)findViewById(R.id.btGravarEmpresa);
        spEndereco = (Spinner)findViewById(R.id.spEndereco);

        btGravarEmpresa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });

        realm = Realm.getDefaultInstance();

        ArrayAdapter enderecoAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Realm.getDefaultInstance().where(Endereco.class).findAll());
        spEndereco.setAdapter(enderecoAdapter);

        final int id;

        if (getIntent().getExtras() == null)
            id = 0;
        else
            id = getIntent().getExtras().getInt(EXTRA_ID, 0);

        if (id > 0) {
            empresa = realm.where(Empresa.class).equalTo("id", id).findFirst();
            edtEmpresa.setText(empresa.getNome_fantasia());
            edtHorarioFunc.setText(empresa.getHorario_func());
            edttel1.setText(empresa.getTel1());
            edttel2.setText(empresa.getTel2());
            edttel3.setText(empresa.getTel3());
            edtHorarioEntrega.setText(empresa.getHorario_entrega());
            edtTaxaEntrega.setText((int)empresa.getTaxa_entrega());

            for (int i = 0; i < spEndereco.getAdapter().getCount(); i++) {
                Endereco endereco = (Endereco) spEndereco.getAdapter().getItem(i);
                if (endereco.getId() == empresa.getEndereco().getId())
                    spEndereco.setSelection(i);
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
        if (edtEmpresa.getText().toString().trim().isEmpty()) {
            showMessage("Endereço vazio");
            return;
        }

        if (edtHorarioFunc.getText().toString().trim().isEmpty()) {
            showMessage("Número vazio");
            return;
        }

        realm.beginTransaction();

        if (empresa == null)
            empresa = Empresa.create(realm);

        empresa.setNome_fantasia(edtEmpresa.getText().toString())
                .setHorario_func(edtHorarioFunc.getText().toString())
                .setTel1(edttel1.getText().toString())
                .setTel2(edttel2.getText().toString())
                .setTel3(edttel3.getText().toString())
                .setHorario_entrega(edtHorarioEntrega.getText().toString())
                .setTaxa_entrega(Double.parseDouble(edtTaxaEntrega.getText().toString()));

        realm.insertOrUpdate(empresa);
        realm.commitTransaction();

        finish();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
