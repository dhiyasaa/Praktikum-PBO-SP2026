package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DAO.CostumerRepo;
import listener.DataListener;
import model.Costumer;
import table.TableCostumer;

public class DialogPelanggan extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTable tablePelanggan;
    private DataListener listener;

    CostumerRepo usr = new CostumerRepo();
    List<Costumer> ls;
    public String id;

    public DialogPelanggan(DataListener listener) {

        this.listener = listener;

        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setSize(450, 249);
        setLocationRelativeTo(null);
        setTitle("Data Pelanggan");
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 416, 182);
        getContentPane().add(scrollPane);

        tablePelanggan = new JTable();

        tablePelanggan.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                listener.onDataReceived(
                        tablePelanggan.getValueAt(tablePelanggan.getSelectedRow(), 0).toString(),
                        tablePelanggan.getValueAt(tablePelanggan.getSelectedRow(), 1).toString()
                );

                dispose();

            }

        });

        scrollPane.setViewportView(tablePelanggan);

        loadTable();

    }

    public void loadTable() {

        ls = usr.show();

        TableCostumer tu = new TableCostumer(ls);

        tablePelanggan.setModel(tu);

        tablePelanggan.getTableHeader().setVisible(true);

    }

}