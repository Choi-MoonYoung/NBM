package org.kr.nearbyme.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kr.nearbyme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewViewHolder> {
    List<MyReview> items = new ArrayList<MyReview>();

    public void add(MyReview review) {
        items.add(review);
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
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
