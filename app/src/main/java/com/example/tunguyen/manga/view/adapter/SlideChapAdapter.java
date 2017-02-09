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
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 09/13/2016.
 */
public class SlideChapAdapter extends PagerAdapter {

    List<ChapterDto> list_slide;
    private LayoutInflater inflater;
    private Context context;
    public SlideChapAdapter(Context context, List<ChapterDto> list_slide) {
        this.context = context;
        this.list_slide=list_slide;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list_slide.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int  position) {
        View imageLayout = inflater.inflate(R.layout.slide_chap, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.SlideChap);
        list_slide.get(position).getLink();

            if(list_slide.get(position).getLink()!=""){
                Picasso.with(context).load(list_slide.get(position).getLink()).into(imageView);
            }
            else {
                Picasso.with(context).load(R.drawable.icon_home).into(imageView);
            }



        view.addView(imageLayout, 0);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation animation = new AlphaAnimation(0.3f, 1.0f);
//                animation.setDuration(1000);
//                v.startAnimation(animation);
//
//            }
//        });
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
