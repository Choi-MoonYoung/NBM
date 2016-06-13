package kr.nearbyme.nbm;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 24..
 */
public class MyApplication extends MultiDexApplication{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        FacebookSdk.sdkInitialize(this);
    }

    public static Context getContext() {
        return context;
    }

}
