package view.HoaDon;

import controller.HoaDonController;
import model.HoaDonModel;
import model.PhongModel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HoaDonButtonPanel extends JPanel {
    private HoaDonController hoaDonController;
    private HoaDonTablePanel tablePanel;
    private HoaDonFormPanel formPanel;
    private PhongModel phongContrtoller;

    public HoaDonButtonPanel(HoaDonController hoaDonController, HoaDonFormPanel formPanel, HoaDonTablePanel tablePanel) {
        this.hoaDonController = hoaDonController;
        this.tablePanel = tablePanel;
        this.formPanel = formPanel;

        setLayout(new GridLayout(1, 6, 26, 26));

        Font fontBtn = new Font("Arial", Font.PLAIN, 20);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(e -> add());

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnDelete.addActionListener(e -> delete());

        JButton btnUpdate = new JButton("SỬA");
        btnUpdate.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnUpdate.addActionListener(e -> update());

        JButton btnSearch = new JButton("TÌM");
        btnSearch.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnSearch.addActionListener(e -> search());

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnReset.addActionListener(e -> tablePanel.loadHoaDon());

        JButton btnPrint = new JButton("IN HÓA ĐƠN");
        btnPrint.setFont(fontBtn);
        btnAdd.setFocusPainted(false);
        btnPrint.addActionListener(e -> print());

        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        add(btnSearch);
        add(btnReset);
        add(btnPrint);
    }

    private void add() {
        try {
            String maHoaDon = formPanel.getJtfMaHoaDon().getText().trim();
            String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
            String maPhong = formPanel.getJtfMaPhong().getText().trim();
            String ngayNhanStr = formPanel.getJtfNgayNhanPhong().getText().trim();
            String ngayTraStr = formPanel.getJtfNgayTraPhong().getText().trim();
            String soGioStr = formPanel.getJtfSoGio().getText().trim();

            if (maHoaDon.isEmpty() || maKhachHang.isEmpty() || maPhong.isEmpty() ||
                    ngayNhanStr.isEmpty() || ngayTraStr.isEmpty() || soGioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date ngayNhan = dateFormat.parse(ngayNhanStr);
            Date ngayTra = dateFormat.parse(ngayTraStr);

            if (ngayTra.before(ngayNhan)) {
                JOptionPane.showMessageDialog(this, "Ngày trả phòng không thể trước ngày nhận phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int soGio = Integer.parseInt(soGioStr);
            if (soGio <= 0) {
                JOptionPane.showMessageDialog(this, "Số giờ phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double giaPhong = hoaDonController.getGiaPhong(maPhong);
            if (giaPhong == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giá phòng cho mã phòng này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double tongTien = soGio * giaPhong;
            HoaDonModel hoaDon = new HoaDonModel(maHoaDon, maKhachHang, maPhong, ngayNhan, ngayTra, soGio, tongTien);

            if (hoaDonController.addHoaDon(hoaDon)) {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công!");
                tablePanel.addRowToTable(hoaDon);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ParseException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delete() {
        String maHoaDon = formPanel.getJtfMaHoaDon().getText().trim();
        if (maHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn cần xóa");
            return;
        }

        try {
            boolean isDeleted = hoaDonController.deleteHoaDon(maHoaDon);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công.");
                tablePanel.loadHoaDon();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn thất bại.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void update() {
        String maHoaDon = formPanel.getJtfMaHoaDon().getText().trim();
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String ngayNhanStr = formPanel.getJtfNgayNhanPhong().getText().trim();
        String ngayTraStr = formPanel.getJtfNgayTraPhong().getText().trim();
        String soGioStr = formPanel.getJtfSoGio().getText().trim();

        if (maHoaDon.isEmpty() || maKhachHang.isEmpty() || maPhong.isEmpty()
        || ngayNhanStr.isEmpty() || ngayTraStr.isEmpty() || soGioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date ngayNhan = dateFormat.parse(ngayNhanStr);
            Date ngayTra = dateFormat.parse(ngayTraStr);

            if (ngayTra.before(ngayNhan)) {
                JOptionPane.showMessageDialog(this, "Ngày trả phòng không thể trước ngày nhận phòng!");
                return;
            }
            int soGio = Integer.parseInt(soGioStr);
            if (soGio <= 0) {
                JOptionPane.showMessageDialog(this, "Số giờ phải lớn hơn 0!");
                return;
            }

            HoaDonModel updatedHoaDon = new HoaDonModel(maHoaDon, maKhachHang, maPhong, ngayNhan, ngayTra, soGio);
            if (hoaDonController.updateHoaDon(updatedHoaDon)) {
                JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công!");
                tablePanel.loadHoaDon();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa hóa đơn thất bại.");
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày tháng không đúng định dạng (dd/MM/yyyy).");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số giờ phải là một số nguyên hợp lệ.");
        }
    }

    private void search() {
        String maHoaDon = formPanel.getJtfMaHoaDon().getText().trim();
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String ngayNhanStr = formPanel.getJtfNgayNhanPhong().getText().trim();
        String ngayTraStr = formPanel.getJtfNgayTraPhong().getText().trim();
        String soGioStr = formPanel.getJtfSoGio().getText().trim();
        List<HoaDonModel> found = null;

        try {
            if (!maHoaDon.isEmpty()) {
                found = hoaDonController.searchHoaDon("MaHoaDon", maHoaDon);
            } else if (!maKhachHang.isEmpty()) {
                found = hoaDonController.searchHoaDon("MaKhachHang", maKhachHang);
            } else if (!maPhong.isEmpty()) {
                found = hoaDonController.searchHoaDon("MaPhong", maPhong);
            } else if (!ngayNhanStr.isEmpty()) {
                found = hoaDonController.searchHoaDon("NgayNhanPhong", ngayNhanStr);
            } else if (!ngayTraStr.isEmpty()) {
                found = hoaDonController.searchHoaDon("NgayTraPhong", ngayTraStr);
            } else if (!soGioStr.isEmpty()) {
                found = hoaDonController.searchHoaDon("SoGio", soGioStr);
            }
            if (found != null && !found.isEmpty()) {
                tablePanel.clearTable();
                for (HoaDonModel hoaDon : found) {
                    tablePanel.addRowToTable(hoaDon);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void print() {
        JTable table = tablePanel.getTable();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để in!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringBuilder hoaDon = new StringBuilder();
        hoaDon.append("===========================================\n");
        hoaDon.append("            HÓA ĐƠN THANH TOÁN             \n");
        hoaDon.append("===========================================\n");
        hoaDon.append(String.format("%-30s: %s\n", "Ngày lập hóa đơn", java.time.LocalDate.now()));
        hoaDon.append("-----------------------------------------------------------------------\n");
        for (int i = 0; i < table.getColumnCount(); i++) {
            String columnName = table.getColumnName(i);
            Object value = table.getValueAt(selectedRow, i);
            hoaDon.append(String.format("%-30s: %s\n", columnName, value));
        }
        hoaDon.append("===========================================\n");
        hoaDon.append("                Xin cảm ơn                 \n");
        hoaDon.append("          Hẹn gặp lại lần sau!             \n");
        hoaDon.append("===========================================\n");
        inHoaDon(hoaDon.toString());
    }

    private void inHoaDon(String invoiceContent) {
        JTextArea textArea = new JTextArea(invoiceContent);
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        try {
            boolean complete = textArea.print();
            if (complete) {
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "In hóa đơn bị hủy!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi in hóa đơn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
