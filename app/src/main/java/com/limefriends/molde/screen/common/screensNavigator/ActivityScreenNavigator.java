package com.limefriends.molde.screen.common.screensNavigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.limefriends.molde.screen.common.camera.CameraActivity;
import com.limefriends.molde.screen.controller.feed.detail.FeedDetailActivity;
import com.limefriends.molde.screen.controller.magazine.comment.CardNewsCommentActivity;
import com.limefriends.molde.screen.controller.magazine.detail.CardNewsDetailActivity;
import com.limefriends.molde.screen.controller.magazine.info.HowToDetectActivity;
import com.limefriends.molde.screen.controller.magazine.info.HowToRespondActivity;
import com.limefriends.molde.screen.controller.magazine.info.RecentMolcaInfoActivity;
import com.limefriends.molde.screen.controller.map.favorite.FavoriteActivity;
import com.limefriends.molde.screen.controller.map.report.ReportActivity;
import com.limefriends.molde.screen.controller.map.search.SearchLocationActivity;
import com.limefriends.molde.screen.controller.mypage.comment.MyCommentActivity;
import com.limefriends.molde.screen.controller.mypage.inquiry.InquiryActivity;
import com.limefriends.molde.screen.controller.mypage.login.LoginActivity;
import com.limefriends.molde.screen.controller.mypage.report.MyFeedActivity;
import com.limefriends.molde.screen.controller.mypage.scrap.ScrapActivity;
import com.limefriends.molde.screen.controller.mypage.settings.SettingsActivity;
import com.limefriends.molde.screen.controller.main.MoldeMainActivity;
import com.limefriends.molde.screen.view.tutorial.SubTutorialActivity;
import com.limefriends.molde.screen.view.tutorial.TutorialActivity;

import static com.limefriends.molde.common.Constant.Common.EXTRA_KEY_ACTIVITY_NAME;
import static com.limefriends.molde.common.Constant.Common.EXTRA_KEY_CARDNEWS_ID;
import static com.limefriends.molde.common.Constant.MyPage.INTENT_VALUE_MYCOMMENT;
import static com.limefriends.molde.common.Constant.MyPage.RC_SIGN_IN;
import static com.limefriends.molde.common.Constant.Scrap.INTENT_VALUE_SCRAP;
import static com.limefriends.molde.screen.controller.map.main.MapFragment.REQ_FAVORITE;
import static com.limefriends.molde.screen.controller.map.main.MapFragment.REQ_SEARCH_MAP;

public class ActivityScreenNavigator {

    private final Context mContext;

    public ActivityScreenNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toRecentMolcaInfoActivity() {
        RecentMolcaInfoActivity.start(mContext);
    }

    public void toHowToDetectActivity() {
        HowToDetectActivity.start(mContext);
    }

    public void toHowToRespondActivity() {
        HowToRespondActivity.start(mContext);
    }

    public void toCardNewsCommentActivity(int cardNewsId, String description) {
        CardNewsCommentActivity.start(mContext, cardNewsId, description);
    }

    public void toSettingsActivity() {
        SettingsActivity.start(mContext);
    }

    public void toInquiryActivity() {
        InquiryActivity.start(mContext);
    }

    public void toMyFeedActivity() {
        MyFeedActivity.start(mContext);
    }

    public void toMyCommentActivity() {
        MyCommentActivity.start(mContext);
    }

    public void toScrapActivity() {
        ScrapActivity.start(mContext);
    }

    public void toCardNewsDetailActivity(int newsId) {
        CardNewsDetailActivity.start(mContext, newsId);
    }

    public void toMoldeMainActivity() {
        MoldeMainActivity.start(mContext);
    }

    public void toSubTutorialActivity() {
        SubTutorialActivity.start(mContext);
    }

    public void toTutorialActivity() {
        TutorialActivity.start(mContext);
    }

    public void toFeedDetailActivity(int feedId) {
        FeedDetailActivity.start(mContext, feedId);
    }

    public void toReportActivity() {
        ReportActivity.start(mContext);
    }

    public void toSystemSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS,
                Uri.fromParts("package", mContext.getPackageName(), null));
        mContext.startActivity(intent);
    }

    // 아무리 생각해도 Activity 를 static 으로 넘겨주는 것은 너무 위험하다
    public void toCardNewsDetailActivity(Context context, int newsId) {
        Intent intent = new Intent(context, CardNewsDetailActivity.class);
        intent.putExtra(EXTRA_KEY_CARDNEWS_ID, newsId);
        intent.putExtra(EXTRA_KEY_ACTIVITY_NAME, INTENT_VALUE_MYCOMMENT);
        context.startActivity(intent);
    }

    public void toCardNewsDetailActivity(Activity activity, int newsId, int reqCode) {
        Intent intent = new Intent(activity, CardNewsDetailActivity.class);
        intent.putExtra(EXTRA_KEY_CARDNEWS_ID, newsId);
        intent.putExtra(EXTRA_KEY_ACTIVITY_NAME, INTENT_VALUE_SCRAP);
        activity.startActivityForResult(intent, reqCode);
    }

    public void toMoldeReportCameraActivity(Activity activity, int imageSeq, int size, int reqCode) {
        Intent intent = new Intent();
        intent.setClass(activity, CameraActivity.class);
        intent.putExtra("imageSeq", imageSeq);
        intent.putExtra("imageArraySize", size);
        activity.startActivityForResult(intent, reqCode);
    }

    public void toLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, RC_SIGN_IN);
    }

    public void toSearchLocationActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, SearchLocationActivity.class);
        activity.startActivityForResult(intent, REQ_SEARCH_MAP);
    }

    public void toFavoriteActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, FavoriteActivity.class);
        activity.startActivityForResult(intent, REQ_FAVORITE);
    }


}
