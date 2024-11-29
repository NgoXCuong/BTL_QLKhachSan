package dao;

import model.PhongModel;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongDao {
    public List<PhongModel> getAllPhong() {
        List<PhongModel> danhSachPhong = new ArrayList<>();
        String query = "SELECT * FROM PHONG";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhongModel phong = new PhongModel();
                phong.setMa(rs.getString("maPhong"));
                phong.setLoaiPhong(rs.getString("loaiPhong"));
                phong.setGiaPhong(rs.getInt("giaPhong"));
                phong.setTinhTrang(rs.getString("tinhTrang"));
                danhSachPhong.add(phong);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách phòng: " + e.getMessage());
        }
        return danhSachPhong;
    }

    public boolean addRoom(PhongModel phong) {
        String query = "INSERT INTO PHONG (MaPhong, LoaiPhong, GiaPhong, TinhTrang) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, phong.getMa());
            pstmt.setString(2, phong.getLoaiPhong());
            pstmt.setDouble(3, phong.getGiaPhong());
            pstmt.setString(4, phong.getTinhTrang());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRoom(String maPhong) {
        String query = "DELETE FROM PHONG WHERE MaPhong = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maPhong);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRoom(PhongModel phong) {
        String query = "UPDATE PHONG SET LoaiPhong = ?, GiaPhong = ?, TinhTrang = ? WHERE MaPhong = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            if (phong.getMa() == null || phong.getMa().trim().isEmpty()) {
                System.err.println("Mã phòng không được để trống!");
                return false;
            }

            psmt.setString(1, phong.getLoaiPhong());
            psmt.setDouble(2, phong.getGiaPhong());
            psmt.setString(3, phong.getTinhTrang());
            psmt.setString(4, phong.getMa());

            int affectedRows = psmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phòng: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<PhongModel> searchRoomByAttribute(String attribute, String value) {
        String query = "SELECT * FROM PHONG WHERE " + attribute + " = ?";
        List<PhongModel> resultList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, value);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PhongModel room = new PhongModel(
                            rs.getString("MaPhong"),
                            rs.getString("LoaiPhong"),
                            rs.getDouble("GiaPhong"),
                            rs.getString("TinhTrang")
                    );
                    resultList.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public double getGiaPhong(String maPhong) {
        String query = "SELECT GiaPhong FROM PHONG WHERE MaPhong = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, maPhong.trim());
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("GiaPhong");
                } else {
                    System.out.println("Không tìm thấy giá phòng cho mã phòng: " + maPhong);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean isRoomExist(String maPhong) {
        String query = "SELECT COUNT(*) FROM PHONG WHERE MaPhong = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, maPhong);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean phongInHoaDon(String maPhong) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT COUNT(*) FROM HOADON WHERE MaPhong = ?")) {
            statement.setString(1, maPhong);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
