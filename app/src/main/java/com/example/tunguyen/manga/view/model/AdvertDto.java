package com.example.tunguyen.manga.view.model;

public class AdvertDto {
        public AdvertDto (String IdAdvertManga,String NameAdvertManga,String ImgAdvertManga,String NameAuthorAdvertManga, String num_update,String CodeAdvertManga){
                this.IdAdvertManga=Integer.parseInt(IdAdvertManga);
                this.NameAdvertManga=NameAdvertManga;
                this.ImgAdvertManga=ImgAdvertManga;
                this.NameAuthorAdvertManga=NameAuthorAdvertManga;
                this.num_update=Integer.parseInt(num_update);
                this.CodeAdvertManga=CodeAdvertManga;
        }
        public AdvertDto (String IdAdvertManga,String NameAdvertManga,String ImgAdvertManga,String NameAuthorAdvertManga,String TypeAdvertManga,String StatusChapAdvertManga,String CodeAdvertManga){
                this.IdAdvertManga=Integer.parseInt(IdAdvertManga);
                this.NameAdvertManga=NameAdvertManga;
                this.ImgAdvertManga=ImgAdvertManga;
                this.NameAuthorAdvertManga=NameAuthorAdvertManga;
                this.TypeAdvertManga=TypeAdvertManga;
                this.StatusChapAdvertManga=Integer.parseInt(StatusChapAdvertManga);
                this.CodeAdvertManga=CodeAdvertManga;

        }
        public static  int IdAdvertRefer;
        public static  String NameAdvertRefer;
        public static  String TypeAdvertRefer;
        public static  String ImgAdvertRefer;
        public static int CountViewRefer;
        public static  int PositionItemChapterRefer;

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

        public String getCodeAdvertManga() {
                return CodeAdvertManga;
        }

        public void setCodeAdvertManga(String codeAdvertManga) {
                CodeAdvertManga = codeAdvertManga;
        }

        public  String CodeAdvertManga;
        public  int StatusAdvertManga;
        public  int StatusChapAdvertManga;
        public  int CountChapAdvertManga;

        public int getTypeStatusAdvertManga() {
                return TypeStatusAdvertManga;
        }

        public void setTypeStatusAdvertManga(int typeStatusAdvertManga) {
                TypeStatusAdvertManga = typeStatusAdvertManga;
        }

        public  int TypeStatusAdvertManga;
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



        public int getNum_update() {
                return num_update;
        }

        public void setNum_update(int num_update) {
                this.num_update = num_update;
        }

        public  int num_update;

}
