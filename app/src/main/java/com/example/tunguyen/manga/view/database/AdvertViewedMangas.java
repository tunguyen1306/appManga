package com.example.tunguyen.manga.view.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public  class AdvertViewedMangas implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

@DatabaseField(generatedId = true, columnName = "Id")
public int Id;
    @DatabaseField(columnName = "IdAdvertManga")
    public int IdAdvertManga;

    @DatabaseField(columnName = "NameAdvertManga")
    public   String NameAdvertManga="NameAdvertManga";

    @DatabaseField(columnName = "ImgAdvertManga")
    public String ImgAdvertManga;

    @DatabaseField(columnName = "NameChapterManga")
    public String NameChapManga;

    @DatabaseField(columnName = "IdChapterManga")
    public int IdChapterManga;

    @DatabaseField(columnName = "TimeUpdatedChapterManga")
    public String TimeUpdatedChapterManga;
    public AdvertViewedMangas()
    {


    }

    public AdvertViewedMangas(int IdAdvertManga, String NameAdvertManga,String ImgAdvertManga,String NameChapManga,int IdChapterManga,String TimeUpdatedChapterManga)
    {

        this.IdAdvertManga=IdAdvertManga;
        this.NameAdvertManga=NameAdvertManga;
        this.ImgAdvertManga=ImgAdvertManga;
        this.NameChapManga=NameChapManga;
        this.IdChapterManga=IdChapterManga;
        this.TimeUpdatedChapterManga=TimeUpdatedChapterManga;
    }

}
