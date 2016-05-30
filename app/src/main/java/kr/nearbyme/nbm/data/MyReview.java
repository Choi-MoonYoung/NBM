package kr.nearbyme.nbm.data;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 16..
 */
public class MyReview {
    @SerializedName("id")
    public String writing_id;
    //public Drawable kind;
    @SerializedName("content")
    public String writing_content;
    @SerializedName("regDate")
    public String writing_regDate;

    public String getWriting_id() {
        return writing_id;
    }

    public void setWriting_id(String writing_id) {
        this.writing_id = writing_id;
    }

    /*
    public Drawable getKind() {
        return kind;
    }

    public void setKind(Drawable kind) {
        this.kind = kind;
    }
    */

    public String getWriting_content() {
        return writing_content;
    }

    public void setWriting_content(String writing_content) {
        this.writing_content = writing_content;
    }

    public String getWriting_regDate() {
        return writing_regDate;
    }

    public void setWriting_regDate(String writing_regDate) {
        this.writing_regDate = writing_regDate;
    }
}
