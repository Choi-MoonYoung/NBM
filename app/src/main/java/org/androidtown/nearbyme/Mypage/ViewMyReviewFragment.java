package org.androidtown.nearbyme.Mypage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidtown.nearbyme.Mypage.MyReview;
import org.androidtown.nearbyme.Mypage.MyReviewAdapter;
import org.androidtown.nearbyme.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewMyReviewFragment extends Fragment {
    RecyclerView recyclerView;
    MyReviewAdapter mAdapter;


    public ViewMyReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new MyReviewAdapter();



    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_my_review, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_myreview);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void initData() {
        for (int i = 0; i < 40 ; i++) {
            MyReview p = new MyReview();
            p.setReview("item" + i);
            p.setDate("date" + i);
            p.setKind(ContextCompat.getDrawable(getContext(), R.drawable.item1));
            mAdapter.add(p);
        }
    }




}
