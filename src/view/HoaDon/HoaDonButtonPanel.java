package view.HoaDon;

import controller.HoaDonController;
import model.HoaDonModel;
import model.KhachHangModel;
import model.PhongModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class HoaDonButtonPanel extends JPanel {
    private HoaDonController hoaDonController;
    private HoaDonTablePanel tablePanel;
    private HoaDonFormPanel formPanel;
//    private JTable table;
//    private DefaultTableModel tableModel;

    public HoaDonButtonPanel(HoaDonController hoaDonController, HoaDonFormPanel formPanel, HoaDonTablePanel tablePanel) {
        this.hoaDonController = hoaDonController;
        this.tablePanel = tablePanel;
        this.formPanel = formPanel;

        setLayout(new GridLayout(1, 6, 10, 10));

        Font fontBtn = new Font("Arial", Font.PLAIN, 20);

        JButton btnAdd = new JButton("THÊM");
        btnAdd.setFont(fontBtn);
        btnAdd.addActionListener(e -> handleAddInvoice());

        JButton btnDelete = new JButton("XÓA");
        btnDelete.setFont(fontBtn);
        btnDelete.addActionListener(e -> handleDeleteInvoiceById());

        JButton btnUpdate = new JButton("SỬA");
        btnUpdate.setFont(fontBtn);
        btnUpdate.addActionListener(e -> handleUpdateInvoice());

        JButton btnSearch = new JButton("TÌM");
        btnSearch.setFont(fontBtn);
        btnSearch.addActionListener(e -> handleSearchInvoice());

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(fontBtn);
        btnReset.addActionListener(e -> tablePanel.loadHoaDon());

        JButton btnPrint = new JButton("IN HÓA ĐƠN");
        btnPrint.setFont(fontBtn);
        btnPrint.addActionListener(e -> handlePrintInvoice());

        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        add(btnSearch);
        add(btnReset);
        add(btnPrint);
    }

    private void handleAddInvoice() {
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

    private void handleDeleteInvoiceById() {
        String maHoaDon = formPanel.getJtfMaHoaDon().getText();
        if (maHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn cần xóa");
            return;
        }

        if (hoaDonController.deleteHoaDon(maHoaDon)) {
            JOptionPane.showMessageDialog(this, "Xóa phòng thành công.");
            tablePanel.loadHoaDon();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa phòng thất bại.");
        }
    }

    private void handleUpdateInvoice() {
        String maHoaDon = formPanel.getJtfMaHoaDon().getText().trim();
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String ngayNhanStr = formPanel.getJtfNgayNhanPhong().getText().trim();
        String ngayTraStr = formPanel.getJtfNgayTraPhong().getText().trim();
        String soGioStr = formPanel.getJtfSoGio().getText().trim();

        if (maHoaDon.isEmpty() || maKhachHang.isEmpty() || maPhong.isEmpty()
        || ngayNhanStr.isEmpty() || ngayTraStr.isEmpty() || soGioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thông tim hóa đơn không được để trống");
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

    private void handleSearchInvoice() {
        String maHoaDon = formPanel.getJtfMaHoaDon().getText().trim();
        String maKhachHang = formPanel.getJtfMaKhachHang().getText().trim();
        String maPhong = formPanel.getJtfMaPhong().getText().trim();
        String ngayNhanStr = formPanel.getJtfNgayNhanPhong().getText().trim();
        String ngayTraStr = formPanel.getJtfNgayTraPhong().getText().trim();
        String soGioStr = formPanel.getJtfSoGio().getText().trim();
        List<HoaDonModel> found = null;

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
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp.");
        }
    }

    private void handlePrintInvoice() {
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
        hoaDon.append(String.format("%-20s: %s\n", "Ngày lập hóa đơn", java.time.LocalDate.now())); // Ngày hiện tại
        hoaDon.append("-------------------------------------------\n");

        // Thêm thông tin từ bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            String columnName = table.getColumnName(i);
            Object value = table.getValueAt(selectedRow, i);
            hoaDon.append(String.format("%-20s: %s\n", columnName, value)); // Định dạng dữ liệu hiển thị
        }

        hoaDon.append("===========================================\n");
        hoaDon.append("         Xin cảm ơn Quý khách hàng!         \n");
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
