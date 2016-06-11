package kr.nearbyme.nbm.Writereview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class WritePostKeyContentViewHolder extends RecyclerView.ViewHolder  implements Checkable {
    TextView keyView;
    Key mData;
    boolean isChecked;


    public interface OnItemClickListener3 {
        public void onItemClick3(View view, int position);
    }

    OnItemClickListener3 mListener3;
    public void setOnItemClickListener3(OnItemClickListener3 listener) {
        mListener3 = listener;
    }

    public void setKeyData(Key data){
        mData = data;
        keyView.setText(data.getKey());
    }



    public void setChecked(boolean checked) {
        if(isChecked != checked){
            isChecked = checked;
            drawCheck();
        }

    }

    public void drawCheck() {
        if(isChecked){
            keyView.setBackgroundColor(Color.GRAY);
            //check draw
        }
        else{
            keyView.setBackgroundColor(Color.WHITE);
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



    public WritePostKeyContentViewHolder(View itemView) {
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



}
