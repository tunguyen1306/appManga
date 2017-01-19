package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducthien on 18/01/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<AdvertDto> AdvertDtos;
    TextView txt_tile;
    CustomAdapter.ViewHolder listViewHolder;
    public CustomAdapter(Context context, List<AdvertDto> AdvertRelateListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdvertDtos = AdvertRelateListView;

    }
    @Override
    public int getCount() {
        return AdvertDtos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            listViewHolder = new CustomAdapter.ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_gridview,parent,false);
            listViewHolder.txtNameAdvertRelate=(TextView)convertView.findViewById(R.id.txtNameAllAdvert);
            listViewHolder.txtAddressAdvertRelate=(TextView)convertView.findViewById(R.id.txtAuthorAllAdvertr);
            listViewHolder.imgAdvertRelate=(ImageView) convertView.findViewById(R.id.imgAllAdvert);
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (CustomAdapter.ViewHolder)convertView.getTag();
        }
        listViewHolder.txtNameAdvertRelate.setText(AdvertDtos.get(position).NameAdvertManga);
        listViewHolder.txtAddressAdvertRelate.setText(AdvertDtos.get(position).NameAuthorAdvertManga);
        if(AdvertDtos.get(position).ImgAdvertManga !="")
        {
            Picasso.with(_Context).load(AdvertDtos.get(position).ImgAdvertManga).resize(180, 180).into(listViewHolder.imgAdvertRelate);}
        else
        {
            Picasso.with(_Context).load(R.drawable.img_error).into(listViewHolder.imgAdvertRelate);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameAdvertRelate,txtAddressAdvertRelate,txtPercentAdvertRelate;
        ImageView imgAdvertRelate;
    }
}
