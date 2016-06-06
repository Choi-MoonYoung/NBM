package kr.nearbyme.nbm.Mypage;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Review.ReviewDetailActivity;
import kr.nearbyme.nbm.data.UserWritingResult;
import kr.nearbyme.nbm.data.UserWritingResults;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;


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
        mAdapter.setOnItemClickListener(new MyReviewViewHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View view, UserWritingResult data) {
                Toast.makeText(getContext(), "눌렸습니다", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), ReviewDetailActivity.class);
                intent.putExtra(ReviewDetailActivity.EXTRA_REVIEW_ID, data.writing.getWriting_id());
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
        View view = inflater.inflate(R.layout.fragment_view_my_review, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_myreview);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void initData() {

        NetworkManager.getInstance().getMyReviewList(new NetworkManager.OnResultListener<UserWritingResults>() {
            @Override
            public void onSuccess(Request request, UserWritingResults result) {
                mAdapter.clear2();
                mAdapter.addAll2(result.userWritingResults);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
