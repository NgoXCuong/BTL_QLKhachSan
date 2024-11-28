package model;

import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonModel extends QLKSModel{
    private String maKhachHang;
    private String maPhong;
    private Date ngayNhanPhong;
    private Date ngayTraPhong;
    private int soGio;
    private double tongTien;

    public HoaDonModel() {
    }

    public HoaDonModel(String maHoaDon, String maKhachHang, String maPhong, Date ngayNhanPhong, Date ngayTraPhong, int soGio, double tongTien) {
        super(maHoaDon);
        this.maKhachHang = maKhachHang;
        this.maPhong = maPhong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.soGio = soGio;
        this.tongTien = tongTien;
    }

    public HoaDonModel(String maHoaDon, String maKhachHang, String maPhong, Date ngayNhan, Date ngayTra, int soGio) {
        super(maHoaDon);
        this.maKhachHang = maKhachHang;
        this.maPhong = maPhong;
        this.ngayNhanPhong = ngayNhan;
        this.ngayTraPhong = ngayTra;
        this.soGio = soGio;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public int getSoGio() {
        return soGio;
    }

    public void setSoGio(int soGio) {
        this.soGio = soGio;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
