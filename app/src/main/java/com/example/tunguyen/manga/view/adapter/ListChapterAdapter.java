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
import com.example.tunguyen.manga.view.model.Preference;
import com.example.tunguyen.manga.view.model.clsAllAdvertDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TuNguyen on 01/17/2017.
 */

public class ListChapterAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<clsAllAdvertDto> AllAdvertDto;
    TextView txt_tile;
    ViewHolder listViewHolder;
    public ListChapterAdapter(Context context, List<clsAllAdvertDto> ChapterListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AllAdvertDto = ChapterListView;

    }
    @Override
    public int getCount() {
        return AllAdvertDto.size();
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
            listViewHolder = new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_list_chapter,parent,false);
            listViewHolder.txtNameChapter=(TextView)convertView.findViewById(R.id.txtNameChapter);
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (ViewHolder)convertView.getTag();
        }
                 listViewHolder.txtNameChapter.setText(AllAdvertDto.get(position).ListChapterManga.get(position).NameChapterManga);



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_login=new Intent(_Context,DetailBrand.class);
//                BrandDto.idBrandPromotiom=brandDtos.get(position).getId_brand_promotiom();
//                BrandDto.NameBrandPromotiom = brandDtos.get(position).getName_brand_promotiom();
//                BrandDto.idCategory = brandDtos.get(position).getCategory_id_brand_promotion();
//                Preference.savePreference(_Context.getApplicationContext());
//                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                _Context.startActivity(intent_login);



            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameChapter,txtAddressChapter,txtPercentChapter;
        ImageView imgChapter;
    }
}
