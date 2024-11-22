package controller;

import dao.PhongDao;
import model.PhongModel;
import java.util.List;

public class PhongController {
    private static PhongDao phongDao;

    public PhongController() {
        phongDao = new PhongDao();
    }

    // Lấy tất cả các phòng từ PhongDao
    public List<PhongModel> getAllRooms() {
        return phongDao.getAllPhong();
    }

    // Thêm phòng mới vào cơ sở dữ liệu
    public boolean addRoom(PhongModel phong) {
        return phongDao.addRoom(phong);
    }

    // Xóa phòng
    public boolean deleteRoom(String maPhong) {
        return phongDao.deleteRoom(maPhong);
    }

    // Cập nhật thông tin phòng
    public boolean updateRoom(PhongModel phong) {
        return phongDao.updateRoom(phong);  // Sửa gọi hàm
    }

    // Tìm kiếm phòng theo thuộc tính
    public List<PhongModel> searchRoomByAttribute(String attribute, String value) {
        return phongDao.searchRoomByAttribute(attribute, value);
    }

    // Kiểm tra phòng có trong hóa đơn không
    public boolean phongInHoaDon(String maPhong) {
        return phongDao.phongInHoaDon(maPhong);
    }

    public boolean isRoomExist(String maPhong) {
        return phongDao.isRoomExist(maPhong); // Gọi phương thức từ lớp DAO
    }

}
