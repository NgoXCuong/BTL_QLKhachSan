package view.HoaDon;

import controller.HoaDonController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HoaDonFormPanel extends JPanel {
    private HoaDonController hoaDonController;
    private JTextField jtfMaHoaDon;
    private JTextField jtfMaKhachHang;
    private JTextField jtfMaPhong;
    private JTextField jtfNgayNhanPhong;
    private JTextField jtfNgayTraPhong;
    private JTextField jtfSoGio;
    private JTextField jtfTongTien;

    public HoaDonFormPanel(HoaDonView hoaDonView, HoaDonController controller, DefaultTableModel tableModel) {
        this.hoaDonController = controller;
        setLayout(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thông tin hóa đơn");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        titledBorder.setTitleColor(Color.BLUE);
        setBorder(titledBorder);

        Font fontText = new Font("Arial", Font.PLAIN, 16);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jtfMaHoaDon = new JTextField(15);
        jtfMaKhachHang = new JTextField(15);
        jtfMaPhong = new JTextField(15);
        jtfNgayNhanPhong = new JTextField(15);
        jtfNgayTraPhong = new JTextField(15);
        jtfSoGio = new JTextField(15);

        addComponent( new JLabel("Mã Hóa Đơn: "), jtfMaHoaDon, gbc, 0, fontText);
        addComponent( new JLabel("Mã Khách Hàng: "), jtfMaKhachHang, gbc, 1, fontText);
        addComponent( new JLabel("Mã Phòng: "), jtfMaPhong, gbc, 2, fontText);
        addComponent( new JLabel("Ngày Nhận Phòng: "), jtfNgayNhanPhong, gbc, 3, fontText);
        addComponent( new JLabel("Ngày Trả Phòng: "), jtfNgayTraPhong, gbc, 4, fontText);
        addComponent( new JLabel("Số Giờ: "), jtfSoGio, gbc, 5, fontText);
    }

    private void addComponent(JComponent label, JComponent field, GridBagConstraints gbc, int row, Font font) {
        gbc.gridx = 0;
        gbc.gridy = row;
        label.setFont(font);
        add(label, gbc);
        gbc.gridx = 1;
        field.setFont(font);
        add(field, gbc);
    }

    public JTextField getJtfMaHoaDon() {
        return jtfMaHoaDon;
    }

    public JTextField getJtfMaKhachHang() {
        return jtfMaKhachHang;
    }

    public JTextField getJtfMaPhong() {
        return jtfMaPhong;
    }

    public JTextField getJtfNgayNhanPhong() {
        return jtfNgayNhanPhong;
    }

    public JTextField getJtfNgayTraPhong() {
        return jtfNgayTraPhong;
    }

    public JTextField getJtfSoGio() {
        return jtfSoGio;
    }
}

