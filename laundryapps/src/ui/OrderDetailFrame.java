package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.ServiceRepo;
import model.Service;
import table.TableService;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import DAO.OrderDetailRepo;
import model.OrderDetail;
import table.TableOrderDetail;
import javax.swing.JOptionPane;
import listener.DataListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import DAO.OrderRepo;
import model.Order;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import DAO.CostumerRepo;
import model.Costumer;
import java.util.List;

public class OrderDetailFrame extends JFrame implements DataListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTrx;
	private JTextField txtPelanggan;
	private JDateChooser txtTanggal;
	private JDateChooser txtTanggalPengambilan;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel txtTotalOrder;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JComboBox cbxPembayaran;
	private JComboBox cbxStatusPembayaran;
	private JTable tableService;
	private JLabel lblHargakg;
	private JTextField txtHarga;
	private JLabel lblHargakg_1;
	private JTextField txtJumlah;
	private JLabel lblHargakg_2;
	private JTextField txtTotal;
	private JButton btnSimpanDetail;
	private JButton btnUbahDetail;
	private JButton btnHapusDetail;
	private JButton btnBatalDetail;
	private JTable tableOrderDetail;
	private JComboBox cbxStatus;
	private JButton btnSimpanOrder;
	private JButton btnBatalOrder;
	
	ServiceRepo sr = new ServiceRepo();
	OrderRepo repo_order = new OrderRepo();
	OrderDetailRepo repo_od = new OrderDetailRepo();
	CostumerRepo repo_cs = new CostumerRepo();

	List<Service> ls_service;
	List<OrderDetail> ls_od;
	List<Costumer> ls_cs;

	public String id_service;
	public String id_order_detail;
	public String id_pelanggan;
	private boolean editMode = false;
	
	public static String txt_pelanggan = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                OrderDetailFrame frame = new OrderDetailFrame();
	                frame.setVisible(true);

	                frame.loadTableService();
	                frame.loadTableDetail();

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	/**
	 * Create the frame.
	 */
	public OrderDetailFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order ID");
		lblNewLabel.setBounds(10, 25, 169, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pelanggan");
		lblNewLabel_1.setBounds(10, 70, 169, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tanggal");
		lblNewLabel_2.setBounds(9, 119, 169, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tanggal Pengambilan");
		lblNewLabel_3.setBounds(10, 168, 169, 23);
		contentPane.add(lblNewLabel_3);
		
		txtTrx = new JTextField();
		txtTrx.setBounds(10, 47, 169, 23);
		contentPane.add(txtTrx);
		txtTrx.setColumns(10);
		
		txtPelanggan = new JTextField();
		txtPelanggan.setBounds(10, 92, 169, 23);
		txtPelanggan.setColumns(10);
		contentPane.add(txtPelanggan);
		txtPelanggan.addMouseListener(new MouseAdapter() {

		    @Override
		    public void mouseClicked(MouseEvent e) {

		        DialogPelanggan dialog =
		                new DialogPelanggan(OrderDetailFrame.this);

		        dialog.setVisible(true);

		    }

		});
		
		txtTanggal = new JDateChooser();
		txtTanggal.setBounds(10,141,169,23);
		txtTanggal.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtTanggal);
		
		txtTanggalPengambilan = new JDateChooser();
		txtTanggalPengambilan.setBounds(10,193,169,23);
		txtTanggalPengambilan.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtTanggalPengambilan);
		
		cbxStatus = new JComboBox();
		cbxStatus.setBounds(10, 237, 169, 23);
		contentPane.add(cbxStatus);

		cbxStatus.addItem("Proses");
		cbxStatus.addItem("Selesai");
		
		lblNewLabel_4 = new JLabel("Status");
		lblNewLabel_4.setBounds(10, 215, 169, 23);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Total Order");
		lblNewLabel_5.setBounds(10, 270, 169, 13);
		contentPane.add(lblNewLabel_5);
		
		txtTotalOrder = new JLabel("Total Order");
		txtTotalOrder.setBounds(10, 293, 169, 23);
		contentPane.add(txtTotalOrder);
		
		lblNewLabel_6 = new JLabel("Pembayaran");
		lblNewLabel_6.setBounds(10, 326, 169, 13);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Status Pembayaran");
		lblNewLabel_7.setBounds(10, 375, 169, 13);
		contentPane.add(lblNewLabel_7);
		
		cbxPembayaran = new JComboBox();
		cbxPembayaran.setBounds(10, 342, 169, 23);
		contentPane.add(cbxPembayaran);

		cbxPembayaran.addItem("Cash");
		cbxPembayaran.addItem("Transfer");
		
		cbxStatusPembayaran = new JComboBox();
		cbxStatusPembayaran.setBounds(10, 394, 169, 23);
		contentPane.add(cbxStatusPembayaran);

		cbxStatusPembayaran.addItem("Belum Lunas");
		cbxStatusPembayaran.addItem("Lunas");
		
		btnSimpanOrder = new JButton("Simpan");
		btnSimpanOrder.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {

		        if(id_pelanggan != null && !id_pelanggan.isEmpty()){

		            Order order = new Order();

		            order.setId(txtTrx.getText());
		            order.setId_pelanggan(id_pelanggan);
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		            order.setTanggal(
		                    sdf.format(txtTanggal.getDate()));

		            order.setTanggal_pengambilan(
		                    sdf.format(txtTanggalPengambilan.getDate()));
		            order.setStatus(cbxStatus.getSelectedItem().toString());
		            order.setPembayaran(cbxPembayaran.getSelectedItem().toString());
		            order.setStatus_pembayaran(cbxStatusPembayaran.getSelectedItem().toString());
		            order.setTotal(txtTotalOrder.getText());

		            if(editMode){

		            	repo_order.update(order);

		            	JOptionPane.showMessageDialog(null, "Order berhasil diupdate");

		            	dispose();

		            }else{

		                repo_order.save(order);

		                JOptionPane.showMessageDialog(
		                        null,
		                        "Order berhasil disimpan");

		            }
		            loadTableDetail();
		            dispose();

		            JOptionPane.showMessageDialog(null,"Order Berhasil Disimpan");
		         // Generate nomor transaksi berikutnya
		            txtTrx.setText(repo_order.generateOrderID());
		            
		            // reset form
		            txtPelanggan.setText("");
		            txtTanggal.setDate(null);
		            txtTanggalPengambilan.setDate(null);

		            txtTotalOrder.setText("0");
		            tableOrderDetail.setModel(
		                    new javax.swing.table.DefaultTableModel());
		            id_pelanggan = "";
		            id_order_detail = null;
		            id_service = null;


		        }else{

		            JOptionPane.showMessageDialog(null,
		                    "Silahkan pilih pelanggan terlebih dahulu");

		        }

		    }

		});
		btnSimpanOrder.setBounds(10, 427, 84, 20);
		contentPane.add(btnSimpanOrder);
		
		btnBatalOrder = new JButton("Batal");
		btnBatalOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBatalOrder.setBounds(95, 427, 84, 20);
		contentPane.add(btnBatalOrder);
		
		JLabel lblLayanan = new JLabel("Layanan");
		lblLayanan.setBounds(203, 25, 169, 23);
		contentPane.add(lblLayanan);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			    id_service = tableService.getValueAt(tableService.getSelectedRow(),0).toString();

			    txtHarga.setText(
			        tableService.getValueAt(tableService.getSelectedRow(),3).toString()
			    );

			    if(!txtJumlah.getText().isEmpty()){
			        txtTotal.setText(""+total(txtJumlah.getText()));
			    }

			}
		});
		tableService.setBounds(203, 47, 352, 76);
		contentPane.add(tableService);
		
		lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setBounds(203, 124, 169, 23);
		contentPane.add(lblHargakg);
		
		txtHarga = new JTextField();
		txtHarga.setBounds(203, 143, 169, 23);
		txtHarga.setColumns(10);
		contentPane.add(txtHarga);
		
		lblHargakg_1 = new JLabel("Jumlah");
		lblHargakg_1.setBounds(203, 168, 169, 23);
		contentPane.add(lblHargakg_1);
		
		txtJumlah = new JTextField();
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

			    String value_jumlah = txtJumlah.getText().toString();

			    txtTotal.setText("" + total(value_jumlah));

			}
		});
		txtJumlah.setBounds(203, 187, 169, 23);
		txtJumlah.setColumns(10);
		contentPane.add(txtJumlah);
		
		lblHargakg_2 = new JLabel("Total");
		lblHargakg_2.setBounds(386, 168, 169, 23);
		contentPane.add(lblHargakg_2);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(386, 187, 169, 23);
		txtTotal.setColumns(10);
		contentPane.add(txtTotal);
		
		btnSimpanDetail = new JButton("Simpan");
		btnSimpanDetail.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if(id_order_detail == null){

		            OrderDetail od = new OrderDetail();

		            od.setOrder_id(txtTrx.getText());
		            od.setService_id(id_service);
		            od.setHarga(txtHarga.getText());
		            od.setJumlah(txtJumlah.getText());
		            od.setTotal(txtTotal.getText());

		            repo_od.save(od);

		            JOptionPane.showMessageDialog(null, "Berhasil disimpan");

		            loadTableDetail();

		            txtTotalOrder.setText("" + repo_od.total(txtTrx.getText()));

		            reset();

		        }

		    }
		});
		btnSimpanDetail.setBounds(203, 218, 84, 20);
		contentPane.add(btnSimpanDetail);
		
		btnUbahDetail = new JButton("Ubah");
		btnUbahDetail.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if(id_order_detail != null){

		            OrderDetail od = new OrderDetail();

		            od.setId(id_order_detail);
		            od.setOrder_id(txtTrx.getText());
		            od.setService_id(id_service);
		            od.setHarga(txtHarga.getText());
		            od.setJumlah(txtJumlah.getText());
		            od.setTotal(txtTotal.getText());

		            repo_od.update(od);

		            loadTableDetail();

		            reset();

		            txtTotalOrder.setText("" + repo_od.total(txtTrx.getText()));

		        }else{

		            JOptionPane.showMessageDialog(null,
		                    "Silahkan pilih order terlebih dahulu");

		        }

		    }
		});
		btnUbahDetail.setBounds(292, 216, 84, 20);
		contentPane.add(btnUbahDetail);
		
		btnHapusDetail = new JButton("Hapus");
		btnHapusDetail.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        if(id_order_detail != null){

		            repo_od.delete(id_order_detail);

		            loadTableDetail();

		            reset();

		            txtTotalOrder.setText("" + repo_od.total(txtTrx.getText()));

		        }else{

		            JOptionPane.showMessageDialog(null,
		                    "Silahkan pilih data yang akan dihapus");

		        }

		    }
		});
		btnHapusDetail.setBounds(386, 216, 84, 20);
		contentPane.add(btnHapusDetail);
		
		btnBatalDetail = new JButton("Batal");
		btnBatalDetail.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		    }
		});
		btnBatalDetail.setBounds(471, 218, 84, 20);
		contentPane.add(btnBatalDetail);
		
		tableOrderDetail = new JTable();
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_order_detail = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),0).toString();

				id_service = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),2).toString();

				txtHarga.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),3).toString());
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),4).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),5).toString());
			}
		});
		tableOrderDetail.setBounds(203, 248, 352, 199);
		contentPane.add(tableOrderDetail);
		txtTrx.setText(repo_order.generateOrderID());
	}
	public OrderDetailFrame(String id) {

	    this();

	    editMode = true;

	    loadData(id);

	}
	// load table service
	public void loadTableService() {

	    ls_service = sr.show();

	    TableService tu = new TableService(ls_service);

	    tableService.setModel(tu);

	    tableService.getTableHeader().setVisible(true);

	}
	public void loadTableDetail() {

	    ls_od = repo_od.show(txtTrx.getText());

	    TableOrderDetail tu = new TableOrderDetail(ls_od);

	    tableOrderDetail.setModel(tu);

	    tableOrderDetail.getTableHeader().setVisible(true);

	}
	public double total(String jumlah) {

	    double result = 0;

	    if(jumlah.isEmpty()){

	        result = 0;

	    }else{

	        result = Double.parseDouble(jumlah)
	                * Double.parseDouble(txtHarga.getText());

	    }

	    return result;

	}
	public void reset() {

	    txtHarga.setText("");
	    txtJumlah.setText("");
	    txtTotal.setText("");

	    id_service = null;
	    id_order_detail = null;

	}
	public void loadData(String id) {

	    Order od = repo_order.getData(id);

	    if (od != null) {

	        txtTrx.setText(od.getId());

	        id_pelanggan = od.getId_pelanggan();
	        ls_cs = repo_cs.show();

	        for (Costumer cs : ls_cs) {

	            if (cs.getId().equals(id_pelanggan)) {

	                txtPelanggan.setText(cs.getNama());

	                break;

	            }

	        }

	        txtTanggal.setDate(java.sql.Date.valueOf(od.getTanggal()));

	        txtTanggalPengambilan.setDate(
	                java.sql.Date.valueOf(od.getTanggal_pengambilan()));

	        cbxStatus.setSelectedItem(od.getStatus());

	        cbxPembayaran.setSelectedItem(od.getPembayaran());

	        cbxStatusPembayaran.setSelectedItem(
	                od.getStatus_pembayaran());

	        txtTotalOrder.setText(od.getTotal());

	        loadTableDetail();

	    }

	}
	@Override
	public void onDataReceived(String id, String nama) {

	    txtPelanggan.setText(nama);

	    id_pelanggan = id;

	}
	
}
