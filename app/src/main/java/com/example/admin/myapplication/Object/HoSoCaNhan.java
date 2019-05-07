package com.example.admin.myapplication.Object;

public class HoSoCaNhan {

    public String hoTen;
    public String diaChi;
    public String gioiTinh;
    public String soDienThoai;
    public String linkImage;

    public HoSoCaNhan( String hoTen, String diaChi, String gioiTinh, String soDienThoai, String linkImage) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.linkImage = linkImage;
    }


    public HoSoCaNhan() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    @Override
    public String toString() {
        return "HoSoCaNhan{" +
                "hoTen='" + hoTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", linkImage='" + linkImage + '\'' +
                '}';
    }
}
