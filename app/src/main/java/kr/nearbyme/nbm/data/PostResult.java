package kr.nearbyme.nbm.data;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 26..
 */
public class PostResult implements Rdata, S{

    public Post post;
    public User user;
    public Shop shop;
    public Designer dsnr;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Designer getDsnr() {
        return dsnr;
    }

    public void setDsnr(Designer dsnr) {
        this.dsnr = dsnr;
    }
}
