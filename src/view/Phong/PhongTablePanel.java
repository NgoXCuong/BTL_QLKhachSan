//package view.Phong;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class PhongTablePanel extends JPanel {
//    private JTable table;
//    private DefaultTableModel model;
//
//    public PhongTablePanel() {
//        setLayout(new BorderLayout());
//
//        model = new DefaultTableModel();
//        model.addColumn("Mã Phòng");
//        model.addColumn("Loại Phòng");
//        model.addColumn("Giá Phòng");
//        model.addColumn("Tình Trạng");
//
//        table = new JTable(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//        add(scrollPane, BorderLayout.CENTER);
//    }
//
//    public void addRoom(String maPhong, String loaiPhong, String giaPhong, String tinhTrang) {
//        model.addRow(new Object[]{maPhong, loaiPhong, giaPhong, tinhTrang});
//    }
//}

package view.Phong;

import controller.PhongController;
import model.PhongModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PhongTablePanel extends JPanel {
    private DefaultTableModel tableModel;
    private PhongController phongController;

    public PhongTablePanel(PhongController phongController) {
        this.phongController = phongController;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Mã Phòng", "Loại Phòng", "Giá Phòng", "Tình Trạng"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Danh sách phòng");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder.setTitleColor(Color.BLUE);
        scrollPane.setBorder(titledBorder);

        add(scrollPane, BorderLayout.CENTER);

        loadPhong();
    }

    public void clearTable() {
        tableModel.setRowCount(0);  // Xóa hết các dòng
    }

    public void addRowToTable(PhongModel phong) {
        tableModel.addRow(new Object[] {
                phong.getMa(),
                phong.getLoaiPhong(),
                phong.getGiaPhong(),
                phong.getTinhTrang()
        });
    }

    public void loadPhong() {
        tableModel.setRowCount(0);
        List<PhongModel> phongList = phongController.getAllRooms();
        for (PhongModel phong : phongList) {
            tableModel.addRow(new Object[]{
                    phong.getMa(),
                    phong.getLoaiPhong(),
                    phong.getGiaPhong(),
                    phong.getTinhTrang()
            });
        }
    }
}
