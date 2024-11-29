package controller;

import dao.KhachHangDao;
import model.KhachHangModel;
import java.util.List;

public class KhachHangController {

    private KhachHangDao khachHangDao;

    public KhachHangController() {
        this.khachHangDao = new KhachHangDao(); // Initialize the DAO directly
    }

    public List<KhachHangModel> getAllKhachHang() {
        return khachHangDao.getAllKhachHang();
    }

    public boolean addKhachHang(KhachHangModel khachHang) {
        return khachHangDao.addKhachHang(khachHang);
    }

    public boolean deleteKhachHang(String maKhachHang) {
        return khachHangDao.deleteKhachHang(maKhachHang);
    }

    public boolean updateKhachHang(KhachHangModel khachHang) {
        return khachHangDao.updateKhachHang(khachHang);
    }

    public List<KhachHangModel> searchKhachHang(String attribute, String value) {
        return khachHangDao.searchKhachHang(attribute, value);
    }

    public boolean isKhachHangExists(String maKhachHang) {
        return khachHangDao.checkKhachHangExists(maKhachHang);
    }

    public boolean isKhachHangInHoaDon(String maKhachHang) {
        return khachHangDao.isKhachHangInHoaDon(maKhachHang);
    }

}
