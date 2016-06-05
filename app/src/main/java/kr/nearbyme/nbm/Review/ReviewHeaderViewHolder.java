package kr.nearbyme.nbm.Review;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;
import kr.nearbyme.nbm.data.ReviewData;
import kr.nearbyme.nbm.manager.PropertyManager;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 18..
 */
public class ReviewHeaderViewHolder extends RecyclerView.ViewHolder{
    RecyclerView recyclerView;
    Button filter;
    ReviewData mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, ReviewData reviewData);
    }

    TokenAdapter mAdapter;


    OnItemClickListener mListener1;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener1 = listener;
    }


    public ReviewHeaderViewHolder(View itemView) {
        super(itemView);

        filter = (Button) itemView.findViewById(R.id.btn_filter);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_keyshow);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener1 != null) {
                    mListener1.onItemClick(v, mData);

                }
            }
        });

        mAdapter = new TokenAdapter();
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setData();

    }





    public void setData() {


        mAdapter.clear();

        for(int i=0; i< PropertyManager.getInstance().getFilters().size(); i++){
            Key k1 =  new Key();
            k1.setKey(PropertyManager.getInstance().getFilters().get(i));
            mAdapter.add(k1);

        }



    }
}
