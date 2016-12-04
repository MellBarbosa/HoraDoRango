package com.projeto.horadorango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Bairro;
import com.projeto.horadorango.model.Categoria;
import com.projeto.horadorango.model.Cidade;
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Endereco;
import com.projeto.horadorango.model.Produto;
import com.projeto.horadorango.model.Sincronizacao;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.projeto.horadorango.R.id.edtReferencia;

public class SplashActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        realm = Realm.getDefaultInstance();

        ApiComunicator.get().sincronizar().enqueue(new Callback<Sincronizacao>() {
            @Override
            public void onResponse(Call<Sincronizacao> call, Response<Sincronizacao> response) {
                Log.e("sincronizacao", response.body()+"");
                salvarInformacoes(response.body());
            }

            @Override
            public void onFailure(Call<Sincronizacao> call, Throwable t) {
                exibirErros();
            }
        });
    }

    public void salvarInformacoes(Sincronizacao sincronizacao){

        realm.beginTransaction();

        for(int i =0; i < sincronizacao.getBairros().size() ;i++) {
            Bairro bairro = sincronizacao.getBairros().get(i);
            bairro = realm.copyToRealmOrUpdate(bairro);
            realm.insertOrUpdate(bairro);
        }

        for(int i =0; i < sincronizacao.getCategorias().size() ;i++) {
            Categoria categoria = sincronizacao.getCategorias().get(i);
            categoria = realm.copyToRealmOrUpdate(categoria);
            realm.insertOrUpdate(categoria);
        }

       for(int i =0; i < sincronizacao.getCidades().size() ;i++) {
            Cidade cidade = sincronizacao.getCidades().get(i);
            cidade = realm.copyToRealmOrUpdate(cidade);
            realm.insertOrUpdate(cidade);
        }

       for(int i =0; i < sincronizacao.getEnderecos().size() ;i++) {
            Endereco endereco = sincronizacao.getEnderecos().get(i);
            endereco = realm.copyToRealmOrUpdate(endereco);
            realm.insertOrUpdate(endereco);
        }

       for(int i =0; i < sincronizacao.getEmpresas().size() ;i++) {
            Empresa empresa = sincronizacao.getEmpresas().get(i);
            empresa = realm.copyToRealmOrUpdate(empresa);
            realm.insertOrUpdate(empresa);
        }

        for(int i =0; i < sincronizacao.getProdutos().size() ;i++) {
            Produto produto = sincronizacao.getProdutos().get(i);
            produto = realm.copyToRealmOrUpdate(produto);
            realm.insertOrUpdate(produto);
        }

        realm.commitTransaction();

        startActivity(new Intent(this, MainActivity.class));

    }

    public void exibirErros(){
       Log.e("erro de sinconizacao", "erro");
    }
}
