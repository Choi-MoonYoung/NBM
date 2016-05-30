package kr.nearbyme.nbm;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;

import kr.nearbyme.nbm.Review.ReviewDetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends DialogFragment {
    Button close, presentLoc, setLoc, one, three, five, ten, done;


    public MapFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        close = (Button) view.findViewById(R.id.btn_close);
        presentLoc = (Button) view.findViewById(R.id.btn_presentloc);
        setLoc = (Button) view.findViewById(R.id.btn_setloc);
        done = (Button) view.findViewById(R.id.button_done);

        setLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "눌렸어요!!!!!", Toast.LENGTH_SHORT).show();
                SetLocationFragment f = new SetLocationFragment();
                f.show(getActivity().getSupportFragmentManager(), "create");
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);

    }
}
