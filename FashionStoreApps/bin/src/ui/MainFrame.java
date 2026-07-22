package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JButton btnProduct;
    private JButton btnCategory;
    private JButton btnCustomer;
    private JButton btnOrder;
    private JButton btnProfile;
    private JButton btnLogout;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public MainFrame() {

        setTitle("Fashion Store Apps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 460);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("FASHION STORE APPS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255, 128, 192));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBounds(90, 20, 380, 40);
        contentPane.add(lblTitle);

        // ====================
        // BUTTON
        // ====================

        btnProduct = new JButton("PRODUK");
        btnProduct.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnProduct.setBounds(55, 90, 180, 65);
        contentPane.add(btnProduct);

        btnCategory = new JButton("KATEGORI");
        btnCategory.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCategory.setBounds(320, 90, 180, 65);
        contentPane.add(btnCategory);

        btnCustomer = new JButton("CUSTOMER");
        btnCustomer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCustomer.setBounds(55, 175, 180, 65);
        contentPane.add(btnCustomer);

        btnOrder = new JButton("ORDER");
        btnOrder.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnOrder.setBounds(320, 175, 180, 65);
        contentPane.add(btnOrder);

        btnProfile = new JButton("PROFIL");
        btnProfile.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnProfile.setBounds(55, 260, 180, 65);
        contentPane.add(btnProfile);

        btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setBounds(320, 260, 180, 65);
        contentPane.add(btnLogout);

        // ====================
        // ACTION
        // ====================

        btnProduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ProductFrame product = new ProductFrame();
                product.setVisible(true);

            }

        });

        btnCategory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                CategoryFrame category = new CategoryFrame();
                category.setVisible(true);

            }

        });

        btnCustomer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                CustomerFrame customer = new CustomerFrame();
                customer.setVisible(true);

            }

        });

        btnOrder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                OrderFrame order = new OrderFrame();
                order.setVisible(true);

            }

        });

        btnProfile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ProfileFrame profile = new ProfileFrame();
                profile.setVisible(true);

            }

        });

        btnLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int pilih = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION);

                if (pilih == JOptionPane.YES_OPTION) {

                    dispose();

                    LoginFrame login = new LoginFrame();
                    login.setVisible(true);

                }

            }

        });

    }

}