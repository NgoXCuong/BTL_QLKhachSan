package view.KhachHang;

import controller.KhachHangController;
import model.KhachHangModel;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KhachHangTablePanel extends JPanel {
    private DefaultTableModel tableModel;
    private KhachHangController khachHangController;

    public KhachHangTablePanel(KhachHangController controller) {
        this.khachHangController = controller;
        setLayout(new BorderLayout());

        // Create table
        tableModel = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "CMND", "Số Điện Thoại"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Danh sách khách hàng");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder.setTitleColor(Color.BLUE);
        scrollPane.setBorder(titledBorder);

        add(scrollPane, BorderLayout.CENTER);

        loadKhachHang();
    }

    public void clearTable() {
        tableModel.setRowCount(0);  // Removes all rows
    }

    public void addRowToTable(KhachHangModel khachHang) {
        tableModel.addRow(new Object[] {
                khachHang.getMa(),
                khachHang.getTenKhachHang(),
                khachHang.getCmnd(),
                khachHang.getSoDienThoai()
        });
    }

    public void loadKhachHang() {
        tableModel.setRowCount(0);
        List<KhachHangModel> khachHangs = khachHangController.getAllKhachHang();
        for (KhachHangModel khachHang : khachHangs) {
            tableModel.addRow(new Object[]{khachHang.getMa(), khachHang.getTenKhachHang(), khachHang.getCmnd(), khachHang.getSoDienThoai()});
        }
    }
}
