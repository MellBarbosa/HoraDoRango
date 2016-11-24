package com.projeto.horadorango;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.Produto;

import io.realm.Realm;

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_ID = "_ID";

    private Realm realm;
    private Empresa empresa;
    private Produto produto;
    private Endereco endereco;

    private TextView tvEmpresa;
    private TextView tvHorarioFunc;
    private TextView tvHorariosEntrega;
    private TextView tvEndereco;
    private TextView tvTelefones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Faça seu Pedido");
        }

        FloatingActionButton fabProdEmp = (FloatingActionButton) findViewById(R.id.fabProdEmp);
        fabProdEmp.setOnClickListener(this);

        ListView ProdutosEmpresaListView = (ListView) findViewById(R.id.ProdutosEmpresaListView);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvHorarioFunc = (TextView)findViewById(R.id.tvHorarioFunc);
        tvHorariosEntrega = (TextView)findViewById(R.id.tvHorariosEntrega);
        tvEndereco = (TextView)findViewById(R.id.tvEndereco);
        tvTelefones = (TextView)findViewById(R.id.tvTelefones);

        final int id;

        id = getIntent().getExtras().getInt(EXTRA_ID, 0);

        if (id > 0) {
            empresa = realm.where(Empresa.class).equalTo("id", id).findFirst();
            tvEmpresa.setText(empresa.getNome_fantasia());
            tvHorarioFunc.setText(empresa.getHorario_func());
            tvHorariosEntrega.setText(empresa.getHorario_entrega());
            // concatenar os 3 telefones
            tvTelefones.setText(empresa.getTel1()+ "; " +empresa.getTel2()+ "; " +empresa.getTel3());
            // buscar o endereco desta empresa concatenado com bairro, numero, complemento
            tvEndereco.setText(endereco.getEndereco());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabProdEmp:
                Intent ca = new Intent(this, CarrinhoActivity.class);
                startActivity(ca);
                break;
        }
    }
}
