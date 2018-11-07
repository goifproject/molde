package com.limefriends.molde.common.util.comparator;


import com.limefriends.molde.model.entity.cardNews.CardNewsEntity;

import java.util.Comparator;

public class CardNewsComparator implements Comparator<CardNewsEntity> {

    @Override
    public int compare(CardNewsEntity entity, CardNewsEntity t1) {
        return Integer.compare(entity.getNewsId(), t1.getNewsId());
    }
}
