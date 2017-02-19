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
import android.widget.ListView;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.DetailAdvert;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class SearchAdvertAdapter extends BaseAdapter {

    public class ViewHolder {
        TextView txtNameAllAdvert,txtAddressAllAdvert,txtPercentAllAdvert;
        ImageView imgAllAdvert;


    }

    public List<AdvertDto> parkingList;

    public Context context;
    ArrayList<AdvertDto> arraylist;
    private ListView listView;
    private List<AdvertDto> AdvertDtos;
    private LayoutInflater layoutInflater;
    private SearchAdvertAdapter(List<AdvertDto> apps, Context context) {
        this.parkingList = apps;
        this.context = context;
        arraylist = new ArrayList<AdvertDto>();
        arraylist.addAll(parkingList);

    }

    @Override
    public int getCount() {
        return parkingList.size();
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


        ViewHolder listViewHolder;

        try {
            if(convertView == null)
            {
                listViewHolder = new SearchAdvertAdapter.ViewHolder();
                convertView=layoutInflater.inflate(R.layout.item_all_advert,parent,false);
                listViewHolder.txtNameAllAdvert=(TextView)convertView.findViewById(R.id.txtNameAllAdvert);
                listViewHolder.txtAddressAllAdvert=(TextView)convertView.findViewById(R.id.txtNameAuthorAllAdvert);
                listViewHolder.imgAllAdvert=(ImageView) convertView.findViewById(R.id.imgAllAdvert);
                convertView.setTag(listViewHolder);
            }
            else
            {
                listViewHolder = (SearchAdvertAdapter.ViewHolder)convertView.getTag();
            }
            listViewHolder.txtNameAllAdvert.setText(AdvertDtos.get(position).NameAdvertManga);
            listViewHolder.txtAddressAllAdvert.setText(AdvertDtos.get(position).NameAuthorAdvertManga);
            if(AdvertDtos.get(position).ImgAdvertManga !="")
            {
                Picasso.with(context).load(AdvertDtos.get(position).ImgAdvertManga).into(listViewHolder.imgAllAdvert);}
            else
            {
                Picasso.with(context).load(R.drawable.img_error).into(listViewHolder.imgAllAdvert);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = new AlphaAnimation(0.3f, 1.0f);
                    animation.setDuration(1000);
                    v.startAnimation(animation);
                    Intent intent_login=new Intent(context,DetailAdvert.class);
                    intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    IdAdvertRefer=AdvertDtos.get(position).getIdAdvertManga();
                    NameAdvertRefer =AdvertDtos.get(position).getNameAdvertManga();
                    TypeAdvertRefer =AdvertDtos.get(position).getTypeAdvertManga();
                    ImgAdvertRefer =AdvertDtos.get(position).getImgAdvertManga();
                    Preference.CountView(AdvertDtos.get(position).getIdAdvertManga(),123);
                    Preference.savePreference(context.getApplicationContext());
                    context.startActivity(intent_login);


                }
            });
        }
        catch (Exception ex)
        {

        }
        return convertView;


    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        parkingList.clear();
        if (charText.length() == 0) {
            parkingList.addAll(arraylist);

        } else {
            for (AdvertDto postDetail : arraylist) {
                if (charText.length() != 0 && postDetail.getNameAdvertManga().toLowerCase(Locale.getDefault()).contains(charText)) {
                    parkingList.add(postDetail);
                } else if (charText.length() != 0 && postDetail.getTypeAdvertManga().toLowerCase(Locale.getDefault()).contains(charText)) {
                    parkingList.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }
}