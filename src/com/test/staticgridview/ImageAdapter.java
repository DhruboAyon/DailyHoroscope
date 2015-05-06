package com.test.staticgridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
 
    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.icon_sign_teen_aquarius, R.drawable.icon_sign_teen_aries,
            R.drawable.icon_sign_teen_cancer, R.drawable.icon_sign_teen_capricorn,
            R.drawable.icon_sign_teen_gemini, R.drawable.icon_sign_teen_leo,
            R.drawable.icon_sign_teen_libra, R.drawable.icon_sign_teen_pisces,
            R.drawable.icon_sign_teen_sagittarius, R.drawable.icon_sign_teen_scorpio,
            R.drawable.icon_sign_teen_taurus, R.drawable.icon_sign_teen_virgo,
    };
 
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }
 
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setBackgroundColor(R.drawable.image_bg);
        imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
        return imageView;
    }
}