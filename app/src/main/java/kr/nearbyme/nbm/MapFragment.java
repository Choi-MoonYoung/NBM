package kr.nearbyme.nbm;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import kr.nearbyme.nbm.manager.PropertyManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends DialogFragment {
    public static final int REQUEST_MY_ACTIVITY = 0;

    RadioGroup radioGroup;
    Button close, presentLoc, setLoc, done;
    ImageView lineView;
    double locX, locY;
    int radius;



    public interface DoneClickListener{
        public void onDoneClick(double locx, double locy, int radius);
    }
    DoneClickListener dListener;

    public void setMapDoneClickListener(DoneClickListener listener) {
        dListener = listener;
    }


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
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dListener=(DoneClickListener)activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
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
        lineView = (ImageView) view.findViewById(R.id.image_line);

        setLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "아직 지원하지 않는 서비스입니다", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getContext(), "눌렸어요!!!!!", Toast.LENGTH_SHORT).show();
//                SetLocationFragment f = new SetLocationFragment();
//                f.show(getActivity().getSupportFragmentManager(), "create");
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

                dListener.onDoneClick(locX, locY, radius);

                PropertyManager.getInstance().setMyPosition(locX, locY);
                PropertyManager.getInstance().setMyRadius(radius);

                WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
                Log.i("MapFragment", "2 swidth : " + params.width + " , height : " + params.height);
                Log.i("MapFragment", "x : " + params.x + ", y : " + params.y);
                Point p = new Point();
                getActivity().getWindowManager().getDefaultDisplay().getSize(p);
                Log.i("MapFragment","Display width : " + p.x + ", height : " + p.y);
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
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        getDialog().getWindow().setLayout(width, height);
        Log.i("MapFragment", "width : " + width + ", height : " + height);
        Point p = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(p);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.window_background);
        Log.i("MapFragment","Display width : " + p.x + ", height : " + p.y);

    }
}
