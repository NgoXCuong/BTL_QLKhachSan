package view.Phong;

import controller.PhongController;

import javax.swing.*;
import java.awt.*;

public class PhongView extends JFrame {
    private PhongController phongController;

    public PhongView(PhongController phongController) {
        setTitle("Quản Lý Khách Sạn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1536, 864);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = new PhongTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        PhongTablePanel phongTable = new PhongTablePanel(phongController);
        add(phongTable, BorderLayout.CENTER);

        PhongFormPanel phongForm = new PhongFormPanel(phongController);
        add(phongForm, BorderLayout.WEST);

        PhongButtonPanel buttonPanel = new PhongButtonPanel(phongController, phongForm, phongTable);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public PhongView() {

    }

    public static void main(String[] args) {
        new PhongView(new PhongController());
    }
}
