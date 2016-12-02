package com.projeto.horadorango;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.projeto.horadorango.model.Bairro;
import com.projeto.horadorango.model.Categoria;
import com.projeto.horadorango.model.Cidade;
import com.projeto.horadorango.model.Empresa;
import com.projeto.horadorango.model.Produto;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mell on 16/11/2016.
 */

public class HoraDoRangoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("hora-do-rango")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .initialData(new Realm.Transaction() {
                    public void execute(Realm realm) {
                        initialData(realm);
                    }
                })
                .build();

        Realm.setDefaultConfiguration(config);


    }


    private void initialData(Realm realm) {

        realm.insert(Categoria.create(realm).setDescricao("Rango do Dia"));
        realm.insert(Categoria.create(realm).setDescricao("Bebidas"));
        realm.insert(Categoria.create(realm).setDescricao("Sobremesa"));

        realm.insert(Bairro.create(realm).setDescricao("Centro"));
        realm.insert(Bairro.create(realm).setDescricao("Niterói"));
        realm.insert(Bairro.create(realm).setDescricao("Liberdade"));

        realm.insert(Cidade.create(realm).setDescricao("Divinópolis"));
        realm.insert(Cidade.create(realm).setDescricao("Belo Horizonte"));
        realm.insert(Cidade.create(realm).setDescricao("Rio de Janeiro"));

    }
}
