package view.Phong;

import controller.PhongController;
import model.PhongModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PhongButtonPanel extends JPanel {
    private PhongController phongController;
    private PhongFormPanel formPanel;
    private PhongTablePanel tablePanel;

    public PhongButtonPanel(PhongController phongController, PhongFormPanel phongForm, PhongTablePanel phongTable) {
        this.phongController = phongController;
        this.formPanel = phongForm;
        this.tablePanel = phongTable;
        setLayout(new GridLayout(1, 5, 26, 26));

        Font fontBtn = new Font("Arial", Font.PLAIN, 20);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> addRoom());

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnDelete.addActionListener(e -> deleteRoom());

        JButton btnUpdate = new JButton("SỬA");
        btnUpdate.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnUpdate.addActionListener(e -> updateRoom());

        JButton btnSearch = new JButton("TÌM");
        btnSearch.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnSearch.addActionListener(e -> searchRoom());

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnReset.addActionListener(e -> resetForm());

        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        add(btnSearch);
        add(btnReset);
    }

    private void addRoom() {
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String loaiPhong = (String) formPanel.getJcbLoaiPhong().getSelectedItem();
        String giaPhong = formPanel.getJtfGiaPhong().getText().trim();
        String tinhTrang = (String) formPanel.getJcbTinhTrang().getSelectedItem();

        if (maPhong.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã phòng không thể để trống!");
            return;
        }

        double giaPhongValue = 0;
        try {
            giaPhongValue = Double.parseDouble(giaPhong);
            if (giaPhongValue <= 0) {
                JOptionPane.showMessageDialog(null, "Giá phòng phải lớn hơn 0!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Giá phòng không hợp lệ!");
            return;
        }

        try {
            if (phongController.isRoomExist(maPhong)) {
                JOptionPane.showMessageDialog(null, "Phòng này đã tồn tại, không thể thêm!");
                return;
            }

            PhongModel newPhong = new PhongModel(maPhong, loaiPhong, giaPhongValue, tinhTrang);

            if (phongController.addRoom(newPhong)) {
                JOptionPane.showMessageDialog(null, "Thêm phòng thành công!");
                tablePanel.loadPhong();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm phòng thất bại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private void deleteRoom() {
        try {
            String maPhong = formPanel.getJtfMaPhong().getText().trim();
            if (maPhong.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã phòng cần xóa");
                return;
            }

            if (phongController.phongInHoaDon(maPhong)) {
                JOptionPane.showMessageDialog(null,
                        "Không thể xóa phòng. Phòng này đang tồn tại trong hóa đơn.",
                        "Lỗi ràng buộc", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean isDeleted = phongController.deleteRoom(maPhong);
            if (isDeleted) {
                JOptionPane.showMessageDialog(null, "Xóa phòng thành công.");
                tablePanel.loadPhong();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa phòng thất bại. Phòng không tồn tại hoặc có lỗi khác.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void updateRoom() {
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String loaiPhong = (String) formPanel.getJcbLoaiPhong().getSelectedItem();
        String giaPhong = formPanel.getJtfGiaPhong().getText();
        String tinhTrang = (String) formPanel.getJcbTinhTrang().getSelectedItem();

        if (maPhong == null || maPhong.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã phòng không được để trống.");
            return;
        }

        try {
            double giaPhongDouble = Double.parseDouble(giaPhong);

            if (giaPhongDouble <= 0) {
                JOptionPane.showMessageDialog(null, "Giá phòng phải lớn hơn 0.");
                return;
            }

            PhongModel updatePhong = new PhongModel(maPhong, loaiPhong, giaPhongDouble, tinhTrang);

            if (phongController.updateRoom(updatePhong)) {
                JOptionPane.showMessageDialog(null, "Sửa phòng thành công!");
                tablePanel.loadPhong();
            } else {
                JOptionPane.showMessageDialog(null, "Sửa phòng thất bại.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Giá phòng không hợp lệ. Vui lòng nhập một số.");
        }
    }

    private void searchRoom() {
        String maPhong = formPanel.getJtfMaPhong().getText();
        String loaiPhong = (String) formPanel.getJcbLoaiPhong().getSelectedItem();
        String giaPhong = formPanel.getJtfGiaPhong().getText();
        String tinhTrang = (String) formPanel.getJcbTinhTrang().getSelectedItem();

        List<PhongModel> found = null;

        try {
            if (!maPhong.isEmpty()) {
                found = phongController.searchRoomByAttribute("MaPhong", maPhong);
            } else if (!loaiPhong.isEmpty()) {
                found = phongController.searchRoomByAttribute("LoaiPhong", loaiPhong);
            } else if (!giaPhong.isEmpty()) {
                found = phongController.searchRoomByAttribute("GiaPhong", giaPhong);
            } else if (!tinhTrang.isEmpty()) {
                found = phongController.searchRoomByAttribute("TinhTrang", tinhTrang);
            }

            if (found != null && !found.isEmpty()) {
                tablePanel.clearTable();
                for (PhongModel phong : found) {
                    tablePanel.addRowToTable(phong);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy phòng phù hợp.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm phòng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        tablePanel.loadPhong();
    }
}
