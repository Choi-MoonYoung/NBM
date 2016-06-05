package kr.nearbyme.nbm.data;

import java.io.Serializable;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class Key implements Serializable {
    public String key;

    public Key() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Key(String str) {
        key = str;
    }
}
