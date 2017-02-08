package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tunguyen.manga.R;
import com.example.tunguyen.manga.view.activity.DetailAdvert;
import com.example.tunguyen.manga.view.database.AdvertMangas;
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.tunguyen.manga.view.model.AdvertDto.IdAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.NameAdvertRefer;
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;


public class AdvertPopularAdapter extends RecyclerView.Adapter<AdvertPopularAdapter.SimpleViewHolder> {
    private final Context mContext;
    List<AdvertMangas> _list = new ArrayList<>();
    String from_activity;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ln_view;
        ImageView image;
        TextView txtNameAdvert,txtAuthor;

        public SimpleViewHolder(View view) {
            super(view);
            ln_view = (LinearLayout) view.findViewById(R.id.ln_view);
            image = (ImageView) view.findViewById(R.id.imgPopular);
            txtNameAdvert = (TextView) view.findViewById(R.id.txtNameAdvertPopular);
            txtAuthor = (TextView) view.findViewById(R.id.txtAuthorPopular);
        }
    }

    public AdvertPopularAdapter(Context context, List<AdvertMangas> list, String from_activity) {
        mContext = context;
        this._list = list;
        this.from_activity = from_activity;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_advert_popular, parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        if (_list.get(position).getIdAdvertManga() == 0) {
            try {
                holder.image.setImageResource(R.drawable.ic_image);
            } catch (Exception ex) {
            }
        } else {
            if(_list.get(position).getImgAdvertManga()!="")
            {
                Picasso.with(mContext).load(_list.get(position).getImgAdvertManga()).into(holder.image);}
            else
            {
                Picasso.with(mContext).load(R.drawable.img_error).into(holder.image);
            }

        }
        holder.txtNameAdvert.setText(_list.get(position).getNameAdvertManga());
        holder.txtAuthor.setText(_list.get(position).getNameAuthorAdvertManga());
        holder.ln_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);

                Intent intent_login=new Intent(mContext,DetailAdvert.class);
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                IdAdvertRefer=_list.get(position).getIdAdvertManga();
                NameAdvertRefer =_list.get(position).getNameAdvertManga();
                TypeAdvertRefer =_list.get(position).getTypeAdvertManga();
                Preference.CountView(_list.get(position).getIdAdvertManga(),123);
                Preference.savePreference(mContext.getApplicationContext());
                mContext.startActivity(intent_login);


            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return _list.size();
        } catch (Exception ex) {
            return 0;
        }
    }
}
