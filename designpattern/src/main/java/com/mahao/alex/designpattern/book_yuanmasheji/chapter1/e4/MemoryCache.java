package com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e4;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.mahao.alex.designpattern.book_yuanmasheji.chapter1.e2.*;

/**
 * Created by Alex_MaHao on 2016/5/16.
 */
public class MemoryCache implements ImageCache {

    LruCache<String,Bitmap> mImageCache;

    public MemoryCache(){

        initImageCache();
    }

    private void initImageCache() {

        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);

        int cacheSize = maxMemory/4;

        mImageCache = new LruCache<String ,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()*value.getHeight()/1024;


            }
        };


    }

    public void put(String url ,Bitmap bitmap){
        mImageCache.put(url, bitmap);
    }


    public Bitmap get(String url){
        return mImageCache.get(url);
    }

}
