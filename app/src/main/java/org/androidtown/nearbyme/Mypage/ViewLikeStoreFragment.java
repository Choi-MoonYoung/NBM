
package org.androidtown.nearbyme.Mypage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidtown.nearbyme.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewLikeStoreFragment extends Fragment {


    public ViewLikeStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_like_store, container, false);
    }

}