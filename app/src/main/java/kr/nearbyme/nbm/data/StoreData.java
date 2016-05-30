package kr.nearbyme.nbm.data;

import android.graphics.drawable.Drawable;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class StoreData implements S {
    public Drawable searchImg;
    public String storeName;
    //public Drawable button;


    public Drawable getSearchImg() {
        return searchImg;
    }

    public void setSearchImg(Drawable searchImg) {
        this.searchImg = searchImg;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
