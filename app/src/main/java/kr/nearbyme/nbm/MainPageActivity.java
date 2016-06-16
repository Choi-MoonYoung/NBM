package kr.nearbyme.nbm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.io.IOException;
import java.util.Arrays;

import kr.nearbyme.nbm.data.LoginServerResult;
import kr.nearbyme.nbm.manager.NetworkManager;
import kr.nearbyme.nbm.manager.PropertyManager;
import okhttp3.Request;


public class MainPageActivity extends AppCompatActivity {
    Button btnEnter, btnFacebook;

    CallbackManager callbackManager;
    LoginManager loginManager;
    int user_sort = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnEnter = (Button) findViewById(R.id.btn_enter);
        btnFacebook = (Button) findViewById(R.id.btn_facebook);

        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(intent);
                PropertyManager.getInstance().setIsGuest(1);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
//                startActivity(intent);
                login();
            }
        });

    }

    private void login() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token == null) {
            loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    serverLogin();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });

            loginManager.logInWithReadPermissions(this, Arrays.asList("email"));
        } else {
            serverLogin();
        }
    }

    private void serverLogin() {
        AccessToken user_token = AccessToken.getCurrentAccessToken();
        if (user_token != null) {
            NetworkManager.getInstance().facebookLogin(user_sort, user_token.getToken(), new NetworkManager.OnResultListener<LoginServerResult>() {
//                @Override
//                public void onSuccess(Request request, String result) {
//                    startActivity(new Intent(MainPageActivity.this, MainActivity.class));
//                }

                @Override
                public void onSuccess(Request request, LoginServerResult result) {
                    PropertyManager.getInstance().setUser_id(result.result.getUser_id());
                    PropertyManager.getInstance().setUser_name(result.result.getUser_name());
                    PropertyManager.getInstance().setUser_profilePic(result.result.getUser_profilePic());
                    PropertyManager.getInstance().setIsGuest(0);

                    Log.d("user", result.result.getUser_name());
                    startActivity(new Intent(MainPageActivity.this, MainActivity.class));
                }

                @Override
                public void onFail(Request request, IOException exception) {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




}
