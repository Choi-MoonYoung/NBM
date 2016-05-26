package org.kr.nearbyme.Store;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kr.nearbyme.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {
    RecyclerView recyclerView;
    StoreListAdapter mAdapter;


    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new StoreListAdapter();

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_store);
//        View header = inflater.inflate(R.layout.view_search_store, null);
//
//        text = (EditText) header.findViewById(R.id.edit_store_search);
//        order = (Button) header.findViewById(R.id.btn_order);
//
//        recyclerView.addHeaderView(header, null, true); //첫번째인자 헤더뷰객체 두번째인자 뷰가눌렸을때 넘겨줄 데이터값 세번째인자 뷰가 선택될것인지

        View v = inflater.inflate(R.layout.view_search_store, null);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void initData() {
        StoreData s = new StoreData();
        s.setSearchImg(ContextCompat.getDrawable(getContext(), R.drawable.icon));
        mAdapter.add(s);

        for (int i = 0; i < 40 ; i++) {
            Store p = new Store();
            p.setStoreName("item" + i);
            p.setStoreDescription("description" + i);
            p.setDistance("distance" + i);
            p.setStoreImg(ContextCompat.getDrawable(getContext(), R.drawable.item2));
            mAdapter.add(p);
        }
    }

}

