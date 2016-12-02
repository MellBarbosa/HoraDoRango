package com.projeto.horadorango;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

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

         login_button.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
                // Call private method
                onFblogin();
            }
        });
    }

    private void onFblogin() {

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "name", "id"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}