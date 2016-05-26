package org.kr.nearbyme.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.kr.nearbyme.R;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class StoreListViewHolder extends RecyclerView.ViewHolder{
    TextView storeNameView, storeDescriptionView, storeDistanceView;
    ImageView storeImageView;
    Store mData;

    public StoreListViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_store_name);
        storeDescriptionView = (TextView) itemView.findViewById(R.id.text_store_description);
        storeDistanceView = (TextView) itemView.findViewById(R.id.text_distance);
        storeImageView = (ImageView) itemView.findViewById(R.id.image_store);
    }

    public void setStoreData(Store data){
        mData = data;
        storeNameView.setText(data.getStoreName());
        storeDescriptionView.setText(data.getStoreDescription());
        storeDistanceView.setText(data.getDistance());
        storeImageView.setImageDrawable(data.getStoreImg());

    }
}
