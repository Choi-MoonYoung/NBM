package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.ShopDetailResult;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 20..
 */
public class StoreDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_STOREINFO = 0;
    public static final int VIEW_TYPE_STORESTYLE = 1;

    List<PostResult> items = new ArrayList<PostResult>();
    Shop shopDetail = new Shop();

    ShopDetailResult result=new ShopDetailResult();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void setResult(ShopDetailResult _result) {
        result=_result;

    }

    public void addAll(List<PostResult> posts){
        items.addAll(posts);
        notifyDataSetChanged();

    }


    public void add(PostResult r) {
        items.add(r);
        notifyDataSetChanged();
    }



    ReviewViewHolder.OnItemClickListener mListener;
    public void setOnItemClickListener(ReviewViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return VIEW_TYPE_STOREINFO;
        }
        return VIEW_TYPE_STORESTYLE;
    }
    View view;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case VIEW_TYPE_STOREINFO :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_store_info, parent, false);
                return new StoreInfoViewHolder(view);
            case VIEW_TYPE_STORESTYLE :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_review, parent, false);
                return new ReviewViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_STOREINFO :
                StoreInfoViewHolder storeInfoViewHolder = (StoreInfoViewHolder)holder;
                //storeInfoViewHolder.setStoreInfo(result.);
                storeInfoViewHolder.setStoreInfo(result.getShop_info());
                break;
            case VIEW_TYPE_STORESTYLE :
                ReviewViewHolder itemViewHolder = (ReviewViewHolder)holder;

                if(result.getPost_info()==null){
                    itemViewHolder.setReview(null);
                }else {
                    itemViewHolder.setReview(result.getPost_info().get(holder.getPosition()-position));
                }
                itemViewHolder.setOnItemClickListener(mListener);
                break;
        }


    }

    @Override
    public int getItemCount() {
        if(result.post_info==null){
            return 0;
        }else {
            return result.post_info.size() + 1;
        }
    }


}
