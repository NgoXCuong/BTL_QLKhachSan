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
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        titledBorder.setTitleColor(Color.BLUE);
        scrollPane.setBorder(titledBorder);

        add(scrollPane, BorderLayout.CENTER);

        loadPhong();
    }

    public void clearTable() {
        tableModel.setRowCount(0);
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
        clearTable();
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
