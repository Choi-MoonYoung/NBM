package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Comment;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 19..
 */
public class ReviewCommentWriteViewHolder extends RecyclerView.ViewHolder{
    EditText writeCommentView;
    Button btnDone;
    Comment mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, Comment comment, String cmt_content);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public ReviewCommentWriteViewHolder(View itemView) {
        super(itemView);
        writeCommentView = (EditText) itemView.findViewById(R.id.edit_commentwrite);
        btnDone = (Button) itemView.findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData, writeCommentView.getText().toString());
                }
            }
        });
        }


    public void setReviewCommentData(Comment data){
        mData = data;


    }
}
