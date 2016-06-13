package kr.nearbyme.nbm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.AccessToken;

import java.io.IOException;

import kr.nearbyme.nbm.data.LoginServerResult;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;

public class SplashActivity extends AppCompatActivity {
    Handler mHandler = new Handler(Looper.getMainLooper());
    int user_sort = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        AccessToken user_token = AccessToken.getCurrentAccessToken();
        if (user_token == null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, MainPageActivity.class));
                    finish();
                }
            }, 2000);
        } else {
            NetworkManager.getInstance().facebookLogin(user_sort, user_token.getToken(), new NetworkManager.OnResultListener<LoginServerResult>() {
//                @Override
//                public void onSuccess(Request request, String result) {
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        }
//                    }, 2000);
//                }

                @Override
                public void onSuccess(Request request, LoginServerResult result) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    }, 2000);
                }

                @Override
                public void onFail(Request request, IOException exception) {
                    Toast.makeText(SplashActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
