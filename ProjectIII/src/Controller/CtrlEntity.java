package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.DAOEntity;
import Model.Entity;
import javax.swing.JComboBox;

/**
 *
 * @author deivi
 */
public class CtrlEntity {

    DAOEntity dao = new DAOEntity();
    int id;

    public void loadDataEntity(JTable tblentity) {

        DefaultTableModel model = (DefaultTableModel) tblentity.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblentity.setRowSorter(order);
        model.setRowCount(0);
        List<Entity> entities = dao.readEntity();
        for (Entity entity : entities) {
            Object[] row = {entity.getId(), entity.getId_number(), entity.getName(),
                entity.getEmail(), entity.getCelphone(), entity.getAdress(),
                entity.getDescription()};
            model.addRow(row);
        }
    }

    public void addEntity(JTextField txtidnumberent, JTextField txtnameen, JTextField txtemailen, JTextField txtcelen, JTextField txtadressen, JTextField txtdescripen) {
        this.dao.createEntity(new Entity(txtidnumberent.getText(), txtnameen.getText(), txtemailen.getText(), txtcelen.getText(), txtadressen.getText(), txtdescripen.getText()));
    }

    public void clearFields(JTextField txtidnumberent, JTextField txtnameen, JTextField txtemailen, JTextField txtcelen, JTextField txtadressen, JTextField txtdescripen) {
        txtidnumberent.setText("");
        txtnameen.setText("");
        txtemailen.setText("");
        txtcelen.setText("");
        txtadressen.setText("");
        txtdescripen.setText("");
    }

    public void updateEntity(JTextField txtidnumberent, JTextField txtnameen, JTextField txtemailen, JTextField txtcelen, JTextField txtadressen, JTextField txtdescripen) {
        this.dao.updateEntity(new Entity(id, txtidnumberent.getText(), txtnameen.getText(), txtemailen.getText(), txtcelen.getText(), txtadressen.getText(), txtdescripen.getText()));
    }

    public void deleteEntity() {
        this.dao.deleteEntity(id);
    }

    public void selectedRow(JTable tblentity, JTextField txtidnumberent, JTextField txtnameen, JTextField txtemailen, JTextField txtcelen, JTextField txtadressen, JTextField txtdescripen) {
        try {
            int row = tblentity.getSelectedRow();
            if (row >= 0) {
                id = Integer.parseInt(tblentity.getValueAt(row, 0).toString());
                txtidnumberent.setText(tblentity.getValueAt(row, 1).toString());
                txtnameen.setText(tblentity.getValueAt(row, 2).toString());
                txtemailen.setText(tblentity.getValueAt(row, 3).toString());
                txtcelen.setText(tblentity.getValueAt(row, 4).toString());
                txtadressen.setText(tblentity.getValueAt(row, 5).toString());
                txtdescripen.setText(tblentity.getValueAt(row, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }
}
