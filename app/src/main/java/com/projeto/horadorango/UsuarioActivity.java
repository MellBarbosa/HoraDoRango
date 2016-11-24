package com.projeto.horadorango;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.projeto.horadorango.model.Usuario;

public class UsuarioActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtSenha;
//    private EditText edtSenhaAtual;
    private EditText edtConfirmacaoSenha;
    private CheckBox chkTermos;
    private Button btConfirmar;
    private boolean novo;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Cadastro Login");

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
     //   edtSenhaAtual = (EditText)findViewById(R.id.edtSenhaAtual);
        edtConfirmacaoSenha = (EditText)findViewById(R.id.edtConfirmacaoSenha);
        chkTermos = (CheckBox)findViewById(R.id.chkTermos);
        btConfirmar = (Button)findViewById(R.id.btConfirmar);

 //       edtNome.setOnClickListener(this);
 //       edtEmail.setOnClickListener(this);
 //       edtTelefone.setOnClickListener(this);
 //       edtSenha.setOnClickListener(this);
 //       edtSenhaAtual.setOnClickListener(this);
 //       edtConfirmacaoSenha.setOnClickListener(this);
  //      chkTermos.setOnClickListener(this);
 //       btConfirmar.setOnClickListener(this);

        Usuario u = (Usuario)getIntent().getSerializableExtra("Usuario");
        if (u !=null){
            novo = false;
            usuario = u;
            edtNome.setText(usuario.getLogin());
            edtEmail.setText(usuario.getEmail());
            edtTelefone.setText(usuario.getTelefone());
            edtSenha.setText(usuario.getSenha());
         //   chkTermos.setText(usuario.getTermos());
        }
        else{
            novo = true;
            usuario = new Usuario();
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

 /*   @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btConfirmar:
                String nome;
                String telefone;

                nome = edtNome.getText().toString();
                telefone = edtTel.getText().toString();

                usuario.setNome(nome);
                usuario.setTelefone(telefone);
                usuario.setCategoria(categoria);

                UsuarioDao DAO = new UsuarioDao(this);
                if (novo){
                    DAO.insert(usuario);
                    new AlertDialog.Builder(this)
                            .setTitle("ATENÇÃO")
                            .setMessage("Cliente Salvo com Sucesso")
                            .setNeutralButton("Ok", null)
                            .show();
                }
                else{
                    DAO.update(usuario);
                    new AlertDialog.Builder(this)
                            .setTitle("ATEN��O")
                            .setMessage("Cliente Alterado com Sucesso")
                            .setNeutralButton("Ok", null)
                            .show();
                }

                edtNome.setText("");
                edtTel.setText("");
                spCategoria.setSelection(0);
                break;
            case R.id.btVoltar:
                finish();
                break;
        }
    }   */
}
