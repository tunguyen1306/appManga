package com.example.tunguyen.manga.view.model;

public class AdvertDto {
        public AdvertDto (String IdAdvertManga,String NameAdvertManga,String ImgAdvertManga,String NameAuthorAdvertManga, String num_update){
                this.IdAdvertManga=Integer.parseInt(IdAdvertManga);
                this.NameAdvertManga=NameAdvertManga;
                this.ImgAdvertManga=ImgAdvertManga;
                this.NameAuthorAdvertManga=NameAuthorAdvertManga;
                this.num_update=Integer.parseInt(num_update);
        }
        public AdvertDto (String IdAdvertManga,String NameAdvertManga,String ImgAdvertManga,String NameAuthorAdvertManga){
                this.IdAdvertManga=Integer.parseInt(IdAdvertManga);
                this.NameAdvertManga=NameAdvertManga;
                this.ImgAdvertManga=ImgAdvertManga;
                this.NameAuthorAdvertManga=NameAuthorAdvertManga;

        }
        public static  int IdAdvertRefer;
        public static  String NameAdvertRefer;
        public static int CountViewRefer;
        public  int IdAdvertManga;
        public  String NameAdvertManga;

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

        public  String NameAuthorAdvertManga;
        public  int StatusAdvertManga;
        public  int StatusChapAdvertManga;
        public  int CountChapAdvertManga;

        public String getImgAdvertManga() {
                return ImgAdvertManga;
        }

        public void setImgAdvertManga(String imgAdvertManga) {
                ImgAdvertManga = imgAdvertManga;
        }

        public String ImgAdvertManga;

        public String getDesAdvertManga() {
                return DesAdvertManga;
        }

        public void setDesAdvertManga(String desAdvertManga) {
                DesAdvertManga = desAdvertManga;
        }

        public  String DesAdvertManga;

        public String getTypeAdvertManga() {
                return TypeAdvertManga;
        }

        public void setTypeAdvertManga(String typeAdvertManga) {
                TypeAdvertManga = typeAdvertManga;
        }

        public  String TypeAdvertManga;
        public int getCountView() {
                return CountView;
        }

        public void setCountView(int countView) {
                CountView = countView;
        }

        public  int CountView;

        public int getnum_update() {
                return num_update;
        }

        public void setnum_update(int countUpdate) {
                num_update = countUpdate;
        }

        public  int num_update;

}
