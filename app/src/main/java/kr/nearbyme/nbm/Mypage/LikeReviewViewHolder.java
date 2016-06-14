package kr.nearbyme.nbm.Mypage;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.wefika.flowlayout.FlowLayout;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.PostResult;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class LikeReviewViewHolder extends RecyclerView.ViewHolder {
    TextView filterTagsView, storeNameView, designerNameView, userNameView, dateView;
    ImageView postImageView;
    CircularImageView userImageView;
    FlowLayout tagLayout;
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
        //filterTagsView = (TextView) itemView.findViewById(R.id.text_tag);
        tagLayout = (FlowLayout) itemView.findViewById(R.id.text_tag);
        userImageView = (CircularImageView) itemView.findViewById(R.id.image_userIcon);
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
            for(int i = 0; i< post.post.getPost_filters().size(); i++){
                TextView filterTagsView = new TextView(itemView.getContext());
                FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(6, 6, 6, 6);
                filterTagsView.setLayoutParams(lp);
                filterTagsView.setTextSize(10);
                filterTagsView.setBackgroundColor(Color.BLUE);
                filterTagsView.setBackgroundResource(R.drawable.tag_s_001);
                filterTagsView.setText(post.post.getPost_filters().get(i));
                tagLayout.addView(filterTagsView);
            }
            Glide.with(userImageView.getContext()).load(post.user.getUser_profilePic()).into(userImageView);
            userNameView.setText(post.user.getUser_name());
            dateView.setText(post.post.getPost_regDate());
            Glide.with(postImageView.getContext()).load(post.post.getPost_pic()).into(postImageView);
        }

    }


}

