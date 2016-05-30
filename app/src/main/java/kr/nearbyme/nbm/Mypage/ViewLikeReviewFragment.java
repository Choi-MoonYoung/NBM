
package kr.nearbyme.nbm.Mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Review.ReviewDetailActivity;
import kr.nearbyme.nbm.data.LikePost;
import kr.nearbyme.nbm.data.Post;
import kr.nearbyme.nbm.data.PostListResult;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.UserWritingResults;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewLikeReviewFragment extends Fragment {
    RecyclerView recyclerView;
    LikeReviewAdapter mAdapter;

    public ViewLikeReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new LikeReviewAdapter();
        mAdapter.setOnItemClickListener(new LikeReviewViewHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View view, PostResult post) {
                Toast.makeText(getContext(), "눌렸습니다", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), ReviewDetailActivity.class);
                intent.putExtra(ReviewDetailActivity.EXTRA_REVIEW_ID, post.post.getPost_id());
                startActivity(intent);
            }

        });




    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        recyclerView.scrollToPosition(0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_like_review, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_mylikereview);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));



        return view;

    }

    private void initData() {

        NetworkManager.getInstance().getLikePost(new NetworkManager.OnResultListener<LikePost>() {
            @Override
            public void onSuccess(Request request, LikePost result) {


                mAdapter.clear();
                mAdapter.addAll(result.result);

                //mAdapter.addStore(result.getShop_info());
                // mAdapter.addAll(result.getPost_info());

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



/*
        for (int i = 0; i < 40 ; i++) {
            Post p = new Post();
            p.setPost_pic(ContextCompat.getDrawable(getContext(), R.drawable.item3));
            //p.setPost_filters("");
            p.setShop_name("shop" + i);
            p.setDsnr_name("designer" + i);
            p.setUser_profilePic(ContextCompat.getDrawable(getContext(), R.drawable.icon_person));
            p.setUser_name("user" + i);
            p.setPost_regDate("dddd");
            //p.setPost_filters();
            mAdapter.add(p);
        }
        */
    }



}
