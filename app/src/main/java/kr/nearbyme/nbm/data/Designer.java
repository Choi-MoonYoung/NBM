package kr.nearbyme.nbm.data;

import android.graphics.drawable.Drawable;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 25..
 */
public class Designer {

    public String dsnr_id;
    public String dsnr_name;
    public String dsnr_intro;
    public String dsnr_profilePic;
    public String reg_date;

    public String getDsnr_id() {
        return dsnr_id;
    }

    public void setDsnr_id(String dsnr_id) {
        this.dsnr_id = dsnr_id;
    }

    public String getDsnr_name() {
        return dsnr_name;
    }

    public void setDsnr_name(String dsnr_name) {
        this.dsnr_name = dsnr_name;
    }

    public String getDsnr_info() {
        return dsnr_intro;
    }

    public void setDsnr_info(String dsnr_intro) {
        this.dsnr_intro = dsnr_intro;
    }

    public String getDsnr_profilePic() {
        return dsnr_profilePic;
    }

    public void setDsnr_profilePic(String dsnr_profilePic) {
        this.dsnr_profilePic = dsnr_profilePic;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}
