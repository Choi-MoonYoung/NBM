package kr.nearbyme.nbm.Writereview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Write;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteReviewFragment extends Fragment {
    AutoCompleteTextView storeNameView, designerNameView;
    RatingBar ratingBar;
    Button buttonTag, buttonPost, buttonSelect;
    TextView filterTagsView;
    EditText contentView;

    ScrollView scrollView;

    Write mData;




    public WriteReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_review, container, false);

        scrollView = (ScrollView) view.findViewById(R.id.scrollView2);
        storeNameView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView_store);
        designerNameView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView_dsnr);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_review);
        buttonTag = (Button) view.findViewById(R.id.btn_keyword);
        filterTagsView = (TextView) view.findViewById(R.id.text_keywords);
        contentView = (EditText) view.findViewById(R.id.edit_review);
        buttonSelect = (Button) view.findViewById(R.id.btn_post);
        buttonPost = (Button) view.findViewById(R.id.btn_post);



        return view;
    }

}
