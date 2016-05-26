package kr.nearbyme.nbm.data;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 21..
 */
public class Write {
    String user_id, shop_id, dsnr_id, content, filters;
    double score;
    String post_pic;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getDsnr_id() {
        return dsnr_id;
    }

    public void setDsnr_id(String dsnr_id) {
        this.dsnr_id = dsnr_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPost_pic() {
        return post_pic;
    }

    public void setPost_pic(String post_pic) {
        this.post_pic = post_pic;
    }
}
