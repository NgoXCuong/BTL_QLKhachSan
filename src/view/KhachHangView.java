//package view;
//
//import controller.KhachHangController;
//import model.KhachHangModel;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//
//public class KhachHangView extends JFrame {
//    private KhachHangController khachHangController;
//    private DefaultTableModel tableModel;
//
//    public KhachHangView() {
//        khachHangController = new KhachHangController();
//        setTitle("Quản Lý Khách Hàng");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1536, 864);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout(10, 10));
//
//        Font fontTitle = new Font("Arial", Font.BOLD, 40);
//        Font fontButton = new Font("Arial", Font.PLAIN, 20);
//        Font fontText = new Font("Arial", Font.PLAIN, 16);
//        Font fontTable = new Font("Arial", Font.BOLD, 14);
//
//        // Title Panel
//        JPanel titlePanel = new JPanel(new BorderLayout());
//        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//        JButton btnBack = new JButton("<= Quay lại");
//        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnBack.setBackground(Color.GRAY);
//        btnBack.setForeground(Color.WHITE);
//        btnBack.setFocusPainted(false);
//        btnBack.addActionListener(e -> {
//            dispose();
//            new Main(); // You may want to define this properly
//        });
//
//        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG", JLabel.CENTER);
//        lblTitle.setFont(fontTitle);
//        lblTitle.setForeground(new Color(56, 120, 56));
//
//        titlePanel.add(btnBack, BorderLayout.WEST);
//        titlePanel.add(lblTitle, BorderLayout.CENTER);
//        add(titlePanel, BorderLayout.NORTH);
//
//        // Table Panel
//        tableModel = new DefaultTableModel(new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "CMND", "Số Điện Thoại"}, 0);
//        JTable table = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(table);
//        TitledBorder titledBorder1 = BorderFactory.createTitledBorder("Danh sách khách hàng");
//        titledBorder1.setTitleFont(fontTable);
//        titledBorder1.setTitleColor(Color.BLUE);
//        scrollPane.setBorder(titledBorder1);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Form Panel
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        TitledBorder titledBorder2 = BorderFactory.createTitledBorder("Thông tin khách hàng");
//        titledBorder2.setTitleFont(fontTable);
//        titledBorder2.setTitleColor(Color.BLUE);
//        formPanel.setBorder(titledBorder2);
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JTextField jtfMaKhachHang = new JTextField(15);
//        JTextField jtfTenKhachHang = new JTextField(15);
//        JTextField jtfCMND = new JTextField(15);
//        JTextField jtfSoDienThoai = new JTextField(15);
//
//        addComponent(formPanel, new JLabel("Mã Khách Hàng:"), jtfMaKhachHang, gbc, 0, fontText);
//        addComponent(formPanel, new JLabel("Tên Khách Hàng:"), jtfTenKhachHang, gbc, 1, fontText);
//        addComponent(formPanel, new JLabel("CMND:"), jtfCMND, gbc, 2, fontText);
//        addComponent(formPanel, new JLabel("Số Điện Thoại:"), jtfSoDienThoai, gbc, 3, fontText);
//
//        add(formPanel, BorderLayout.WEST);
//
//        // Button Panel
//        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
//        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//        JButton btnAdd = taoNut("THÊM", Color.GREEN, fontButton);
//        btnAdd.addActionListener(e -> {
//            String maKhachHang = jtfMaKhachHang.getText().trim();
//            String tenKhachHang = jtfTenKhachHang.getText().trim();
//            String cmnd = jtfCMND.getText().trim();
//            String soDienThoai = jtfSoDienThoai.getText().trim();
//            KhachHangModel newKhachHang = new KhachHangModel(maKhachHang, tenKhachHang, cmnd, soDienThoai);
//            if (khachHangController.addKhachHang(newKhachHang)) {
//                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
//                loadKhachHang();
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại.");
//            }
//        });
//
//        JButton btnDelete = taoNut("XÓA", Color.RED, fontButton);
//        btnDelete.addActionListener(e -> {
//            String maKhachHang = jtfMaKhachHang.getText().trim();
//            if (maKhachHang.isEmpty()) {
//                JOptionPane.showMessageDialog(KhachHangView.this, "Vui lòng nhập mã khách hàng cần xóa");
//                return;
//            }
//
////            if (khachHangController.khachHangInHoaDon(maKhachHang)) {
////                JOptionPane.showMessageDialog(KhachHangView.this, "Khách hàng đã tồn tại trong hóa đơn, không thể xóa.");
////                return;
////            }
//
//            if (khachHangController.deleteKhachHang(maKhachHang)) {
//                JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
//                loadKhachHang();
//            } else {
//                JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại.");
//            }
//        });
//
//        JButton btnUpdate = taoNut("SỬA", Color.CYAN, fontButton);
//        btnUpdate.addActionListener(e -> {
//            String maKhachHang = jtfMaKhachHang.getText().trim();
//            String tenKhachHang = jtfTenKhachHang.getText().trim();
//            String cmnd = jtfCMND.getText().trim();
//            String soDienThoai = jtfSoDienThoai.getText().trim();
//            KhachHangModel updatedKhachHang = new KhachHangModel(maKhachHang, tenKhachHang, cmnd, soDienThoai);
//            if (khachHangController.updateKhachHang(updatedKhachHang)) {
//                JOptionPane.showMessageDialog(this, "Sửa khách hàng thành công!");
//                loadKhachHang();
//            } else {
//                JOptionPane.showMessageDialog(this, "Sửa khách hàng thất bại.");
//            }
//        });
//
//        JButton btnSearch = taoNut("TÌM", Color.ORANGE, fontButton);
//        btnSearch.addActionListener(e -> {
//            String maKhachHang = jtfMaKhachHang.getText().trim();
//            String tenKhachHang = jtfTenKhachHang.getText().trim();
//            String cmnd = jtfCMND.getText().trim();
//            String soDienThoai = jtfSoDienThoai.getText().trim();
//            List<KhachHangModel> found = null;
//
//            // Perform the search based on the user's input
//            if (!maKhachHang.isEmpty()) {
//                found = khachHangController.searchKhachHang("MaKhachHang", maKhachHang);
//            } else if (!tenKhachHang.isEmpty()) {
//                found = khachHangController.searchKhachHang("TenKhachHang", tenKhachHang);
//            } else if (!cmnd.isEmpty()) {
//                found = khachHangController.searchKhachHang("CMND", cmnd);
//            } else if (!soDienThoai.isEmpty()) {
//                found = khachHangController.searchKhachHang("SoDienThoai", soDienThoai);
//            }
//
//            if (found != null && !found.isEmpty()) {
//                // Display the search results in the table
//                tableModel.setRowCount(0);
//                for (KhachHangModel khachHang : found) {
//                    tableModel.addRow(new Object[]{
//                            khachHang.getMa(),
//                            khachHang.getTenKhachHang(),
//                            khachHang.getCmnd(),
//                            khachHang.getSoDienThoai()
//                    });
//                }
//            } else {
//                JOptionPane.showMessageDialog(KhachHangView.this, "Không tìm thấy khách hàng phù hợp.");
//            }
//        });
//
//
//        JButton btnReset = taoNut("Reset", Color.PINK, fontButton);
//        btnReset.addActionListener(e -> loadKhachHang());
//
//        buttonPanel.add(btnAdd);
//        buttonPanel.add(btnDelete);
//        buttonPanel.add(btnUpdate);
//        buttonPanel.add(btnSearch);
//        buttonPanel.add(btnReset);
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        setVisible(true);
//        loadKhachHang();
//    }
//
//    private void addComponent(JPanel panel, JComponent label, JComponent field, GridBagConstraints gbc, int row, Font font) {
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        label.setFont(font);
//        panel.add(label, gbc);
//        gbc.gridx = 1;
//        field.setFont(font);
//        panel.add(field, gbc);
//    }
//
//    private JButton taoNut(String text, Color color, Font font) {
//        JButton button = new JButton(text);
//        button.setBackground(color);
//        button.setFont(font);
//        button.setForeground(Color.BLACK);
//        button.setFocusPainted(false);
//        return button;
//    }
//
//    private void loadKhachHang() {
//        tableModel.setRowCount(0);
//        List<KhachHangModel> khachHangs = khachHangController.getAllKhachHang();
//        for (KhachHangModel khachHang : khachHangs) {
//            tableModel.addRow(new Object[]{khachHang.getMa(), khachHang.getTenKhachHang(), khachHang.getCmnd(), khachHang.getSoDienThoai()});
//        }
//    }
//
//    public static void main(String[] args) {
//        new KhachHangView();
//    }
//}
