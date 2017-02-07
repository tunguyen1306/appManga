package com.example.tunguyen.manga.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tunguyen.manga.R;

import com.example.tunguyen.manga.view.activity.DetailChapter;
import com.example.tunguyen.manga.view.database.ChapterMangas;
import com.example.tunguyen.manga.view.database.DatabaseHelper;
import com.example.tunguyen.manga.view.model.ChapterDto;
import com.example.tunguyen.manga.view.model.Preference;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import static com.example.tunguyen.manga.view.model.ChapterDto.IdChapterRefer;
import static com.example.tunguyen.manga.view.model.ChapterDto.NameChapterRefer;

/**
 * Created by TuNguyen on 01/17/2017.
 */

public class ListChapterAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _Context;
    List<ChapterMangas> ChapterDto;
    TextView txt_tile;
    ViewHolder listViewHolder;


    public ListChapterAdapter(Context context, List<ChapterMangas> ChapterListView) {
        this._Context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ChapterDto = ChapterListView;

    }
    @Override
    public int getCount() {
        return ChapterDto.size();
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

        listViewHolder.txtNameChapter.setText(ChapterDto.get(position).NameChapterManga);
        if (ChapterDto.get(position).CheckChapterManga==1)
        {
           // listViewHolder.txtNameChapter.setTextColor(_Context);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login=new Intent(_Context,DetailChapter.class);
                intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                IdChapterRefer=ChapterDto.get(position).getIdChapterManga();
                NameChapterRefer =ChapterDto.get(position).getNameChapterManga();
                Preference.savePreference(_Context.getApplicationContext());
                _Context.startActivity(intent_login);
            }
        });
        return convertView;
    }
    public  class  ViewHolder{
        TextView txtNameChapter;
    }

}
