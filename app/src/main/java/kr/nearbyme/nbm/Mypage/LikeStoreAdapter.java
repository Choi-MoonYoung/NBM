package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class LikeStoreAdapter extends RecyclerView.Adapter<LikeStoreViewHolder> {
    List<Shop> items = new ArrayList<Shop>();

    LikeStoreViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(LikeStoreViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    LikeStoreViewHolder.OnItemClickListener2 mListener2;
    public void setOnItemClickListener2(LikeStoreViewHolder.OnItemClickListener2 listener) {
        mListener2 = listener;
    }
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }


    public void add(Shop shop) {
        items.add(shop);
        notifyDataSetChanged();
    }

    public void addAll(List<Shop> result) {
        items.addAll(result);
        notifyDataSetChanged();
    }


    @Override
    public LikeStoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_like_store, parent, false);
        return new LikeStoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LikeStoreViewHolder holder, int position) {
        holder.setLikeStore(items.get(position));
        holder.setOnItemClickListener(mListener);
        holder.setOnItemClickListener2(mListener2);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}
