package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Comment;
import kr.nearbyme.nbm.data.PostResult;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 19..
 */
public class ReviewDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_DETAIL = 0;
    public static final int VIEW_TYPE_COMMENT = 1;
    public static final int VIEW_TYPE_COMMENTWRITE = 2;


    List<Comment> items = new ArrayList<Comment>();
    PostResult postDetail = new PostResult();
    Comment commentData = new Comment();

    ReviewDetailViewHolder.OnItemClickListener mListener;
    ReviewDetailViewHolder.OnLikeClickListener likeClickListener;

    public void setOnItemClickListener(ReviewDetailViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnLikeClickListener(ReviewDetailViewHolder.OnLikeClickListener listener) {
        likeClickListener = listener;
    }

    ReviewCommentWriteViewHolder.OnItemClickListener mListener2;

    public void setOnItemClickListener(ReviewCommentWriteViewHolder.OnItemClickListener listener){
        mListener2 = listener;
    }

    ReviewCommentViewHolder.OnDeleteClickListener deleteListener;
    public void setOnDeleteClickListener(ReviewCommentViewHolder.OnDeleteClickListener listener){
        deleteListener = listener;
    }


    public void addComment(Comment r) {
        items.add(r);
        notifyDataSetChanged();
    }
    public void addCommentAll(List<Comment> r) {
        items.addAll(r);
        notifyDataSetChanged();
    }

    public void addReview(PostResult r) {
        this.postDetail = r;
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
    }


    @Override
    public int getItemViewType(int position) {

        if(position == 0) return VIEW_TYPE_DETAIL;
        position--;
        if(items.size() > position ){
            return VIEW_TYPE_COMMENT;
        }
        position -= items.size();

        if(position == 0) {
            return VIEW_TYPE_COMMENTWRITE;
        }
        throw new IllegalArgumentException("invalid position");
    }




    View editView;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case VIEW_TYPE_DETAIL :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_review_detail, parent, false);
                return new ReviewDetailViewHolder(view);
            case VIEW_TYPE_COMMENT :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment, parent, false);
                return new ReviewCommentViewHolder(view);
            case VIEW_TYPE_COMMENTWRITE :
                if (editView == null) {
                    editView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_comment_write, parent, false);
                }
                view = editView;
                return new ReviewCommentWriteViewHolder(view);

        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case VIEW_TYPE_DETAIL :
                ReviewDetailViewHolder detailViewHolder = (ReviewDetailViewHolder)holder;
                detailViewHolder.setDetailReviewData(postDetail);
                detailViewHolder.setOnItemClickListener(mListener);
                detailViewHolder.setOnLikeClickListener(likeClickListener);
                break;
            case VIEW_TYPE_COMMENT :
                position--;
                ReviewCommentViewHolder commentViewHolder = (ReviewCommentViewHolder)holder;
                commentViewHolder.setReviewCommentData(items.get(position));
                commentViewHolder.setOnDeleteClickListener(deleteListener);
                break;
            case VIEW_TYPE_COMMENTWRITE :
                ReviewCommentWriteViewHolder commentWriteViewHolder = (ReviewCommentWriteViewHolder)holder;
                commentWriteViewHolder.setReviewCommentData(commentData);
                commentWriteViewHolder.setOnItemClickListener(mListener2);
                break;
            default:
                throw new IllegalArgumentException("invalid view type");
        }


    }

    @Override
    public int getItemCount() {
        return items.size()+2;
    }


}
