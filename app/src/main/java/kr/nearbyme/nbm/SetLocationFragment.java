package kr.nearbyme.nbm;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetLocationFragment extends DialogFragment {
    Spinner spinnerMetropolitan, spinnerDistrict, spinnerDong;
    ArrayAdapter<String> mAdapter;
    String[] metropolitan = {"서울특별시", "인천광역시", "경기도"};
    String[] distrcit = {"강남구", "서초구", "영등포구", "서대문구"};
    String[] dong = {"개포동", "문래동", "목동"};
    Button button;



    public SetLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_location, container, false);

        spinnerMetropolitan = (Spinner) view.findViewById(R.id.spinnerM);
        spinnerDistrict = (Spinner) view.findViewById(R.id.spinnerD);
        spinnerDong = (Spinner) view.findViewById(R.id.spinnerDong);

        button = (Button) view.findViewById(R.id.btn_done);

        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, metropolitan);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetropolitan.setAdapter(mAdapter);

        spinnerMetropolitan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, distrcit);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(mAdapter);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dong);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDong.setAdapter(mAdapter);

        spinnerDong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
