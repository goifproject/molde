package com.limefriends.molde.ui.menu_map.main.card;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;

import static com.limefriends.molde.ui.menu_map.main.card.IReportCardPagerAdapter.MAX_ELEVATION_FACTOR;

public class PageChangeListener implements ViewPager.OnPageChangeListener {

    private ShadowTransformer.OnPageSelectedCallback callback;
    private IReportCardPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private boolean mScalingEnabled;

    public interface OnPageSelectedCallback {
        void applyReportCardInfo(int position);
    }

    public PageChangeListener(ShadowTransformer.OnPageSelectedCallback callback, IReportCardPagerAdapter mAdapter, ViewPager mViewPager) {
        this.callback = callback;
        this.mAdapter = mAdapter;
        this.mViewPager = mViewPager;

        mViewPager.addOnPageChangeListener(this);
    }

    public void setCallback(ShadowTransformer.OnPageSelectedCallback callback) {
        this.callback = callback;
    }

    public void enableScaling(boolean enable) {
        if (mScalingEnabled && !enable) {
            // shrink main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        } else if (!mScalingEnabled && enable) {
            // grow main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }
        mScalingEnabled = enable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseElevation();
        float realOffset;
        float mLastOffset = 0;
        boolean goingLeft = mLastOffset > positionOffset;

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        Log.e("onPageScrolled", "" + realCurrentPosition);

        CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (currentCard != null) {
            if (mScalingEnabled) {
                currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation
                    * (MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        Log.e("onPageScrolled", "" + nextPosition);

        CardView nextCard = mAdapter.getCardViewAt(nextPosition);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextCard != null) {
            if (mScalingEnabled) {
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation
                    * (MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }
        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("onPageSelected", "" + position);
        if (callback != null) {
            callback.applyReportCardInfo(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
