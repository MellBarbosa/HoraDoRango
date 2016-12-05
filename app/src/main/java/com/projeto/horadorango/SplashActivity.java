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
import com.projeto.horadorango.model.Usuario;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.projeto.horadorango.R.id.edtReferencia;
import static com.projeto.horadorango.R.id.end;

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

        for(int i = 0; i < sincronizacao.getBairros().size() ;i++) {
            Bairro bairro = sincronizacao.getBairros().get(i);
            realm.copyToRealmOrUpdate(bairro);
        }

        for(int i = 0; i < sincronizacao.getCategorias().size() ;i++) {
            Categoria categoria = sincronizacao.getCategorias().get(i);
            realm.copyToRealmOrUpdate(categoria);
        }

       for (int i = 0; i < sincronizacao.getCidades().size() ;i++) {
           Cidade cidade = sincronizacao.getCidades().get(i);
           realm.copyToRealmOrUpdate(cidade);
        }

        for(int i = 0; i < sincronizacao.getEmpresas().size() ;i++) {
            Empresa empresa = sincronizacao.getEmpresas().get(i);

            if (empresa.getEndereco() == null) {
                Endereco endereco = realm.where(Endereco.class).equalTo("id", empresa.getEnderecoId()).findFirst();
                empresa.setEndereco(endereco);
            }

            realm.copyToRealmOrUpdate(empresa);
        }

       for (int i = 0; i < sincronizacao.getEnderecos().size() ;i++) {
            Endereco endereco = sincronizacao.getEnderecos().get(i);

           if (endereco.getBairro() == null) {
               Bairro bairro = realm.where(Bairro.class).equalTo("id", endereco.getBairro_id()).findFirst();
               endereco.setBairro(bairro);
           }

           if (endereco.getCidade() == null) {
               Cidade cidade = realm.where(Cidade.class).equalTo("id", endereco.getCidade_id()).findFirst();
               endereco.setCidade(cidade);
           }

            realm.copyToRealmOrUpdate(endereco);


        }

        for (int i = 0; i < sincronizacao.getProdutos().size() ;i++) {
            Produto produto = sincronizacao.getProdutos().get(i);

            if (produto.getEmpresa() == null) {
                Empresa empresa = realm.where(Empresa.class).equalTo("id", produto.getEmpresaId()).findFirst();
                produto.setEmpresa(empresa);
            }

            if (produto.getCategoria() == null) {
                Categoria categoria = realm.where(Categoria.class).equalTo("id", produto.getCategoriaId()).findFirst();
                produto.setCategoria(categoria);
            }

            realm.copyToRealmOrUpdate(produto);
        }

        realm.commitTransaction();

        if (realm.where(Usuario.class).findAll().size() == 0){
            startActivity(new Intent(this, LoginActivity.class));
        }else {
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }

    public void exibirErros(){
       Log.e("erro de sinconizacao", "erro");
    }
}
