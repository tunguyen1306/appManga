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
import com.example.tunguyen.manga.view.model.Preference;
import com.squareup.picasso.Picasso;
import java.util.List;
import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.ImgAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;


/**
 * Created by TuNguyen on 09/10/2016.
 */
public class AdvertFavoriteAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<AdvertMangas> AdvertDtos;
    TextView txt_tile;
    ViewHolder listViewHolder;

    public AdvertFavoriteAdapter(Context context, List<AdvertMangas> AdvertFavoriteListView) {
        this._Context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdvertDtos = AdvertFavoriteListView;

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
            if (convertView == null) {
                listViewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.item_list_favorite, parent, false);
                listViewHolder.txtNameAdvertFavorite = (TextView) convertView.findViewById(R.id.txtNameAdvertFavorite);
                listViewHolder.txtNameAuthorAdvertFavorite = (TextView) convertView.findViewById(R.id.txtNameAuthorAdvertFavorite);
                listViewHolder.imgAdvertFavorite = (ImageView) convertView.findViewById(R.id.imgAdvertFavorite);
                convertView.setTag(listViewHolder);
            } else {
                listViewHolder = (ViewHolder) convertView.getTag();
            }
            listViewHolder.txtNameAdvertFavorite.setText(AdvertDtos.get(position).NameAdvertManga);
            listViewHolder.txtNameAuthorAdvertFavorite.setText(AdvertDtos.get(position).NameAuthorAdvertManga);

            if (AdvertDtos.get(position).ImgAdvertManga != "") {
                Picasso.with(_Context).load(AdvertDtos.get(position).ImgAdvertManga).into(listViewHolder.imgAdvertFavorite);
            } else {
                Picasso.with(_Context).load(R.drawable.img_error).into(listViewHolder.imgAdvertFavorite);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation = new AlphaAnimation(0.3f, 1.0f);
                    animation.setDuration(1000);
                    v.startAnimation(animation);
                    IdAdvertRefer = AdvertDtos.get(position).getIdAdvertManga();
                    NameAdvertRefer = AdvertDtos.get(position).getNameAdvertManga();
                    ImgAdvertRefer = AdvertDtos.get(position).getImgAdvertManga();

                    Preference.savePreference(_Context.getApplicationContext());
                    Intent intent_login = new Intent(_Context, DetailAdvert.class);
                    intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    _Context.startActivity(intent_login);


                }
            });
        } catch (Exception ex) {

        }


        return convertView;
    }

    public class ViewHolder {
        TextView txtNameAdvertFavorite, txtNameAuthorAdvertFavorite;
        ImageView imgAdvertFavorite;
    }

}
