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
 * Created by CHOIMOONYOUNG on 2016. 5. 24..
 */
public class TokenAdapter extends RecyclerView.Adapter<TokenViewHolder> {
    List<Key> items = new ArrayList<>();

    public void add(Key key) {
        items.add(key);
        notifyDataSetChanged();
    }
    public void addAll(List<Key> key){
        items.addAll(key);
        notifyDataSetChanged();
    }
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public TokenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_keyword, parent, false);
        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TokenViewHolder holder, int position) {
        holder.setTokenHeaderData(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}
