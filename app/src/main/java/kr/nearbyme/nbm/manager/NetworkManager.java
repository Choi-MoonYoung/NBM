package kr.nearbyme.nbm.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import kr.nearbyme.nbm.MyApplication;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kr.nearbyme.nbm.data.LikePost;
import kr.nearbyme.nbm.data.LikeShopResult;
import kr.nearbyme.nbm.data.LikeShopResultResult;
import kr.nearbyme.nbm.data.PostDetailResult;
import kr.nearbyme.nbm.data.PostListResult;
import kr.nearbyme.nbm.data.ShopDetailResult;
import kr.nearbyme.nbm.data.ShopListResult;
import kr.nearbyme.nbm.data.UserWritingResult;
import kr.nearbyme.nbm.data.UserWritingResults;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.JavaNetCookieJar;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dongja94 on 2016-05-09.
 */
public class NetworkManager {
    private static NetworkManager instance;
    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private static final int DEFAULT_CACHE_SIZE = 50 * 1024 * 1024;
    private static final String DEFAULT_CACHE_DIR = "miniapp";
    OkHttpClient mClient;
    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context context = MyApplication.getContext();
        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));

        File dir = new File(context.getExternalCacheDir(), DEFAULT_CACHE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        builder.cache(new Cache(dir, DEFAULT_CACHE_SIZE));

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        mClient = builder.build();
    }

    public interface OnResultListener<T> {
        public void onSuccess(Request request, T result);
        public void onFail(Request request, IOException exception);
    }

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;

    class NetworkHandler extends Handler {
        public NetworkHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkResult result = (NetworkResult)msg.obj;
            switch (msg.what) {
                case MESSAGE_SUCCESS :
                    result.listener.onSuccess(result.request, result.result);
                    break;
                case MESSAGE_FAIL :
                    result.listener.onFail(result.request, result.excpetion);
                    break;
            }
        }
    }

    NetworkHandler mHandler = new NetworkHandler(Looper.getMainLooper());

    static class NetworkResult<T> {
        Request request;
        OnResultListener<T> listener;
        IOException excpetion;
        T result;
    }

    Gson gson = new Gson();

    //내가 작성한 후기와 댓글 리스트 조회
    private static final String NBM_SERVER = "http://52.79.192.50:3000";
    private static final String NBM_USERWRITING_URL = NBM_SERVER + "/user/writings";
    public Request getMyReviewList(OnResultListener<UserWritingResults> listener) {


        Request request = new Request.Builder()
                .url(NBM_USERWRITING_URL)
                .build();
        final NetworkResult<UserWritingResults> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;

                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    UserWritingResults data = gson.fromJson(response.body().charStream(), UserWritingResults.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }


    //매장 상세보기
    private static final String NBM_SHOPDETAIL_URL = NBM_SERVER + "/shop/detail/:shop_id=%s";
    public Request getShopDetail(String shop_id, OnResultListener<ShopDetailResult> listener) {

        String url = String.format(NBM_SHOPDETAIL_URL, shop_id);

        Request request = new Request.Builder()
                .url(url)
                .build();
        final NetworkResult<ShopDetailResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;

                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String text = response.body().string();
                    ShopDetailResult data = gson.fromJson(text, ShopDetailResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    //매장보기
    private static final String NBM_SHOPLIST_URL = NBM_SERVER +"/shop/list";

    public Request getShopList(Object tag ,String keyword, int order, double locX, double locY, int radius, OnResultListener<ShopListResult> listener) {
        RequestBody body = new FormBody.Builder()
                .add("keyword", keyword)
                .add("order", order+"")
                .add("locX", locX+"")
                .add("locY", locY+"")
                .add("radius", radius+"")
                .build();
        Request request = new Request.Builder()
                .url(NBM_SHOPLIST_URL)
                .header("Accept", "application/json")
                .post(body)
                .build();
        final NetworkResult<ShopListResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ShopListResult data = gson.fromJson(response.body().charStream(), ShopListResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //후기 리스트 보기
    private static final String NBM_POSTLIST_URL = NBM_SERVER +"/post/list";

    public Request getPostList(Object tag ,List<String> filters, double locX, double locY, int radius, OnResultListener<PostListResult> listener) {
        String filter="";
        if(filters!=null) {
            for (String x : filters) {
                filter += x;
                if (!x.equals(filters.get(filters.size()-1))) {
                    filter += ",";
                }
            }
        }
        RequestBody body = new FormBody.Builder()
                .add("filters", filter)
                .add("locX", locX + "")
                .add("locY", locY + "")
                .add("radius", radius + "")
                .build();
        Request request = new Request.Builder()
                .url(NBM_POSTLIST_URL)
                .header("Accept", "application/json")
                .post(body)
                .build();
        final NetworkResult<PostListResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    PostListResult data = gson.fromJson(response.body().charStream(), PostListResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //후기 상세보기
    private static final String NBM_POSTDETAIL_URL = NBM_SERVER + "/post/detail/:param_sort=%s/:param_id=%s";
    public Request getPostDetail(String param_sort, String param_id, OnResultListener<PostDetailResult> listener) {

        String url = String.format(NBM_POSTDETAIL_URL, param_sort, param_id);

        Request request = new Request.Builder()
                .url(url)
                .build();
        final NetworkResult<PostDetailResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;

                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String text = response.body().string();
                    PostDetailResult data = gson.fromJson(text, PostDetailResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    //좋아요한 후기
    private static final String NBM_LIKEPOST_URL = NBM_SERVER + "/user/liked_post";
    public Request getLikePost(OnResultListener<LikePost> listener) {

        String url = String.format(NBM_LIKEPOST_URL);

        Request request = new Request.Builder()
                .url(url)
                .build();
        final NetworkResult<LikePost> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;

                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String text = response.body().string();
                    LikePost data = gson.fromJson(text, LikePost.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }

    //찜한 매장
    private static final String NBM_LIKESHOP_URL = NBM_SERVER + "/user/liked_shop";
    public Request getLikeShop(OnResultListener<LikeShopResultResult> listener) {

        String url = String.format(NBM_LIKESHOP_URL);

        Request request = new Request.Builder()
                .url(url)
                .build();
        final NetworkResult<LikeShopResultResult> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;

                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String text = response.body().string();
                    LikeShopResultResult data = gson.fromJson(text, LikeShopResultResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    result.excpetion = new IOException(response.message());
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
                }
            }
        });
        return request;
    }



}
