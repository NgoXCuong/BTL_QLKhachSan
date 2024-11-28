package view.HoaDon;

import controller.HoaDonController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HoaDonThongKe extends JPanel {
    private HoaDonController hoaDonController;

    public HoaDonThongKe(HoaDonController hoaDonController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thống kê doanh thu");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        titledBorder.setTitleColor(Color.BLUE);
        setBorder(titledBorder);

        add(createRevenueByDayPanel(hoaDonController));
        add(createRevenueByMonthPanel(hoaDonController));
        add(createRevenueByYearPanel(hoaDonController));
        add(createTotalRevenuePanel(hoaDonController));
    }

    private JPanel createThongKePanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
//        TitledBorder titledBorder = BorderFactory.createTitledBorder("Thống kê doanh thu");
//        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
//        titledBorder.setTitleColor(Color.BLUE);
//        statsPanel.setBorder(titledBorder);

        statsPanel.setPreferredSize(new Dimension(300, 450));  // Điều chỉnh kích thước panel
        Font fontText = new Font("Arial", Font.PLAIN, 16);

        JPanel dayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDay =new JLabel("Ngày (dd/MM//yyyy): ");
        JTextField jtfDay = new JTextField(10);
        JButton btnRevenueByDay = new JButton("Thống kê");
        JLabel lblRevenueByDay = new JLabel("Doanh thu: 0 VND");
        lblDay.setFont(fontText);
        jtfDay.setFont(fontText);
        btnRevenueByDay.setFocusPainted(false);
        btnRevenueByDay.setFont(fontText);
        lblRevenueByDay.setFont(fontText);
        lblRevenueByDay.setForeground(Color.RED);

        dayPanel.add(lblDay);
        dayPanel.add(jtfDay);
        dayPanel.add(btnRevenueByDay);
        dayPanel.add(lblRevenueByDay);

        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblMonth = new JLabel("Tháng (MM/yyyy): ");
        JTextField jtfMonth = new JTextField(10);
        JButton btnRevenueByMonth = new JButton("Thống kê");
        JLabel lblRevenueByMonth = new JLabel("Doanh thu: 0 VND");
        lblMonth.setFont(fontText);
        jtfMonth.setFont(fontText);
        btnRevenueByMonth.setFocusPainted(false);
        btnRevenueByMonth.setFont(fontText);
        lblRevenueByMonth.setFont(fontText);
        lblRevenueByMonth.setForeground(Color.RED);

        monthPanel.add(lblMonth);
        monthPanel.add(jtfMonth);
        monthPanel.add(btnRevenueByMonth);
        monthPanel.add(lblRevenueByMonth);

        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblYear = new JLabel("Năm (yyyy): ");
        JTextField jtfYear = new JTextField(10);
        JButton btnRevenueByYear = new JButton("Thống kê");
        JLabel lblRevenueByYear = new JLabel("Doanh thu: 0 VND");
        lblYear.setFont(fontText);
        jtfYear.setFont(fontText);
        btnRevenueByYear.setFocusPainted(false);
        btnRevenueByYear.setFont(fontText);
        lblRevenueByYear.setFont(fontText);
        lblRevenueByYear.setForeground(Color.RED);
        yearPanel.add(lblYear);
        yearPanel.add(jtfYear);
        yearPanel.add(btnRevenueByYear);
        yearPanel.add(lblRevenueByYear);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnTotalRevenue = new JButton("Tổng doanh thu");
        JLabel lblTotalRevenue = new JLabel("Tổng doanh thu: 0 VND");
        lblTotalRevenue.setFont(fontText);
        lblTotalRevenue.setForeground(Color.RED);

        btnTotalRevenue.setFont(fontText);
        btnTotalRevenue.setFocusPainted(false);
        totalPanel.add(btnTotalRevenue);
        totalPanel.add(lblTotalRevenue);

        statsPanel.add(dayPanel);
        statsPanel.add(monthPanel);
        statsPanel.add(yearPanel);
        statsPanel.add(totalPanel);

        btnRevenueByDay.addActionListener(e -> {
            String dayInput = jtfDay.getText().trim();
            if (!dayInput.matches("\\d{2}/\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Ngày không hợp lệ! Định dạng đúng: d/MM/yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] parts = dayInput.split("/");
            String day = parts[0];
            String month = parts[1];
            String year = parts[2];

            try {
                double revenue = hoaDonController.getRevenueByDay(day, month, year);
                lblRevenueByDay.setText("Doanh thu ngày "+ day + "/" + month + "/" + year + ": " + revenue + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thống kê: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRevenueByMonth.addActionListener(e -> {
            String monthInput = jtfMonth.getText().trim();
            if (!monthInput.matches("\\d{2}/\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Tháng không hợp lệ! Định dạng đúng: MM/yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] parts = monthInput.split("/");
            String month = parts[0];
            String year = parts[1];

            try {
                double revenue = hoaDonController.getRevenueByMonth(month, year);
                lblRevenueByMonth.setText("Doanh thu tháng " + month + "/" + year + ": " + revenue + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thống kê: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRevenueByYear.addActionListener(e -> {
            String yearInput = jtfYear.getText().trim();
            if (!yearInput.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Năm không hợp lệ! Định dạng đúng: yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double revenue = hoaDonController.getRevenueByYear(yearInput);
                lblRevenueByYear.setText("Doanh thu năm " + yearInput + ": " + revenue + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thống kê: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnTotalRevenue.addActionListener(e -> {
            try {
                double totalRevenue = hoaDonController.getTotalRevenue();
                lblTotalRevenue.setText("Tổng doanh thu: " + totalRevenue + " VND");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thống kê: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        return statsPanel;
    }

    private JPanel createRevenueByDayPanel(HoaDonController hoaDonController) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Doanh Thu Theo Ngày", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
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

        JLabel revenueLabel = new JLabel("Doanh Thu Hôm Nay: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(e -> {
            try {
                String day = tfDay.getText().trim();
                String month = tfMonth.getText().trim();
                String year = tfYear.getText().trim();
                double revenueByDay = hoaDonController.getRevenueByDay(day, month, year);
                revenueLabel.setText("Doanh Thu Hôm Nay: " + revenueByDay + " VND");
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
        title.setFont(new Font("Arial", Font.BOLD, 16));
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

        JLabel revenueLabel = new JLabel("Doanh Thu Tháng Này: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(e -> {
            try {
                String month = tfMonth.getText().trim();
                String year = tfYear.getText().trim();
                double revenueByMonth = hoaDonController.getRevenueByMonth(month, year);
                revenueLabel.setText("Doanh Thu Tháng Này: " + revenueByMonth + " VND");
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
        title.setFont(new Font("Arial", Font.BOLD, 16));
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
                revenueLabel.setText("Doanh Thu Năm Nay: " + revenueByYear + " VND");
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
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);

        JLabel revenueLabel = new JLabel("Tổng Doanh Thu: --- VND");
        revenueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(revenueLabel, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("Làm Mới");
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
