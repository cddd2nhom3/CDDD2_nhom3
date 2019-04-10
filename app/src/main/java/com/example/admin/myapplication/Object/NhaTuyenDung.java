package com.example.admin.myapplication.Object;

public class NhaTuyenDung {
    private String tenDoanhNghiep;
    private String email;
    private String matKhau;
    private String diaChi;
    private int soDienThoai;

    public NhaTuyenDung(String tenDoanhNghiep, String email, String matKhau, String diaChi, int soDienThoai) {
        this.tenDoanhNghiep = tenDoanhNghiep;
        this.email = email;
        this.matKhau = matKhau;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public String getTenDoanhNghiep() {
        return tenDoanhNghiep;
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

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setTenDoanhNghiep(String tenDoanhNghiep) {
        this.tenDoanhNghiep = tenDoanhNghiep;
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

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
