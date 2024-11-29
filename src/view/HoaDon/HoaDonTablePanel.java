package view.HoaDon;

import controller.HoaDonController;
import model.HoaDonModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDonTablePanel extends JPanel {
    private DefaultTableModel tableModel;
    private HoaDonController hoaDonController;
    private AbstractButton tblHoaDon;
    private JTable table;

    public HoaDonTablePanel() {
        this.hoaDonController = new HoaDonController();
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[] {
                "Mã Hóa Đơn", "Mã Khách Hàng", "Mã Phòng", "Ngày Nhận Phòng", "Ngày Trả Phòng", "Số Giờ", "Tổng Tiền"
        },0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Danh sách hóa đơn");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        titledBorder.setTitleColor(Color.BLUE);
        scrollPane.setBorder(titledBorder);

        add(scrollPane, BorderLayout.CENTER);

        loadHoaDon();
    }

    public JTable getTable() {
        return table;
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addRowToTable(HoaDonModel hoaDon) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String formattedNgayNhan = dateFormat.format(hoaDon.getNgayNhanPhong());
        String formattedNgayTra = dateFormat.format(hoaDon.getNgayTraPhong());

        tableModel.addRow(new Object[]{
                hoaDon.getMa(),
                hoaDon.getMaKhachHang(),
                hoaDon.getMaPhong(),
                formattedNgayNhan,
                formattedNgayTra,
                hoaDon.getSoGio(),
                hoaDon.getTongTien()
        });
    }

    public void loadHoaDon() {
        tableModel.setRowCount(0);
        List<HoaDonModel> hoaDons = hoaDonController.getAllHoaDon();
        for (HoaDonModel hoaDon : hoaDons) {
            tableModel.addRow(new Object[]{hoaDon.getMa(), hoaDon.getMaKhachHang(), hoaDon.getMaPhong(), hoaDon.getNgayNhanPhong(), hoaDon.getNgayTraPhong(), hoaDon.getSoGio(), hoaDon.getTongTien()});
        }
    }
}
