package kr.nearbyme.nbm.Review;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;

/**
 * Created by CHOIMOONYOUNG on 2016. 5. 23..
 */
public class KeyHeaderViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    AutoCompleteTextView searchView;
    Key mData;

    public KeyHeaderViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textDescription);
        searchView = (AutoCompleteTextView) itemView.findViewById(R.id.autocompletetext_searchkeyword);

    }

    public void setKeyHeaderData(Key key) {
        mData = key;
    }
}
