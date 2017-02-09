package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.DetailAdvert;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;

/**
 * Created by ducthien on 18/01/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<AdvertMangas> AdvertDtos;
    TextView txt_tile;
    CustomAdapter.ViewHolder listViewHolder;
    public CustomAdapter(Context context, List<AdvertMangas> AdvertRelateListView) {
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
            public void onClick(View view) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);

                Intent intent_login=new Intent(_Context,DetailAdvert.class);
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                IdAdvertRefer=AdvertDtos.get(position).getIdAdvertManga();
                NameAdvertRefer =AdvertDtos.get(position).getNameAdvertManga();
                TypeAdvertRefer =AdvertDtos.get(position).getTypeAdvertManga();
                ImgAdvertRefer =AdvertDtos.get(position).getImgAdvertManga();
                Preference.CountView(AdvertDtos.get(position).getIdAdvertManga(),123);
                Preference.savePreference(_Context.getApplicationContext());
                _Context.startActivity(intent_login);

            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameAdvertRelate,txtAddressAdvertRelate,txtPercentAdvertRelate;
        ImageView imgAdvertRelate;
    }
}
