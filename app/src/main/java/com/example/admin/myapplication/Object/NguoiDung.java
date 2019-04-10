package com.example.admin.myapplication.Object;

public class NguoiDung {
    private String uid;
    private String hoTen;
    private String email;
    private String matKhau;
    private String diaChi;
    private String gioiTinh;
    private int soDienThoai;

    public NguoiDung(String Uid, String hoTen, String email, String matKhau, String diaChi, String gioiTinh, int soDienThoai) {
        this.uid = Uid;
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
    }
    public String getUid() { return uid;}

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

    public void setUid(String uid) {
        this.uid = uid;
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
