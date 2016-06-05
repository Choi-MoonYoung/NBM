package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.PostResult;


/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    ReviewListViewHolder.OnItemClickListener mListener;

    public void setOnItemClickListener(ReviewListViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    ReviewHeaderViewHolder.OnItemClickListener mListener1;
    public void setOnItemClickListener(ReviewHeaderViewHolder.OnItemClickListener listener) {
        mListener1 = listener;
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }


    List<PostResult> items = new ArrayList<PostResult>();

    public void addAll(List<PostResult> postResults) {
        this.items = postResults;
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
            case VIEW_TYPE_HEADER :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_review_header, parent, false);
                return new ReviewHeaderViewHolder(view);
            case VIEW_TYPE_ITEM :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_review, parent, false);
                return new ReviewListViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER :
                ReviewHeaderViewHolder headerViewHolder = (ReviewHeaderViewHolder)holder;
                headerViewHolder.setOnItemClickListener(mListener1);
                headerViewHolder.setData();
                break;
            case VIEW_TYPE_ITEM :
                position--;
                ReviewListViewHolder itemViewHolder = (ReviewListViewHolder)holder;
                itemViewHolder.setReviewList(items.get(position));
                itemViewHolder.setOnItemClickListener(mListener);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return (items.size()+1);
    }
}



