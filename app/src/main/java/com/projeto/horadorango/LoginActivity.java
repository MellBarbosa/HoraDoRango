package com.projeto.horadorango;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projeto.horadorango.Model.Usuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText edtLogin;
    private EditText edtSenha;
    private TextView tvEsqueciSenha;
    private Button btLogin;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        tvEsqueciSenha = (TextView) findViewById(R.id.tvEsqueciSenha);
        btLogin = (Button) findViewById(R.id.btLogin);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCadastrar:
                Intent u = new Intent(this, UsuarioActivity.class);
                startActivity(u);
            case R.id.btLogin:

        }
    }
}