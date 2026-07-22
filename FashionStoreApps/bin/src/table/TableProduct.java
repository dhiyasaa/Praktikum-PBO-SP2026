package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Clothing;

public class TableProduct extends AbstractTableModel {

    private List<Clothing> list;

    public TableProduct(List<Clothing> list) {
        this.list = list;
    }

    String[] kolom = {
            "ID",
            "Nama",
            "Kategori",
            "Brand",
            "Ukuran",
            "Warna",
            "Harga",
            "Stok"
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
    public Object getValueAt(int row, int column) {

        Clothing p = list.get(row);

        switch (column) {

        case 0:
            return p.getIdProduct();

        case 1:
            return p.getNamaProduct();

        case 2:
            return p.getCategory();

        case 3:
            return p.getBrand();

        case 4:
            return p.getUkuran();

        case 5:
            return p.getWarna();

        case 6:
            return p.getHarga();

        case 7:
            return p.getStok();

        }

        return null;

    }

}