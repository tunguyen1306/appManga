package com.example.tunguyen.manga.view.database;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Chaptermangas implements Serializable {

    private static final long serialVersionUID = -222864131214757025L;

    @DatabaseField(generatedId = true, columnName = "Id")
    public int Id;

    @DatabaseField(columnName = "IdChapterManga")
    public int IdChapterManga;

    @DatabaseField(columnName = "NameChapterManga")
    public String NameChapterManga;

    @DatabaseField(columnName = "Link")
    public String Link;

    @DatabaseField(columnName = "IdAdvertManga")
    public int IdAdvertManga;



}
