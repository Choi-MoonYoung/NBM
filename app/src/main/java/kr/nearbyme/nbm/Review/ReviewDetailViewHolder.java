package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Post;
import kr.nearbyme.nbm.data.PostResult;

import android.widget.Button;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 19..
 */
public class ReviewDetailViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, dateView, postView, tagView, likeCountView, commentCountView, dsnrnameView, storenameView;
    ImageView usericonView, reviewimageView;
    Button option, like, comment;
    PostResult mData;

    public ReviewDetailViewHolder(View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(R.id.text_username);
        dateView = (TextView) itemView.findViewById(R.id.text_date);
        postView = (TextView) itemView.findViewById(R.id.text_post);
        tagView = (TextView) itemView.findViewById(R.id.text_tags);
        likeCountView = (TextView) itemView.findViewById(R.id.text_likecount);
        commentCountView = (TextView) itemView.findViewById(R.id.text_commentcount);
        dsnrnameView = (TextView) itemView.findViewById(R.id.text_desingername);
        storenameView = (TextView) itemView.findViewById(R.id.text_storename);
        usericonView = (ImageView) itemView.findViewById(R.id.image_user);
        reviewimageView = (ImageView) itemView.findViewById(R.id.imageView);
        option = (Button) itemView.findViewById(R.id.btn_option);
        like = (Button) itemView.findViewById(R.id.btn_like);
        comment = (Button) itemView.findViewById(R.id.btn_comment);

    }

    public void setDetailReviewData(PostResult data){

        mData = data;
        if(data.user!=null && data.post!=null
                && data.dsnr!=null && data.shop!=null) {
            nameView.setText(data.user.getUser_name());
            dateView.setText(data.post.getPost_regDate());
            postView.setText(data.post.getPost_content());
            tagView.setText(data.post.getPost_filters().get(0));

            for(int i = 1; i< data.post.getPost_filters().size(); i++)
                tagView.append(", " + data.post.getPost_filters().get(i));
            likeCountView.setText(""+data.post.getPost_likeNum());
            commentCountView.setText(""+data.post.getPost_commentNum());
            dsnrnameView.setText(data.dsnr.getDsnr_name());
            storenameView.setText(data.shop.getShop_name());
            //usericonView.setImageDrawable(data.getUser_profilePic());
            //reviewimageView.setImageDrawable(data.getPost_pic());
        }



    }


}
