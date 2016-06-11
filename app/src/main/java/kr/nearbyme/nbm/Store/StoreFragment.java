package kr.nearbyme.nbm.Store;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.ShopListResult;
import kr.nearbyme.nbm.data.StoreData;
import kr.nearbyme.nbm.manager.NetworkManager;
import kr.nearbyme.nbm.manager.PropertyManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {
    RecyclerView recyclerView;
    StoreListAdapter mAdapter;
    //Button button;
    private String keyword = "없음";
    private int order = 1;
    private double locX;
    private double locY;
    private int radius;


    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new StoreListAdapter();
        mAdapter.setOnItemClickListener(new StoreListViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Shop shop) {
                Toast.makeText(getContext(), "눌렸습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), StoreDetailActivity.class);
                intent.putExtra(StoreDetailActivity.EXTRA_SHOP_ID, shop.getShop_id());
                startActivity(intent);

            }
        });
        mAdapter.setOnShopLikeClickListener(new StoreListViewHolder.OnShopLikeClickListener() {
            @Override
            public void onShopLikeClick(View view, Shop shop) {

            }
        });


        mAdapter.setOnItemClickListener(new StoreSearchViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, StoreData storeData) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("정렬순서");
                builder.setPositiveButton("평점순", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        order = 1;

                    }
                });
                builder.setNegativeButton("후기순", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        order = 2;

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        mAdapter.setOnItemClickListener4(new StoreSearchViewHolder.OnItemClickListener4() {
            @Override
            public void onItemClick4(View view, Shop shop) {

            }
        });


    }



    @Override
    public void onResume() {
        super.onResume();
        locX = PropertyManager.getInstance().getLatitude();
        locY = PropertyManager.getInstance().getLongitude();
        radius = PropertyManager.getInstance().getMyRadius();

        initData();
        recyclerView.scrollToPosition(0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_store);

        View v = inflater.inflate(R.layout.view_search_store, null);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void initData() {
        

        NetworkManager.getInstance().getShopList(getContext()
                , keyword, order, locX, locY, radius, new NetworkManager.OnResultListener<ShopListResult>() {

            @Override
            public void onSuccess(Request request, ShopListResult result) {
                mAdapter.clear();
                mAdapter.addAll(result.result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(),"네트워크 에러",Toast.LENGTH_SHORT).show();
            }
        });

/*
        StoreData s = new StoreData();
        s.setSearchImg(ContextCompat.getDrawable(getContext(), R.drawable.icon));
        mAdapter.add(s);


        for (int i = 0; i < 40 ; i++) {
            Shop p = new Shop();
            p.setShop_name("item" + i);
            p.setShop_intro("description" + i);
            p.setShop_distance("distance" + i);
           // p.setShop_pic(ContextCompat.getDrawable(getContext(), R.drawable.item2));
            mAdapter.add(p);
        }
*/
    }

}

