package kr.nearbyme.nbm.data;

import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 25..
 */
public class User {

    String user_id;
    int user_sort;
    String user_name;
    String user_profilePic;
    String user_token;
    List<String> user_likedPosts;
    List<String> user_likedShops;
    List<String> user_filters;
    String user_regDate;
    int mine;

    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getUser_sort() {
        return user_sort;
    }

    public void setUser_sort(int user_sort) {
        this.user_sort = user_sort;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_profilePic() {
        return user_profilePic;
    }

    public void setUser_profilePic(String user_profilePic) {
        this.user_profilePic = user_profilePic;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public List<String> getUser_likedPosts() {
        return user_likedPosts;
    }

    public void setUser_likedPosts(List<String> user_likedPosts) {
        this.user_likedPosts = user_likedPosts;
    }

    public List<String> getUser_likedShops() {
        return user_likedShops;
    }

    public void setUser_likedShops(List<String> user_likedShops) {
        this.user_likedShops = user_likedShops;
    }

    public List<String> getUser_filters() {
        return user_filters;
    }

    public void setUser_filters(List<String> user_filters) {
        this.user_filters = user_filters;
    }

    public String getUser_regDate() {
        return user_regDate;
    }

    public void setUser_regDate(String user_regDate) {
        this.user_regDate = user_regDate;
    }
}
