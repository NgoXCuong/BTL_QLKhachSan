package view;

import view.KhachHang.KhachHangView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public Main() {
        JFrame frame = new JFrame("Quản Lý Khách Sạn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1536, 864);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        Font fontTitle = new Font("Arial", Font.BOLD, 40);
        Font fontButton = new Font("Arial", Font.PLAIN, 20);

        JLabel jbTitle = new JLabel("QUẢN LÝ KHÁCH SẠN", JLabel.CENTER);
        jbTitle.setFont(fontTitle);
        jbTitle.setForeground(new Color(56, 120, 56));
        jbTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 26, 0));
        frame.add(jbTitle, BorderLayout.NORTH);

        JPanel jButtonPanel  = new JPanel();
        jButtonPanel.setLayout(new GridLayout(4, 1, 26, 26));
        jButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 150, 5, 150));

        JButton btnKhachHang = new JButton("Quản lý khách hàng");
        JButton btnPhong = new JButton("Quản lý phòng");
        JButton btnHoaDon = new JButton("Quản lý hóa đơn");
        JButton btnThoat = new JButton("Đăng xuất!");

        btnKhachHang.setFont(fontButton);
        btnPhong.setFont(fontButton);
        btnHoaDon.setFont(fontButton);
        btnThoat.setFont(fontButton);

        btnKhachHang.setFocusPainted(false);
        btnPhong.setFocusPainted(false);
        btnHoaDon.setFocusPainted(false);
        btnThoat.setFocusPainted(false);


        btnKhachHang.addActionListener(e -> {
            frame.setVisible(false);
            new view.KhachHang.KhachHangView();
        });

        btnPhong.addActionListener(e -> {
            frame.setVisible(false);
            new view.Phong.PhongView();
        });

        btnHoaDon.addActionListener(e -> {
            frame.setVisible(false);
            new view.HoaDon.HoaDonView();
        });

        btnThoat.addActionListener(e -> {
            int xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn đăng xuất?", "Message", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                frame.dispose();
                new SignIn();
            }
        });

        jButtonPanel.add(btnKhachHang);
        jButtonPanel.add(btnPhong);
        jButtonPanel.add(btnHoaDon);
        jButtonPanel.add(btnThoat);

        frame.add(jButtonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new Main();
    }
}
