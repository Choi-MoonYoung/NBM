package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.UserWritingResult;


/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReviewViewHolder extends RecyclerView.ViewHolder {
    TextView reviewView, dateView;
    ImageView kindView;
    //MyReview mData;
    UserWritingResult mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, UserWritingResult userWritingResult);
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

    public void setReviewData(UserWritingResult data){
        mData = data;
        if(mData.getParam_sort() == 0){
            kindView.setImageResource(R.drawable.nm_009_icon_review);
        }
        else{
            kindView.setImageResource(R.drawable.nm_009_icon_coment);
        }
        reviewView.setText(data.writing.getWriting_content());
        dateView.setText(data.writing.getWriting_regDate());

    }


}
