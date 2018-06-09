package com.limefriends.molde.menu_mypage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.limefriends.molde.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoldeMyPageMyCommentActivity extends AppCompatActivity {

    @BindView(R.id.myComment_recyclerView)
    RecyclerView myComment_recyclerView;

    MoldeMyPageMyCommentAdapter adapter;
    List<MyPageMyCommentElem> myPageMyCommentElemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_molde_my_page_my_comment);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.default_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbar_title = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        toolbar_title.setText("내가 쓴 댓글");



        myPageMyCommentElemList = new ArrayList<MyPageMyCommentElem>();

        myPageMyCommentElemList.add(new MyPageMyCommentElem("에어비앤*에서 다시 몰카 발각",
                R.drawable.mypage_image, "2017.02.01",
                "세상에… 이런일이 다 있네요 ㅠㅠ"));

        myPageMyCommentElemList.add(new MyPageMyCommentElem("에어비앤*에서 다시 몰카 발각",
                R.drawable.mypage_image,
                "2017.02.02",
                "세상에… 이런일이 다 있네요 ㅠㅠ"));

        myPageMyCommentElemList.add(new MyPageMyCommentElem("에어비앤*에서 다시 몰카 발각",
                R.drawable.mypage_image,
                "2017.02.03",
                "세상에… 이런일이 다 있네요 ㅠㅠ"));

        myPageMyCommentElemList.add(new MyPageMyCommentElem("에어비앤*에서 다시 몰카 발각",
                R.drawable.mypage_image,
                "2017.02.04",
                "세상에… 이런일이 다 있네요 ㅠㅠ"));

        adapter = new MoldeMyPageMyCommentAdapter(getApplicationContext(),
                R.layout.activity_molde_my_page_my_comment, myPageMyCommentElemList);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        myComment_recyclerView.setLayoutManager(layoutManager);
        myComment_recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}