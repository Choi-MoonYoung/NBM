package org.androidtown.nearbyme.Mypage;

import android.graphics.drawable.Drawable;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReview {
    public Drawable kind;
    public String review;
    public String date;

    public Drawable getKind() {
        return kind;
    }

    public void setKind(Drawable kind) {
        this.kind = kind;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
