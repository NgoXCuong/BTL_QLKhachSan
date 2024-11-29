package dao;

import model.HoaDonModel;
import utils.DatabaseConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    public List<HoaDonModel> getAllHoaDon() {
        List<HoaDonModel> danhSachHoaDon = new ArrayList<>();
        String query = "SELECT * FROM HOADON";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                danhSachHoaDon.add(new HoaDonModel(
                        rs.getString("MaHoaDon"),
                        rs.getString("MaKhachHang"),
                        rs.getString("MaPhong"),
                        rs.getDate("NgayNhanPhong"),
                        rs.getDate("NgayTraPhong"),
                        rs.getInt("SoGio"),
                        rs.getDouble("TongTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }

    public boolean addHoaDon(HoaDonModel hoaDon) {
        String query = "INSERT INTO HOADON (MaHoaDon, MaKhachHang, MaPhong, NgayNhanPhong, NgayTraPhong, SoGio, tongTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            boolean isCustomerExist = checkCustomerExist(hoaDon.getMaKhachHang(), conn);
            if (!isCustomerExist) {
                System.out.println("Lỗi: Khách hàng với mã " + hoaDon.getMaKhachHang() + " không tồn tại!");
                return false;
            }

            psmt.setString(1, hoaDon.getMa());
            psmt.setString(2, hoaDon.getMaKhachHang());
            psmt.setString(3, hoaDon.getMaPhong());
            psmt.setDate(4, new java.sql.Date(hoaDon.getNgayNhanPhong().getTime()));
            psmt.setDate(5, new java.sql.Date(hoaDon.getNgayTraPhong().getTime()));
            psmt.setInt(6, hoaDon.getSoGio());
            psmt.setDouble(7, hoaDon.getTongTien());

            return psmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkCustomerExist(String maKhachHang, Connection conn) throws SQLException {
        String checkQuery = "SELECT * FROM KHACHHANG WHERE MaKhachHang = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, maKhachHang);
        ResultSet rs = checkStmt.executeQuery();
        return rs.next();
    }

    public boolean updateHoaDon(HoaDonModel hoaDon) {
        String query = "UPDATE HOADON SET MaKhachHang = ?, MaPhong = ?, NgayNhanPhong = ?, NgayTraPhong = ?, SoGio = ? WHERE MaHoaDon = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, hoaDon.getMaKhachHang());
            psmt.setString(2, hoaDon.getMaPhong());
            psmt.setDate(3, new java.sql.Date(hoaDon.getNgayNhanPhong().getTime()));
            psmt.setDate(4, new java.sql.Date(hoaDon.getNgayTraPhong().getTime()));
            psmt.setInt(5, hoaDon.getSoGio());
            psmt.setString(6, hoaDon.getMa());

            return psmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteHoaDon(String maHoaDon) {
        String query = "DELETE FROM HOADON WHERE MaHoaDon = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maHoaDon);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HoaDonModel> searchHoaDon(String column, String value) {
        List<HoaDonModel> resultList = new ArrayList<>();

        String query = "SELECT * FROM HoaDon WHERE " + column + " LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (column.equalsIgnoreCase("NgayNhanPhong") || column.equalsIgnoreCase("NgayTraPhong")) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date utilDate = sdf.parse(value); // Chuyển từ chuỗi thành java.util.Date
                    pstmt.setDate(1, new java.sql.Date(utilDate.getTime())); // Chuyển sang java.sql.Date
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Định dạng ngày không hợp lệ. Định dạng yêu cầu: dd/MM/yyyy.");
                }
            } else if (column.equalsIgnoreCase("SoGio") || column.equalsIgnoreCase("TongTien")) {
                pstmt.setString(1, value);
            } else {
                pstmt.setString(1, "%" + value + "%");
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HoaDonModel hoaDon = new HoaDonModel();
                    hoaDon.setMa(rs.getString("MaHoaDon"));
                    hoaDon.setMaKhachHang(rs.getString("MaKhachHang"));
                    hoaDon.setMaPhong(rs.getString("MaPhong"));
                    hoaDon.setNgayNhanPhong(rs.getDate("NgayNhanPhong"));
                    hoaDon.setNgayTraPhong(rs.getDate("NgayTraPhong"));
                    hoaDon.setSoGio(rs.getInt("SoGio"));
                    hoaDon.setTongTien(rs.getDouble("TongTien"));

                    resultList.add(hoaDon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi định dạng: " + e.getMessage());
        }
        return resultList;
    }

    public double getRevenueByDay(String day, String month, String year) {
        String query = """
                SELECT SUM(tongTien) FROM HOADON
                WHERE DAY(ngayNhanPhong) = ? AND MONTH(ngayNhanPhong) = ? AND YEAR(ngayNhanPhong) = ?
                AND DAY(ngayTraPhong) = ? AND MONTH(ngayTraPhong) = ? AND YEAR(ngayTraPhong) = ?
                """;
        return getRevenue(query, day, month, year, day, month, year);
    }

    public double getRevenueByMonth(String month, String year) {
        String query = """
                SELECT SUM(tongTien) FROM HOADON
                WHERE MONTH(ngayNhanPhong) <= ? AND YEAR(ngayNhanPhong) <= ?
                AND MONTH(ngayTraPhong) >= ? AND YEAR(ngayTraPhong) >= ?
                """;
        return getRevenue(query, month, year, month, year);
    }

    public double getRevenueByYear(String year) {
        String query = """
                SELECT SUM(tongTien) FROM HOADON
                WHERE YEAR(ngayNhanPhong) = ?
                """;
        return getRevenue(query, year);
    }

    public double getTotalRevenue() {
        String query = "SELECT SUM(tongTien) FROM HOADON";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total revenue: " + e.getMessage());
        }
        return 0.0;
    }

    private double getRevenue(String query, String... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
