//package controller;
//
//import model.KhachHangModel;
//import java.util.List;
//
//public class KhachHangController {
//    private static KhachHangModel khachHangModel;
//
//    public KhachHangController() {
//        khachHangModel = new KhachHangModel();
//    }
//
//    public List<Object[]> getAllKhachHang() {
//        return khachHangModel.getAllKhachHang();
//    }
//
//    public boolean addKhachHang(String maKhachHang, String tenKhachHang, String cmnd, String soDienThoai) {
//        return khachHangModel.addKhachHang(maKhachHang, tenKhachHang, cmnd, soDienThoai);
//    }
//
//    public static boolean deleteKhachHang(String maKhachHang) {
//        return khachHangModel.deleteKhachHang(maKhachHang);
//    }
//
//    public static boolean updateKhachHang(String maKhachHang, String tenKhachHang, String cmnd, String soDienThoai) {
//        return khachHangModel.updateKhachHang(maKhachHang, tenKhachHang, cmnd, soDienThoai, maKhachHang);
//    }
//
//    public List<Object[]> searchKhachHang(String attribute, String value) {
//        return khachHangModel.searchKhachHang(attribute, value);
//    }
//
//    public boolean khachHangInHoaDon(String maKhachHang) {
//        return khachHangModel.khachHangInHoaDon(maKhachHang);
//    }
//}
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

//    public boolean khachHangInHoaDon(String maKhachHang) {
//        return hoaDonDAO.isCustomerInInvoice(maKhachHang);
//    }
}
