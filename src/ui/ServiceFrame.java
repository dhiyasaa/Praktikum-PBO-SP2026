package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtJenis;
    private JTextField txtHarga;
    private JTextField txtStatus;
    private JTable tableService;

    ServiceRepo usr = new ServiceRepo();
    List<Service> ls;
    public String id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServiceFrame frame = new ServiceFrame();
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
    public ServiceFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 420);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblJenis = new JLabel("Jenis");
        lblJenis.setBounds(30, 35, 80, 20);
        contentPane.add(lblJenis);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(30, 65, 80, 20);
        contentPane.add(lblHarga);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(30, 95, 80, 20);
        contentPane.add(lblStatus);

        txtJenis = new JTextField();
        txtJenis.setBounds(120, 35, 300, 20);
        contentPane.add(txtJenis);
        txtJenis.setColumns(10);

        txtHarga = new JTextField();
        txtHarga.setBounds(120, 65, 300, 20);
        contentPane.add(txtHarga);
        txtHarga.setColumns(10);

        txtStatus = new JTextField();
        txtStatus.setBounds(120, 95, 300, 20);
        contentPane.add(txtStatus);
        txtStatus.setColumns(10);
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Service service = new Service();

                service.setJenis(txtJenis.getText());
                service.setHarga(txtHarga.getText());
                service.setStatus(txtStatus.getText());

                usr.save(service);

                reset();

                loadTable();

            }
        });
        btnSave.setBackground(Color.GREEN);
        btnSave.setBounds(20, 130, 90, 25);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Service service = new Service();

                service.setId(id);
                service.setJenis(txtJenis.getText());
                service.setHarga(txtHarga.getText());
                service.setStatus(txtStatus.getText());

                usr.update(service);

                reset();

                loadTable();

            }
        });
        btnUpdate.setBackground(Color.CYAN);
        btnUpdate.setBounds(120, 130, 90, 25);
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
        btnDelete.setBounds(220, 130, 90, 25);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                reset();

            }
        });
        btnCancel.setBackground(Color.YELLOW);
        btnCancel.setBounds(320, 130, 90, 25);
        contentPane.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 170, 440, 180);
        contentPane.add(scrollPane);

        tableService = new JTable();

        tableService.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = tableService.getSelectedRow();

                if (row != -1) {

                    id = tableService.getValueAt(row, 0).toString();

                    txtJenis.setText(
                            tableService.getValueAt(row, 1).toString());

                    txtHarga.setText(
                            tableService.getValueAt(row, 2).toString());

                    txtStatus.setText(
                            tableService.getValueAt(row, 3).toString());

                }

            }

        });

        scrollPane.setViewportView(tableService);

        loadTable();

    }
    public void reset() {

        txtJenis.setText("");
        txtHarga.setText("");
        txtStatus.setText("");

        id = null;

    }

    public void loadTable() {

        ls = usr.show();

        TableService ts = new TableService(ls);

        tableService.setModel(ts);

        tableService.getTableHeader().setVisible(true);

    }

}