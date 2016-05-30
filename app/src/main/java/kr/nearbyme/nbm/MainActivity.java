package kr.nearbyme.nbm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import android.support.v4.app.FragmentTabHost;
import android.widget.Toast;


import kr.nearbyme.nbm.Mypage.MyPageFragment;
import kr.nearbyme.nbm.Review.ReviewFragment;
import kr.nearbyme.nbm.Store.StoreDetailActivity;
import kr.nearbyme.nbm.Store.StoreFragment;
import kr.nearbyme.nbm.Writereview.WriteReviewFragment;


public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    Button loc_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setCustomView(R.layout.custom_action);

        loc_button = (Button) findViewById(R.id.btn_location);
        loc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "눌렸습니다", Toast.LENGTH_SHORT).show();
                MapFragment dialog = new MapFragment();
                dialog.show(getSupportFragmentManager(), "dialog");
            }
        });

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("ReviewList").setIndicator("후기"), ReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("StoreList").setIndicator("매장"), StoreFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("WriteReview").setIndicator("글쓰기"), WriteReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("MyPage").setIndicator("마이"), MyPageFragment.class, null);


    }

}
