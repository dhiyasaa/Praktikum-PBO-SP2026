package ui;

import java.awt.EventQueue;
import java.util.List;
import DAO.UserRepo;
import model.User;
import table.TableUser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTable tableUsers;
	
	UserRepo usr = new UserRepo();
	List<User> ls;
	public String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(113, 35, 285, 18);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(113, 63, 285, 18);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(113, 91, 285, 18);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(33, 38, 70, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(33, 66, 70, 12);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(33, 94, 70, 12);
		contentPane.add(lblPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        User user = new User();

		        user.setNama(txtName.getText());
		        user.setUsername(txtUsername.getText());
		        user.setPassword(txtPassword.getText());
		        usr.save(user);
		        reset();
		    }
		});
		btnSave.setBackground(new Color(0, 255, 0));
		btnSave.setForeground(new Color(0, 0, 0));
		btnSave.setBounds(33, 123, 84, 20);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(0, 0, 255));
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        User user = new User();

		        user.setNama(txtName.getText());
		        user.setUsername(txtUsername.getText());
		        user.setPassword(txtPassword.getText());
		        user.setId(id);

		        usr.update(user);

		        reset();

		        loadTable();
		    }
		});
		btnUpdate.setForeground(new Color(0, 0, 0));
		btnUpdate.setBounds(127, 123, 84, 20);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if (id != null) {

		            usr.delete(id);

		            reset();

		            loadTable();

		        } else {

		            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");

		        }

		    }
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setForeground(new Color(0, 0, 0));
		btnDelete.setBounds(221, 123, 84, 20);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 255, 0));
		btnCancel.setBounds(315, 123, 84, 20);
		contentPane.add(btnCancel);
		
		tableUsers = new JTable();
		tableUsers.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {

		        id = tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString();
		        txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1).toString());
		        txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2).toString());
		        txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3).toString());

		    }
		});
		tableUsers.setBounds(33, 153, 366, 192);
		contentPane.add(tableUsers);

	}
	
	public void reset() {
	    txtName.setText("");
	    txtUsername.setText("");
	    txtPassword.setText("");
	}
	public void loadTable() {
	    ls = usr.show();
	    TableUser tu = new TableUser(ls);
	    tableUsers.setModel(tu);
	    tableUsers.getTableHeader().setVisible(true);
	}
}
