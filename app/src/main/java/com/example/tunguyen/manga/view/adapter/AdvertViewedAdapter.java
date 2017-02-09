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
import com.example.tunguyen.manga.view.activity.DetailChapter;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.database.AdvertViewedMangas;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.PositionItemChapterRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;
import static com.example.tunguyen.manga.view.model.ChapterDto.IdChapterRefer;
import static com.example.tunguyen.manga.view.model.ChapterDto.NameChapterRefer;

/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertViewedAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<AdvertViewedMangas> AdvertDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public AdvertViewedAdapter(Context context, List<AdvertViewedMangas> AdvertViewedListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdvertDtos = AdvertViewedListView;

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
                convertView=layoutInflater.inflate(R.layout.item_list_viewed,parent,false);
                listViewHolder.txtNameAdvertViewed=(TextView)convertView.findViewById(R.id.txtNameAdvertViewed);
                listViewHolder.txtNameChapViewed=(TextView)convertView.findViewById(R.id.txtNameChapViewed);
                listViewHolder.imgAdvertViewed=(ImageView) convertView.findViewById(R.id.imgAdvertViewed);
                listViewHolder.txtTimeChapViewed=(TextView)convertView.findViewById(R.id.txtTimeChapViewed);
                convertView.setTag(listViewHolder);
            }
            else
            {
                listViewHolder = (ViewHolder)convertView.getTag();
            }
            listViewHolder.txtNameAdvertViewed.setText(AdvertDtos.get(position).NameAdvertManga);
            listViewHolder.txtNameChapViewed.setText(AdvertDtos.get(position).NameChapManga);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formattedDate = df.format(c.getTime());
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            try {

                Date date1 = simpleDateFormat.parse(formattedDate);
                Date date2 = simpleDateFormat.parse(AdvertDtos.get(position).TimeUpdatedChapterManga);

                listViewHolder.txtTimeChapViewed.setText(printDifference(date2, date1));

            } catch (ParseException e) {
                e.printStackTrace();
            }




            if(AdvertDtos.get(position).ImgAdvertManga !="")
            {
                Picasso.with(_Context).load(AdvertDtos.get(position).ImgAdvertManga).into(listViewHolder.imgAdvertViewed);}
            else
            {
                Picasso.with(_Context).load(R.drawable.img_error).into(listViewHolder.imgAdvertViewed);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    IdAdvertRefer=AdvertDtos.get(position).getIdAdvertManga();
                    NameAdvertRefer =AdvertDtos.get(position).getNameAdvertManga();
                    ImgAdvertRefer =AdvertDtos.get(position).getImgAdvertManga();
                    PositionItemChapterRefer=AdvertDtos.get(position).getPositionItemChapterManga();
                    IdChapterRefer=AdvertDtos.get(position).getIdChapterManga();
                    Preference.savePreference(_Context.getApplicationContext());
                    if (AdvertDtos.get(position).getPositionItemChapterManga()>0)
                    {
                        Intent intent_detailchapter=new Intent(_Context,DetailChapter.class);
                        intent_detailchapter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        _Context.startActivity(intent_detailchapter);
                    }
                    else {
                        Intent intent_login=new Intent(_Context,DetailAdvert.class);
                        intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        _Context.startActivity(intent_login);
                    }



                }
            });
        }
        catch (Exception ex)
        {

        }


        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameAdvertViewed,txtNameChapViewed,txtTimeChapViewed;
        ImageView imgAdvertViewed;
    }
    public String printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();


        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
        String t ="";

        if (elapsedSeconds>0)
        {
            t=elapsedSeconds+" giây trước";
        }
        if (elapsedMinutes>0)
        {
            t=elapsedMinutes+" phút trước";
        }
        if (elapsedHours>0)
        {
            t=elapsedHours+" giờ trước";
        }
        if (elapsedDays>0)
        {
            t=elapsedDays+" ngày trước";
        }
       return t;
    }
}
