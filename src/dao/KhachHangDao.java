package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhachHangModel;
import utils.DatabaseConnection;


public class KhachHangDao {

    public List<KhachHangModel> getAllKhachHang() {
        List<KhachHangModel> khachHangList = new ArrayList<>();
        String query = "SELECT * FROM KHACHHANG";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel(
                        rs.getString("MaKhachHang"),
                        rs.getString("TenKhachHang"),
                        rs.getString("CMND"),
                        rs.getString("SoDienThoai")
                );
                khachHangList.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHangList;
    }

    public boolean addKhachHang(KhachHangModel khachHang) {
        String query = "INSERT INTO KHACHHANG (MaKhachHang, TenKhachHang, CMND, SoDienThoai) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, khachHang.getMa());
            pstmt.setString(2, khachHang.getTenKhachHang());
            pstmt.setString(3, khachHang.getCmnd());
            pstmt.setString(4, khachHang.getSoDienThoai());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKhachHang(String maKhachHang) {
        String query = "DELETE FROM KHACHHANG WHERE MaKhachHang = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maKhachHang);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKhachHang(KhachHangModel khachHang) {
        String query = "UPDATE KHACHHANG SET TenKhachHang = ?, CMND = ?, SoDienThoai = ? WHERE MaKhachHang = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, khachHang.getTenKhachHang());
            pstmt.setString(2, khachHang.getCmnd());
            pstmt.setString(3, khachHang.getSoDienThoai());
            pstmt.setString(4, khachHang.getMa());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<KhachHangModel> searchKhachHang(String attribute, String value) {
        String query = "SELECT * FROM KHACHHANG WHERE " + attribute + " = ?";
        List<KhachHangModel> resultList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, value);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    KhachHangModel kh = new KhachHangModel(
                            rs.getString("MaKhachHang"),
                            rs.getString("TenKhachHang"),
                            rs.getString("CMND"),
                            rs.getString("SoDienThoai")
                    );
                    resultList.add(kh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
