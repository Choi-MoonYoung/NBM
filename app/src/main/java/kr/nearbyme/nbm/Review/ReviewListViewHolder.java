package kr.nearbyme.nbm.Review;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wefika.flowlayout.FlowLayout;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.ShopDetailResult;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 30..
 */
public class ReviewListViewHolder extends RecyclerView.ViewHolder{
    TextView storeNameView, designerNameView, userNameView, dateView;
    ImageView postImageView, userImageView;
    PostResult mData;
    FlowLayout tagLayout;
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
        tagLayout = (FlowLayout) itemView.findViewById(R.id.text_tag);
        //filterTagsView = (TextView) itemView.findViewById(R.id.text_tag);
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
            tagLayout.removeAllViews();

            for(int i = 0; i< data.post.getPost_filters().size(); i++){
                TextView filterTagsView = new TextView(itemView.getContext());
                FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(6, 6, 6, 6);
                filterTagsView.setLayoutParams(lp);
                filterTagsView.setTextSize(10);
                filterTagsView.setBackgroundColor(Color.BLUE);
                filterTagsView.setBackgroundResource(R.drawable.tag_s_001);
                filterTagsView.setText(data.post.getPost_filters().get(i));
                tagLayout.addView(filterTagsView);
            }

            if (data.getUser().getUser_profilePic() == null){
                userImageView.setImageResource(R.drawable.nm_007_user_image);
            }
            else{
                Glide.with(userImageView.getContext()).load(data.user.getUser_profilePic()).into(userImageView);

            }

            userNameView.setText(data.user.getUser_name());
            dateView.setText(data.post.getPost_regDate());
            Glide.with(postImageView.getContext()).load(data.post.getPost_pic()).into(postImageView);
        }
    }
}
