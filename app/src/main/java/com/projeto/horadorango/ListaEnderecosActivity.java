package com.projeto.horadorango;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projeto.horadorango.Adapter.EnderecoAdapter;
import com.projeto.horadorango.DAO.EnderecoDao;
import com.projeto.horadorango.Model.Endereco;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListaEnderecosActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton fab;
    private ListView lvEnderecos;
    private ArrayList<Endereco> listaEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_enderecos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Meus Endereços");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        lvEnderecos = (ListView)findViewById(R.id.lvEnderecos);

    //    lvEnderecos.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab:
                Intent ed = new Intent(this, EnderecoActivity.class);
                startActivity(ed);
        }

    }

}
