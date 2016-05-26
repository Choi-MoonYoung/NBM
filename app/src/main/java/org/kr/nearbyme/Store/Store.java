package org.kr.nearbyme.Store;

import android.graphics.drawable.Drawable;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class Store implements S {
    public Drawable storeImg;
    public String storeName;
    public String storeDescription;
    public String distance;
    //rating bar는 타입?


    public Drawable getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(Drawable storeImg) {
        this.storeImg = storeImg;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
