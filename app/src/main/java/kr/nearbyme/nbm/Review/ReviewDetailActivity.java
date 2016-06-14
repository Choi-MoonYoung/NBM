package kr.nearbyme.nbm.Review;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Comment;
import kr.nearbyme.nbm.data.PostDetailResult;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.manager.NetworkManager;
import kr.nearbyme.nbm.manager.PropertyManager;
import okhttp3.Request;

public class ReviewDetailActivity extends AppCompatActivity {
    public static final String EXTRA_REVIEW_ID = "review_id";
    String post_id;
    String cmt_id;
    int onoff;
    int mine_post;
    int mine_cmt;
//    int mine_cmt[];

    EditText writeComment;
    Button btnPost;

    RecyclerView recyclerView;
    ReviewDetailAdapter mAdapter;
    private String param_sort;
    private String param_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReviewDetailAdapter();
        setContentView(R.layout.activity_review_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("후기");


        Intent intent = getIntent();
        post_id = intent.getStringExtra(EXTRA_REVIEW_ID);
        PropertyManager.getInstance().setParam_id(post_id);

        mAdapter.setOnItemClickListener(new ReviewDetailViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, PostResult post) { //후기삭제

                if((PropertyManager.getInstance().getIsGuest() == 0)){
                    if(mine_post == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReviewDetailActivity.this);
                        builder.setMessage("삭제하시겠습니까?");
                        builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                        builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletePost();
                                Toast.makeText(ReviewDetailActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();

                                finish();

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
                else if(PropertyManager.getInstance().getIsGuest() == 1){
                    Toast.makeText(ReviewDetailActivity.this, "로그인이 필요한 서비스입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });



        mAdapter.setOnLikeClickListener(new ReviewDetailViewHolder.OnLikeClickListener() {
            @Override
            public void onLikeClick(View view, PostResult post) {

                if (PropertyManager.getInstance().getIsGuest() == 0) {
                    if (post.getPost().getLiked() == 0) {
                        onoff = 1;
                        changeLike();

                    } else {
                        onoff = 0;
                        changeLike();

                    }
                } else {
                    Toast.makeText(ReviewDetailActivity.this, "로그인이 필요한 서비스입니다", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mAdapter.setOnItemClickListener(new ReviewCommentWriteViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Comment comment, String cmt_content) {
                postComment(cmt_content);


            }
        });

        mAdapter.setOnDeleteClickListener(new ReviewCommentViewHolder.OnDeleteClickListener() { //댓글삭제
            @Override
            public void onDelteClick(View view, Comment comment, String cmt_content) {
                cmt_id = comment.getCmt_id();
                mine_cmt = comment.getMine();


                if(mine_cmt == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReviewDetailActivity.this);
                    builder.setMessage("삭제하시겠습니까?");
                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteComment(post_id, cmt_id);
                            Toast.makeText(ReviewDetailActivity.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                            initData();
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }

            }
        });
/*
        writeComment = (EditText) findViewById(R.id.edit_commentWrite);
        btnPost = (Button) findViewById(R.id.btn_post);
*/
        recyclerView = (RecyclerView)findViewById(R.id.recycler_detail);
        recyclerView.setAdapter(mAdapter);



        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);


        initData();


    }

    private void deleteComment(String post_id, String cmt_id) {
        NetworkManager.getInstance().deleteComment(post_id, cmt_id, new NetworkManager.OnResultListener<String>() {
            @Override
            public void onSuccess(Request request, String result) {
                initData();


            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });

    }

    private void changeLike(){
        NetworkManager.getInstance().changePostLike(post_id, onoff, new NetworkManager.OnResultListener<String>() {
            @Override
            public void onSuccess(Request request, String result) {
                initData();
//                onResume();


            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(ReviewDetailActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deletePost(){

        NetworkManager.getInstance().deleteMyPost(post_id, new NetworkManager.OnResultListener<String>() {
            @Override
            public void onSuccess(Request request, String result) {

            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });
    }


    private void modifyPost(){

    }

    private void postComment(String cmt_content){
        NetworkManager.getInstance().postComment(post_id, cmt_content, new NetworkManager.OnResultListener<String>() {
            @Override
            public void onSuccess(Request request, String result) {
                initData();

            }

            @Override
            public void onFail(Request request, IOException exception) {

            }
        });


    }

    private void initData() {
        param_sort = PropertyManager.getInstance().getParam_sort();
        param_id = PropertyManager.getInstance().getParam_id();

        NetworkManager.getInstance().getPostDetail(param_sort, param_id, new NetworkManager.OnResultListener<PostDetailResult>() {
            @Override
            public void onSuccess(Request request, PostDetailResult result) {


                mAdapter.clear();

                mAdapter.addReview(result.result);
                mAdapter.addCommentAll(result.result.post.post_comments);
                mine_post = result.result.user.getMine();

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(ReviewDetailActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
