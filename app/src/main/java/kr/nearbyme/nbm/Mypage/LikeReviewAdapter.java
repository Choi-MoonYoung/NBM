package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.LikePost;
import kr.nearbyme.nbm.data.PostResult;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class LikeReviewAdapter extends RecyclerView.Adapter<LikeReviewViewHolder> {
    List<PostResult> items = new ArrayList<>();

    LikeReviewViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(LikeReviewViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(PostResult post) {
        items.add(post);
        notifyDataSetChanged();
    }

    public void addAll(List<PostResult> posts) {
        items.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public LikeReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_like_review, parent, false);
        return new LikeReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LikeReviewViewHolder holder, int position) {
        holder.setLikeReview(items.get(position));
        holder.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}

