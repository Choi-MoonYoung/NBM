package kr.nearbyme.nbm.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.MyReview;
import kr.nearbyme.nbm.data.Post;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.ReviewData;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class LikeReviewViewHolder extends RecyclerView.ViewHolder {
    TextView filterTagsView, storeNameView, designerNameView, userNameView, dateView;
    ImageView postImageView, userImageView;
    PostResult mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, PostResult postResult);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public LikeReviewViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_sname);
        designerNameView = (TextView) itemView.findViewById(R.id.text_dname);
        filterTagsView = (TextView) itemView.findViewById(R.id.text_tag);
        userImageView = (ImageView) itemView.findViewById(R.id.image_userIcon);
        userNameView = (TextView) itemView.findViewById(R.id.text_user);
        dateView = (TextView) itemView.findViewById(R.id.text_writedate);
        postImageView = (ImageView) itemView.findViewById(R.id.image_review);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);
                }
            }
        });


    }

    public void setLikeReview(PostResult post){
        mData = post;

        if(mData!=null) {
            storeNameView.setText(post.shop.getShop_name());
            designerNameView.setText(post.dsnr.getDsnr_name());
            //filterTagsView.setText((CharSequence) post.post.getPost_filters());
            for(int i = 1; i< post.post.getPost_filters().size(); i++)
                filterTagsView.append(", " + post.post.getPost_filters().get(i));
            Glide.with(userImageView.getContext()).load(post.user.getUser_profilePic()).into(userImageView);
            userNameView.setText(post.user.getUser_name());
            dateView.setText(post.post.getPost_regDate());
            Glide.with(postImageView.getContext()).load(post.post.getPost_pic()).into(postImageView);
        }

    }


}

