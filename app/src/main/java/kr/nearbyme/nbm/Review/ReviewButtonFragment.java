package kr.nearbyme.nbm.Review;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.DialogFragment;

import kr.nearbyme.nbm.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewButtonFragment extends DialogFragment {



    public ReviewButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_button, container, false);

        return view;
    }

    @NonNull


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(R.layout.fragment_review_button);



        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }

}
