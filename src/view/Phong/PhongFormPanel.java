package view.Phong;

import controller.PhongController;

import javax.swing.*;
import java.awt.*;

public class PhongFormPanel extends JPanel {
    private JTextField jtfMaPhong;
    private JComboBox<String> jcbLoaiPhong;
    private JTextField jtfGiaPhong;
    private JComboBox<String> jcbTinhTrang;

    public PhongFormPanel(PhongController phongController) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Thông tin phòng"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jtfMaPhong = new JTextField(15);
        jcbLoaiPhong = new JComboBox<>(new String[]{"", "Đặc Biệt", "VIP", "Bình Dân"});
        jtfGiaPhong = new JTextField(15);
        jcbTinhTrang = new JComboBox<>(new String[]{"", "Trống", "Đã Sử Dụng", "Đang dọn dẹp"});

        addThanhPhan(new JLabel("Mã Phòng: "), jtfMaPhong, gbc, 0);
        addThanhPhan(new JLabel("Loại Phòng: "), jcbLoaiPhong, gbc, 1);
        addThanhPhan(new JLabel("Giá Phòng: "), jtfGiaPhong, gbc, 2);
        addThanhPhan(new JLabel("Tình Trạng: "), jcbTinhTrang, gbc, 3);
    }

    private void addThanhPhan(JComponent label, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        add(label, gbc);
        gbc.gridx = 1;
        add(field, gbc);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    public JTextField getJtfMaPhong() {
        return jtfMaPhong;
    }

    public JComboBox<String> getJcbLoaiPhong() {
        return jcbLoaiPhong;
    }

    public JTextField getJtfGiaPhong() {
        return jtfGiaPhong;
    }

    public JComboBox<String> getJcbTinhTrang() {
        return jcbTinhTrang;
    }
}