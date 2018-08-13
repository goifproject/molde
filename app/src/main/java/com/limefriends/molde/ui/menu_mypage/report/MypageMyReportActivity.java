package com.limefriends.molde.ui.menu_mypage.report;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.limefriends.molde.R;
import com.limefriends.molde.comm.custom.recyclerview.AddOnScrollRecyclerView;
import com.limefriends.molde.entity.feed.MoldeFeedEntity;
import com.limefriends.molde.entity.feed.MoldeFeedResponseInfoEntity;
import com.limefriends.molde.entity.feed.MoldeFeedResponseInfoEntityList;
import com.limefriends.molde.remote.MoldeRestfulService;
import com.limefriends.molde.remote.MoldeNetwork;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageMyReportActivity extends AppCompatActivity {

    private static final int PER_PAGE = 10;
    private static final int FIRST_PAGE = 0;
    private int currentPage = FIRST_PAGE;
    @BindView(R.id.myReport_recyclerView)
    AddOnScrollRecyclerView myReport_recyclerView;

    MyPageMyReportAdapter reportAdapter;

    private boolean hasMoreToLoad = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_activity_my_report);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.default_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbar_title = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        toolbar_title.setText("내 신고 내역");


//        myReportEntityList.add(new MyPageMyReportEntity(R.drawable.report_map_img,
//                "2018. 03. 01", "서울시 마포구 와우산로 92 체육관 2층 여자화장실 1번째 칸"));
//
//        myReportEntityList.add(new MyPageMyReportEntity(R.drawable.report_map_img,
//                "2018. 03. 02", "서울시 마포구 와우산로 92 체육관 2층 여자화장실 2번째 칸"));
//
//        myReportEntityList.add(new MyPageMyReportEntity(R.drawable.report_map_img,
//                "2018. 03. 03", "서울시 마포구 와우산로 92 체육관 2층 여자화장실 3번째 칸"));
//
//        myReportEntityList.add(new MyPageMyReportEntity(R.drawable.report_map_img,
//                "2018. 03. 04", "서울시 마포구 와우산로 92 체육관 2층 여자화장실 4번째 칸"));


        reportAdapter = new MyPageMyReportAdapter(getApplicationContext());

        // 1. 어댑터
        myReport_recyclerView.setAdapter(reportAdapter);
        // 2. 레이아웃 매니저
        myReport_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()), false);
        // 3. loadMore
        myReport_recyclerView.setOnLoadMoreListener(new AddOnScrollRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                loadMyReport(PER_PAGE, currentPage);
            }
        });

        loadMyReport(PER_PAGE, FIRST_PAGE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    private void loadMyReport(int perPage, int page) {

        // 1. 더 이상 불러올 데이터가 없는지 확인
        if (!hasMoreToLoad) return;

        // 2. 불러온다면 프로그래스바를 띄움
        // reportAdapter.setProgressMore(true);

        // 3. 스크롤에 의해서 다시 호출될 수 있기 때문에 로딩중임을 명시해 줌
        myReport_recyclerView.setIsLoading(true);


        MoldeRestfulService.Feed feedService
                = MoldeNetwork.getInstance().generateService(MoldeRestfulService.Feed.class);

        // TODO 로그인 할 때 받아놓고 없으면 로그인 페이지로 넘어가도록 해야 한다.
        Call<MoldeFeedResponseInfoEntityList> call = feedService.getMyFeed("lkj", perPage, page);

        call.enqueue(new Callback<MoldeFeedResponseInfoEntityList>() {
            @Override
            public void onResponse(Call<MoldeFeedResponseInfoEntityList> call, Response<MoldeFeedResponseInfoEntityList> response) {
                if (response.isSuccessful()) {


                    // 4. 호출 후 데이터 정리
                    List<MoldeFeedEntity> entities = fromSchemaToLocalEntity(response.body().getData());
                    // 6. 데이터 추가
                    reportAdapter.setData(entities);
                    // 7. 추가 완료 후 다음 페이지로 넘어가도록 세팅
                    currentPage++;
                    // 8. 더 이상 데이터를 세팅중이 아님을 명시
                    myReport_recyclerView.setIsLoading(false);
                    if (entities.size() < PER_PAGE) {
                        // Log.e("호출확인5", "magazine fragment");
                        setHasMoreToLoad(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<MoldeFeedResponseInfoEntityList> call, Throwable t) {
                Log.e("문제확인", t.getMessage());
            }
        });

    }

    private List<MoldeFeedEntity> fromSchemaToLocalEntity(List<MoldeFeedResponseInfoEntity> schemas) {
        List<MoldeFeedEntity> entities = new ArrayList<>();
        for (MoldeFeedResponseInfoEntity schema : schemas) {
            entities.add(new MoldeFeedEntity(
                    schema.getRepId(),
                    schema.getUserName(),
                    schema.getUserEmail(),
                    schema.getUserId(),
                    schema.getRepContents(),
                    schema.getRepLat(),
                    schema.getRepLon(),
                    schema.getRepAddr(),
                    schema.getRepDetailAddr(),
                    schema.getRepDate(),
                    schema.getRepImg(),
                    schema.getRepState()
            ));
        }
        return entities;
    }

    private void setHasMoreToLoad(boolean hasMore) {
        hasMoreToLoad = hasMore;
    }

    /**
     * TODO 생명주기 관리가 전혀 안 됨. 왜 이게 어쩔 때는 저장되었다가 어쩔 때는 원상태인지 파악이 안 됨
     */
    // 5. 생명주기
    @Override
    public void onPause() {
        super.onPause();
        setHasMoreToLoad(true);
        currentPage = 0;
    }

}