package kr.nearbyme.nbm.data;

import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class Shop implements S {

    public String shop_id;
    public String shop_pic;
    public String shop_name;
    public String shop_intro;
    public String dist;
    public int liked;
    public double shop_score;
    public String shop_address;
    public String shop_phone;
    public String shop_time;
    public String shop_price;
    public String shop_menu;
    public String shop_postNum;
    public int shop_likeNum;
    public List<Designer> dsnr_info;

    public List<Designer> getDsnr_info() {
        return dsnr_info;
    }

    public void setDsnr_info(List<Designer> dsnr_info) {
        this.dsnr_info = dsnr_info;
    }


    public String getShop_postNum() {
        return shop_postNum;
    }

    public void setShop_postNum(String shop_postNum) {
        this.shop_postNum = shop_postNum;
    }

    public int getShop_likeNum() {
        return shop_likeNum;
    }

    public void setShop_likeNum(int shop_likeNum) {
        this.shop_likeNum = shop_likeNum;
    }

    public String getShop_menu() {
        return shop_menu;
    }

    public void setShop_menu(String shop_menu) {
        this.shop_menu = shop_menu;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_pic() {
        return shop_pic;
    }

    public void setShop_pic(String shop_pic) {
        this.shop_pic = shop_pic;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_intro() {
        return shop_intro;
    }

    public void setShop_intro(String shop_intro) {
        this.shop_intro = shop_intro;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public double getShop_score() {
        return shop_score;
    }

    public void setShop_score(double shop_score) {
        this.shop_score = shop_score;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getShop_time() {
        return shop_time;
    }

    public void setShop_time(String shop_time) {
        this.shop_time = shop_time;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

}
