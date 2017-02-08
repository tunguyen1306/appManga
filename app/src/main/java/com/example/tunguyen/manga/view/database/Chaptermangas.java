package com.example.tunguyen.manga.view.database;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class ChapterMangas implements Serializable {

    private static final long serialVersionUID = -222864131214757025L;

    @DatabaseField(generatedId = true, columnName = "Id")
    public int Id;

    @DatabaseField(columnName = "IdChapterManga")
    public int IdChapterManga;

    @DatabaseField(columnName = "NameChapterManga")
    public String NameChapterManga;

    @DatabaseField(columnName = "Link")
    public String Link;

    public int getCheckChapterManga() {
        return CheckChapterManga;
    }

    public void setCheckChapterManga(int checkChapterManga) {
        CheckChapterManga = checkChapterManga;
    }

    @DatabaseField(columnName = "CheckChapterManga")
    public int CheckChapterManga;

    public int getIdAdvertManga() {
        return IdAdvertManga;
    }

    public void setIdAdvertManga(int idAdvertManga) {
        IdAdvertManga = idAdvertManga;
    }

    public int getIdChapterManga() {
        return IdChapterManga;
    }

    public void setIdChapterManga(int idChapterManga) {
        IdChapterManga = idChapterManga;
    }

    public String getNameChapterManga() {
        return NameChapterManga;
    }

    public void setNameChapterManga(String nameChapterManga) {
        NameChapterManga = nameChapterManga;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    @DatabaseField(columnName = "IdAdvertManga")
    public int IdAdvertManga;

    public ChapterMangas(){

    }

    public ChapterMangas(final int IdChapterManga, final String NameChapterManga, final String Link, final int IdAdvertManga, final int CheckChapterManga){
        this.IdAdvertManga=IdAdvertManga;
        this.NameChapterManga = NameChapterManga;
        this.Link = Link;
        this.IdChapterManga = IdChapterManga;
        this.CheckChapterManga=CheckChapterManga;
    }

}
