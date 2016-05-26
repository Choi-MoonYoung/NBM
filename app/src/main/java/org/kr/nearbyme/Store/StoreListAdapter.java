package org.kr.nearbyme.Store;

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
public class StoreListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }

    List<S> items = new ArrayList<S>();

    public void add(S s) {
        items.add(s);
        notifyDataSetChanged();
    }


    View view;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case VIEW_TYPE_HEADER :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_store, parent, false);
                return new StoreSearchViewHolder(view);
            case VIEW_TYPE_ITEM :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_store, parent, false);
                return new StoreListViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER :
                StoreSearchViewHolder headerViewHolder = (StoreSearchViewHolder)holder;
                headerViewHolder.setStoreSearchData((StoreData)items.get(position));
                break;
            case VIEW_TYPE_ITEM :
                StoreListViewHolder itemViewHolder = (StoreListViewHolder)holder;
                itemViewHolder.setStoreData((Store)items.get(position));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
