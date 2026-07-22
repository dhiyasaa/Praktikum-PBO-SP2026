package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.CustomerRepo;
import model.Customer;
import model.CustomerBuilder;
import table.TableCustomer;

public class CustomerFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtId;
    private JTextField txtNama;
    private JTextField txtEmail;
    private JTextField txtNoHp;
    private JTextArea txtAlamat;

    private JTextField txtCari;

    private JTable table;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReset;
    private JButton btnBack;
    private JButton btnCari;

    CustomerRepo repo = new CustomerRepo();

    public CustomerFrame() {

        setTitle("Data Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,900,620);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        contentPane.setBackground(Color.WHITE);

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("DATA CUSTOMER");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255,105,180));
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,26));
        lblTitle.setBounds(220,20,450,35);
        contentPane.add(lblTitle);
        JLabel lblId = new JLabel("ID Customer");
        lblId.setBounds(40,90,100,25);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150,90,220,25);
        contentPane.add(txtId);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(40,125,100,25);
        contentPane.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(150,125,220,25);
        contentPane.add(txtNama);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(40,160,100,25);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150,160,220,25);
        contentPane.add(txtEmail);

        JLabel lblNoHp = new JLabel("No HP");
        lblNoHp.setBounds(450,90,100,25);
        contentPane.add(lblNoHp);

        txtNoHp = new JTextField();
        txtNoHp.setBounds(550,90,220,25);
        contentPane.add(txtNoHp);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(450,125,100,25);
        contentPane.add(lblAlamat);

        txtAlamat = new JTextArea();
        txtAlamat.setLineWrap(true);

        JScrollPane alamatPane = new JScrollPane(txtAlamat);
        alamatPane.setBounds(550,125,220,80);
        contentPane.add(alamatPane);
        
        btnSave = new JButton("SIMPAN");
        btnSave.setBounds(100,230,120,35);
        contentPane.add(btnSave);

        btnUpdate = new JButton("UBAH");
        btnUpdate.setBounds(250,230,120,35);
        contentPane.add(btnUpdate);

        btnDelete = new JButton("HAPUS");
        btnDelete.setBounds(400,230,120,35);
        contentPane.add(btnDelete);

        btnReset = new JButton("RESET");
        btnReset.setBounds(550,230,120,35);
        contentPane.add(btnReset);

        btnBack = new JButton("KEMBALI");
        btnBack.setBounds(700,230,120,35);
        contentPane.add(btnBack);
        JLabel lblCari = new JLabel("Cari Customer");
        lblCari.setBounds(30,280,100,25);
        contentPane.add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(130,280,180,25);
        contentPane.add(txtCari);

        txtCari.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                if(txtCari.getText().trim().isEmpty()){

                    loadData();

                }else{

                    table.setModel(
                            new TableCustomer(
                                    repo.search(txtCari.getText())
                            )
                    );

                }

            }

        });

        btnCari = new JButton("CARI");
        btnCari.setBounds(330,280,90,25);
        contentPane.add(btnCari);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30,320,825,220);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                txtId.setText(table.getValueAt(row, 0).toString());
                txtNama.setText(table.getValueAt(row, 1).toString());
                txtEmail.setText(table.getValueAt(row, 2).toString());
                txtAlamat.setText(table.getValueAt(row, 3).toString());
                txtNoHp.setText(table.getValueAt(row, 4).toString());

                txtId.setEditable(false);

            }

        });

        // ==========================
        // Tombol Kembali
        // ==========================

        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }

        });

        // ==========================
        // Tombol Simpan
        // ==========================

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if (txtNama.getText().isEmpty()
                            || txtEmail.getText().isEmpty()
                            || txtAlamat.getText().isEmpty()
                            || txtNoHp.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Lengkapi semua data!");
                        return;

                    }

                    Customer customer = new CustomerBuilder()
                            .setIdCustomer(txtId.getText())
                            .setNama(txtNama.getText())
                            .setEmail(txtEmail.getText())
                            .setAlamat(txtAlamat.getText())
                            .setNoHp(txtNoHp.getText())
                            .build();

                    repo.save(customer);

                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");

                    loadData();
                    resetForm();
                    generateId();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage());

                }

            }

        });

        // ==========================
        // Tombol Update
        // ==========================

        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if (txtId.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(null,
                                "Pilih data yang akan diubah!");
                        return;

                    }

                    if (txtNama.getText().isEmpty()
                            || txtEmail.getText().isEmpty()
                            || txtAlamat.getText().isEmpty()
                            || txtNoHp.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(null,
                                "Lengkapi semua data!");
                        return;

                    }

                    Customer customer = new CustomerBuilder()
                            .setIdCustomer(txtId.getText())
                            .setNama(txtNama.getText())
                            .setEmail(txtEmail.getText())
                            .setAlamat(txtAlamat.getText())
                            .setNoHp(txtNoHp.getText())
                            .build();

                    repo.update(customer);

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

        // ==========================
        // Tombol Hapus
        // ==========================

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtId.getText().isEmpty()) {

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

        // ==========================
        // Tombol Reset
        // ==========================

        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                resetForm();
                generateId();

            }

        });

        // ==========================
        // Tombol Cari
        // ==========================

        btnCari.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtCari.getText().trim().isEmpty()) {

                    loadData();

                } else {

                    List<Customer> list = repo.search(txtCari.getText());

                    table.setModel(new TableCustomer(list));

                }

            }

        });

        loadData();
        resetForm();
        generateId();

        }
    public void loadData() {

        List<Customer> list = repo.show();

        TableCustomer tableCustomer = new TableCustomer(list);

        table.setModel(tableCustomer);

    }

    public void resetForm() {

        txtNama.setText("");
        txtEmail.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        txtCari.setText("");

        txtNama.requestFocus();

    }

    public void generateId() {

        txtId.setText(repo.generateId());
        txtId.setEditable(false);

    }

    }
