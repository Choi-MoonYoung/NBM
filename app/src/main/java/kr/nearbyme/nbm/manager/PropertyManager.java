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

    public List<String> getWritePostfilter() {
        return writePostfilter;
    }

    public void setWritePostfilter(List<String> writePostfilter) {
        this.writePostfilter = writePostfilter;
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
