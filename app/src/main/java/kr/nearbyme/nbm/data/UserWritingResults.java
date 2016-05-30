package kr.nearbyme.nbm.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 25..
 */
public class UserWritingResults {
    @SerializedName("result")
    public List<UserWritingResult> userWritingResults;

    public List<UserWritingResult> getUserWritingResults() {
        return userWritingResults;
    }

    public void setUserWritingResults(List<UserWritingResult> userWritingResults) {
        this.userWritingResults = userWritingResults;
    }
}
