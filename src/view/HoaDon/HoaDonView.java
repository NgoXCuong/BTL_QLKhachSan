package view.HoaDon;

import controller.HoaDonController;
import view.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HoaDonView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;

    public HoaDonView() {
        HoaDonController hoaDonController = new HoaDonController();
        setTitle("Quản Lý Khách Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = createTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        HoaDonTablePanel tablePanel = new HoaDonTablePanel();
        HoaDonFormPanel formPanel = new HoaDonFormPanel(this, hoaDonController, tableModel);
        HoaDonButtonPanel buttonPanel = new HoaDonButtonPanel(hoaDonController, formPanel, tablePanel);
        HoaDonThongKe thongKePanel = new HoaDonThongKe(hoaDonController);

        add(formPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(thongKePanel, BorderLayout.EAST);
        add(tablePanel, BorderLayout.CENTER);

        setVisible(true);
        loadHoaDon();
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("QUẢN LÝ HÓA ĐƠN", JLabel.CENTER);
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

    private void loadHoaDon() {
    }


    public static void main(String[] args) {
        new HoaDonView();
    }
}
