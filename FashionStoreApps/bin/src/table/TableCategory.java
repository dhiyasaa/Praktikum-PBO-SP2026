package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Category;

public class TableCategory extends AbstractTableModel{

    List<Category> list;

    public TableCategory(List<Category> list){

        this.list=list;

    }

    @Override
    public int getRowCount() {

        return list.size();

    }

    @Override
    public int getColumnCount() {

        return 2;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch(columnIndex){

        case 0:
            return list.get(rowIndex).getIdCategory();

        case 1:
            return list.get(rowIndex).getNamaCategory();

        }

        return null;

    }

    @Override
    public String getColumnName(int column){

        switch(column){

        case 0:
            return "ID";

        case 1:
            return "Nama Kategori";

        }

        return null;

    }

}