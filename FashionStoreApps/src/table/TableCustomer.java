package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class TableCustomer extends AbstractTableModel {

    List<Customer> list;

    public TableCustomer(List<Customer> list) {
        this.list = list;
    }

    String[] kolom = {
            "ID Customer",
            "Nama",
            "Email",
            "Alamat",
            "No. HP"
    };

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolom[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Customer customer = list.get(rowIndex);

        switch (columnIndex) {

        case 0:
            return customer.getIdCustomer();

        case 1:
            return customer.getNama();

        case 2:
            return customer.getEmail();

        case 3:
            return customer.getAlamat();

        case 4:
            return customer.getNoHp();

        default:
            return null;

        }

    }

}