package kr.nearbyme.nbm.Store;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Shop;
import kr.nearbyme.nbm.data.StoreData;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 17..
 */
public class StoreSearchViewHolder extends RecyclerView.ViewHolder {
    EditText store;
    Button search;
    ImageView order, order2;
    StoreData mData;
    Shop shopData;

    public interface OnItemClickListener {
        public void onItemClick(View view, StoreData storeData);
    }

    public interface OnOrderClickListener {
        public void onOrderClick(View view, StoreData storeData);
    }

    public interface OnItemClickListener4 {
        public void onItemClick4(View view, String str);
    }



    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    OnOrderClickListener mOrderListener;
    public void setOnOrderClickListener(OnOrderClickListener listener) {
        mOrderListener = listener;
    }


    OnItemClickListener4 mListener4;
    public void setOnItemClickListener4(OnItemClickListener4 listener) {
        mListener4 = listener;
    }




    public StoreSearchViewHolder(View itemView) {
        super(itemView);
        store = (EditText) itemView.findViewById(R.id.edit_store_search);
        order = (ImageView) itemView.findViewById(R.id.btn_order);
        order2 = (ImageView) itemView.findViewById(R.id.btn_order2);
        //iconView = (ImageView) itemView.findViewById(R.id.image_search);
        search = (Button) itemView.findViewById(R.id.btn_search);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setVisibility(View.GONE);
                order2.setVisibility(View.VISIBLE);
                if (mListener != null) {
                    mListener.onItemClick(v, mData);
//                    setOrderList();


                }
            }
        });
        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setVisibility(View.VISIBLE);
                order2.setVisibility(View.GONE);
                if (mOrderListener != null) {
                    mOrderListener.onOrderClick(v, mData);
//                    setReviewList();
                }

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener4 != null){

                    mListener4.onItemClick4(v, store.getText()+"");
                }
            }
        });

    }


    public void setStoreSearchData(StoreData data){
        mData = data;
        //iconView.setImageDrawable(data.getSearchImg());


    }

}
