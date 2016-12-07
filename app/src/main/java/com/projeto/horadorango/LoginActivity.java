package com.projeto.horadorango;

import android.content.Intent;
import android.media.Image;
import android.opengl.Visibility;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.projeto.horadorango.api.ApiComunicator;
import com.projeto.horadorango.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.Arrays;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.projeto.horadorango.R.id.login_button;

public class LoginActivity extends AppCompatActivity {

    public static CallbackManager callbackManager;
    private LoginButton login_button;
    private ImageView imgLogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.activity_login);

        login_button = (LoginButton) findViewById(R.id.login_button);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);

        login_button.setReadPermissions(Arrays.asList("email", "public_profile"));

        callbackManager = CallbackManager.Factory.create();
        login_button.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        carregarInformacoes(loginResult.getAccessToken());
                    }


                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        exibirErro();
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void carregarInformacoes( AccessToken FacebookToken ){

        GraphRequest request = GraphRequest.newMeRequest(FacebookToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.i("LoginActivity", response.toString());

                try {
                    logar(object.getString("id"),object.getString("name"),object.getString("email"));
                } catch (JSONException e) {
                    exibirErro();
                }

            }
        });
        Bundle parameters = new Bundle(); parameters.putString("fields", "id,name,email"); request.setParameters(parameters);
        request.executeAsync();


    }

    public void exibirErro(){

        LoginManager.getInstance().logOut();
        Toast.makeText(this, "Erro ao Efetuar Login", Toast.LENGTH_LONG).show();

    }

    public void logar(String id,String name,String email) {

        ApiComunicator.get().login(name, email, id).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

              salvarUsuario(response.body());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                exibirErro();
            }
        });
    }

    public void salvarUsuario(Usuario usuario){

        android.util.Log.e("userID", "" + usuario.getId());
        login_button.setVisibility(View.GONE);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(usuario);
        realm.commitTransaction();

        startActivity(new Intent(this, MainActivity.class));

        finish();


    }


}