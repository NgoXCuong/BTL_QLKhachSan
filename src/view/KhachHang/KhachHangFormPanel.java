package view.KhachHang;

import controller.KhachHangController;
import javax.swing.*;
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

        Font fontText = new Font("Arial", Font.PLAIN, 16);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jtfMaKhachHang = new JTextField(15);
        jtfTenKhachHang = new JTextField(15);
        jtfCMND = new JTextField(15);
        jtfSoDienThoai = new JTextField(15);

        addComponent(new JLabel("Mã Khách Hàng:"), jtfMaKhachHang, gbc, 0, fontText);
        addComponent(new JLabel("Tên Khách Hàng:"), jtfTenKhachHang, gbc, 1, fontText);
        addComponent(new JLabel("CMND:"), jtfCMND, gbc, 2, fontText);
        addComponent(new JLabel("Số Điện Thoại:"), jtfSoDienThoai, gbc, 3, fontText);

        setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
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

    // Getters for the text fields to use in the button panel actions
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
}
