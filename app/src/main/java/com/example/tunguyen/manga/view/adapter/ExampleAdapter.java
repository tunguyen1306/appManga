package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
import static com.example.tunguyen.manga.view.model.AdvertDto.TypeAdvertRefer;

public class ExampleAdapter extends CursorAdapter {

    private List<AdvertMangas> items;

    private TextView txtNameAdvertManga,txtNameAuthorAdvertRelate;
    ImageView imgAdvertRelate;
    Context _Context;
    public ExampleAdapter(Context context, Cursor cursor, List<AdvertMangas> items) {

        super(context, cursor, false);

        this.items = items;
        this._Context=context;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        txtNameAdvertManga.setText(items.get(cursor.getPosition()).NameAdvertManga);
        txtNameAuthorAdvertRelate.setText(items.get(cursor.getPosition()).NameAuthorAdvertManga);
        if (items.get(cursor.getPosition()).ImgAdvertManga != "") {
            Picasso.with(_Context).load(items.get(cursor.getPosition()).ImgAdvertManga).into(imgAdvertRelate);
        } else {
            Picasso.with(_Context).load(R.drawable.img_error).into(imgAdvertRelate);
        }

    }

    @Override
    public View newView(Context context,final Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_search, parent, false);

        txtNameAdvertManga = (TextView) view.findViewById(R.id.txtNameAdvertRelate);
        txtNameAuthorAdvertRelate = (TextView) view.findViewById(R.id.txtNameAuthorAdvertRelate);
        imgAdvertRelate = (ImageView) view.findViewById(R.id.imgAdvertRelate);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(1000);
                v.startAnimation(animation);
                Intent intent_login=new Intent(_Context,DetailAdvert.class);
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                IdAdvertRefer=items.get(cursor.getPosition()).getIdAdvertManga();
                NameAdvertRefer =items.get(cursor.getPosition()).getNameAdvertManga();
                TypeAdvertRefer =items.get(cursor.getPosition()).getTypeAdvertManga();
                ImgAdvertRefer =items.get(cursor.getPosition()).getImgAdvertManga();
                Preference.CountView(items.get(cursor.getPosition()).getIdAdvertManga(),123);
                Preference.savePreference(_Context.getApplicationContext());
                _Context.startActivity(intent_login);
            }
        });
        return view;

    }

}