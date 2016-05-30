package kr.nearbyme.nbm.Review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import kr.nearbyme.nbm.R;
import kr.nearbyme.nbm.data.Post;
import kr.nearbyme.nbm.data.Comment;
import kr.nearbyme.nbm.data.PostDetailResult;
import kr.nearbyme.nbm.data.PostResult;
import kr.nearbyme.nbm.data.ShopDetailResult;
import kr.nearbyme.nbm.manager.NetworkManager;
import okhttp3.Request;

public class ReviewDetailActivity extends AppCompatActivity {
    public static final String EXTRA_REVIEW_ID = "review_id";
    String review_id;
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
/*
        writeComment = (EditText) findViewById(R.id.edit_commentWrite);
        btnPost = (Button) findViewById(R.id.btn_post);
*/
        recyclerView = (RecyclerView)findViewById(R.id.recycler_detail);
        recyclerView.setAdapter(mAdapter);

        initData();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //recyclerView.setHasFixedSize(true);



        Intent intent = getIntent();
        review_id = intent.getStringExtra(EXTRA_REVIEW_ID);


    }

    private void initData() {

        NetworkManager.getInstance().getPostDetail(param_sort, param_id, new NetworkManager.OnResultListener<PostDetailResult>() {
            @Override
            public void onSuccess(Request request, PostDetailResult result) {


                mAdapter.clear();

                mAdapter.addReview(result.result);
                mAdapter.addCommentAll(result.result.post.post_comments);

                //mAdapter.addStore(result.getShop_info());
                // mAdapter.addAll(result.getPost_info());

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(ReviewDetailActivity.this, "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
/*
        PostResult s = new PostResult();
        //s.setUser_profilePic(ContextCompat.getDrawable(this, R.drawable.icon_person));
        s.user.setUser_name("user");
        s.post.setPost_regDate("ddddd");
        s.post.setPost_content("dsfasdfdsafdf");
        //s.setPost_pic(ContextCompat.getDrawable(this, R.drawable.item3));
        s.post.setPost_likeNum(1);
        s.post.setCommentNum(3);
        s.shop.setShop_name("asdfdf");
        s.dsnr.setDsnr_name("dsfdg");

        mAdapter.addReview(s);

        for (int i = 0; i < 3 ; i++) {
            Comment p = new Comment();
            p.setCmt_writerName("name");
            p.setCmt_content("good");
            p.setCmt_regDate("mmmm");
            mAdapter.addComment(p);
        }*/
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
