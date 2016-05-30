package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class KeyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }

    List<Key> items = new ArrayList<Key>();
    Key header = new Key("");

    public void add(Key r) {
        items.add(r);
        notifyDataSetChanged();
    }
    public void addAll(List<Key> keys){
        items.addAll(keys);
    }

    public void addKeyHeader(Key r) {
        this.header = r;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case VIEW_TYPE_HEADER :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_searchkeyword, parent, false);
                return new KeyHeaderViewHolder(view);
            case VIEW_TYPE_ITEM :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_keywords, parent, false);
                return new KeyContentViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_HEADER :
                KeyHeaderViewHolder keyHeaderViewHolder = (KeyHeaderViewHolder)holder;
                keyHeaderViewHolder.setKeyHeaderData(header);
                break;
            case VIEW_TYPE_ITEM :
                position--;
                KeyContentViewHolder itemViewHolder = (KeyContentViewHolder)holder;
                itemViewHolder.setKeyData(items.get(position));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return (items.size()+1);
    }


}
