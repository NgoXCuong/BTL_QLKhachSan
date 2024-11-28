package view.KhachHang;

import controller.KhachHangController;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class KhachHangFormPanel extends JPanel {
    private KhachHangController khachHangController;
    private JTextField jtfMaKhachHang;
    private JTextField jtfTenKhachHang;
    private JTextField jtfCMND;
    private JTextField jtfSoDienThoai;

    public KhachHangFormPanel(KhachHangController controller) {
        this.khachHangController = controller;
        setLayout(new GridBagLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thông tin khách hàng");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder.setTitleColor(Color.BLUE);
        setBorder(titledBorder);

        Font fontText = new Font("Arial", Font.PLAIN, 16);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jtfMaKhachHang = new JTextField(15);
        jtfTenKhachHang = new JTextField(15);
        jtfCMND = new JTextField(15);
        jtfSoDienThoai = new JTextField(15);

        // Add the components to the layout
        addComponent(new JLabel("Mã Khách Hàng:"), jtfMaKhachHang, gbc, 0, fontText);
        addComponent(new JLabel("Tên Khách Hàng:"), jtfTenKhachHang, gbc, 1, fontText);
        addComponent(new JLabel("CMND:"), jtfCMND, gbc, 2, fontText);
        addComponent(new JLabel("Số Điện Thoại:"), jtfSoDienThoai, gbc, 3, fontText);

        // Set tooltips for better UX
        jtfMaKhachHang.setToolTipText("Nhập mã khách hàng");
        jtfTenKhachHang.setToolTipText("Nhập tên khách hàng");
        jtfCMND.setToolTipText("Nhập số CMND của khách hàng");
        jtfSoDienThoai.setToolTipText("Nhập số điện thoại khách hàng");
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

    public JTextField getJtfMaKhachHang() {
        return jtfMaKhachHang;
    }

    public JTextField getJtfTenKhachHang() {
        return jtfTenKhachHang;
    }

    public JTextField getJtfCMND() {
        return jtfCMND;
    }

    public JTextField getJtfSoDienThoai() {
        return jtfSoDienThoai;
    }

    public void setMaKhachHang(String maKhachHang) {
        jtfMaKhachHang.setText(maKhachHang);
    }

    public void setTenKhachHang(String tenKhachHang) {
        jtfTenKhachHang.setText(tenKhachHang);
    }

    public void setCMND(String cmnd) {
        jtfCMND.setText(cmnd);
    }

    public void setSoDienThoai(String soDienThoai) {
        jtfSoDienThoai.setText(soDienThoai);
    }
}
