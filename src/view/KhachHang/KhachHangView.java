package view.KhachHang;

import controller.KhachHangController;

import javax.swing.*;
import java.awt.*;

public class KhachHangView extends JFrame {
    private KhachHangController khachHangController;

    public KhachHangView(KhachHangController controller) {
        this.khachHangController = controller;
        setTitle("Quản Lý Khách Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        KhachHangTitlePanel titlePanel = new KhachHangTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        KhachHangTablePanel tablePanel = new KhachHangTablePanel(khachHangController);
        add(tablePanel, BorderLayout.CENTER);

        KhachHangFormPanel formPanel = new KhachHangFormPanel(khachHangController);
        add(formPanel, BorderLayout.WEST);

        KhachHangButtonPanel buttonPanel = new KhachHangButtonPanel(khachHangController, formPanel, tablePanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public KhachHangView() {

    }

    public static void main(String[] args) {
        new KhachHangView(new KhachHangController());
    }
}
