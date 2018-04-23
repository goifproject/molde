package com.limefriends.molde.menu_mypage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.limefriends.molde.MoldeMainActivity;
import com.limefriends.molde.R;

public class MoldeMyPageFragment extends Fragment implements MoldeMainActivity.onKeyBackPressedListener{

    public static MoldeMyPageFragment newInstance() {
        return new MoldeMyPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mypage_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MoldeMainActivity)context).setOnKeyBackPressedListener(this);
    }

    @Override
    public void onBackKey() {
    }

}
