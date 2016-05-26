package org.kr.nearbyme.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.kr.nearbyme.R;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class ReviewHeaderViewHolder extends RecyclerView.ViewHolder{
    ImageView iconView;
    TextView filtertags;
    Button filter;
    ReviewData mData;

    public ReviewHeaderViewHolder(View itemView) {
        super(itemView);
        filtertags = (TextView) itemView.findViewById(R.id.text_filtertags);
        filter = (Button) itemView.findViewById(R.id.btn_filter);
        iconView = (ImageView) itemView.findViewById(org.kr.nearbyme.R.id.image_search);
    }


    public void setReviewHeaderData(ReviewData data){
        mData = data;
        filtertags.setText((CharSequence) data.getTags());
        //order.setText(data.getStoreDescription());
        //storeDistanceView.setText(data.getDistance());
        iconView.setImageDrawable(data.getSearchImg());

    }
}
