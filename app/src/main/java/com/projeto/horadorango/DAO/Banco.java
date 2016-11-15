package com.projeto.horadorango.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.projeto.horadorango.Model.Bairro;
import com.projeto.horadorango.Model.Categoria;
import com.projeto.horadorango.Model.Cidade;
import com.projeto.horadorango.Model.Empresa;
import com.projeto.horadorango.Model.Endereco;
import com.projeto.horadorango.Model.Pedido;
import com.projeto.horadorango.Model.PedidoItem;
import com.projeto.horadorango.Model.Produto;
import com.projeto.horadorango.Model.Usuario;
import com.projeto.horadorango.Util.StringUtil;

import java.sql.SQLException;

/**
 * Created by Mell on 25/10/2016.
 */

public class Banco<E> extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "HoradoRango.db";
    private static final int DATABASE_VERSION = 1;

    public Banco(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource){
        try {
            TableUtils.createTable(connectionSource, Bairro.class);
            TableUtils.createTable(connectionSource, Categoria.class);
            TableUtils.createTable(connectionSource, Cidade.class);
            TableUtils.createTable(connectionSource, Empresa.class);
            TableUtils.createTable(connectionSource, Endereco.class);
            TableUtils.createTable(connectionSource, Pedido.class);
            TableUtils.createTable(connectionSource, PedidoItem.class);
            TableUtils.createTable(connectionSource, Produto.class);
            TableUtils.createTable(connectionSource, Usuario.class);

        } catch (SQLException e) {
            Log.e(StringUtil.APP_TAG, "Criar banco", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion){
        try {

            TableUtils.dropTable(connectionSource, Bairro.class, false);
            TableUtils.dropTable(connectionSource, Categoria.class, false);
            TableUtils.dropTable(connectionSource, Cidade.class, false);
            TableUtils.dropTable(connectionSource, Empresa.class, false);
            TableUtils.dropTable(connectionSource, Endereco.class, false);
            TableUtils.dropTable(connectionSource, Pedido.class, false);
            TableUtils.dropTable(connectionSource, PedidoItem.class, false);
            TableUtils.dropTable(connectionSource, Produto.class, false);
            TableUtils.dropTable(connectionSource, Usuario.class, false);
        } catch (SQLException e) {
            Log.e(StringUtil.APP_TAG, "Apagar banco", e);
        }
        onCreate(database, connectionSource);
    }
}
