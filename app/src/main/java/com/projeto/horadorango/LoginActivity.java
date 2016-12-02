package com.projeto.horadorango;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

import org.json.JSONException;
import org.json.JSONObject;

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

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        //    Profile profile = Profile.getCurrentProfile();
                        //   Log.e("fb", profile.getName());
                        //   Log.e("fb", profile.getId());
                        //    Log.e("fb", profile.getName());
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("LoginActivity", response.toString());
                                // Get facebook data from login
                                // Bundle bFacebookData = getFacebookData(object);

                                try {
                                    Log.e("fb", object.getString("email"));
                                    Log.e("fb", object.getString("id"));
                                    Log.e("fb", object.getString("first_name"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

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

    private void onFblogin() {



        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}