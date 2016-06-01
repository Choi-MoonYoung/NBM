package kr.nearbyme.nbm.Review;

import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;
import kr.nearbyme.nbm.data.Shop;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class KeyContentViewHolder extends RecyclerView.ViewHolder implements Checkable {
    TextView keyView;
    Key mData;

    public interface OnItemClickListener3 {
        public void onItemClick3(View view, int position);
    }

    OnItemClickListener3 mListener3;
    public void setOnItemClickListener3(OnItemClickListener3 listener) {
        mListener3 = listener;
    }

    boolean isChecked;
    @Override
    public void setChecked(boolean checked) {
        if(isChecked != checked){
            isChecked = checked;
            drawCheck();
        }

    }

    public void drawCheck() {
        if(isChecked){
            keyView.setBackgroundResource(android.R.drawable.checkbox_on_background);
            //check draw
        }
        else{
//            keyView.setBackgroundResource(android.R.drawable.checkbox_off_background);
            //uncheck draw
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);

    }



    public KeyContentViewHolder(View itemView) {
        super(itemView);
        keyView = (TextView) itemView.findViewById(R.id.text_key);

        keyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener3 != null) {
                    mListener3.onItemClick3(v, getAdapterPosition());

                }
            }
        });

    }

    public void setKeyData(Key data){
        mData = data;
        keyView.setText(data.getKey());
    }

}
