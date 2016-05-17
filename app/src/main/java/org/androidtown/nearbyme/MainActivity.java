package org.androidtown.nearbyme;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.androidtown.nearbyme.Mypage.MyPageFragment;
import org.androidtown.nearbyme.Review.ReviewFragment;
import org.androidtown.nearbyme.Store.StoreFragment;
import org.androidtown.nearbyme.Writereview.WriteReviewFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("ReviewList").setIndicator("후기"), ReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("StoreList").setIndicator("매장"), StoreFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("MyPage").setIndicator("마이페이지"), MyPageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("WriteReview").setIndicator("후기쓰기"), WriteReviewFragment.class, null);
    }
}
