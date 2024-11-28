package view.Phong;

import controller.PhongController;
import model.KhachHangModel;
import model.PhongModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PhongButtonPanel extends JPanel {
    private PhongController phongController;
    private PhongFormPanel formPanel;
    private PhongTablePanel tablePanel;

    public PhongButtonPanel(PhongController phongController, PhongFormPanel phongForm, PhongTablePanel phongTable) {
        this.phongController = phongController;
        this.formPanel = phongForm;
        this.tablePanel = phongTable;
        setLayout(new GridLayout(1, 5, 10, 10));

        Font fontBtn = new Font("Arial", Font.PLAIN, 20);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setFont(fontBtn);
        btnAdd.setBackground(Color.GREEN);
        btnAdd.addActionListener(e -> addRoom());

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setFont(fontBtn);
        btnDelete.setBackground(Color.RED);
        btnDelete.addActionListener(e -> deleteRoom());

        JButton btnUpdate = new JButton("SỬA");
        btnUpdate.setFont(fontBtn);
        btnUpdate.setBackground(Color.CYAN);
        btnUpdate.addActionListener(e -> updateRoom());

        JButton btnSearch = new JButton("TÌM");
        btnSearch.setFont(fontBtn);
        btnSearch.setBackground(Color.ORANGE);
        btnSearch.addActionListener(e -> searchRoom());

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(fontBtn);
        btnReset.setBackground(Color.PINK);
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
            JOptionPane.showMessageDialog(this, "Mã phòng không thể để trống!");
            return;
        }

        double giaPhongValue = 0;
        try {
            giaPhongValue = Double.parseDouble(giaPhong);
            if (giaPhongValue <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phòng phải lớn hơn 0!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phòng không hợp lệ!");
            return;
        }

        try {
            if (phongController.isRoomExist(maPhong)) {
                JOptionPane.showMessageDialog(this, "Phòng này đã tồn tại, không thể thêm!");
                return;
            }

            PhongModel newPhong = new PhongModel(maPhong, loaiPhong, giaPhongValue, tinhTrang);

            if (phongController.addRoom(newPhong)) {
                JOptionPane.showMessageDialog(this, "Thêm phòng thành công!");
                tablePanel.loadPhong();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm phòng thất bại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private void deleteRoom() {
        String maPhong = formPanel.getJtfMaPhong().getText();
        if (maPhong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phòng cần xóa");
            return;
        }

        if (phongController.deleteRoom(maPhong)) {
            JOptionPane.showMessageDialog(this, "Xóa phòng thành công.");
            tablePanel.loadPhong();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa phòng thất bại.");
        }

    }

    private void updateRoom() {
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String loaiPhong = (String) formPanel.getJcbLoaiPhong().getSelectedItem();
        String giaPhong = formPanel.getJtfGiaPhong().getText();
        String tinhTrang = (String) formPanel.getJcbTinhTrang().getSelectedItem();

        if (maPhong == null || maPhong.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phòng không được để trống.");
            return;
        }

        try {
            double giaPhongDouble = Double.parseDouble(giaPhong);

            if (giaPhongDouble <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phòng phải lớn hơn 0.");
                return;
            }

            PhongModel updatePhong = new PhongModel(maPhong, loaiPhong, giaPhongDouble, tinhTrang);

            if (phongController.updateRoom(updatePhong)) {
                JOptionPane.showMessageDialog(this, "Sửa phòng thành công!");
                tablePanel.loadPhong();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa phòng thất bại.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá phòng không hợp lệ. Vui lòng nhập một số.");
        }
    }



    private void searchRoom() {
        String maPhong = formPanel.getJtfMaPhong().getText();
        String loaiPhong = (String) formPanel.getJcbLoaiPhong().getSelectedItem();
        String giaPhong = formPanel.getJtfGiaPhong().getText();
        String tinhTrang = (String) formPanel.getJcbTinhTrang().getSelectedItem();

        List<PhongModel> found = null;

        if(!maPhong.isEmpty()) {
            found = phongController.searchRoomByAttribute("MaPhong", maPhong);
        } else if(loaiPhong.isEmpty()) {
            found = phongController.searchRoomByAttribute("LoaiPhong", loaiPhong);
        } else if(giaPhong.isEmpty()) {
            found = phongController.searchRoomByAttribute("GiaPhong", giaPhong);
        } else if(tinhTrang.isEmpty()) {
            found = phongController.searchRoomByAttribute("TinhTrang", tinhTrang);
        }

        if (found != null && !found.isEmpty()) {
            tablePanel.clearTable();
            for (PhongModel phong : found) {
                tablePanel.addRowToTable(phong);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp.");
        }
    }

    private void resetForm() {
        tablePanel.loadPhong();
    }
}
