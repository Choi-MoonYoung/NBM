package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.MyReview;
import kr.nearbyme.nbm.data.Post;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewViewHolder> {
    List<MyReview> items = new ArrayList<MyReview>();



    MyReviewViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(MyReviewViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(MyReview myReview) {
        items.add(myReview);
        notifyDataSetChanged();
    }

    public void addAll(List<MyReview> myReviews) {
        items.addAll(myReviews);
        notifyDataSetChanged();
    }




    @Override
    public MyReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_myreview, parent, false);
        return new MyReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyReviewViewHolder holder, int position) {
        holder.setReviewData(items.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
