package kr.nearbyme.nbm.Setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import kr.nearbyme.nbm.R;

public class SettingActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        btn1 = (Button)findViewById(R.id.btn_info);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, InfoActivity.class);
                startActivity(intent);

            }
        });
        btn2 = (Button) findViewById(R.id.btn_personinfo);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, PersonInfoActivity.class);
                startActivity(intent);

            }
        });
        btn3 = (Button) findViewById(R.id.btn_locationinfo);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, LocationInfoActivity.class);
                startActivity(intent);

            }
        });
        btn4 = (Button) findViewById(R.id.btn_logout);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("로그아웃 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        btn5 = (Button) findViewById(R.id.btn_delete);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("회원탈퇴시 개인정보 및 자료가 모두 삭제됩니다. 정말 탈퇴하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }

        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
