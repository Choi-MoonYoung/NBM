package kr.nearbyme.nbm.Mypage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Store.StoreDetailActivity;
import kr.nearbyme.nbm.data.LikePost;
import kr.nearbyme.nbm.data.LikeShopResult;
import kr.nearbyme.nbm.data.LikeShopResultResult;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewLikeStoreFragment extends Fragment {
    RecyclerView recyclerView;
    LikeStoreAdapter mAdapter;



    public ViewLikeStoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new LikeStoreAdapter();
        mAdapter.setOnItemClickListener(new LikeStoreViewHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View view, Shop shop) {
                Toast.makeText(getContext(), "눌렸습니다", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), StoreDetailActivity.class);
                intent.putExtra(StoreDetailActivity.EXTRA_SHOP_ID, shop.getShop_id());
                startActivity(intent);
            }

        });


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        recyclerView.scrollToPosition(0);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_like_store, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_likestore);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }

    private void initData() {

        NetworkManager.getInstance().getLikeShop(new NetworkManager.OnResultListener<LikeShopResultResult>() {
            @Override
            public void onSuccess(Request request, LikeShopResultResult result) {

                mAdapter.clear();
                List<Shop> shopList = new ArrayList<Shop>();
                for(LikeShopResult tmp : result.result){
                    shopList.add(tmp.shop);
                }
                mAdapter.addAll(shopList);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

/*
        for (int i = 0; i < 40 ; i++) {
            Shop p = new Shop();
            p.setShop_name("item" + i);
            p.setShop_intro("description" + i);

            //p.setShop_pic(ContextCompat.getDrawable(getContext(), R.drawable.item2));
            mAdapter.add(p);
        }*/
    }

}
