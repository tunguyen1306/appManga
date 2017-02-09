package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by TuNguyen on 09/10/2016.
 */
public class AllAdvertAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<AdvertDto> AdvertDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public AllAdvertAdapter(Context context, List<AdvertDto> AllAdvertListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdvertDtos = AllAdvertListView;

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
        try {
            if(convertView == null)
            {
                listViewHolder = new ViewHolder();
                convertView=layoutInflater.inflate(R.layout.item_all_advert,parent,false);
                listViewHolder.txtNameAllAdvert=(TextView)convertView.findViewById(R.id.txtNameAllAdvert);
                listViewHolder.txtAddressAllAdvert=(TextView)convertView.findViewById(R.id.txtNameAuthorAllAdvert);
                listViewHolder.imgAllAdvert=(ImageView) convertView.findViewById(R.id.imgAllAdvert);
                convertView.setTag(listViewHolder);
            }
            else
            {
                listViewHolder = (ViewHolder)convertView.getTag();
            }
            listViewHolder.txtNameAllAdvert.setText(AdvertDtos.get(position).NameAdvertManga);
            listViewHolder.txtAddressAllAdvert.setText(AdvertDtos.get(position).NameAuthorAdvertManga);
            if(AdvertDtos.get(position).ImgAdvertManga !="")
            {
                Picasso.with(_Context).load(AdvertDtos.get(position).ImgAdvertManga).into(listViewHolder.imgAllAdvert);}
            else
            {
                Picasso.with(_Context).load(R.drawable.img_error).into(listViewHolder.imgAllAdvert);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        }
        catch (Exception ex)
        {

        }


        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameAllAdvert,txtAddressAllAdvert,txtPercentAllAdvert;
        ImageView imgAllAdvert;
    }
}
