package com.projeto.horadorango;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.horadorango.adapter.ProdutoAdapter;
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.PedidoItem;
import com.projeto.horadorango.model.Produto;

import org.parceler.Parcels;

import java.util.List;

import io.realm.Realm;

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_ID = "_ID";

    private Realm realm;
    private Empresa empresa;
    private Produto produto;
    private Endereco endereco;
    private ProdutoAdapter produtoAdapter;

    private TextView tvEmpresa;
    private TextView tvHorarioFunc;
    private TextView tvHorariosEntrega;
    private TextView tvEnderecoEmpresa;
    private TextView tvTelefones;
    private ImageView ivMenos;
    private ImageView ivMais;
    private TextView tvQuantidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        if (getSupportActionBar() != null) {
          //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Faça seu Pedido");
        }

        FloatingActionButton fabProdEmp = (FloatingActionButton) findViewById(R.id.fabProdEmp);
        fabProdEmp.setOnClickListener(this);

        ListView ProdutosEmpresaListView = (ListView) findViewById(R.id.ProdutosEmpresaListView);
        tvEmpresa = (TextView) findViewById(R.id.tvEmpresa);
        tvHorarioFunc = (TextView)findViewById(R.id.tvHorarioFunc);
        tvHorariosEntrega = (TextView)findViewById(R.id.tvHorariosEntrega);
        tvEnderecoEmpresa = (TextView)findViewById(R.id.tvEndereco);
        tvTelefones = (TextView)findViewById(R.id.tvTelefones);
        ivMenos = (ImageView)findViewById(R.id.ivMenos);
        ivMais = (ImageView)findViewById(R.id.ivMais);

        final int id;

        id = getIntent().getExtras().getInt(EXTRA_ID, 0);

        if (id > 0) {
            realm = Realm.getDefaultInstance();
            empresa = realm.where(Empresa.class).equalTo("id", id).findFirst();
            tvEmpresa.setText(empresa.getNome_fantasia());
            tvHorarioFunc.setText(empresa.getHorario_func());
            tvHorariosEntrega.setText(empresa.getHorario_entrega());
            tvTelefones.setText(empresa.getTel1()+ "; " +empresa.getTel2()+ "; " +empresa.getTel3());
        //    tvEnderecoEmpresa.setText(empresa.getEndereco().getEndereco());// + ',' + empresa.getEndereco().getNumero() + ',' + empresa.getEndereco().getComplemento() + ',' + empresa.getEndereco().getBairro().getDescricao() );
        }

        ListView produtosEmpresaListView = (ListView) findViewById(R.id.ProdutosEmpresaListView);

        produtoAdapter = new ProdutoAdapter(this, realm.where(Produto.class).equalTo("empresa.id", id).findAll());
        produtosEmpresaListView.setAdapter(produtoAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabProdEmp:

                List<PedidoItem> itemsPedido = produtoAdapter.getProdutosSelecionados();

                if(itemsPedido.size() == 0){
                    Toast.makeText(this, "Pedido não contém itens!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent ca = new Intent(this, CarrinhoActivity.class);
                ca.putExtra(CarrinhoActivity.EXTRA_IDENT, Parcels.wrap(itemsPedido));
                startActivity(ca);

                break;
        }
    }
}
