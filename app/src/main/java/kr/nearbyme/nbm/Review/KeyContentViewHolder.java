package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class KeyContentViewHolder extends RecyclerView.ViewHolder {
    TextView keyView;
    Key mData;

    public interface OnItemClickListener {
        public void onItemClick(View view, Key key);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public KeyContentViewHolder(View itemView) {
        super(itemView);
        keyView = (TextView) itemView.findViewById(R.id.text_key);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, mData);
                }
            }
        });

    }

    public void setKeyData(Key data){
        mData = data;
        keyView.setText(data.getKey());
    }

}
