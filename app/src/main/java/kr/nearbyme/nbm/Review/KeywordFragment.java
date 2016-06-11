package kr.nearbyme.nbm.Review;


import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Key;
import kr.nearbyme.nbm.manager.PropertyManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeywordFragment extends DialogFragment {
    RecyclerView recyclerView;
    KeyAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    Button done;

    List<Key> keys= new ArrayList<>();
    String[] strs = new String[]{"여자 연예인\n헤어스타일" , "단발머리" , "숏컷" , "롱\n헤어스타일" , "긴머리" , "차예련\n헤어스타일" ,
            "c컬" , "볼륨펌" , "매직" , "볼륨매직" , "오렌지레드\n컬러" , "애쉬브라운\n컬러" , "염색" , "면접헤어\n스타일" ,
            "업스타일" , "웨딩촬영\n스타일" , "투블럭" , "웨이브" , "펌" , "드라이" , "클리닉" , "투톤헤어" ,
            "포니테일" , "내츄럴펌" , "레이어드컷" , "동그란\n얼굴형" , "반업\n스타일링" , "오렌지브라운\n컬러" ,
            "와인컬러" , "s컬펌" , "볼륨웨이브\n단발" , "미디움아웃\nc컬" , "체리레드\n컬러" , "러블리\n스타일" , "트리트먼트" ,
            "긴앞머리\n스타일" , "글램 웨이브" , "세미업\n스타일" , "뱅헤어" , "물결\n웨이브펌" , "다크브라운" , "비니\n헤어스타일" ,
            "다크브라운\n컬러" , "프렌치\n웨이브" , "묶음머리" , "긴생머리" ,  "미디움\n원랭스컷" , "시스루뱅" , "에어드라이\n컷" ,
            "미디움\n디자인컷" , "뿌리염색" , "롱\n레이어드컷" , "미디움\n다듬기컷" , "앞머리펌" , "볼륨\n텍스쳐컷" , "드라이" ,
            "두피클리닉" , "보브 단발컷" , "레이디\n투블럭컷"};

    public interface KeyWordDoneClickListener{
        public void onKeyWordDoneClick(List<String> keyFilters);
    }
    KeyWordDoneClickListener keyDoneListener;

    public void setKeyWordDoneClickListener(KeyWordDoneClickListener listener) {
        keyDoneListener = listener;
    }


    public KeywordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
        mAdapter = new KeyAdapter();

        mAdapter.setOnItemClickListener3(new KeyContentViewHolder.OnItemClickListener3() {
            @Override
            public void onItemClick3(View view, int position) {
                mAdapter.onItemClick3(view, position);
            }

        });


    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        for(int i=0; i<strs.length; i++) {
            keys.add(new Key(strs[i]));
        }


        mAdapter.addAll(keys);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_key);
        done = (Button) view.findViewById(R.id.btn_done);



        recyclerView.setAdapter(mAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 3);

        recyclerView.setLayoutManager(mLayoutManager);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "완료", Toast.LENGTH_SHORT).show();

                List<String> checkedFilter = new ArrayList<String>();
                int n = 0;

                String keyword;
                for (int i = 0; i < strs.length; i++) {
                    if (mAdapter.checkedItems.get(i)) {
                        keyword = strs[i];

                        checkedFilter.add(n, keyword);

                        n++;
                    }

                }
                PropertyManager.getInstance().setFilters(checkedFilter);


                if (keyDoneListener != null) {
                    keyDoneListener.onKeyWordDoneClick(checkedFilter);
                }

                dismiss();

            }
        });


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
//        getDialog().getWindow().setLayout(width, height);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        getDialog().getWindow().setLayout(width, height);
        Log.i("MapFragment", "width : " + width + ", height : " + height);
        Point p = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(p);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.window_background);
        Log.i("MapFragment", "Display width : " + p.x + ", height : " + p.y);
    }
}
