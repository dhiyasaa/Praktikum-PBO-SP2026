package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Order;

public class TableOrder extends AbstractTableModel{

    List<Order> list;

    public TableOrder(List<Order> list) {
        this.list = list;
    }

    String[] kolom = {
            "ID Order",
            "Tanggal",
            "ID Customer",
            "ID Product",
            "Qty",
            "Total"
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

        Order order = list.get(rowIndex);

        switch(columnIndex){

        case 0:
            return order.getIdOrder();

        case 1:
            return order.getTanggal();

        case 2:
            return order.getIdCustomer();

        case 3:
            return order.getIdProduct();

        case 4:
            return order.getQty();

        case 5:
            return order.getTotal();

        default:
            return null;

        }

    }

}