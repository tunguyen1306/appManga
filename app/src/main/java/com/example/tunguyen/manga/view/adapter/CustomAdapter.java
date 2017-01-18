package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducthien on 18/01/2017.
 */

public class CustomAdapter extends BaseAdapter{
    private Context mContext;
    private final ArrayList<String> NameAdvert;
    private final ArrayList<String> ImgAdvert;

    public CustomAdapter(Context c, ArrayList<String> NameAdvert, ArrayList<String> ImgAdvert ) {
        mContext = c;
        this.ImgAdvert = ImgAdvert;
        this.NameAdvert = NameAdvert;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return NameAdvert.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_gridview, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(NameAdvert.get(position));
            if(ImgAdvert.get(position)!=""){
                Picasso.with(mContext).load(ImgAdvert.get(position)).into(imageView);
            }
            else {
                Picasso.with(mContext).load(R.drawable.img_error).into(imageView);
            }


        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}