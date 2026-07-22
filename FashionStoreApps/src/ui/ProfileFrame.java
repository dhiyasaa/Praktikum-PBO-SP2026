package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    public ProfileFrame() {

        setTitle("About");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,500,350);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("FASHION STORE APPS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,24));
        lblTitle.setForeground(new Color(255,105,180));
        lblTitle.setBounds(40,30,400,35);
        contentPane.add(lblTitle);

        JLabel lbl1 = new JLabel("Version : 1.0");
        lbl1.setBounds(60,100,300,25);
        contentPane.add(lbl1);

        JLabel lbl2 = new JLabel("Developer : Dhiya Safira Andini");
        lbl2.setBounds(60,130,300,25);
        contentPane.add(lbl2);

        JLabel lbl3 = new JLabel("Fashion Store-Proyek UAS SP PBO");
        lbl3.setBounds(60,160,300,25);
        contentPane.add(lbl3);

        JButton btnClose = new JButton("TUTUP");
        btnClose.setBounds(180,240,120,35);
        contentPane.add(btnClose);

        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }

        });

    }

}