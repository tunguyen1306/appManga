package com.example.tunguyen.manga.view.database;


import android.provider.BaseColumns;

public final class AdvertMangas {
    public  AdvertMangas(){}
    public static class _AdvertMangas implements BaseColumns{
        public static final String TABLE_NAME = "AdvertMangas";
        public static final String COLUMN_ID_ADVERT="IdAdvertManga";
        public static final String COLUMN_NAME_ADVERT="NameAdvertManga";
        public static final String COLUMN_IMG_ADVERT = "ImgAdvertManga";
    }

}
