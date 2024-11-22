package view.Phong;

import javax.swing.*;
import java.awt.*;

public class PhongTitlePanel extends JPanel {
    public PhongTitlePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnBack = new JButton("<= Quay lại");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        btnBack.setMargin(new Insets(5, 10, 5, 10));
        btnBack.setFocusPainted(false);
        btnBack.setBackground(Color.GRAY);
        btnBack.setOpaque(true);
        btnBack.addActionListener(e -> {
            // Logic quay lại
        });

        JLabel jbTitle = new JLabel("QUẢN LÝ PHÒNG", JLabel.CENTER);
        jbTitle.setFont(new Font("Arial", Font.BOLD, 40));
        jbTitle.setForeground(new Color(56, 120, 56));

        add(btnBack, BorderLayout.WEST);
        add(jbTitle, BorderLayout.CENTER);
    }
}
