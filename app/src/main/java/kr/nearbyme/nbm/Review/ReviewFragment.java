package kr.nearbyme.nbm.Review;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Designer;
import kr.nearbyme.nbm.data.Post;
import kr.nearbyme.nbm.data.PostListResult;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.ReviewData;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.ShopDetailResult;
import kr.nearbyme.nbm.data.User;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {
    RecyclerView recyclerView;
    ReviewAdapter mAdapter;
    GridLayoutManager mLayoutManager;
    private List<String> filters =new ArrayList<String>();
    private double locX = 1;
    private double locY = 1;
    private int radius = 1;


    public ReviewFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filters.add("2");
        mAdapter = new ReviewAdapter();

        mAdapter.setOnItemClickListener(new ReviewListViewHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View view, PostResult post) {
                Toast.makeText(getContext(), "눌렸습니다", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), ReviewDetailActivity.class);
                intent.putExtra(ReviewDetailActivity.EXTRA_REVIEW_ID, post.post.getPost_id());
                startActivity(intent);
            }

        });

        mAdapter.setOnItemClickListener(new ReviewHeaderViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ReviewData reviewData) {
                Toast.makeText(getContext(), "버튼이 눌렸습니다", Toast.LENGTH_SHORT).show();
                KeywordFragment f = new KeywordFragment();
                f.show(getActivity().getSupportFragmentManager(), "create");

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
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_review);

        View v = inflater.inflate(R.layout.view_review_header, null);

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

        NetworkManager.getInstance().getPostList(getContext(), filters, locX, locY, radius, new NetworkManager.OnResultListener<PostListResult>() {
            @Override
            public void onSuccess(Request request, PostListResult result) {



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
        ShopDetailResult result = new ShopDetailResult();
        result.post_info = new ArrayList<PostResult>();


        for (int i = 0; i < 40 ; i++) {
            PostResult p = new PostResult();
            p.shop = new Shop();
            p.dsnr = new Designer();
            p.user = new User();
            p.post = new Post();

            User user = new User();

            //p.setPost_pic(ContextCompat.getDrawable(getContext(), R.drawable.item3));
            //p.setPost_filters("");
            p.shop.setShop_name("shop" + i);
            p.dsnr.setDsnr_name("designer" + i);
            //p.setUser_profilePic(ContextCompat.getDrawable(getContext(), R.drawable.icon_person));
            p.user.setUser_name("user" + i);
            p.post.setPost_regDate("dddd");

            result.post_info.add(p);
        }
        mAdapter.add(result);

        */
    }

}