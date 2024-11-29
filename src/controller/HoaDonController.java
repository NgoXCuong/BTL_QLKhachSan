package controller;

import dao.HoaDonDao;
import dao.PhongDao;
import model.HoaDonModel;

import java.util.List;

public class HoaDonController {
    private static HoaDonDao hoaDonDao;
    private static PhongDao phongDao;

    public HoaDonController() {
        hoaDonDao = new HoaDonDao();
        phongDao = new PhongDao();
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

    public double getGiaPhong(String maPhong) {
        return phongDao.getGiaPhong(maPhong);
    }
}
