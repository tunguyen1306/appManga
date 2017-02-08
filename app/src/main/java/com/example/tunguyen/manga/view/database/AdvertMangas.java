package com.example.tunguyen.manga.view.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public  class AdvertMangas implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

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

    @DatabaseField( columnName = "StatusChapAdvertManga")
    public int StatusChapAdvertManga;

    @DatabaseField( columnName = "StatusAdvertManga")
    public int StatusAdvertManga;

    @DatabaseField( columnName = "CountChapAdvertManga")
    public int CountChapAdvertManga;

    @DatabaseField(columnName = "NameAuthorAdvertManga")
    public String NameAuthorAdvertManga;

    @DatabaseField(columnName = "DesAdvertManga")
    public String DesAdvertManga;

    @DatabaseField(columnName = "TypeAdvertManga")
    public String TypeAdvertManga;

    @DatabaseField( columnName = "CountView")
    public int CountView;

    public int getTypeStatusAdvertManga() {
        return TypeStatusAdvertManga;
    }

    public void setTypeStatusAdvertManga(int typeStatusAdvertManga) {
        TypeStatusAdvertManga = typeStatusAdvertManga;
    }

    @DatabaseField( columnName = "TypeStatusAdvertManga")
    public int TypeStatusAdvertManga;

    public AdvertMangas(){

    }

    public AdvertMangas(final int IdAdvertManga,final String NameAdvertManga,final String ImgAdvertManga,final int CheckAdvertManga,int StatusChapAdvertManga,
                        int StatusAdvertManga,String NameAuthorAdvertManga,String DesAdvertManga,String TypeAdvertManga,int CountView,int TypeStatusAdvertManga){
        this.IdAdvertManga=IdAdvertManga;
        this.NameAdvertManga = NameAdvertManga;
        this.ImgAdvertManga = ImgAdvertManga;
        this.CheckAdvertManga = CheckAdvertManga;
        this.StatusChapAdvertManga = StatusChapAdvertManga;
        this.StatusAdvertManga = StatusAdvertManga;
        this.NameAuthorAdvertManga = NameAuthorAdvertManga;
        this.DesAdvertManga = DesAdvertManga;
        this.TypeAdvertManga = TypeAdvertManga;
        this.CountView = CountView;
        this.TypeStatusAdvertManga = TypeStatusAdvertManga;
    }

    public AdvertMangas(final int IdAdvertManga,final String NameAdvertManga,final String ImgAdvertManga,final int CheckAdvertManga){
        this.IdAdvertManga=IdAdvertManga;
        this.NameAdvertManga = NameAdvertManga;
        this.ImgAdvertManga = ImgAdvertManga;
        this.CheckAdvertManga = CheckAdvertManga;
    }


    public String getNameAuthorAdvertManga() {
        return NameAuthorAdvertManga;
    }

    public void setNameAuthorAdvertManga(String nameAuthorAdvertManga) {
        NameAuthorAdvertManga = nameAuthorAdvertManga;
    }

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

    public int getStatusAdvertManga() {
        return StatusAdvertManga;
    }

    public void setStatusAdvertManga(int statusAdvertManga) {
        StatusAdvertManga = statusAdvertManga;
    }

    public int getStatusChapAdvertManga() {
        return StatusChapAdvertManga;
    }

    public void setStatusChapAdvertManga(int statusChapAdvertManga) {
        StatusChapAdvertManga = statusChapAdvertManga;
    }

    public int getCountChapAdvertManga() {
        return CountChapAdvertManga;
    }

    public void setCountChapAdvertManga(int countChapAdvertManga) {
        CountChapAdvertManga = countChapAdvertManga;
    }


    public String getImgAdvertManga() {
        return ImgAdvertManga;
    }

    public void setImgAdvertManga(String imgAdvertManga) {
        ImgAdvertManga = imgAdvertManga;
    }
    public String getDesAdvertManga() {
        return DesAdvertManga;
    }

    public void setDesAdvertManga(String desAdvertManga) {
        DesAdvertManga = desAdvertManga;
    }



    public String getTypeAdvertManga() {
        return TypeAdvertManga;
    }

    public void setTypeAdvertManga(String typeAdvertManga) {
        TypeAdvertManga = typeAdvertManga;
    }


    public int getCountView() {
        return CountView;
    }

    public void setCountView(int countView) {
        CountView = countView;
    }





    public int getNum_update() {
        return num_update;
    }

    public void setNum_update(int num_update) {
        this.num_update = num_update;
    }

    public  int num_update;
}
