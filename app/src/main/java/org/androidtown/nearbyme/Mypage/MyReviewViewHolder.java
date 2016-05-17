package org.androidtown.nearbyme.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidtown.nearbyme.R;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReviewViewHolder extends RecyclerView.ViewHolder {
    TextView reviewView, dateView;
    ImageView kindView;
    MyReview mData;

    public MyReviewViewHolder(View itemView) {
        super(itemView);
        reviewView = (TextView) itemView.findViewById(R.id.text_review);
        dateView = (TextView) itemView.findViewById(R.id.text_date);
        kindView = (ImageView) itemView.findViewById(R.id.image_kind);
    }

    public void setReviewData(MyReview data){
        mData = data;
        reviewView.setText(data.getReview());
        dateView.setText(data.getDate());
        kindView.setImageDrawable(data.getKind());

    }


}
