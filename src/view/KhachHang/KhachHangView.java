package view.KhachHang;

import controller.KhachHangController;
import view.Main;

import javax.swing.*;
import java.awt.*;

public class KhachHangView extends JFrame {
    public KhachHangView() {
        KhachHangController khachHangController = new KhachHangController();
        setTitle("Quản Lý Khách Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = createTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        KhachHangTablePanel tablePanel = new KhachHangTablePanel(khachHangController);
        add(tablePanel, BorderLayout.CENTER);

        KhachHangFormPanel formPanel = new KhachHangFormPanel(khachHangController);
        add(formPanel, BorderLayout.WEST);

        KhachHangButtonPanel buttonPanel = new KhachHangButtonPanel(khachHangController, formPanel, tablePanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("QUẢN LÝ KHÁCH HÀNG", JLabel.CENTER);
        titleLabel.setForeground(new Color(56, 120, 56));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JButton btnBack = new JButton("<= Quay lại");
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            dispose();
            new Main();
        });
        titlePanel.add(btnBack, BorderLayout.WEST);

        return titlePanel;
    }

    public static void main(String[] args) {
        new KhachHangView();
    }
}
