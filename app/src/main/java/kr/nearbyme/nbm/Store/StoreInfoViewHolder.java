package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 20..
 */
public class StoreInfoViewHolder extends RecyclerView.ViewHolder{
    TextView scoreView, storeNameView, storeInfoView, storeAdressView, storeHourView, storeCallView,
            storePriceNameView, storePriceView, storeDesignerView, storeDesignerNameView, storeDesignerInfoView, styleNameView;

    ImageView storeImageView, designerImageView;
    RatingBar ratingBar;
    Button btnCall, btnMap;
    CheckBox btnLike;

    Shop mData;

    public interface OnCallClickListener {
        public void onCallClick(View view, Shop shop);
    }

    OnCallClickListener callClickListener;
    public void setOnCallClickListener(OnCallClickListener listener) {
        callClickListener = listener;
    }

    public interface OnLikeClickListener {
        public void onLikeClick(View view, Shop shop);
    }

    OnLikeClickListener likeClickListener;
    public void setOnLikeClickListener(OnLikeClickListener listener) {
        likeClickListener = listener;
    }

    public interface OnMapClickListener {
        public void onMapClick(View view, Shop shop);
    }

    OnMapClickListener mapClickListener;
    public void setOnMapClickListener(OnMapClickListener listener) {
        mapClickListener = listener;
    }



    public StoreInfoViewHolder(View itemView) {
        super(itemView);
        storeImageView = (ImageView) itemView.findViewById(R.id.imageStore);
        btnCall = (Button) itemView.findViewById(R.id.btn_call);
        btnLike = (CheckBox) itemView.findViewById(R.id.btn_like);
        btnMap = (Button) itemView.findViewById(R.id.btn_map);
        storeNameView = (TextView) itemView.findViewById(R.id.text_storename);
        storeInfoView = (TextView) itemView.findViewById(R.id.text_storeInfo);
        storeAdressView = (TextView) itemView.findViewById(R.id.text_storeAddress);
        storeHourView = (TextView) itemView.findViewById(R.id.text_storeHour);
        storeCallView = (TextView) itemView.findViewById(R.id.text_storeNum);
        storePriceNameView = (TextView) itemView.findViewById(R.id.text_price);
        storePriceView = (TextView) itemView.findViewById(R.id.text_priceReceive);
//        storeDesignerView = (TextView) itemView.findViewById(R.id.textDesigner);
//        designerImageView = (ImageView) itemView.findViewById(R.id.image_designerIcon);
//        storeDesignerNameView = (TextView) itemView.findViewById(R.id.text_designerName);
//        storeDesignerInfoView = (TextView) itemView.findViewById(R.id.text_designerInfo);
        styleNameView = (TextView) itemView.findViewById(R.id.text_style);
        scoreView = (TextView) itemView.findViewById(R.id.text_score);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar_store);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callClickListener != null) {
                    callClickListener.onCallClick(v, mData);
                }
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeClickListener != null){
                    likeClickListener.onLikeClick(v, mData);
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapClickListener != null){
                    mapClickListener.onMapClick(v, mData);
                }
            }
        });



    }

    public void setStoreInfo(Shop data){
        mData = data;

        if(data!=null) {

            Glide.with(storeImageView.getContext()).load(data.getShop_pic()).into(storeImageView);
            storeNameView.setText(data.getShop_name());
            storeInfoView.setText(data.getShop_intro());
            storeAdressView.setText(data.getShop_address());
            storeHourView.setText(data.getShop_time());
            storeCallView.setText(data.getShop_phone());
            storePriceView.setText(data.getShop_price());
            scoreView.setText(Double.toString(data.getShop_score()));

//            if(data.getLiked() == 0){
//                btnLike.setBackgroundResource(R.drawable.btnnm_008a_btn_like_off);
//            }
//            else
//                btnLike.setBackgroundResource(R.drawable.btnnm_008a_btn_like_on);


        }

    }
}

