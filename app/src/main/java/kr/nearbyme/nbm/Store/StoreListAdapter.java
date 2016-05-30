package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.S;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.ShopListResult;
import kr.nearbyme.nbm.data.StoreData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class StoreListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    StoreListViewHolder.OnItemClickListener mListener;
    StoreListViewHolder.OnItemClickListener3 mListener3;

    public void setOnItemClickListener(StoreListViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnItemClickListener3(StoreListViewHolder.OnItemClickListener3 listener) {
        mListener3 = listener;
    }


    StoreSearchViewHolder.OnItemClickListener mListener2;
    public void setOnItemClickListener(StoreSearchViewHolder.OnItemClickListener listener) {
        mListener2 = listener;
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }


    List<Shop> items = new ArrayList<>();


    public void addAll(List<Shop> s) {
        items.addAll(s);
        notifyDataSetChanged();
    }

/*
    public void addShop(ShopListResult shopListResult){
        this.shopListResult = shopListResult;
        notifyDataSetChanged();
    }

*/
    public void clear() {
        items.clear();
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
                //headerViewHolder.setStoreSearchData((StoreData)items.get(position));
                headerViewHolder.setOnItemClickListener(mListener2);
                break;
            case VIEW_TYPE_ITEM :
                StoreListViewHolder itemViewHolder = (StoreListViewHolder)holder;
                position--;
                itemViewHolder.setStoreData((Shop)items.get(position));
                itemViewHolder.setOnItemClickListener(mListener);
                itemViewHolder.setOnItemClickListener3(mListener3);
                break;
        }


    }

    @Override
    public int getItemCount() {

        return (items.size()+1);
    }


}
