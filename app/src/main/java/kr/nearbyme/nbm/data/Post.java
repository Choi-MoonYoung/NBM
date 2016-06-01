package kr.nearbyme.nbm.data;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class Post implements Rdata, S {

    public String post_id;
    public String post_pic;
    public List<String> post_filters;
    int liked;
    public String post_regDate;
    public int post_likeNum;
    public int post_commentNum;
    public String post_content;
    public List<Comment> post_comments;
    public double post_score;

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public List<Comment> getPost_comments() {
        return post_comments;
    }

    public void setPost_comments(List<Comment> post_comments) {
        this.post_comments = post_comments;
    }

    public double getPost_score() {
        return post_score;
    }

    public void setPost_score(double post_score) {
        this.post_score = post_score;
    }

    public int getPost_likeNum() {
        return post_likeNum;
    }

    public void setPost_likeNum(int post_likeNum) {
        this.post_likeNum = post_likeNum;
    }

    public int getPost_commentNum() {
        return post_commentNum;
    }

    public void setPost_commentNum(int post_commentNum) {
        this.post_commentNum = post_commentNum;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_pic() {
        return post_pic;
    }

    public void setPost_pic(String post_pic) {
        this.post_pic = post_pic;
    }

    public List<String> getPost_filters() {
        return post_filters;
    }

    public void setPost_filters(List<String> post_filters) {
        this.post_filters = post_filters;
    }
/*
    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getDsnr_name() {
        return dsnr_name;
    }

    public void setDsnr_name(String dsnr_name) {
        this.dsnr_name = dsnr_name;
    }

    public String getUser_profilePic() {
        return user_profilePic;
    }

    public void setUser_profilePic(String user_profilePic) {
        this.user_profilePic = user_profilePic;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }*/

    public String getPost_regDate() {
        return post_regDate;
    }

    public void setPost_regDate(String post_regDate) {
        this.post_regDate = post_regDate;
    }
}
