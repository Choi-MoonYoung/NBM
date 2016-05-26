package org.kr.nearbyme.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;

import org.kr.nearbyme.R;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class StoreSearchViewHolder extends RecyclerView.ViewHolder {
    ImageView iconView;
    EditText store;
    Button order;
    StoreData mData;

    public StoreSearchViewHolder(View itemView) {
        super(itemView);
        store = (EditText) itemView.findViewById(R.id.edit_store_search);
        order = (Button) itemView.findViewById(R.id.btn_order);
        iconView = (ImageView) itemView.findViewById(R.id.image_search);
    }


    public void setStoreSearchData(StoreData data){
        mData = data;
        //store.setText(data.getStoreName());
        //order.setText(data.getStoreDescription());
        //storeDistanceView.setText(data.getDistance());
        iconView.setImageDrawable(data.getSearchImg());

    }
}
