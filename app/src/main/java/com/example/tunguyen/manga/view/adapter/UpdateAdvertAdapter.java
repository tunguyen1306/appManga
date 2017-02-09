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
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;

/**
 * Created by ducthien on 18/01/2017.
 */

public class UpdateAdvertAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<AdvertDto> AdvertDtos;
    TextView txt_tile;
    UpdateAdvertAdapter.ViewHolder listViewHolder;
    public UpdateAdvertAdapter(Context context, List<AdvertDto> CountUpdateListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdvertDtos = CountUpdateListView;

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
            listViewHolder = new UpdateAdvertAdapter.ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_update_gridview,parent,false);
            listViewHolder.txtNameCountUpdate=(TextView)convertView.findViewById(R.id.txtNameCountUpdate);
            listViewHolder.txtAuthorCountUpdate=(TextView)convertView.findViewById(R.id.txtAuthorCountUpdate);
            listViewHolder.txtCountUpdate=(TextView)convertView.findViewById(R.id.txtCountUpdate);
            listViewHolder.imgCountUpdate=(ImageView) convertView.findViewById(R.id.imgCountUpdate);

            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (UpdateAdvertAdapter.ViewHolder)convertView.getTag();
        }
        listViewHolder.txtNameCountUpdate.setText(AdvertDtos.get(position).NameAdvertManga);
        listViewHolder.txtAuthorCountUpdate.setText(AdvertDtos.get(position).NameAuthorAdvertManga);
        String numUpdate=String.valueOf(AdvertDtos.get(position).num_update);
        if (AdvertDtos.get(position).num_update==0){
            listViewHolder.txtCountUpdate.setText("0");
        }else {
            listViewHolder.txtCountUpdate.setText(numUpdate);
        }

        if(AdvertDtos.get(position).ImgAdvertManga !="")
        {
            Picasso.with(_Context).load(AdvertDtos.get(position).ImgAdvertManga).into(listViewHolder.imgCountUpdate);}
        else
        {
            Picasso.with(_Context).load(R.drawable.img_error).into(listViewHolder.imgCountUpdate);
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
        TextView txtNameCountUpdate,txtAuthorCountUpdate,txtCountUpdate;
        ImageView imgCountUpdate;
    }
}
