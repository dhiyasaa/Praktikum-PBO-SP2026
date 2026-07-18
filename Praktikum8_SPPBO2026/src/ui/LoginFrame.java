package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import error.ValidationException;
import model.User;
import service.LoginService;
import util.ValidationUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

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
		
		txtUsername = new JTextField();
		txtUsername.setBounds(82, 95, 272, 24);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(82, 147, 272, 24);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String userValue = txtUsername.getText();
			    String passValue = txtPassword.getText();

			    User user = new User(userValue, passValue);      // Create user object

			    try {
			        ValidationUtil.validate(user);
			        LoginService loginService = new LoginService();
			        if(loginService.authenticate(user)) {
			            System.out.println("Login successful!");
			            new MainFrame().setVisible(true);
			            dispose();
			        }
			        else {
			            System.out.println("Invalid username or password.");
			            JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
			        }
			    } catch (ValidationException | NullPointerException exception) {
			        System.out.println("Data tidak valid : " + exception.getMessage());
			        JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
			    } finally {
			        System.out.println("Selalu di eksekusi");
			    }
			}
		});
		btnNewButton.setBounds(82, 181, 272, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(82, 129, 200, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(82, 79, 177, 12);
		contentPane.add(lblUsername);

	}
}
