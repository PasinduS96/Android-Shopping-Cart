package com.example.pasindusenarathne.mobile_app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Pasindu Senarathne on 9/13/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slide1,R.drawable.slide2};


    @Override
    public int getCount() {
        return images.length;
    }

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(View view,Object object){

        return (view == (LinearLayout)object);
    }

    public Object instantiateItem(ViewPager container,int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customized_view, container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.sliderImageView);
        imageView.setImageResource(images[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView((LinearLayout)view);
    }


}
