package view.HoaDon;

import controller.HoaDonController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HoaDonThongKe extends JPanel {
    public HoaDonThongKe(HoaDonController hoaDonController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thống kê doanh thu");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        titledBorder.setTitleColor(Color.BLUE);
        setBorder(titledBorder);

        add(createRevenueByDayPanel(hoaDonController));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(createRevenueByMonthPanel(hoaDonController));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(createRevenueByYearPanel(hoaDonController));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(createTotalRevenuePanel(hoaDonController));
    }

    private JPanel createRevenueByDayPanel(HoaDonController hoaDonController) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Doanh Thu Theo Ngày", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField tfDay = new JTextField(2);
        JTextField tfMonth = new JTextField(2);
        JTextField tfYear = new JTextField(4);
        JButton btnCalculate = new JButton("Tính");

        inputPanel.add(new JLabel("Ngày:"));
        inputPanel.add(tfDay);
        inputPanel.add(new JLabel("Tháng:"));
        inputPanel.add(tfMonth);
        inputPanel.add(new JLabel("Năm:"));
        inputPanel.add(tfYear);
        inputPanel.add(btnCalculate);

        panel.add(inputPanel, BorderLayout.CENTER);

        JLabel revenueLabel = new JLabel("Doanh Thu: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(e -> {
            try {
                String day = tfDay.getText().trim();
                String month = tfMonth.getText().trim();
                String year = tfYear.getText().trim();
                double revenueByDay = hoaDonController.getRevenueByDay(day, month, year);
                revenueLabel.setText("Doanh Thu Ngày " + day + "/" + month + "/" + year + ": " + revenueByDay + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Vui lòng nhập đúng định dạng ngày/tháng/năm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createRevenueByMonthPanel(HoaDonController hoaDonController) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Doanh Thu Theo Tháng", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField tfMonth = new JTextField(2);
        JTextField tfYear = new JTextField(4);
        JButton btnCalculate = new JButton("Tính");

        inputPanel.add(new JLabel("Tháng:"));
        inputPanel.add(tfMonth);
        inputPanel.add(new JLabel("Năm:"));
        inputPanel.add(tfYear);
        inputPanel.add(btnCalculate);

        panel.add(inputPanel, BorderLayout.CENTER);

        JLabel revenueLabel = new JLabel("Doanh Thu: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(e -> {
            try {
                String month = tfMonth.getText().trim();
                String year = tfYear.getText().trim();
                double revenueByMonth = hoaDonController.getRevenueByMonth(month, year);
                revenueLabel.setText("Doanh Thu Tháng " + month + "/" + year + ": " + revenueByMonth + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Vui lòng nhập đúng định dạng tháng/năm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createRevenueByYearPanel(HoaDonController hoaDonController) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Doanh Thu Theo Năm", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField tfYear = new JTextField(4);
        JButton btnCalculate = new JButton("Tính");

        inputPanel.add(new JLabel("Năm:"));
        inputPanel.add(tfYear);
        inputPanel.add(btnCalculate);

        panel.add(inputPanel, BorderLayout.CENTER);

        JLabel revenueLabel = new JLabel("Doanh Thu Năm Nay: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(e -> {
            try {
                String year = tfYear.getText().trim();
                double revenueByYear = hoaDonController.getRevenueByYear(year);
                revenueLabel.setText("Doanh Thu Năm "+ year + ": " + revenueByYear + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Vui lòng nhập đúng định dạng năm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        return panel;
    }

    private JPanel createTotalRevenuePanel(HoaDonController hoaDonController) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Tổng Doanh Thu", JLabel.CENTER);
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(title, BorderLayout.NORTH);

        JLabel revenueLabel = new JLabel("Tổng Doanh Thu: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("Tổng Doanh Thu");
        btnRefresh.addActionListener(e -> {
            try {
                double totalRevenue = hoaDonController.getTotalRevenue();
                revenueLabel.setText("Tổng Doanh Thu: " + totalRevenue + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Có lỗi xảy ra khi lấy tổng doanh thu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnRefresh, BorderLayout.SOUTH);
        return panel;
    }
}
