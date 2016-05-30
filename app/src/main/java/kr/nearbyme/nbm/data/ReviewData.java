package kr.nearbyme.nbm.data;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class ReviewData implements Rdata {
    public Drawable searchImg;
    public List<String> tags;

    public Drawable getSearchImg() {
        return searchImg;
    }

    public void setSearchImg(Drawable searchImg) {
        this.searchImg = searchImg;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
