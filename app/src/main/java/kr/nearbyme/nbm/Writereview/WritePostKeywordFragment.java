package kr.nearbyme.nbm.Writereview;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Review.KeyContentViewHolder;
import kr.nearbyme.nbm.data.Key;
import kr.nearbyme.nbm.manager.PropertyManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class WritePostKeywordFragment extends DialogFragment{
    RecyclerView recyclerView;
    WritePostKeyAdapter mAdapter;
    GridLayoutManager mLayoutManager;

    Button done, close;

    List<Key> keys= new ArrayList<>();
    String[] strs = new String[]{"여자연예인헤어스타일" , "단발머리" , "숏컷" , "롱헤어스타일" , "긴머리" , "차예련헤어스타일" ,
            "c컬" , "볼륨펌" , "매직" , "볼륨매직" , "오렌지레드컬러" , "애쉬라운컬러" , "염색" , "면접헤어스타일" ,
            "업스타일" , "웨딩촬영스타일" , "투블럭" , "웨이브" , "펌" , "드라이" , "클리닉" , "투톤헤어" ,
            "포니테일" , "내츄럴펌" , "레이어드컷" , "동그란얼굴형에 어울리는" , "반업스타일링" , "오렌지브라운컬러" ,
            "와인컬러" , "s컬펌" , "볼륨웨이브단발" , "미디움아웃c컬" , "체리레드컬러" , "러블리스타일" , "트리트먼트" ,
            "긴앞머리스타일" , "글램웨이브" , "세미업스타일" , "뱅헤어" , "물결웨이브펌" , "다크브라운" , "비니헤어스타일" ,
            "다크브라운컬러" , "프렌치웨이브" , "묶음머리" , "긴생머리" ,  "미디움원랭스컷" , "시스루뱅" , "에어드라이 컷" ,
            "미디움디자인컷" , "뿌리염색" , "롱레이어드컷" , "미디움다듬기컷" , "앞머리펌" , "볼륨텍스쳐컷" , "드라이" ,
            "두피클리닉" , "보브단발컷" , "레이디투블럭컷"};



    public interface WritePostKeyWordDoneClickListener{
        public void onWritePostKeyWordDoneClick(List<String> keyFilters);
    }
    WritePostKeyWordDoneClickListener writePostKeyWordDoneClickListener;

    public void setWritePostKeyWordDoneClickListener(WritePostKeyWordDoneClickListener listener) {
        writePostKeyWordDoneClickListener = listener;
    }




    public WritePostKeywordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new WritePostKeyAdapter();

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
       // close = (Button) view.findViewById(R.id.btn_close);



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
                for (int i = 0; i < mAdapter.checkedItems.size(); i++) {
                    if (mAdapter.checkedItems.get(i)) {
                        keyword = strs[i];
                        //checkedFilter.add(n, i+"");
                        checkedFilter.add(n, keyword);
                        n++;
                    }

                }

//                if (writePostKeyWordDoneClickListener != null) {
//                    writePostKeyWordDoneClickListener.onWritePostKeyWordDoneClick(checkedFilter);
//                }

                PropertyManager.getInstance().setWritePostfilter(checkedFilter);
                dismiss();

            }
        });

//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });


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
        getDialog().getWindow().setLayout(width, height);
    }
}
