package com.limefriends.molde.screen.common.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.limefriends.molde.screen.magazine.main.view.CardNewsView;
import com.limefriends.molde.screen.magazine.main.view.CardNewsViewImpl;

import javax.annotation.Nullable;

public class ViewFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewFactory(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    public  <T extends ViewMvc> T newInstance(Class<T> viewClass, @Nullable ViewGroup container) {
        ViewMvc viewMvc;

        if (viewClass == CardNewsView.class) {
            viewMvc = new CardNewsViewImpl(mLayoutInflater, container);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + viewClass);
        }

        return (T) viewMvc;
    }

}
