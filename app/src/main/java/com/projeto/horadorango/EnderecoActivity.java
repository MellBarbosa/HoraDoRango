package com.projeto.horadorango;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projeto.horadorango.DAO.BairroDao;
import com.projeto.horadorango.DAO.CidadeDao;
import com.projeto.horadorango.DAO.EnderecoDao;
import com.projeto.horadorango.Model.Bairro;
import com.projeto.horadorango.Model.Cidade;
import com.projeto.horadorango.Model.Endereco;
import com.projeto.horadorango.R;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.projeto.horadorango.R.id.btGravar;
import static com.projeto.horadorango.R.styleable.MenuItem;

public class EnderecoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEndereco;
    private Spinner spBairro;
    private EditText edtCep;
    private EditText edtNumero;
    private EditText edtComplemento;
    private Spinner spCidade;
    private EditText edtReferencia;
    private Button btGravar;
    private Bairro bairro;
    private Cidade cidade;
    private boolean novo;
    private Endereco endereco;
    private ArrayList<Bairro> listaBairro;
    private ArrayList<Cidade> listaCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Cadastro Endereço");

        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        spBairro = (Spinner) findViewById(R.id.spBairro);
        edtCep = (EditText) findViewById(R.id.edtCep);
        edtNumero = (EditText) findViewById(R.id.edtNumero);
        edtComplemento = (EditText) findViewById(R.id.edtComplemento);
        spCidade = (Spinner) findViewById(R.id.spCidade);
        edtReferencia = (EditText) findViewById(R.id.edtReferencia);
        btGravar = (Button)findViewById(R.id.btGravar);

        edtEndereco.setOnClickListener(this);
        edtCep.setOnClickListener(this);
        edtNumero.setOnClickListener(this);
        edtComplemento.setOnClickListener(this);
        edtReferencia.setOnClickListener(this);
        btGravar.setOnClickListener(this);

  /*      BairroDao bDao = null;
        try {
            bDao = new BairroDao(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
          listaBairro = (ArrayList<Bairro>) bDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaBairro);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBairro.setAdapter(adaptador);

        CidadeDao cDao = null;
        try {
            cDao = new CidadeDao(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            listaCidade = (ArrayList<Cidade>) cDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter adaptadorC = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCidade);
        adaptadorC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCidade.setAdapter(adaptadorC);
*/
        Endereco ed = (Endereco)getIntent().getSerializableExtra("Endereço");
        if (ed !=null){
            novo = false;
            endereco = ed;
            edtEndereco.setText(endereco.getEndereco());
            edtCep.setText(endereco.getEndereco());
            edtNumero.setText(endereco.getNumero());
            edtComplemento.setText(endereco.getComplemento());
            edtReferencia.setText(endereco.getPonto_referencia());

          /*  for (int i = 0; i < listaBairro.size(); i++) {
                if (listaBairro.get(i).getId() == endereco.getBairro().getId())
                    spBairro.setSelection(i);
            }

            for (int i = 0; i < listaCidade.size(); i++) {
                if (listaCidade.get(i).getId() == endereco.getCidade().getId())
                    spCidade.setSelection(i);
            }*/
        }
        else{
            novo = true;
            endereco = new Endereco();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btGravar:
                endereco.setEndereco(edtEndereco.getText().toString());
                endereco.setNumero(Integer.parseInt(edtNumero.getText().toString()));
                endereco.setComplemento(edtComplemento.getText().toString());
                endereco.setCep(edtCep.getText().toString());
                endereco.setPonto_referencia(edtReferencia.getText().toString());
                EnderecoDao dao = null;
                try {
                    dao = new EnderecoDao(this);
                    dao.salvar(endereco);
                } catch (SQLException e) {
                    e.printStackTrace();
                }



                if (novo) {
              //      try {
               //         dao.insert(endereco);
               //     } catch (SQLException e) {
               //         e.printStackTrace();
               //     }
                    new AlertDialog.Builder(this)
                            .setTitle("Atenção")
                            .setMessage("Endereço Salvo com Sucesso")
                            .setNeutralButton("Ok", null)
                            .show();
                } else {
                    try {
                        dao.update(endereco);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    new AlertDialog.Builder(this)
                            .setTitle("Atenção")
                            .setMessage("Cliente Alterado com Sucesso")
                            .setNeutralButton("Ok", null)
                            .show();
                }

                edtComplemento.setText("");
                edtEndereco.setText("");
                edtNumero.setText("");
                edtCep.setText("");
                edtReferencia.setText("");
         //       spBairro.setSelection(0);
         //       spCidade.setSelection(0);
                break;
        }
    }
}
