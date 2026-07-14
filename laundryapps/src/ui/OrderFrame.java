package ui;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import DAO.OrderRepo;
import model.Order;
import table.TableOrder;

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblOrder;
	private JButton btnOrder;
	private JButton btnHapus;
	private JButton btnEditDetail;

	OrderRepo repo_od = new OrderRepo();
	List<Order> ls_od;

	String order_id = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
					frame.loadTableOrder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DATA ORDERAN");
		lblNewLabel.setBounds(10, 10, 114, 12);
		contentPane.add(lblNewLabel);
		
		btnOrder = new JButton("Buat Orderan");
		btnOrder.setBounds(10, 32, 114, 20);
		contentPane.add(btnOrder);
		btnOrder.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {

		    	OrderDetailFrame odf = new OrderDetailFrame(order_id);

		    	odf.addWindowListener(new java.awt.event.WindowAdapter() {
		    	    @Override
		    	    public void windowClosed(java.awt.event.WindowEvent e) {
		    	        loadTableOrder();
		    	    }
		    	});

		    	odf.setVisible(true);

		    	loadTableOrder();
		        odf.loadTableDetail();
		        odf.loadTableService();

		    }

		});
		
		btnEditDetail = new JButton("Edit/Detail");
		btnEditDetail = new JButton("Edit/Detail");
		btnEditDetail.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		        if(!order_id.equals("")){

		            OrderDetailFrame odf = new OrderDetailFrame(order_id);

		            odf.addWindowListener(new java.awt.event.WindowAdapter() {
		                @Override
		                public void windowClosed(java.awt.event.WindowEvent e) {
		                    loadTableOrder();
		                }
		            });

		            odf.setVisible(true);

		        }else{

		            JOptionPane.showMessageDialog(null,
		                    "Pilih data terlebih dahulu");

		        }

		    }
		});
		btnEditDetail.setBounds(384, 32, 114, 20);
		contentPane.add(btnEditDetail);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setBounds(290, 32, 84, 20);
		contentPane.add(btnHapus);
		btnHapus.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {

		        if(!order_id.equals("")){

		            repo_od.delete(order_id);

		            loadTableOrder();

		        }else{

		            JOptionPane.showMessageDialog(null,
		                    "Pilih data yang akan dihapus");

		        }

		    }

		});
		
		tblOrder = new JTable();
		tblOrder.setBounds(10, 62, 488, 191);
		contentPane.add(tblOrder);
		tblOrder.addMouseListener(new MouseAdapter() {

		    @Override
		    public void mouseClicked(MouseEvent e) {

		        order_id =
		                tblOrder.getValueAt(
		                        tblOrder.getSelectedRow(),0).toString();

		    }

		});
		

	}
	
	public void loadTableOrder() {

	    ls_od = repo_od.show();

	    TableOrder tu = new TableOrder(ls_od);

	    tblOrder.setModel(tu);

	    tblOrder.getTableHeader().setVisible(true);

	}
}
