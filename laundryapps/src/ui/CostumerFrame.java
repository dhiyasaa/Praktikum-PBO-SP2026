package ui;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.border.EmptyBorder;

import DAO.CostumerRepo;
import model.Costumer;
import table.TableCostumer;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNoHp;
    private JTable tableCostumer;

    CostumerRepo usr = new CostumerRepo();
    List<Costumer> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CostumerFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 420);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(30,30,80,20);
        contentPane.add(lblNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(30,60,80,20);
        contentPane.add(lblAlamat);

        JLabel lblNoHp = new JLabel("No HP");
        lblNoHp.setBounds(30,90,80,20);
        contentPane.add(lblNoHp);

        txtNama = new JTextField();
        txtNama.setBounds(120,30,300,20);
        contentPane.add(txtNama);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(120,60,300,20);
        contentPane.add(txtAlamat);

        txtNoHp = new JTextField();
        txtNoHp.setBounds(120,90,300,20);
        contentPane.add(txtNoHp);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Costumer costumer = new Costumer();

                costumer.setNama(txtNama.getText());
                costumer.setAlamat(txtAlamat.getText());
                costumer.setNoHp(txtNoHp.getText());

                usr.save(costumer);

                reset();

                loadTable();

            }
        });
        btnSave.setBackground(Color.GREEN);
        btnSave.setBounds(20,130,90,25);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Costumer costumer = new Costumer();

                costumer.setId(id);
                costumer.setNama(txtNama.getText());
                costumer.setAlamat(txtAlamat.getText());
                costumer.setNoHp(txtNoHp.getText());

                usr.update(costumer);

                reset();

                loadTable();

            }
        });
        btnUpdate.setBackground(Color.CYAN);
        btnUpdate.setBounds(120,130,90,25);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (id != null) {

                    usr.delete(id);

                    reset();

                    loadTable();

                } else {

                    JOptionPane.showMessageDialog(null,
                            "Silahkan pilih data yang akan dihapus");

                }

            }
        });
        btnDelete.setBackground(Color.RED);
        btnDelete.setBounds(220,130,90,25);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reset();

            }
        });
        btnCancel.setBackground(Color.YELLOW);
        btnCancel.setBounds(320,130,90,25);
        contentPane.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20,170,440,180);
        contentPane.add(scrollPane);

        tableCostumer = new JTable();
        tableCostumer.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = tableCostumer.getSelectedRow();

                if (row != -1) {

                    id = tableCostumer.getValueAt(row, 0).toString();

                    txtNama.setText(tableCostumer.getValueAt(row, 1).toString());

                    txtAlamat.setText(tableCostumer.getValueAt(row, 2).toString());

                    txtNoHp.setText(tableCostumer.getValueAt(row, 3).toString());

                }

            }

        });
        scrollPane.setViewportView(tableCostumer);

        loadTable();
    }
    public void reset() {

        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");

        id = null;

    }

    public void loadTable() {

        ls = usr.show();

        TableCostumer tc = new TableCostumer(ls);

        tableCostumer.setModel(tc);

        tableCostumer.getTableHeader().setVisible(true);

    }
}