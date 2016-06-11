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

    double latitude, longitude;
    int radius;
    List<String> filters;
    List<String> writePostfilter;
    String param_sort;
    String param_id;
//    int onoff;
//
//    public int getOnoff() {
//        return onoff;
//    }
//
//    public void setOnoff(int onoff) {
//        this.onoff = onoff;
//    }

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
