package com.limefriends.molde.menu_map.callback_method;

import com.limefriends.molde.menu_map.entity.MoldeSearchMapHistoryEntity;

public interface MoldeMapHistoryRecyclerViewAdapterCallback {
    void applyHistoryMapInfo(MoldeSearchMapHistoryEntity historyEntity, String cmd);
}