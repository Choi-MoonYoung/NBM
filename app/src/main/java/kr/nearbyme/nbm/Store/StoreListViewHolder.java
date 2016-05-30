package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.bumptech.glide.Glide;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class StoreListViewHolder extends RecyclerView.ViewHolder{
    TextView storeNameView, storeDescriptionView, storeDistanceView;
    ImageView storeImageView;
    Button buttonLike;
    Shop mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, Shop shop);
    }
    public interface OnItemClickListener3 {
        public void onItemClick3(View view, Shop shop);
    }




    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    OnItemClickListener3 mListener3;
    public void setOnItemClickListener3(OnItemClickListener3 listener) {
        mListener3 = listener;
    }



    public StoreListViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_store_name);
        storeDescriptionView = (TextView) itemView.findViewById(R.id.text_store_description);
        storeDistanceView = (TextView) itemView.findViewById(R.id.text_distance);
        storeImageView = (ImageView) itemView.findViewById(R.id.image_store);
        buttonLike = (Button) itemView.findViewById(R.id.buttonLike);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);
                }
            }
        });

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener3 != null) {
                    mListener3.onItemClick3(v, mData);

                }
            }
        });


    }

    public void setStoreData(Shop data){
        mData = data;
        storeNameView.setText(data.getShop_name());
        storeDescriptionView.setText(data.getShop_intro());
        storeDistanceView.setText(data.getShop_distance());

        Glide.with(storeImageView.getContext()).load(data.getShop_pic()).into(storeImageView);

    }
}
