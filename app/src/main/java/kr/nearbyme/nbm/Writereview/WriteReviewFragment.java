package kr.nearbyme.nbm.Writereview;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.nearbyme.nbm.MyApplication;
import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.Review.KeywordFragment;
import kr.nearbyme.nbm.data.PostUpload;
import kr.nearbyme.nbm.data.Write;
import kr.nearbyme.nbm.data.WriteResult;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class WriteReviewFragment extends Fragment {
    AutoCompleteTextView storeNameView, designerNameView;
    RatingBar ratingBar;
    Button buttonTag, buttonPost;
    TextView filterTagsView;
    EditText contentView;
    ImageView ImageUploadView;

    ScrollView scrollView;

    Write mData;
    private String shop_id;
    private String dsnr_id;
    private double post_score;
    private String post_content;
    List<String> post_filters = new ArrayList<String>();

    private static final int RC_GALLERY = 1;
    private static final int RC_CAMERA = 2;

    private void getImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getCameraCaptureFile());
        startActivityForResult(intent, RC_CAMERA);
    }


    File mCameraCaptureFile;

    private Uri getCameraCaptureFile() {
        File dir = getContext().getExternalFilesDir("myphoto");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        mCameraCaptureFile = new File(dir, "my_photo_"+System.currentTimeMillis()+".jpg");
        return Uri.fromFile(mCameraCaptureFile);
    }

    private void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/jpeg");
        startActivityForResult(intent, RC_GALLERY);
    }

    File mUploadFile = null;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mUploadFile != null) {
            outState.putString("uploadfile", mUploadFile.getAbsolutePath());
        }
        if (mCameraCaptureFile != null) {
            outState.putString("cameraFile", mCameraCaptureFile.getAbsolutePath());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor c = getContext().getContentResolver().query(uri, projection, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    mUploadFile = new File(path);
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inSampleSize = 2;
                    Bitmap bm = BitmapFactory.decodeFile(path, opts);
                    ImageUploadView.setImageBitmap(bm);
                }
            }
            return;
        }

        if (requestCode == RC_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {


//                Toast.makeText(MyApplication.getContext(), mCameraCaptureFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                File file = mCameraCaptureFile;
//                BitmapFactory.Options opts = new BitmapFactory.Options();
//                opts.inSampleSize = 2;
//                Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
                mUploadFile = file;
                Glide.with(this).load(mUploadFile).into(ImageUploadView);
//                ImageUploadView.setImageBitmap(bm);

            }
            return;
        }

//        onActivityResult(requestCode, resultCode, data); //
    }





    public WriteReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String path = savedInstanceState.getString("uploadfile");
            if (!TextUtils.isEmpty(path)) {
                mUploadFile = new File(path);
            }
            path = savedInstanceState.getString("cameraFile");
            if (!TextUtils.isEmpty(path)) {
                mCameraCaptureFile = new File(path);
            }
        }
    }

    private void initData() {
        NetworkManager.getInstance().getPostUpload(getContext(), shop_id, dsnr_id, post_score, post_content, post_filters, mUploadFile, new NetworkManager.OnResultListener<WriteResult>() {


            @Override
            public void onSuccess(Request request, WriteResult result) {
                mData.post_id = result.result.post_id;
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });




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
        ImageUploadView = (ImageView) view.findViewById(R.id.image_upload);
        buttonPost = (Button) view.findViewById(R.id.btn_post);

        if (mUploadFile != null) {
            Glide.with(this).load(mUploadFile).into(ImageUploadView);
        }

        ImageUploadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("사진올리기");
                builder.setPositiveButton("앨범에서 선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getImageFromGallery();

                    }
                });
                builder.setNegativeButton("카메라 촬영", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getImageFromCamera();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });



        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_id = storeNameView.getText().toString();
                dsnr_id = designerNameView.getText().toString();
                post_score = ratingBar.getRating();
                post_content = contentView.getText().toString();

                initData();


            }
        });

        buttonTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritePostKeywordFragment f = new WritePostKeywordFragment();
                f.show(getActivity().getSupportFragmentManager(), "create");

            }
        });



        return view;
    }


}
