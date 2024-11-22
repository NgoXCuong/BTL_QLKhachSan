package view.KhachHang;

import controller.KhachHangController;
import model.KhachHangModel;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KhachHangButtonPanel extends JPanel {
    private KhachHangController khachHangController;
    private KhachHangFormPanel formPanel;
    private KhachHangTablePanel tablePanel;

    public KhachHangButtonPanel(KhachHangController controller, KhachHangFormPanel formPanel, KhachHangTablePanel tablePanel) {
        this.khachHangController = controller;
        this.formPanel = formPanel;
        this.tablePanel = tablePanel;
        setLayout(new GridLayout(1, 5, 10, 10));

        Font fontButton = new Font("Arial", Font.PLAIN, 20);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setFont(fontButton);
        btnAdd.setBackground(Color.GREEN);
        btnAdd.addActionListener(e -> addCustomer());

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setFont(fontButton);
        btnDelete.setBackground(Color.RED);
        btnDelete.addActionListener(e -> deleteCustomer());

        JButton btnUpdate = new JButton("SỬA");
        btnUpdate.setFont(fontButton);
        btnUpdate.setBackground(Color.CYAN);
        btnUpdate.addActionListener(e -> updateCustomer());

        JButton btnSearch = new JButton("TÌM");
        btnSearch.setFont(fontButton);
        btnSearch.setBackground(Color.ORANGE);
        btnSearch.addActionListener(e -> searchCustomer());

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(fontButton);
        btnReset.setBackground(Color.PINK);
        btnReset.addActionListener(e -> resetForm());

        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        add(btnSearch);
        add(btnReset);
    }

    private void addCustomer() {
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String tenKhachHang = formPanel.getJtfTenKhachHang().getText().trim();
        String cmnd = formPanel.getJtfCMND().getText().trim();
        String soDienThoai = formPanel.getJtfSoDienThoai().getText().trim();
        KhachHangModel newKhachHang = new KhachHangModel(maKhachHang, tenKhachHang, cmnd, soDienThoai);
        if (khachHangController.addKhachHang(newKhachHang)) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
            tablePanel.loadKhachHang();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại.");
        }
    }

    private void deleteCustomer() {
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        if (maKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng cần xóa");
            return;
        }

        if (khachHangController.deleteKhachHang(maKhachHang)) {
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
            tablePanel.loadKhachHang();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại.");
        }
    }

    private void updateCustomer() {
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String tenKhachHang = formPanel.getJtfTenKhachHang().getText().trim();
        String cmnd = formPanel.getJtfCMND().getText().trim();
        String soDienThoai = formPanel.getJtfSoDienThoai().getText().trim();
        KhachHangModel updatedKhachHang = new KhachHangModel(maKhachHang, tenKhachHang, cmnd, soDienThoai);
        if (khachHangController.updateKhachHang(updatedKhachHang)) {
            JOptionPane.showMessageDialog(this, "Sửa khách hàng thành công!");
            tablePanel.loadKhachHang();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa khách hàng thất bại.");
        }
    }

    private void searchCustomer() {
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String tenKhachHang = formPanel.getJtfTenKhachHang().getText().trim();
        String cmnd = formPanel.getJtfCMND().getText().trim();
        String soDienThoai = formPanel.getJtfSoDienThoai().getText().trim();
        List<KhachHangModel> found = null;

        if (!maKhachHang.isEmpty()) {
            found = khachHangController.searchKhachHang("MaKhachHang", maKhachHang);
        } else if (!tenKhachHang.isEmpty()) {
            found = khachHangController.searchKhachHang("TenKhachHang", tenKhachHang);
        } else if (!cmnd.isEmpty()) {
            found = khachHangController.searchKhachHang("CMND", cmnd);
        } else if (!soDienThoai.isEmpty()) {
            found = khachHangController.searchKhachHang("SoDienThoai", soDienThoai);
        }

        if (found != null && !found.isEmpty()) {
            tablePanel.clearTable();
            for (KhachHangModel khachHang : found) {
                tablePanel.addRowToTable(khachHang);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp.");
        }
    }

    private void resetForm() {
        tablePanel.loadKhachHang();
    }
}
