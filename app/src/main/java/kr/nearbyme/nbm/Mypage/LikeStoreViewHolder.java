package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class LikeStoreViewHolder extends RecyclerView.ViewHolder{
    TextView storeNameView, storeDescriptionView, storeRatingNumView;
    ImageView storeImageView;
    Shop mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, Shop shop);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public LikeStoreViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_store_name);
        storeDescriptionView = (TextView) itemView.findViewById(R.id.text_store_description);
        storeImageView = (ImageView) itemView.findViewById(R.id.image_store);
        storeRatingNumView = (TextView) itemView.findViewById(R.id.text_score);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);
                }
            }
        });

    }

    public void setLikeStore(Shop data){
        mData = data;
        if(mData != null){
            storeNameView.setText(data.getShop_name());
            storeDescriptionView.setText(data.getShop_intro());
            storeRatingNumView.setText(""+data.getShop_score());

            Glide.with(storeImageView.getContext()).load(data.getShop_pic()).into(storeImageView);

        }


    }
}
