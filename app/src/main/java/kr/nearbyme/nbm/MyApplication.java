package kr.nearbyme.nbm;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 24..
 */
public class MyApplication extends MultiDexApplication{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

}
