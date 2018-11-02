package com.limefriends.molde.screen.common.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.limefriends.molde.screen.common.dialog.DialogFactory;
import com.limefriends.molde.screen.common.dialog.DialogManager;
import com.limefriends.molde.screen.common.imageLoader.ImageLoader;
import com.limefriends.molde.screen.common.toastHelper.ToastHelper;
import com.limefriends.molde.screen.common.toolbar.NestedToolbar;
import com.limefriends.molde.screen.common.recyclerview.itemView.CardNewsCommentItemView;
import com.limefriends.molde.screen.common.recyclerview.itemView.FeedItemView;
import com.limefriends.molde.screen.common.recyclerview.itemView.FeedItemViewImpl;
import com.limefriends.molde.screen.feed.detail.view.FeedDetailView;
import com.limefriends.molde.screen.feed.detail.view.FeedDetailViewImpl;
import com.limefriends.molde.screen.feed.main.view.FeedView;
import com.limefriends.molde.screen.feed.main.view.FeedViewImpl;
import com.limefriends.molde.screen.magazine.comment.view.CardNewsCommentView;
import com.limefriends.molde.screen.common.recyclerview.itemView.CardNewsCommentItemViewImpl;
import com.limefriends.molde.screen.magazine.comment.view.CardNewsCommentViewImpl;
import com.limefriends.molde.screen.common.imagePager.itemView.ImagePagerView;
import com.limefriends.molde.screen.common.imagePager.itemView.ImagePagerViewImpl;
import com.limefriends.molde.screen.magazine.detail.view.CardNewsDetailView;
import com.limefriends.molde.screen.magazine.detail.view.CardNewsDetailViewImpl;
import com.limefriends.molde.screen.common.recyclerview.itemView.MolcaInfoItemView;
import com.limefriends.molde.screen.magazine.info.view.MolcaInfoView;
import com.limefriends.molde.screen.common.recyclerview.itemView.MolcaInfoItemViewImpl;
import com.limefriends.molde.screen.magazine.info.view.MolcaInfoViewImpl;
import com.limefriends.molde.screen.common.recyclerview.itemView.CardNewsItemView;
import com.limefriends.molde.screen.common.recyclerview.itemView.CardNewsItemViewImpl;
import com.limefriends.molde.screen.magazine.main.view.CardNewsView;
import com.limefriends.molde.screen.magazine.main.view.CardNewsViewImpl;

import javax.annotation.Nullable;

public class ViewFactory {

    private final LayoutInflater mLayoutInflater;
    private final ImageLoader mImageLoader;
    private final DialogFactory mDialogFactory;
    private final DialogManager mDialogManager;
    private final ToastHelper mToastHelper;

    public ViewFactory(LayoutInflater mLayoutInflater,
                       ImageLoader imageLoader,
                       DialogManager dialogManager,
                       DialogFactory dialogFactory,
                       ToastHelper toastHelper) {
        this.mLayoutInflater = mLayoutInflater;
        this.mImageLoader = imageLoader;
        this.mDialogFactory = dialogFactory;
        this.mDialogManager = dialogManager;
        this.mToastHelper = toastHelper;
    }

    public <T extends ViewMvc> T newInstance(Class<T> viewClass, @Nullable ViewGroup container) {

        ViewMvc viewMvc;

        if (viewClass == NestedToolbar.class) {
            viewMvc = new NestedToolbar(mLayoutInflater, container);
        }
        else if (viewClass == CardNewsView.class) {
            viewMvc = new CardNewsViewImpl(mLayoutInflater, container, this);
        }
        else if (viewClass == CardNewsItemView.class) {
            viewMvc = new CardNewsItemViewImpl(mLayoutInflater, container, mImageLoader);
        }
        else if (viewClass == CardNewsDetailView.class) {
            viewMvc = new CardNewsDetailViewImpl(
                    mLayoutInflater, container,this, mToastHelper, mDialogFactory, mDialogManager);
        }
        else if (viewClass == ImagePagerView.class) {
            viewMvc = new ImagePagerViewImpl(mLayoutInflater, container, mImageLoader);
        }
        else if (viewClass == MolcaInfoView.class) {
            viewMvc = new MolcaInfoViewImpl(mLayoutInflater, container, this);
        }
        else if (viewClass == MolcaInfoItemView.class) {
            viewMvc = new MolcaInfoItemViewImpl(mLayoutInflater, container, mImageLoader);
        }
        else if (viewClass == CardNewsCommentView.class) {
            viewMvc = new CardNewsCommentViewImpl(
                    mLayoutInflater, container, this, mToastHelper, mDialogFactory, mDialogManager);
        }
        else if (viewClass == CardNewsCommentItemView.class) {
            viewMvc = new CardNewsCommentItemViewImpl(mLayoutInflater, container);
        }
        else if (viewClass == FeedView.class) {
            viewMvc = new FeedViewImpl(mLayoutInflater, container, this);
        }
        else if (viewClass == FeedItemView.class) {
            viewMvc = new FeedItemViewImpl(
                    mLayoutInflater, container, mImageLoader, mDialogFactory, mDialogManager);
        }
        else if (viewClass == FeedDetailView.class) {
            viewMvc = new FeedDetailViewImpl(
                    mLayoutInflater, container, this, mDialogFactory, mDialogManager, mToastHelper);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + viewClass);
        }
        // noinspection unchecked
        return (T) viewMvc;
    }
}