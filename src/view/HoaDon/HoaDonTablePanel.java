package view.HoaDon;

import controller.HoaDonController;
import model.HoaDonModel;
import model.KhachHangModel;

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
                "MaHoaDon", "MaKhachHang", "MaPhong", "NgayNhanPhong", "NgayTraPhong", "SoGio", "TongTien"
        },0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Danh sách hóa đơn");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
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

//    public String getSelectedInvoiceId() {
//        int selectedRow = tableModel.getRowCount();
//        if (selectedRow != -1) {
//            return tableModel.getValueAt(selectedRow, 0).toString();
//        }
//        return null;
//    }


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

//
//    public void searchHoaDon(String keyword) {
//        List<HoaDonModel> filteredHoaDons = hoaDonController.getAllHoaDon().stream()
//                .filter(hoaDon -> hoaDon.getMa().contains(keyword) ||
//                        hoaDon.getMaKhachHang().contains(keyword) ||
//                        hoaDon.getMaPhong().contains(keyword))
//                .toList();
//
//        loadTableData(filteredHoaDons);
//    }

//    public void loadTableData(List<HoaDonModel> hoaDons) {
//        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
//        model.setRowCount(0);
//        for (HoaDonModel hoaDon : hoaDons) {
//            model.addRow(new Object[]{
//                    hoaDon.getMa(),
//                    hoaDon.getMaKhachHang(),
//                    hoaDon.getMaPhong(),
//                    new SimpleDateFormat("dd/MM/yyyy").format(hoaDon.getNgayNhanPhong()),
//                    new SimpleDateFormat("dd/MM/yyyy").format(hoaDon.getNgayTraPhong()),
//                    hoaDon.getSoGio(),
//                    hoaDon.getTongTien()
//            });
//        }
//    }

}
