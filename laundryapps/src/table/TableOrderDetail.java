package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.OrderDetail;

public class TableOrderDetail extends AbstractTableModel {

    List<OrderDetail> ls;

    private String[] columnNames = {
            "ID",
            "Order ID",
            "Service ID",
            "Harga",
            "Jumlah",
            "Total"
    };

    public TableOrderDetail(List<OrderDetail> ls) {
        this.ls = ls;
    }

    @Override
    public int getRowCount() {
        return ls.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

        case 0:
            return ls.get(rowIndex).getId();

        case 1:
            return ls.get(rowIndex).getOrder_id();

        case 2:
            return ls.get(rowIndex).getService_id();

        case 3:
            return ls.get(rowIndex).getHarga();

        case 4:
            return ls.get(rowIndex).getJumlah();

        case 5:
            return ls.get(rowIndex).getTotal();

        default:
            return null;

        }

    }

}