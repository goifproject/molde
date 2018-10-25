package com.limefriends.molde.common.manager.cache_manager;


import android.util.Log;

class FileCacheNotFoundException extends Throwable {

    FileCacheNotFoundException(String format) {
        Log.e("CacheNotFoundE", format);
    }
}