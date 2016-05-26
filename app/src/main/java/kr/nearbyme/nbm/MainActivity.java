package kr.nearbyme.nbm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.support.v4.app.FragmentTabHost;


import kr.nearbyme.nbm.Mypage.MyPageFragment;
import kr.nearbyme.nbm.Review.ReviewFragment;
import kr.nearbyme.nbm.Store.StoreFragment;
import kr.nearbyme.nbm.Writereview.WriteReviewFragment;


public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("ReviewList").setIndicator("후기"), ReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("StoreList").setIndicator("매장"), StoreFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("WriteReview").setIndicator("글쓰기"), WriteReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("MyPage").setIndicator("마이"), MyPageFragment.class, null);


    }

}
