package kr.nearbyme.nbm.Store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.ShopDetailResult;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;

public class StoreDetailActivity extends AppCompatActivity {
    public static final String EXTRA_SHOP_ID = "shop_id";
    String shop_id;
    String shop_name;
    String shop_phonenum;
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







        Intent intent = getIntent();
        shop_id = intent.getStringExtra(EXTRA_SHOP_ID);

        initData();

        mAdapter.setOnCallClickListener(new StoreInfoViewHolder.OnCallClickListener() {
            @Override
            public void onCallClick(View view, Shop shop) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+shop_phonenum));
                startActivity(myIntent);
            }
        });
        
    }

    private void initData() {

        NetworkManager.getInstance().getShopDetail(shop_id, new NetworkManager.OnResultListener<ShopDetailResult>() {
            @Override
            public void onSuccess(Request request, ShopDetailResult result) {



                mAdapter.clear();

                mAdapter.setResult(result);

                shop_phonenum = result.shop_info.getShop_phone();
                shop_name = result.shop_info.getShop_name();

                //mAdapter.addStore(result.getShop_info());
               // mAdapter.addAll(result.getPost_info());

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(StoreDetailActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
