package kr.nearbyme.nbm.Review;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.MainActivity;
import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.PostListResult;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.ReviewData;
import kr.nearbyme.nbm.manager.NetworkManager;
import kr.nearbyme.nbm.manager.PropertyManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment implements KeywordFragment.KeyWordDoneClickListener, MainActivity.OnNotifyUpdateListener {
    RecyclerView recyclerView;
    ReviewAdapter mAdapter;
    TokenAdapter mTokenAdapter;
    GridLayoutManager mLayoutManager;
    private List<String> filters =new ArrayList<>();
    private double locX;
    private double locY;
    private int radius;


    public ReviewFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ReviewAdapter();

        mAdapter.setOnItemClickListener(new ReviewListViewHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View view, PostResult post) {

                Intent intent = new Intent(getContext(), ReviewDetailActivity.class);
                intent.putExtra(ReviewDetailActivity.EXTRA_REVIEW_ID, post.post.getPost_id());
                startActivity(intent);
            }

        });

        mAdapter.setOnItemClickListener(new ReviewHeaderViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ReviewData reviewData) {
                KeywordFragment f = new KeywordFragment();
                f.setKeyWordDoneClickListener(ReviewFragment.this);
                f.show(getActivity().getSupportFragmentManager(), "create");


            }
        });



        filters.add("단발");
        PropertyManager.getInstance().setFilters(filters);


    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).addOnOnNotifyUpdateListener(this);
        locX = PropertyManager.getInstance().getLatitude();
        locY = PropertyManager.getInstance().getLongitude();
        radius = PropertyManager.getInstance().getMyRadius();
        filters = PropertyManager.getInstance().getFilters();

        initData();

        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).removeOnNotifyUpdateListener(this);
    }

    @Override
    public void onNotifyUpdate() {
        initData();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_review);

        View v = inflater.inflate(R.layout.view_review_header, container, false);

        recyclerView.setAdapter(mAdapter);

        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                if (type == ReviewAdapter.VIEW_TYPE_HEADER) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    private void initData() {
        filters = PropertyManager.getInstance().getFilters();
        locX = PropertyManager.getInstance().getLatitude();
        locY = PropertyManager.getInstance().getLongitude();
        radius = PropertyManager.getInstance().getMyRadius();

        NetworkManager.getInstance().getPostList(getContext(), filters, locX, locY, radius, new NetworkManager.OnResultListener<PostListResult>() {
            @Override
            public void onSuccess(Request request, PostListResult result) {

                mAdapter.clear();
                mAdapter.addAll(result.result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onKeyWordDoneClick(List<String> keyFilters) {
        PropertyManager.getInstance().setFilters(keyFilters);

        initData();

        mAdapter.notifyDataSetChanged();

    }


}
