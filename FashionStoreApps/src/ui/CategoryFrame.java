package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.CategoryRepo;
import model.Category;
import table.TableCategory;

public class CategoryFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtId;
    private JTextField txtNama;

    private JTable table;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReset;
    private JButton btnBack;

    CategoryRepo repo = new CategoryRepo();

    public CategoryFrame() {

        setTitle("Data Kategori");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,700,500);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("DATA KATEGORI");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,24));
        lblTitle.setForeground(new Color(255,105,180));
        lblTitle.setBounds(180,20,300,30);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("ID Kategori");
        lblId.setBounds(50,80,100,25);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(170,80,180,25);
        contentPane.add(txtId);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(50,120,100,25);
        contentPane.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(170,120,180,25);
        contentPane.add(txtNama);

        btnSave = new JButton("SIMPAN");
        btnSave.setBounds(420,80,120,30);
        contentPane.add(btnSave);

        btnUpdate = new JButton("UBAH");
        btnUpdate.setBounds(420,120,120,30);
        contentPane.add(btnUpdate);

        btnDelete = new JButton("HAPUS");
        btnDelete.setBounds(420,160,120,30);
        contentPane.add(btnDelete);

        btnReset = new JButton("RESET");
        btnReset.setBounds(420,200,120,30);
        contentPane.add(btnReset);

        btnBack = new JButton("KEMBALI");
        btnBack.setBounds(420,240,120,30);
        contentPane.add(btnBack);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40,300,600,150);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                txtId.setText(table.getValueAt(row, 0).toString());
                txtNama.setText(table.getValueAt(row, 1).toString());
                
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

                    if(txtId.getText().isEmpty() || txtNama.getText().isEmpty()){

                        JOptionPane.showMessageDialog(null,
                                "Lengkapi semua data!");

                        return;

                    }

                    Category category = new Category();

                    category.setIdCategory(txtId.getText());
                    category.setNamaCategory(txtNama.getText());

                    repo.save(category);

                    JOptionPane.showMessageDialog(null,
                            "Data berhasil disimpan.");

                    loadData();
                    resetForm();

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
                	if(txtId.getText().isEmpty() || txtNama.getText().isEmpty()){

                	    JOptionPane.showMessageDialog(null,
                	            "Lengkapi semua data!");

                	    return;

                	}
                    Category category = new Category();

                    category.setIdCategory(txtId.getText());
                    category.setNamaCategory(txtNama.getText());

                    repo.update(category);

                    JOptionPane.showMessageDialog(null,
                            "Data berhasil diupdate.");

                    loadData();
                    resetForm();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null,
                            ex.getMessage());

                }

            }

        });

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int pilih = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin menghapus data?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);

                if(pilih==JOptionPane.YES_OPTION){

                    repo.delete(txtId.getText());

                    JOptionPane.showMessageDialog(null,
                            "Data berhasil dihapus.");

                    loadData();
                    resetForm();

                }

            }

        });

        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                resetForm();

            }

        });

        loadData();
        resetForm();

        }
    public void loadData(){

        List<Category> list = repo.show();

        TableCategory tableCategory = new TableCategory(list);

        table.setModel(tableCategory);

    }

    public void resetForm(){

        txtId.setText("");
        txtNama.setText("");

        txtId.requestFocus();
    }
    }