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

import com.projeto.horadorango.model.Categoria;
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Produto;

import io.realm.Realm;

public class CadProdutoActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "_ID";

    private Realm realm;
    private Produto produto;

    private EditText edtDescricao;
    private EditText edtValor;
    private Spinner spCategoria;
    private Spinner spEmp;
    private Button btGravarProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cadastro Produto");
        }

            edtDescricao = (EditText)findViewById(R.id.edtDescricao);
            edtValor = (EditText)findViewById(R.id.edtValor);
            spCategoria = (Spinner) findViewById(R.id.spCategoria);
            spEmp = (Spinner) findViewById(R.id.spEmp);
            btGravarProd = (Button)findViewById(R.id.btGravarProd);

            btGravarProd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    save();
                }
            });

            realm = Realm.getDefaultInstance();

             ArrayAdapter categoriaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Realm.getDefaultInstance().where(Categoria.class).findAll());
             spCategoria.setAdapter(categoriaAdapter);

            ArrayAdapter empresaAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item,
                    Realm.getDefaultInstance().where(Empresa.class).findAll());
            spEmp.setAdapter(empresaAdapter);

            final int id;

            if (getIntent().getExtras() == null)
                id = 0;
            else
                id = getIntent().getExtras().getInt(EXTRA_ID, 0);

            if (id > 0) {

                produto = realm.where(Produto.class).equalTo("id", id).findFirst();
                edtDescricao.setText(produto.getDescricao());
                edtValor.setText((int) produto.getValor());

                for (int i = 0; i < spCategoria.getAdapter().getCount(); i++) {
                    Categoria categoria = (Categoria) spCategoria.getAdapter().getItem(i);
                    if (categoria.getId() == produto.getCategoria().getId())
                        spCategoria.setSelection(i);
                }

                for (int i = 0; i < spEmp.getAdapter().getCount(); i++) {
                    Empresa empresa = (Empresa) spEmp.getAdapter().getItem(i);
                    if (empresa.getId() == produto.getEmpresa().getId())
                        spEmp.setSelection(i);
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
        if (edtDescricao.getText().toString().trim().isEmpty()) {
            showMessage("Endereço vazio");
            return;
        }

        if (edtValor.getText().toString().trim().isEmpty()) {
            showMessage("Número vazio");
            return;
        }

        realm.beginTransaction();

        if (produto == null)
            produto = Produto.create(realm);

        produto.setDescricaco(edtDescricao.getText().toString())
                .setValor(Double.parseDouble(edtValor.getText().toString()))
                .setCategoria((Categoria) spCategoria.getSelectedItem())
                .setEmpresa((Empresa) spEmp.getSelectedItem());

        realm.insertOrUpdate(produto);
        realm.commitTransaction();

        finish();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
