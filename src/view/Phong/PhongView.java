package view.Phong;

import controller.HoaDonController;
import controller.PhongController;
import view.Main;

import javax.swing.*;
import java.awt.*;

public class PhongView extends JFrame {
    public PhongView() {
        PhongController phongController = new PhongController();
        setTitle("Quản Lý Khách Sạn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = createTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        PhongTablePanel phongTable = new PhongTablePanel(phongController);
        add(phongTable, BorderLayout.CENTER);

        PhongFormPanel phongForm = new PhongFormPanel(phongController);
        add(phongForm, BorderLayout.WEST);

        PhongButtonPanel buttonPanel = new PhongButtonPanel(phongController, phongForm, phongTable);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("QUẢN LÝ PHÒNG", JLabel.CENTER);
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
        new PhongView();
    }
}
