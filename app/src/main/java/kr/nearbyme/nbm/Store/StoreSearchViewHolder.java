package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.StoreData;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class StoreSearchViewHolder extends RecyclerView.ViewHolder {
    EditText store;
    Button order, search;
    StoreData mData;
    Shop shopData;

    public interface OnItemClickListener {
        public void onItemClick(View view, StoreData storeData);
    }

    public interface OnItemClickListener4 {
        public void onItemClick4(View view, Shop shop);
    }



    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    OnItemClickListener4 mListener4;
    public void setOnItemClickListener4(OnItemClickListener4 listener) {
        mListener4 = listener;
    }




    public StoreSearchViewHolder(View itemView) {
        super(itemView);
        store = (EditText) itemView.findViewById(R.id.edit_store_search);
        order = (Button) itemView.findViewById(R.id.btn_order);
        //iconView = (ImageView) itemView.findViewById(R.id.image_search);
        search = (Button) itemView.findViewById(R.id.btn_search);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);

                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener4 != null){
                    mListener4.onItemClick4(v, shopData);
                }
            }
        });

    }


    public void setStoreSearchData(StoreData data){
        mData = data;
        //iconView.setImageDrawable(data.getSearchImg());

    }
}
