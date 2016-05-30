package kr.nearbyme.nbm.Mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getContext(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("myReview").setIndicator("내글보기"), ViewMyReviewFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("likeStore").setIndicator("찜한매장"), ViewLikeStoreFragment.class, null );
        mTabHost.addTab(mTabHost.newTabSpec("likeReview").setIndicator("좋아요한후기"), ViewLikeReviewFragment.class, null );

        return view;
    }

}
