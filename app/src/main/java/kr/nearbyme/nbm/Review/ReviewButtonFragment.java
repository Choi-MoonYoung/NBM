package kr.nearbyme.nbm.Review;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.nearbyme.nbm.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ReviewButtonFragment extends Fragment {


    public ReviewButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_button, container, false);
    }

}
