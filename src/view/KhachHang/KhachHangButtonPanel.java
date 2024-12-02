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
        setLayout(new GridLayout(1, 5, 26, 26));

        Font fontButton = new Font("Arial", Font.PLAIN, 20);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setFont(fontButton);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> addCustomer());

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setFont(fontButton);
        btnAdd.setFocusPainted(false);
        btnDelete.addActionListener(e -> deleteCustomer());

        JButton btnUpdate = new JButton("SỬA");
        btnUpdate.setFont(fontButton);
        btnAdd.setFocusPainted(false);
        btnUpdate.addActionListener(e -> updateCustomer());

        JButton btnSearch = new JButton("TÌM");
        btnSearch.setFont(fontButton);
        btnAdd.setFocusPainted(false);
        btnSearch.addActionListener(e -> searchCustomer());

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(fontButton);
        btnAdd.setFocusPainted(false);
        btnReset.addActionListener(e -> resetForm());

        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        add(btnSearch);
        add(btnReset);
    }

    private void addCustomer() {
        try {
            String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
            String tenKhachHang = formPanel.getJtfTenKhachHang().getText().trim();
            String cmnd = formPanel.getJtfCMND().getText().trim();
            String soDienThoai = formPanel.getJtfSoDienThoai().getText().trim();

            if (maKhachHang.isEmpty() || tenKhachHang.isEmpty() || cmnd.isEmpty() || soDienThoai.isEmpty()) {
                JOptionPane.showMessageDialog(null ,"Vui lòng nhập đầy đủ thông tin khách hàng!");
                return;
            }
            if (!cmnd.matches("\\d{9}|\\d{12}")) {
                JOptionPane.showMessageDialog(null,"CMND/CCCD phải là chuỗi số gồm 9 hoặc 12 chữ số!");
                return;
            }
            if (!soDienThoai.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null,"Số điện thoại phải là chuỗi số gồm 10 chữ số!");
                return;
            }
            if (khachHangController.isKhachHangExists(maKhachHang)) {
                JOptionPane.showMessageDialog(null,"Khách hàng với mã \"" + maKhachHang + "\" đã tồn tại!");
                return;
            }

            KhachHangModel newKhachHang = new KhachHangModel(maKhachHang, tenKhachHang, cmnd, soDienThoai);
            if (khachHangController.addKhachHang(newKhachHang)) {
                JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
                tablePanel.loadKhachHang();
            } else {
                JOptionPane.showMessageDialog(null,"Thêm khách hàng thất bại.");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCustomer() {
        try {
            String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
            if (maKhachHang.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Vui lòng nhập mã khách hàng cần xóa!");
                return;
            }
            if (!khachHangController.isKhachHangExists(maKhachHang)) {
                JOptionPane.showMessageDialog(null,"Mã khách hàng \"" + maKhachHang + "\" không tồn tại!");
                return;
            }
            if (khachHangController.isKhachHangInHoaDon(maKhachHang)) {
                JOptionPane.showMessageDialog(null,
                        "Không thể xóa khách hàng. Khách hàng này đang tồn tại trong hóa đơn.",
                        "Lỗi ràng buộc", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (khachHangController.deleteKhachHang(maKhachHang)) {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
                tablePanel.loadKhachHang();
            } else {
                JOptionPane.showMessageDialog(null,"Xóa khách hàng thất bại.");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void updateCustomer() {
        try {
            String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
            String tenKhachHang = formPanel.getJtfTenKhachHang().getText().trim();
            String cmnd = formPanel.getJtfCMND().getText().trim();
            String soDienThoai = formPanel.getJtfSoDienThoai().getText().trim();

            if (maKhachHang.isEmpty() || tenKhachHang.isEmpty() || cmnd.isEmpty() || soDienThoai.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin khách hàng!");
                return;
            }
            if (!cmnd.matches("\\d{9}|\\d{12}")) {
                JOptionPane.showMessageDialog(null,"CMND/CCCD phải là chuỗi số gồm 9 hoặc 12 chữ số!");
                return;
            }
            if (!soDienThoai.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null,"Số điện thoại phải là chuỗi số gồm 10 chữ số!");
                return;
            }
            if (!khachHangController.isKhachHangExists(maKhachHang)) {
                JOptionPane.showMessageDialog(null,"Khách hàng với mã \"" + maKhachHang + "\" không tồn tại!");
                return;
            }
            KhachHangModel updatedKhachHang = new KhachHangModel(maKhachHang, tenKhachHang, cmnd, soDienThoai);
            if (khachHangController.updateKhachHang(updatedKhachHang)) {
                JOptionPane.showMessageDialog(null, "Sửa khách hàng thành công!");
                tablePanel.loadKhachHang();
            } else {
                JOptionPane.showMessageDialog(null,"Sửa khách hàng thất bại.");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchCustomer() {
        try {
            String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
            String tenKhachHang = formPanel.getJtfTenKhachHang().getText().trim();
            String cmnd = formPanel.getJtfCMND().getText().trim();
            String soDienThoai = formPanel.getJtfSoDienThoai().getText().trim();
            List<KhachHangModel> found = null;

            if (maKhachHang.isEmpty() && tenKhachHang.isEmpty() && cmnd.isEmpty() && soDienThoai.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Vui lòng nhập ít nhất một thông tin để tìm kiếm.");
                return;
            }

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
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng phù hợp.");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi tìm kiếm", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        tablePanel.loadKhachHang();
    }
}
