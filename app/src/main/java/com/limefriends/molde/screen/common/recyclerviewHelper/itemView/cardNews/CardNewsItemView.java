package com.limefriends.molde.screen.common.recyclerviewHelper.itemView.cardNews;

import com.limefriends.molde.model.entity.cardNews.CardNewsEntity;
import com.limefriends.molde.screen.common.view.ObservableView;

public interface CardNewsItemView extends ObservableView<CardNewsItemView.Listener>{

    interface Listener {

        void onItemClicked(int itemId);
    }

    void bindCardNews(CardNewsEntity cardNews);
}
