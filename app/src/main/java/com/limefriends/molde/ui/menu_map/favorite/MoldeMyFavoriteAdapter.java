package com.limefriends.molde.ui.menu_map.favorite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.limefriends.molde.R;
import com.limefriends.molde.entity.favorite.MoldeFavoriteEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoldeMyFavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MoldeFavoriteEntity> moldeMyFavoriteList = new ArrayList<>();
    private MyFavoriteAdapterCallBack myFavoriteAdapterCallBack;
    private int currentPosition;

    public interface MyFavoriteAdapterCallBack {
        void applyMyFavoriteMapInfo(MoldeFavoriteEntity entity);
        void onUnSelected(int favId);
    }

    public MoldeMyFavoriteAdapter(Context context) {
        this.context = context;
       // this.moldeMyFavoriteList = moldeMyFavoriteList;
    }

    public void setData(List<MoldeFavoriteEntity> data) {
        this.moldeMyFavoriteList = data;
        notifyDataSetChanged();
    }

    public static class MyFavoriteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_favorite_layout)
        RelativeLayout my_favorite_layout;
        @BindView(R.id.my_favorite_view_new)
        ImageView my_favorite_view_new;
        @BindView(R.id.my_favorite_view_title)
        TextView my_favorite_view_title;
        @BindView(R.id.my_favorite_view_info)
        TextView my_favorite_view_info;
        @BindView(R.id.my_favorite_view_toggle)
        ToggleButton my_favorite_view_toggle;

        public MyFavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.map_my_favorite_info_item, parent, false);
        return new MyFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyFavoriteViewHolder) {

            final MyFavoriteViewHolder viewHolder = (MyFavoriteViewHolder) holder;


            if (moldeMyFavoriteList.get(position).isActive()) {
                viewHolder.my_favorite_view_toggle.setChecked(true);
            } else {
                viewHolder.my_favorite_view_toggle.setChecked(false);
            }

            viewHolder.my_favorite_view_title.setText(moldeMyFavoriteList.get(position).getFavName());
            viewHolder.my_favorite_view_info.setText(moldeMyFavoriteList.get(position).getFavAddr());

            viewHolder.my_favorite_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myFavoriteAdapterCallBack.applyMyFavoriteMapInfo(moldeMyFavoriteList.get(position));
                }
            });

            viewHolder.my_favorite_view_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked) {

                        int favId = moldeMyFavoriteList.get(position).getFavId();

                        currentPosition = position;

                        myFavoriteAdapterCallBack.onUnSelected(favId);

                    }
                }
            });

//            viewHolder.my_favorite_view_toggle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!viewHolder.my_favorite_view_toggle.isChecked()) {
//                        viewHolder.my_favorite_view_toggle.setBackgroundResource(R.drawable.ic_star_off);
//                        moldeMyFavoriteList.get(position).setActive(false);
//                    } else if (viewHolder.my_favorite_view_toggle.isChecked()) {
//                        viewHolder.my_favorite_view_toggle.setBackgroundResource(R.drawable.ic_star_on);
//                        moldeMyFavoriteList.get(position).setActive(true);
//                    }
//                }
//            });
        }

    }

    public void notifyFavoriteRemoved() {
        moldeMyFavoriteList.remove(currentPosition);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return moldeMyFavoriteList.size();
    }

    public void setMoldeMyFavoriteAdapterCallBack(MyFavoriteAdapterCallBack myFavoriteAdapterCallBack) {
        this.myFavoriteAdapterCallBack = myFavoriteAdapterCallBack;
    }

}
