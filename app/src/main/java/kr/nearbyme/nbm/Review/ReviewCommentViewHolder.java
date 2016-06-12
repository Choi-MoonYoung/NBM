package kr.nearbyme.nbm.Review;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
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
       // cmtContentView = (TextView) itemView.findViewById(R.id.text_usercomment);

        }


    public void setReviewCommentData(Comment data){
        mData = data;
//        cmtNameView.setText(data.getCmt_writerName());
//        cmtContentView.setText(data.getCmt_content());

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(data.getCmt_writerName() + "  " + data.getCmt_content());

        stringBuilder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, data.getCmt_writerName().length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        stringBuilder.setSpan(new StyleSpan(Typeface.NORMAL), data.getCmt_writerName().length() + 1,
                data.getCmt_writerName().length() + data.getCmt_content().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        cmtNameView.setText(stringBuilder);


    }
}
