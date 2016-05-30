package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.MyReview;
import kr.nearbyme.nbm.data.Post;


/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReviewViewHolder extends RecyclerView.ViewHolder {
    TextView reviewView, dateView;
    ImageView kindView;
    MyReview mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, MyReview myReview);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MyReviewViewHolder(View itemView) {
        super(itemView);
        reviewView = (TextView) itemView.findViewById(R.id.text_review);
        dateView = (TextView) itemView.findViewById(R.id.text_date);
        kindView = (ImageView) itemView.findViewById(R.id.image_kind);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);
                }
            }
        });

    }

    public void setReviewData(MyReview data){
        mData = data;
        reviewView.setText(data.getWriting_content());
        dateView.setText(data.getWriting_regDate());
        //kindView.setImageDrawable(data.getPost_pic());

    }


}
