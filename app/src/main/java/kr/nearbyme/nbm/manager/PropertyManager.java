package kr.nearbyme.nbm.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

import kr.nearbyme.nbm.MyApplication;

/**
 * Created by CHOIMOONYOUNG on 2016. 6. 2..
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPrefs.edit();
    }

    int isGuest;

    public int getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(int isGuest) {
        this.isGuest = isGuest;
    }

    double latitude, longitude;
    int radius;
    List<String> filters;
    List<String> writePostfilter;
    String param_sort;
    String param_id;

    String user_id;
    String user_name;
    String user_profilePic;

    //비회원을 위한 flag설정하기

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_profilePic() {
        return user_profilePic;
    }

    public void setUser_profilePic(String user_profilePic) {
        this.user_profilePic = user_profilePic;
    }

    public String getParam_sort() {
        return param_sort;
    }

    public void setParam_sort(String param_sort) {
        this.param_sort = param_sort;
    }

    public String getParam_id() {
        return param_id;
    }

    public void setParam_id(String param_id) {
        this.param_id = param_id;
    }

    private static final String FIELD_WRITER = "writer";
    public List<String> getWritePostfilter() {
        return writePostfilter;
    }

    public void setWritePostfilter(List<String> writePostfilter) {
        this.writePostfilter = writePostfilter;
//        Set<String> set = new HashSet<>();
//        for (String s : writePostfilter) {
//            set.add(s);
//        }
//        mEditor.putStringSet(FIELD_WRITER,set);
//        mEditor.commit();
    }

    public void setMyPosition(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setMyRadius(int radius){
        this.radius = radius;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public int getMyRadius(){
        return radius;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
