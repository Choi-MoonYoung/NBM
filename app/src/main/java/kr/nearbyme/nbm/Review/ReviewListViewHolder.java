package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.ShopDetailResult;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 30..
 */
public class ReviewListViewHolder extends RecyclerView.ViewHolder{
    TextView filterTagsView, storeNameView, designerNameView, userNameView, dateView;
    ImageView postImageView, userImageView;
    PostResult mData;
    ShopDetailResult result;

    public interface OnItemClickListener {
        public void onItemClick(View view, PostResult post);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    public ReviewListViewHolder(View itemView) {
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



    public void setReviewList(PostResult data){
        mData = data;


        if(data!=null) {
            storeNameView.setText(data.shop.getShop_name());
            designerNameView.setText(data.dsnr.getDsnr_name());
            filterTagsView.setText(data.post.getPost_filters().get(0));

            for(int i = 1; i< data.post.getPost_filters().size(); i++)
                filterTagsView.append(", " + data.post.getPost_filters().get(i));
            Glide.with(userImageView.getContext()).load(data.user.getUser_profilePic()).into(userImageView);
            userNameView.setText(data.user.getUser_name());
            dateView.setText(data.post.getPost_regDate());
            Glide.with(postImageView.getContext()).load(data.post.getPost_pic()).into(postImageView);
        }
    }
}
