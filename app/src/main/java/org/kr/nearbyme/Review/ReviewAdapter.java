package org.kr.nearbyme.Review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kr.nearbyme.Store.Store;
import org.kr.nearbyme.Store.StoreData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }

    List<R> items = new ArrayList<R>();

    public void add(R r) {
        items.add(r);
        notifyDataSetChanged();
    }


    View view;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case VIEW_TYPE_HEADER :
                view = LayoutInflater.from(parent.getContext()).inflate(org.kr.nearbyme.R.layout.view_review_header, parent, false);
                return new ReviewHeaderViewHolder(view);
            case VIEW_TYPE_ITEM :
                view = LayoutInflater.from(parent.getContext()).inflate(org.kr.nearbyme.R.layout.view_review, parent, false);
                return new ReviewViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER :
                ReviewHeaderViewHolder headerViewHolder = (ReviewHeaderViewHolder)holder;
                headerViewHolder.setStoreSearchData((StoreData)items.get(position));
                break;
            case VIEW_TYPE_ITEM :
                ReviewViewHolder itemViewHolder = (ReviewViewHolder)holder;
                itemViewHolder.setStoreData((Store)items.get(position));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}



