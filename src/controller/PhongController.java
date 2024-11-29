package controller;

import dao.PhongDao;
import model.PhongModel;
import java.util.List;

public class PhongController {

    private static PhongDao phongDao;

    public PhongController() {
        phongDao = new PhongDao();
    }

    public List<PhongModel> getAllRooms() {
        return phongDao.getAllPhong();
    }

    public boolean addRoom(PhongModel phong) {
        return phongDao.addRoom(phong);
    }

    public boolean deleteRoom(String maPhong) {
        return phongDao.deleteRoom(maPhong);
    }

    public boolean updateRoom(PhongModel phong) {
        return phongDao.updateRoom(phong);
    }

    public List<PhongModel> searchRoomByAttribute(String attribute, String value) {
        return phongDao.searchRoomByAttribute(attribute, value);
    }

    public boolean isRoomExist(String maPhong) {
        return phongDao.isRoomExist(maPhong);
    }

    public boolean phongInHoaDon(String maPhong) {
        return phongDao.phongInHoaDon(maPhong);
    }
}
