package kr.nearbyme.nbm;


import android.app.Activity;
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
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Button;

import kr.nearbyme.nbm.Review.ReviewDetailActivity;
import kr.nearbyme.nbm.Review.ReviewFragment;
import kr.nearbyme.nbm.manager.PropertyManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends DialogFragment {
    public static final int REQUEST_MY_ACTIVITY = 0;

    RadioGroup radioGroup;
    Button close, presentLoc, setLoc, done;
    double locX, locY;
    int radius;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MY_ACTIVITY && resultCode == Activity.RESULT_OK) { //호출한 Activity가 종료되고 처리할 내용
            locX = data.getDoubleExtra(GoogleLocActivity.RESULT_LOCX, locX);
            locY = data.getDoubleExtra(GoogleLocActivity.RESULT_LOCY, locY);

        }
        Toast.makeText(getContext(), "" + locX, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_radius);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_one :
                        radius = 1;
                        break;
                    case R.id.btn_three :
                        radius = 3;
                        break;
                    case R.id.btn_five :
                        radius = 5;
                        break;
                    default :
                        radius = 10;
                        break;
                }

            }
        });

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

        presentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoogleLocActivity.class);
                startActivityForResult(intent, REQUEST_MY_ACTIVITY);
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

                PropertyManager.getInstance().setMyPosition(locX, locY);
                PropertyManager.getInstance().setMyRadius(radius);

                dismiss();


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
