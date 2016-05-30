package kr.nearbyme.nbm.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Designer;
import kr.nearbyme.nbm.data.MyReview;
import kr.nearbyme.nbm.data.Post;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.ShopDetailResult;
import kr.nearbyme.nbm.data.UserWritingResults;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;

public class StoreDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SHOP_ID = "shop_id";
    String shop_id;
    RecyclerView recyclerView;
    StoreDetailAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new StoreDetailAdapter();
        setContentView(R.layout.activity_store_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_storedetail);
        recyclerView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                if (type == StoreDetailAdapter.VIEW_TYPE_STOREINFO) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);


        initData();


        Intent intent = getIntent();
        shop_id = intent.getStringExtra(EXTRA_SHOP_ID);


    }

    private void initData() {

        NetworkManager.getInstance().getShopDetail(shop_id, new NetworkManager.OnResultListener<ShopDetailResult>() {
            @Override
            public void onSuccess(Request request, ShopDetailResult result) {



                mAdapter.clear();

                mAdapter.setResult(result);

                //mAdapter.addStore(result.getShop_info());
               // mAdapter.addAll(result.getPost_info());

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(StoreDetailActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        /*
        Shop p = new Shop();

        p.setShop_pic(ContextCompat.getDrawable(this, R.drawable.item2));
        p.setShop_score(4.3);
        p.setShop_intro("dasgdfadsfasdgsfsdafsdagdfa");
        p.setShop_address("dsafd");
        p.setShop_time("22");
        p.setShop_phone("1111111111");
        p.setShop_price("22");
        Designer tdesigner = new Designer();
        tdesigner.setDsnr_profilePic(ContextCompat.getDrawable(this, R.drawable.item1));
        tdesigner.setDsnr_name("aaa");
        tdesigner.setDsnr_info("sdfgdfasdf");
        p.setDesigner(tdesigner);
        mAdapter.addStore(p);

        for (int i = 0; i < 40 ; i++) {
            Post r = new Post();
            r.setPost_pic(ContextCompat.getDrawable(this, R.drawable.item3));
            //p.setPost_filters("");
            r.setShop_name("shop" + i);
            r.setDsnr_name("designer" + i);
            r.setUser_profilePic(ContextCompat.getDrawable(this, R.drawable.icon_person));
            r.setUser_name("user" + i);
            r.setPost_regDate("dddd");
            mAdapter.add(r);
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
