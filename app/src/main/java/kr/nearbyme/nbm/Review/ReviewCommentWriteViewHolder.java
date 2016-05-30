package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Comment;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 19..
 */
public class ReviewCommentWriteViewHolder extends RecyclerView.ViewHolder{
    EditText writeCommentView;
    Button btnDone;
    Comment mData;

    public ReviewCommentWriteViewHolder(View itemView) {
        super(itemView);
        writeCommentView = (EditText) itemView.findViewById(R.id.edit_commentwrite);
        btnDone = (Button) itemView.findViewById(R.id.btn_done);
        }


    public void setReviewCommentData(Comment data){
        mData = data;


    }
}
