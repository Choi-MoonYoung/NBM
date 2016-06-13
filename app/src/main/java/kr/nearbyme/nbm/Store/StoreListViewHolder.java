package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
    RatingBar shoplistRatingbar;
    Shop mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, Shop shop);
    }
    public interface OnShopLikeClickListener {
        public void onShopLikeClick(View view, Shop shop);
    }




    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    OnShopLikeClickListener likeshopListener;
    public void setOnShopLikeClickListener(OnShopLikeClickListener listener) {
        likeshopListener = listener;
    }



    public StoreListViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_store_name);
        storeDescriptionView = (TextView) itemView.findViewById(R.id.text_store_description);
        storeDistanceView = (TextView) itemView.findViewById(R.id.text_distance);
        storeImageView = (ImageView) itemView.findViewById(R.id.image_store);
        buttonLike = (Button) itemView.findViewById(R.id.buttonLike);
        shoplistRatingbar = (RatingBar) itemView.findViewById(R.id.ratingBar);

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
                if (likeshopListener != null) {
                    likeshopListener.onShopLikeClick(v, mData);

                }
            }
        });


    }

    public void setStoreData(Shop data){
        mData = data;
        storeNameView.setText(data.getShop_name());
        storeDescriptionView.setText(data.getShop_intro());
        storeDistanceView.setText(data.getDist() + "M");

        if(data.getLiked() == 0){
            buttonLike.setBackgroundResource(R.drawable.btn_nm008_like_off);
        }
        else if(data.getLiked() == 1){
            buttonLike.setBackgroundResource(R.drawable.btn_nm008_like_on);
        }

        shoplistRatingbar.setRating((float)data.getShop_score());
        Glide.with(storeImageView.getContext()).load(data.getShop_pic()).into(storeImageView);

    }
}
