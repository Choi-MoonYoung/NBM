package kr.nearbyme.nbm;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.Mypage.MyPageFragment;
import kr.nearbyme.nbm.Review.ReviewFragment;
import kr.nearbyme.nbm.Store.StoreFragment;
import kr.nearbyme.nbm.Writereview.WriteReviewFragment;
import kr.nearbyme.nbm.manager.PropertyManager;


public class MainActivity extends AppCompatActivity implements MapFragment.DoneClickListener{
    private FragmentTabHost mTabHost;
    Button loc_button;
    public double locX = 0;
    public double locY = 0;
    public int radius = 0;

    private static final String TAG_F1 = "f1tag";

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

                MapFragment dialog = new MapFragment();
                dialog.setMapDoneClickListener(MainActivity.this);
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


    public interface OnNotifyUpdateListener {
        public void onNotifyUpdate();
    }

    List<OnNotifyUpdateListener> notifiers = new ArrayList<>();

    public void addOnOnNotifyUpdateListener(OnNotifyUpdateListener listener) {
        notifiers.add(listener);
    }

    public void removeOnNotifyUpdateListener(OnNotifyUpdateListener listener) {
        notifiers.remove(listener);
    }

    public void notifyUpdate() {
        for (OnNotifyUpdateListener l : notifiers) {
            l.onNotifyUpdate();
        }
    }

    @Override
    public void onDoneClick(double locx, double locy, int radius) {
        locX = locx;
        locY = locy;
        this.radius = radius;

        PropertyManager.getInstance().setMyPosition(locX, locY);
        PropertyManager.getInstance().setMyRadius(this.radius);

        notifyUpdate();
    }

}
