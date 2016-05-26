package org.kr.nearbyme.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.kr.nearbyme.R;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class ReviewViewHolder extends RecyclerView.ViewHolder{
    TextView filterTagsView, storeNameView, designerNameView, userNameView, dateView;
    ImageView postImageView, userImageView;
    Review mData;



    public ReviewViewHolder(View itemView) {
        super(itemView);
        storeNameView = (TextView) itemView.findViewById(R.id.text_sname);
        designerNameView = (TextView) itemView.findViewById(R.id.text_dname);
        filterTagsView = (TextView) itemView.findViewById(R.id.text_tag);
        userImageView = (ImageView) itemView.findViewById(R.id.image_userIcon);
        userNameView = (TextView) itemView.findViewById(R.id.text_user);
        dateView = (TextView) itemView.findViewById(R.id.text_writedate);
        postImageView = (ImageView) itemView.findViewById(R.id.image_review);

    }

    public void setReview(Review data){
        mData = data;
        storeNameView.setText(data.getShop_name());
        designerNameView.setText(data.getDesigner_name());
        filterTagsView.setText((CharSequence) data.getPost_filters());
        userImageView.setImageDrawable(data.getUser_profilePic());
        userNameView.setText(data.set);
        storeImageView.setImageDrawable(data.getStoreImg());

    }
}
