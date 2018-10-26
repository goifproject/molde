package com.limefriends.molde.screen.magazine.main.view;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.limefriends.molde.R;
import com.limefriends.molde.screen.common.addOnListview.AddOnScrollRecyclerView;
import com.limefriends.molde.screen.common.addOnListview.OnLoadMoreListener;
import com.limefriends.molde.screen.common.views.BaseObservableView;
import com.limefriends.molde.screen.magazine.main.CardNewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardNewsViewImpl
        extends BaseObservableView<CardNewsView.Listener> implements CardNewsView {

    private AddOnScrollRecyclerView cardnews_recyclerView;
    private LinearLayout manual_new_molca;
    private LinearLayout manual_by_location;
    private LinearLayout manual_for_spreading;

    private CardNewsAdapter cardNewsAdapter;

    public CardNewsViewImpl(LayoutInflater inflater, ViewGroup parent) {

        setRootView(inflater.inflate(R.layout.fragment_cardnews, parent, false));

        setupViews();
    }

    private void setupViews() {

        cardnews_recyclerView = findViewById(R.id.cardnews_recyclerView);
        manual_new_molca = findViewById(R.id.manual_new_molca);
        manual_by_location = findViewById(R.id.manual_by_location);
        manual_for_spreading = findViewById(R.id.spread_inside);

        setupViewElevation();

        setupListeners();

        setupMagazineList();
    }

    private void setupViewElevation() {
        manual_new_molca.setElevation(8);
        manual_for_spreading.setElevation(8);
        manual_by_location.setElevation(8);
    }

    private void setupListeners() {
        manual_new_molca.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onNewMolcaInfoClicked();
            }
        });

        manual_by_location.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onHowToPreventClicked();
            }
        });

        manual_for_spreading.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onHowToRespondClicked();
            }
        });
    }

    private void setupMagazineList() {
        if (cardNewsAdapter == null) {
            cardNewsAdapter = new CardNewsAdapter(getContext());
        }

        cardnews_recyclerView.setAdapter(cardNewsAdapter);
        cardnews_recyclerView.setLayoutManager(
                new GridLayoutManager(getContext(), 2), true);
        cardnews_recyclerView.setOnLoadMoreListener(
                () -> {
                    for (Listener listener : getListeners()) {
                        listener.onLoadMore();
                    }
                });
    }
}
