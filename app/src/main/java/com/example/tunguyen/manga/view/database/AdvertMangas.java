package com.example.tunguyen.manga.view.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public  class AdvertMangas implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;
//    private static final String IdAdvertManga="IdAdvertManga";

//    private static final String ImgAdvertManga="ImgAdvertManga";
//    private static final int checkAdvertManga=0;
@DatabaseField(generatedId = true, columnName = "Id")
public int Id;

    @DatabaseField(columnName = "IdAdvertManga")
    public int IdAdvertManga;

    @DatabaseField(columnName = "NameAdvertManga")
    public   String NameAdvertManga="NameAdvertManga";

    @DatabaseField(columnName = "ImgAdvertManga")
    public String ImgAdvertManga;
    @DatabaseField( columnName = "CheckAdvertManga")
    public int CheckAdvertManga;
public AdvertMangas(){

}
    //For our own purpose, so it's easier to create a TeacherDetails object
    public AdvertMangas(final int IdAdvertManga,final String NameAdvertManga,final String ImgAdvertManga,final int CheckAdvertManga){
        this.IdAdvertManga=IdAdvertManga;
        this.NameAdvertManga = NameAdvertManga;
        this.ImgAdvertManga = ImgAdvertManga;
        this.CheckAdvertManga = CheckAdvertManga;
    }
}
