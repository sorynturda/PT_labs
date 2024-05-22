package presentation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

/**
 * This is a static class which populates order, product and client tables using reflection
 */
public class PopulateTable {

    public static DefaultTableModel buildTableModel(List<?> list) {
        if(list.isEmpty())
            return new DefaultTableModel();
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Field field : list.get(0).getClass().getDeclaredFields()) {
            columnNames.add(field.getName());
        }
        for (Object obj : list) {
            Vector<Object> rowVector = new Vector<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    rowVector.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            data.add(rowVector);
        }
        return new DefaultTableModel(data, columnNames);
    }
}
