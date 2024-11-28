package controller;

import dao.HoaDonDao;
import model.HoaDonModel;
import model.PhongModel;
import utils.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HoaDonController {
    private static HoaDonDao hoaDonDao;
    private static PhongModel phongModel;

    public HoaDonController() {
        hoaDonDao = new HoaDonDao();
        phongModel = new PhongModel();
    }

    public List<HoaDonModel> getAllHoaDon() {
        return hoaDonDao.getAllHoaDon();
    }

    public boolean addHoaDon(HoaDonModel hoaDon) {
        return hoaDonDao.addHoaDon(hoaDon);
    }

    public boolean deleteHoaDon(String maHoaDon) {
        return hoaDonDao.deleteHoaDon(maHoaDon);
    }
    public static boolean updateHoaDon(HoaDonModel hoaDon) {
        return hoaDonDao.updateHoaDon(hoaDon);
    }

    public List<HoaDonModel> searchHoaDon(String attribute, String value) {
        return hoaDonDao.searchHoaDon(attribute, value);
    }

    public double getRevenueByDay(String day, String month, String year) {
        return hoaDonDao.getRevenueByDay(day, month, year);
    }

    public double getRevenueByMonth(String month, String year) {
        return hoaDonDao.getRevenueByMonth(month, year);
    }

    public double getRevenueByYear(String year) {
        return hoaDonDao.getRevenueByYear(year);
    }

    public double getTotalRevenue() {
        return hoaDonDao.getTotalRevenue();
    }

    public HoaDonModel getInvoiceById(String maHoaDon) {
        return hoaDonDao.getHoaDonById(maHoaDon);
    }

    public double getPhongGiaPhong(String maPhong) {
        return phongModel.getGiaPhong();
    }

    public double getGiaPhong(String maPhong) {
        String query = "SELECT GiaPhong FROM PHONG WHERE MaPhong = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, maPhong);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("GiaPhong");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public HoaDonModel getHoaDonById(String maHoaDon) {
        return hoaDonDao.getHoaDonById(maHoaDon); // Gọi DAO để lấy dữ liệu
    }
}
