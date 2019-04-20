package com.example.admin.myapplication.Object;

public class LichSu {
    public String search_chucvu;
    public String search_nganh;
    public String search_thanhpho;

    public LichSu() {

    }

    public LichSu(String search_chucvu, String search_nganh, String search_thanhpho) {
        this.search_chucvu = search_chucvu;
        this.search_nganh = search_nganh;
        this.search_thanhpho = search_thanhpho;
    }

    public String getSearch_chucvu() {
        return search_chucvu;
    }

    public String getSearch_nganh() {
        return search_nganh;
    }

    public String getSearch_thanhpho() {
        return search_thanhpho;
    }

    public void setSearch_chucvu(String search_chucvu) {
        this.search_chucvu = search_chucvu;
    }

    public void setSearch_nganh(String search_nganh) {
        this.search_nganh = search_nganh;
    }

    public void setSearch_thanhpho(String search_thanhpho) {
        this.search_thanhpho = search_thanhpho;
    }

    @Override
    public String toString() {
        return "LichSu{" +
                "search_chucvu='" + search_chucvu + '\'' +
                ", search_nganh='" + search_nganh + '\'' +
                ", search_thanhpho='" + search_thanhpho + '\'' +
                '}';
    }
}
