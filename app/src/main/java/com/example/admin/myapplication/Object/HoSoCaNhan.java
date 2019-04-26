package com.example.admin.myapplication.Object;

public class HoSoCaNhan {

    private String hoTen;
    private String diaChi;
    private String gioiTinh;
    private String soDienThoai;

    public HoSoCaNhan( String hoTen, String diaChi, String gioiTinh, String soDienThoai) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
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

    @Override
    public String toString() {
        return "HoSoCaNhan{" +
                ", hoTen='" + hoTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", soDienThoai=" + soDienThoai +
                '}';
    }
}
