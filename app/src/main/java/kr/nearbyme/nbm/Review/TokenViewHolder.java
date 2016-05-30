package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 24..
 */
public class TokenViewHolder extends RecyclerView.ViewHolder {
    //ImageView iconView;
    TextView filtertags;
    Key mData;


    public TokenViewHolder(View itemView) {
        super(itemView);
        filtertags = (TextView) itemView.findViewById(R.id.text_filtertags);
        //iconView = (ImageView) itemView.findViewById(R.id.image_search);

    }

    public void setTokenHeaderData(Key data){
        mData = data;
        filtertags.setText(data.getKey());


    }
}
