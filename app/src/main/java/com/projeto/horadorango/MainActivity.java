package com.projeto.horadorango;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.horadorango.adapter.EmpresaAdapter;
import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Bairro;
import com.projeto.horadorango.model.Categoria;
import com.projeto.horadorango.model.Produto;
import com.projeto.horadorango.model.Sincronizacao;
import com.projeto.horadorango.model.Usuario;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,  AdapterView.OnItemClickListener {

    public static final String TAG = "LOG";
    public static final String API = "http://localhost/simples/index.php/Api";

    private Realm realm;
    private EmpresaAdapter empresaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView empresaListView = (ListView) findViewById(R.id.empresaListView);
        empresaListView.setOnItemClickListener(this);

        realm = Realm.getDefaultInstance();
        empresaAdapter = new EmpresaAdapter(this, realm.where(Produto.class).equalTo("categoria.id", 1).findAll());
        empresaListView.setAdapter(empresaAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent p = new Intent(this, CadProdutoActivity.class);
            startActivity(p);
            // Handle the camera action
        } else if (id == R.id.nav_pedidos) {

           Intent u = new Intent(this, CadEmpresaActivity.class);
            startActivity(u);

        } else if (id == R.id.nav_enderecos) {

            Intent ed = new Intent(this, ListaEnderecosActivity.class);
            startActivity(ed);

        } else if (id == R.id.nav_sair) {

            LoginManager.getInstance().logOut();
            realm.beginTransaction();
            realm.where(Usuario.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, PedidoActivity.class);
        intent.putExtra(PedidoActivity.EXTRA_ID, empresaAdapter.getItem(position).getEmpresa().getId());
        startActivity(intent);
    }



}
