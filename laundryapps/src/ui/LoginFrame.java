package ui;

import java.awt.EventQueue;
import error.ValidationException;
import util.ValidationUtil;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import DAO.UserRepo;

import model.User;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(141, 174, 151, 19);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(141, 128, 151, 19);
		txtUsername.setColumns(10);
		contentPane.add(txtUsername);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps!");
		lblNewLabel.setBounds(141, 10, 151, 38);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(141, 106, 103, 12);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(141, 157, 83, 12);
		contentPane.add(lblNewLabel_2);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			    String username = txtUsername.getText();
			    String password = txtPassword.getText();

			    User user = new User();
			    user.setUsername(username);
			    user.setPassword(password);

			    try {

			        ValidationUtil.validate(user);

			        UserRepo repo = new UserRepo();

			        if (repo.login(user.getUsername(), user.getPassword())) {

			            JOptionPane.showMessageDialog(null, "Login Berhasil");

			            new MainFrame().setVisible(true);
			            dispose();

			        } else {

			            JOptionPane.showMessageDialog(
			                    null,
			                    "Login gagal. Username atau password salah.");

			        }

			    } catch (ValidationException ex) {

			        JOptionPane.showMessageDialog(null, ex.getMessage());

			    }

			}
		});
		btnLogin.setBounds(141, 203, 151, 20);
		contentPane.add(btnLogin);
	}	
}
