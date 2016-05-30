package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.StoreData;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class StoreSearchViewHolder extends RecyclerView.ViewHolder {
    ImageView iconView;
    EditText store;
    Button order;
    StoreData mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, StoreData storeData);
    }



    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    public StoreSearchViewHolder(View itemView) {
        super(itemView);
        store = (EditText) itemView.findViewById(R.id.edit_store_search);
        order = (Button) itemView.findViewById(R.id.btn_order);
        iconView = (ImageView) itemView.findViewById(R.id.image_search);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);

                }
            }
        });


    }


    public void setStoreSearchData(StoreData data){
        mData = data;
        //store.setText(data.getStoreName());
        //order.setText(data.getStoreDescription());
        //storeDistanceView.setText(data.getDistance());
        iconView.setImageDrawable(data.getSearchImg());

    }
}
