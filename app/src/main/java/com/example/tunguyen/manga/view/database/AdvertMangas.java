package com.example.tunguyen.manga.view.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public  class AdvertMangas implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;
//    private static final String IdAdvertManga="IdAdvertManga";
//    private static final String NameAdvertManga="NameAdvertManga";
//    private static final String ImgAdvertManga="ImgAdvertManga";
//    private static final int checkAdvertManga=0;
    @DatabaseField(generatedId = true, columnName = "IdAdvertManga")
    public int IdAdvertManga;

    @DatabaseField(columnName = "NameAdvertManga")
    public String NameAdvertManga;

//    @DatabaseField(columnName = "ImgAdvertManga")
//    public String ImgAdvertManga;
//
//    @DatabaseField( columnName = "CheckAdvertManga")
//    public int CheckAdvertManga;

}
