package view.KhachHang;

import javax.swing.*;
import java.awt.*;

public class KhachHangTitlePanel extends JPanel {
    public KhachHangTitlePanel() {
        setLayout(new BorderLayout());

        Font fontTitle = new Font("Arial", Font.BOLD, 40);

        JButton btnBack = new JButton("<= Quay lại");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        btnBack.setBackground(Color.GRAY);
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);

        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG", JLabel.CENTER);
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(new Color(56, 120, 56));

        add(btnBack, BorderLayout.WEST);
        add(lblTitle, BorderLayout.CENTER);
    }
}
