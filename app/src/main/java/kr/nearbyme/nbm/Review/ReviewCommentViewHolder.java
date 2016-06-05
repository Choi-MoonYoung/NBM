package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Comment;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 19..
 */
public class ReviewCommentViewHolder extends RecyclerView.ViewHolder{
    TextView cmtNameView, cmtContentView;
    Comment mData;

    public ReviewCommentViewHolder(View itemView) {
        super(itemView);
        cmtNameView = (TextView) itemView.findViewById(R.id.text_username);
        cmtContentView = (TextView) itemView.findViewById(R.id.text_usercomment);

        }


    public void setReviewCommentData(Comment data){
        mData = data;
        cmtNameView.setText(data.getCmt_writerName());
        cmtContentView.setText(data.getCmt_content());


    }
}
