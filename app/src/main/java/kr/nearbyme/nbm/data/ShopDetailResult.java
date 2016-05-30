package kr.nearbyme.nbm.data;

import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 26..
 */
public class ShopDetailResult {
    public Shop shop_info;
    public List<PostResult> post_info;

    public Shop getShop_info() {
        return shop_info;
    }

    public void setShop_info(Shop shop_info) {
        this.shop_info = shop_info;
    }



    public List<PostResult> getPost_info() {
        return post_info;
    }

    public void setPost_info(List<PostResult> post_info) {
        this.post_info = post_info;
    }
}
