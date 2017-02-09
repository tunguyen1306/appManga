package com.example.tunguyen.manga.view.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public  class AdvertViewedMangas implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

@DatabaseField(generatedId = true, columnName = "Id")
public int Id;

    public int getIdAdvertManga() {
        return IdAdvertManga;
    }

    public void setIdAdvertManga(int idAdvertManga) {
        IdAdvertManga = idAdvertManga;
    }

    public String getNameAdvertManga() {
        return NameAdvertManga;
    }

    public void setNameAdvertManga(String nameAdvertManga) {
        NameAdvertManga = nameAdvertManga;
    }

    public String getImgAdvertManga() {
        return ImgAdvertManga;
    }

    public void setImgAdvertManga(String imgAdvertManga) {
        ImgAdvertManga = imgAdvertManga;
    }

    public String getNameChapManga() {
        return NameChapManga;
    }

    public void setNameChapManga(String nameChapManga) {
        NameChapManga = nameChapManga;
    }

    public int getIdChapterManga() {
        return IdChapterManga;
    }

    public void setIdChapterManga(int idChapterManga) {
        IdChapterManga = idChapterManga;
    }

    public String getTimeUpdatedChapterManga() {
        return TimeUpdatedChapterManga;
    }

    public void setTimeUpdatedChapterManga(String timeUpdatedChapterManga) {
        TimeUpdatedChapterManga = timeUpdatedChapterManga;
    }

    public int getPositionItemChapterManga() {
        return PositionItemChapterManga;
    }

    public void setPositionItemChapterManga(int positionItemChapterManga) {
        PositionItemChapterManga = positionItemChapterManga;
    }

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

    @DatabaseField(columnName = "PositionItemChapterManga")
    public int PositionItemChapterManga;


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
