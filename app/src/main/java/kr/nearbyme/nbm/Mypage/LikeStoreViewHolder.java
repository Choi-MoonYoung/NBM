package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Button;

import com.bumptech.glide.Glide;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class LikeStoreViewHolder extends RecyclerView.ViewHolder{
    TextView storeNameView, storeDescriptionView, storeRatingNumView;
    ImageView storeImageView;
    Button buttonLike;
    RatingBar ratingBar;
    Shop mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, Shop shop);
    }
    public interface OnItemClickListener2 {
        public void onItemClick2(View view, Shop shop);
    }


    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    OnItemClickListener2 mListener2;
    public void setOnItemClickListener2(OnItemClickListener2 listener) {
        mListener2 = listener;
    }



    public LikeStoreViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_store_name);
        storeDescriptionView = (TextView) itemView.findViewById(R.id.text_store_description);
        storeImageView = (ImageView) itemView.findViewById(R.id.image_store);
        buttonLike = (Button) itemView.findViewById(R.id.buttonLike);
//        storeRatingNumView = (TextView) itemView.findViewById(R.id.text_score);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

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
                if (mListener2 != null){
                    mListener2.onItemClick2(v, mData);
                }

            }
        });

//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//
//            }
//        });

    }

    public void setLikeStore(Shop data){
        mData = data;
        if(mData != null){
            storeNameView.setText(data.getShop_name());
            storeDescriptionView.setText(data.getShop_intro());
            //storeRatingNumView.setText(""+data.getShop_score());
            if(data.getLiked() == 0){
                buttonLike.setBackgroundResource(R.drawable.btn_nm008_like_off);
            }
            else if(data.getLiked() == 1){
                buttonLike.setBackgroundResource(R.drawable.btn_nm008_like_on);
            }

            ratingBar.setRating((float) data.getShop_score());
            Glide.with(storeImageView.getContext()).load(data.getShop_pic()).into(storeImageView);

        }


    }
}
