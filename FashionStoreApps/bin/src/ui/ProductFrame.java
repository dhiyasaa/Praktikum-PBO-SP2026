package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Clothing;
import table.TableProduct;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import dao.ProductRepo;
import model.ProductBuilder;

public class ProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtId;
    private JTextField txtNama;
    private JTextField txtBrand;
    private JTextField txtWarna;
    private JTextField txtHarga;
    private JTextField txtStok;

    private JComboBox<String> cbKategori;
    private JComboBox<String> cbUkuran;

    private JTextField txtCari;
    private JButton btnCari;
    private JTable table;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReset;
    private JButton btnBack;

    ProductRepo repo = new ProductRepo();

    public ProductFrame() {

        setTitle("Data Produk");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,900,620);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        contentPane.setBackground(Color.WHITE);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("DATA PRODUK FASHION");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255,105,180));
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,26));
        lblTitle.setBounds(220,20,450,35);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("ID Produk");
        lblId.setBounds(40,90,100,25);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150,90,220,25);
        contentPane.add(txtId);

        JLabel lblNama = new JLabel("Nama Produk");
        lblNama.setBounds(40,125,100,25);
        contentPane.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150,125,220,25);
        contentPane.add(txtNama);

        JLabel lblKategori = new JLabel("Kategori");
        lblKategori.setBounds(40,160,100,25);
        contentPane.add(lblKategori);

        cbKategori = new JComboBox<>();
        cbKategori.addItem("Outer");
        cbKategori.addItem("Dress");
        cbKategori.addItem("Shirt");
        cbKategori.addItem("Pants");
        cbKategori.addItem("Skirt");
        cbKategori.addItem("Hijab");
        cbKategori.addItem("Accessories");
        cbKategori.setBounds(150,160,220,25);
        contentPane.add(cbKategori);

        JLabel lblBrand = new JLabel("Brand");
        lblBrand.setBounds(40,195,100,25);
        contentPane.add(lblBrand);

        txtBrand = new JTextField();
        txtBrand.setBounds(150,195,220,25);
        contentPane.add(txtBrand);

        JLabel lblUkuran = new JLabel("Ukuran");
        lblUkuran.setBounds(450,90,100,25);
        contentPane.add(lblUkuran);

        cbUkuran = new JComboBox<>();
        cbUkuran.addItem("S");
        cbUkuran.addItem("M");
        cbUkuran.addItem("L");
        cbUkuran.addItem("XL");
        cbUkuran.addItem("XXL");
        cbUkuran.setBounds(550,90,220,25);
        contentPane.add(cbUkuran);

        JLabel lblWarna = new JLabel("Warna");
        lblWarna.setBounds(450,125,100,25);
        contentPane.add(lblWarna);

        txtWarna = new JTextField();
        txtWarna.setBounds(550,125,220,25);
        contentPane.add(txtWarna);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(450,160,100,25);
        contentPane.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(550,160,220,25);
        contentPane.add(txtHarga);

        JLabel lblStok = new JLabel("Stok");
        lblStok.setBounds(450,195,100,25);
        contentPane.add(lblStok);

        txtStok = new JTextField();
        txtStok.setBounds(550,195,220,25);
        contentPane.add(txtStok);

        btnSave = new JButton("SIMPAN");
        btnSave.setBounds(100,260,120,35);
        contentPane.add(btnSave);

        btnUpdate = new JButton("UBAH");
        btnUpdate.setBounds(250,260,120,35);
        contentPane.add(btnUpdate);

        btnDelete = new JButton("HAPUS");
        btnDelete.setBounds(400,260,120,35);
        contentPane.add(btnDelete);

        btnReset = new JButton("RESET");
        btnReset.setBounds(550,260,120,35);
        contentPane.add(btnReset);

        btnBack = new JButton("KEMBALI");
        btnBack.setBounds(700,260,120,35);
        contentPane.add(btnBack);

        JLabel lblCari = new JLabel("Cari Produk");
        lblCari.setBounds(30,300,90,25);
        contentPane.add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(120,300,180,25);
        contentPane.add(txtCari);
        txtCari.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                if (txtCari.getText().trim().isEmpty()) {

                    loadData();

                } else {

                    table.setModel(
                        new TableProduct(
                            repo.search(txtCari.getText())
                        )
                    );

                }

            }

        });

        btnCari = new JButton("CARI");
        btnCari.setBounds(320,300,90,25);
        contentPane.add(btnCari);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30,340,825,220);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                txtId.setText(table.getValueAt(row, 0).toString());
                txtNama.setText(table.getValueAt(row, 1).toString());
                cbKategori.setSelectedItem(table.getValueAt(row, 2).toString());
                txtBrand.setText(table.getValueAt(row, 3).toString());
                cbUkuran.setSelectedItem(table.getValueAt(row, 4).toString());
                txtWarna.setText(table.getValueAt(row, 5).toString());
                txtHarga.setText(table.getValueAt(row, 6).toString());
                txtStok.setText(table.getValueAt(row, 7).toString());

                txtId.setEditable(false);

            }

        });

        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }

        });

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            	try {

            	    if (txtNama.getText().isEmpty()
            	            || txtBrand.getText().isEmpty()
            	            || txtWarna.getText().isEmpty()
            	            || txtHarga.getText().isEmpty()
            	            || txtStok.getText().isEmpty()) {

            	        JOptionPane.showMessageDialog(null, "Lengkapi semua data!");
            	        return;

            	    }

                    Clothing product = new ProductBuilder()

                            .setIdProduct(txtId.getText())
                            .setNamaProduct(txtNama.getText())
                            .setCategory(cbKategori.getSelectedItem().toString())
                            .setBrand(txtBrand.getText())
                            .setUkuran(cbUkuran.getSelectedItem().toString())
                            .setWarna(txtWarna.getText())
                            .setHarga(Double.parseDouble(txtHarga.getText()))
                            .setStok(Integer.parseInt(txtStok.getText()))

                            .build();

                    repo.save(product);

                    JOptionPane.showMessageDialog(null,
                            "Data berhasil disimpan.");

                    loadData();
                    resetForm();
                    generateId();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null,
                            ex.getMessage());

                }

            }

        });

        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            	try {
            		if(txtId.getText().isEmpty()){

            		    JOptionPane.showMessageDialog(null,
            		            "Pilih data yang akan diubah!");

            		    return;

            		}

            	    if (txtNama.getText().isEmpty()
            	            || txtBrand.getText().isEmpty()
            	            || txtWarna.getText().isEmpty()
            	            || txtHarga.getText().isEmpty()
            	            || txtStok.getText().isEmpty()) {

            	        JOptionPane.showMessageDialog(null, "Lengkapi semua data!");
            	        return;

            	    }

            	    Clothing product = new ProductBuilder()

                            .setIdProduct(txtId.getText())
                            .setNamaProduct(txtNama.getText())
                            .setCategory(cbKategori.getSelectedItem().toString())
                            .setBrand(txtBrand.getText())
                            .setUkuran(cbUkuran.getSelectedItem().toString())
                            .setWarna(txtWarna.getText())
                            .setHarga(Double.parseDouble(txtHarga.getText()))
                            .setStok(Integer.parseInt(txtStok.getText()))
                            .build();

                    repo.update(product);

                    JOptionPane.showMessageDialog(null,
                            "Data berhasil diupdate.");

                    loadData();
                    resetForm();
                    generateId();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null,
                            ex.getMessage());

                }

            }

        });

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(txtId.getText().isEmpty()){

            	    JOptionPane.showMessageDialog(null,
            	            "Pilih data yang akan dihapus!");

            	    return;

            	}

                int pilih = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin menghapus data?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);

                if (pilih == JOptionPane.YES_OPTION) {

                    repo.delete(txtId.getText());

                    JOptionPane.showMessageDialog(null,
                            "Data berhasil dihapus.");

                    loadData();
                    resetForm();
                    generateId();

                }

            }

        });

        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                resetForm();
                generateId();

            }

        });
        btnCari.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtCari.getText().trim().isEmpty()){

                    loadData();

                }else{

                    List<Clothing> list = repo.search(txtCari.getText());

                    table.setModel(new TableProduct(list));

                }

            }

        });
        loadData();
        resetForm();
        generateId();

        }

    public void loadData() {

        List<Clothing> list = repo.show();

        TableProduct tableProduct = new TableProduct(list);

        table.setModel(tableProduct);

    }

    public void resetForm() {

        txtNama.setText("");
        txtBrand.setText("");
        txtWarna.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtCari.setText("");

        cbKategori.setSelectedIndex(0);
        cbUkuran.setSelectedIndex(0);

        txtNama.requestFocus();

    }
    
    public void generateId() {

        txtId.setText(repo.generateId());
        txtId.setEditable(false);

    }
    

    }

