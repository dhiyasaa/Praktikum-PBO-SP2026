package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import model.Order;
import model.OrderBuilder;
import table.TableOrder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.OrderRepo;

public class OrderFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtId;
    private JTextField txtTanggal;
    private JTextField txtCustomer;
    private JTextField txtProduct;
    private JTextField txtQty;
    private JTextField txtTotal;
    private JTextField txtCari;

    private JTable table;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReset;
    private JButton btnBack;
    private JButton btnCari;

    OrderRepo repo = new OrderRepo();

    public OrderFrame() {

        setTitle("Data Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,980,650);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        contentPane.setBackground(Color.WHITE);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("DATA ORDER");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255,105,180));
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,26));
        lblTitle.setBounds(250,20,420,35);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("ID Order");
        lblId.setBounds(40,90,100,25);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140,90,220,25);
        contentPane.add(txtId);

        JLabel lblTanggal = new JLabel("Tanggal");
        lblTanggal.setBounds(40,125,100,25);
        contentPane.add(lblTanggal);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(140,125,220,25);
        contentPane.add(txtTanggal);

        JLabel lblCustomer = new JLabel("ID Customer");
        lblCustomer.setBounds(40,160,100,25);
        contentPane.add(lblCustomer);

        txtCustomer = new JTextField();
        txtCustomer.setBounds(140,160,220,25);
        contentPane.add(txtCustomer);

        JLabel lblProduct = new JLabel("ID Product");
        lblProduct.setBounds(470,90,100,25);
        contentPane.add(lblProduct);

        txtProduct = new JTextField();
        txtProduct.setBounds(580,90,220,25);
        contentPane.add(txtProduct);

        JLabel lblQty = new JLabel("Qty");
        lblQty.setBounds(470,125,100,25);
        contentPane.add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(580,125,220,25);
        contentPane.add(txtQty);

        JLabel lblTotal = new JLabel("Total");
        lblTotal.setBounds(470,160,100,25);
        contentPane.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(580,160,220,25);
        txtTotal.setEditable(false);
        contentPane.add(txtTotal);

        btnSave = new JButton("SIMPAN");
        btnSave.setBounds(70,220,120,35);
        contentPane.add(btnSave);

        btnUpdate = new JButton("UBAH");
        btnUpdate.setBounds(220,220,120,35);
        contentPane.add(btnUpdate);

        btnDelete = new JButton("HAPUS");
        btnDelete.setBounds(370,220,120,35);
        contentPane.add(btnDelete);

        btnReset = new JButton("RESET");
        btnReset.setBounds(520,220,120,35);
        contentPane.add(btnReset);

        btnBack = new JButton("KEMBALI");
        btnBack.setBounds(670,220,120,35);
        contentPane.add(btnBack);

        JLabel lblCari = new JLabel("Cari Order");
        lblCari.setBounds(30,280,100,25);
        contentPane.add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(130,280,180,25);
        contentPane.add(txtCari);

        btnCari = new JButton("CARI");
        btnCari.setBounds(330,280,90,25);
        contentPane.add(btnCari);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30,320,900,250);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                txtId.setText(table.getValueAt(row,0).toString());
                txtTanggal.setText(table.getValueAt(row,1).toString());
                txtCustomer.setText(table.getValueAt(row,2).toString());
                txtProduct.setText(table.getValueAt(row,3).toString());
                txtQty.setText(table.getValueAt(row,4).toString());
                txtTotal.setText(table.getValueAt(row,5).toString());

                txtId.setEditable(false);

            }

        });
        txtQty.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                try{

                    if(txtQty.getText().isEmpty()){

                        txtTotal.setText("");

                        return;

                    }

                    // sementara harga manual
                    double harga = 100000;

                    int qty = Integer.parseInt(txtQty.getText());

                    txtTotal.setText(String.valueOf(harga * qty));

                }catch(Exception ex){

                    txtTotal.setText("");

                }

            }

        });
        btnBack.addActionListener(e -> dispose());
        btnReset.addActionListener(e -> {

            resetForm();
            generateId();

        });
        btnSave.addActionListener(e -> {

            try{

                if(txtTanggal.getText().isEmpty()
                        || txtCustomer.getText().isEmpty()
                        || txtProduct.getText().isEmpty()
                        || txtQty.getText().isEmpty()){

                    JOptionPane.showMessageDialog(null,
                            "Lengkapi data!");

                    return;

                }

                Order order = new OrderBuilder()

                        .setIdOrder(txtId.getText())
                        .setTanggal(txtTanggal.getText())
                        .setIdCustomer(txtCustomer.getText())
                        .setIdProduct(txtProduct.getText())
                        .setQty(Integer.parseInt(txtQty.getText()))
                        .setTotal(Double.parseDouble(txtTotal.getText()))
                        .build();

                repo.save(order);

                JOptionPane.showMessageDialog(null,
                        "Order berhasil disimpan.");

                loadData();
                resetForm();
                generateId();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(null,
                        ex.getMessage());

            }

        });
        btnUpdate.addActionListener(e -> {

            try{

                Order order = new OrderBuilder()

                        .setIdOrder(txtId.getText())
                        .setTanggal(txtTanggal.getText())
                        .setIdCustomer(txtCustomer.getText())
                        .setIdProduct(txtProduct.getText())
                        .setQty(Integer.parseInt(txtQty.getText()))
                        .setTotal(Double.parseDouble(txtTotal.getText()))
                        .build();

                repo.update(order);

                JOptionPane.showMessageDialog(null,
                        "Order berhasil diupdate.");

                loadData();
                resetForm();
                generateId();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(null,
                        ex.getMessage());

            }

        });
        btnDelete.addActionListener(e -> {

            if(txtId.getText().isEmpty()){

                JOptionPane.showMessageDialog(null,
                        "Pilih data terlebih dahulu.");

                return;

            }

            int pilih = JOptionPane.showConfirmDialog(
                    null,
                    "Hapus data ini?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);

            if(pilih == JOptionPane.YES_OPTION){

                repo.delete(txtId.getText());

                loadData();
                resetForm();
                generateId();

            }

        });
        btnCari.addActionListener(e -> {

            loadData();

        });
        loadData();
        resetForm();
        generateId();

        }
    public void loadData() {

        List<Order> list = repo.show();

        TableOrder tb = new TableOrder(list);

        table.setModel(tb);

    }

    public void resetForm() {

        txtTanggal.setText("");
        txtCustomer.setText("");
        txtProduct.setText("");
        txtQty.setText("");
        txtTotal.setText("");
        txtCari.setText("");

    }

    public void generateId() {

        txtId.setText(repo.generateId());

        txtId.setEditable(false);

    }
}