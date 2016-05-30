package kr.nearbyme.nbm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.nearbyme.nbm.MainActivity;

public class MainPageActivity extends AppCompatActivity {
    Button btnKakao, btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnKakao = (Button) findViewById(R.id.btn_kakao);
        btnFacebook = (Button) findViewById(R.id.btn_facebook);

        btnKakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
