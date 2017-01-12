package com.example.tunguyen.manga.view.adapter;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideAdapter extends PagerAdapter {

    List<AdvertDto> listAdvert;
    private LayoutInflater inflater;
    private Context context;
    public SlideAdapter(Context context, List<AdvertDto> _listAdvert) {
        this.context = context;
        this.listAdvert=_listAdvert;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listAdvert.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int  position) {
        View imageLayout = inflater.inflate(R.layout.slide_image, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.SlideImage);
        listAdvert.get(position).getImgAdvert();
        if(listAdvert.get(position).getImgAdvert()!=null){
            Picasso.with(context).load(listAdvert.get(position).getImgAdvert()).into(imageView);
        }
        else {
            Picasso.with(context).load(R.drawable.icon_home).into(imageView);
        }


        view.addView(imageLayout, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                v.startAnimation(animation);

            }
        });
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}

