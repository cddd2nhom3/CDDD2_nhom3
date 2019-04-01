package com.example.admin.myapplication.datamodels;

public class NguoiDung {
    private String hoTen;
    private String email;
    private String matKhau;
    private String diaChi;
    private String gioiTinh;
    private int soDienThoai;

    public NguoiDung(String hoTen, String email, String matKhau, String diaChi, String gioiTinh, int soDienThoai) {
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getEmail() {
        return email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
