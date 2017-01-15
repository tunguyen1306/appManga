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
import com.example.tunguyen.manga.view.model.AdvertDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuNguyen on 01/15/2017.
 */

public class ProjectFeaturedAdapter extends RecyclerView.Adapter<ProjectFeaturedAdapter.SimpleViewHolder> {
    private final Context mContext;
    List<AdvertDto> _list = new ArrayList<>();
    String from_activity;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ln_view;
        ImageView image;
        TextView title;
        TextView price;

        public SimpleViewHolder(View view) {
            super(view);
            ln_view = (LinearLayout) view.findViewById(R.id.ln_view);
            image = (ImageView) view.findViewById(R.id.gallery_img);
            title = (TextView) view.findViewById(R.id.gallery_title);
            price = (TextView) view.findViewById(R.id.gallery_price);
        }
    }

    public ProjectFeaturedAdapter(Context context, List<AdvertDto> list, String from_activity) {
        mContext = context;
        this._list = list;
        this.from_activity = from_activity;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_adapter, parent, false);

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
            Picasso.with(mContext).load(_list.get(position).getImgAdvertManga()).resize(180, 180).into(holder.image);
        }
        holder.title.setText(_list.get(position).getNameAdvertManga());
        //holder.price.setText(_list.get(position).getPercent_brand_promotiom());
        holder.ln_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);

//                Intent intent_login=new Intent(mContext,DetailBrand.class);
//                BrandDto.idBrandPromotiom=_list.get(position).getId_brand_promotiom();
//                BrandDto.NameBrandPromotiom = _list.get(position).getName_brand_promotiom();
//                BrandDto.idCategory = _list.get(position).getCategory_id_brand_promotion();
//                Preference.savePreference(mContext.getApplicationContext());
//                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent_login);


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
