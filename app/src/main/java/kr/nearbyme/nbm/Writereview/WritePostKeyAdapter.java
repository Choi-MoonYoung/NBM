package kr.nearbyme.nbm.Writereview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Review.KeyContentViewHolder;
import kr.nearbyme.nbm.data.Key;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class WritePostKeyAdapter extends RecyclerView.Adapter<KeyContentViewHolder> implements KeyContentViewHolder.OnItemClickListener3{

    KeyContentViewHolder.OnItemClickListener3 mListener3;


    public void setOnItemClickListener3(KeyContentViewHolder.OnItemClickListener3 listener) {
        mListener3 = listener;
    }

    List<Key> items = new ArrayList<Key>();

    SparseBooleanArray checkedItems = new SparseBooleanArray();

    public void add(Key r) {
        items.add(r);
        notifyDataSetChanged();
    }
    public void addAll(List<Key> keys){
        items.addAll(keys);
    }
    public void clear() {
        items = null;
    }


        @Override
    public KeyContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_keywords, parent, false);

        return new KeyContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KeyContentViewHolder holder, int position) {
        holder.setKeyData(items.get(position));
        holder.setOnItemClickListener3(mListener3);
        holder.setChecked(checkedItems.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public SparseBooleanArray getCheckedItemPositions() {
        return checkedItems;
    }

    public void setItemCheck(int position, boolean check) {
        checkedItems.put(position, check);
//        Log.i("log_kwon", "position: " + position + ", check: " + check);
        notifyDataSetChanged();
    }

    @Override
    public void onItemClick3(View view, int position) {
        boolean checked = !checkedItems.get(position);
        checkedItems.put(position, checked);
        notifyDataSetChanged();
//        Log.i("log_kwon", "checkedItems size: " + checkedItems.size());
    }

}
