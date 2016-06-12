package kr.nearbyme.nbm.Mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Setting.SettingActivity;



/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {
    FragmentTabHost mTabHost;
    Button btn;


    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        btn = (Button) view.findViewById(R.id.btn_setting);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        ImageView tabView_myreview = new ImageView(getContext());
        tabView_myreview.setImageResource(R.drawable.tab_myreview_selector);

        ImageView tabView_likestore = new ImageView(getContext());
        tabView_likestore.setImageResource(R.drawable.tab_likestore_selector);

        ImageView tabView_likereview = new ImageView(getContext());
        tabView_likereview.setImageResource(R.drawable.tab_likereview_selector);



        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getContext(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("myReview").setIndicator(tabView_myreview), ViewMyReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("likeStore").setIndicator(tabView_likestore), ViewLikeStoreFragment.class, null );
        mTabHost.addTab(mTabHost.newTabSpec("likeReview").setIndicator(tabView_likereview), ViewLikeReviewFragment.class, null );

        return view;
    }

}
