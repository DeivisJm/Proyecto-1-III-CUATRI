package Controller;

import Model.DAOEntity;
import Model.Entity;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fabri
 */
public class CtrlEntity {

    DAOEntity daoentity = new DAOEntity();
    int id;

    public void loadDataEntity(JTable tblentities) {
        DefaultTableModel model = (DefaultTableModel) tblentities.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblentities.setRowSorter(order);
        model.setRowCount(0);
        List<Entity> entity = daoentity.readEntity();

        for (Entity entities : entity) {
            id = entities.getId();
            Object[] row = {entities.getId(), entities.getLegal_id(), entities.getName(), entities.getEmail(), entities.getCelphone(), entities.getAddress(), entities.getDescription()};
            model.addRow(row);
        }
    }

    public void addEntities(JTable tblentity, JTextField txtIdnumberentity, JTextField txtNameentity1, JTextField txtEmailentity1, JTextField txtCelphone1, JTextField txtAddressentity1, JTextField txtDescriptionentity1) {
        this.daoentity.createEntity(new Entity(Integer.parseInt(txtIdnumberentity.getText()), txtNameentity1.getText(), txtEmailentity1.getText(), Integer.parseInt(txtCelphone1.getText()), txtAddressentity1.getText(), txtDescriptionentity1.getText()));
    }

    public void updatedEntities(JTextField txtidnumberentity, JTextField txtnameentity, JTextField txtemailentity, JTextField txtcelphone, JTextField txtaddressentity, JTextField txtdescriptionentity) {
        this.daoentity.updateEntity(new Entity(this.id, Integer.parseInt(txtidnumberentity.getText()), txtnameentity.getText(), txtemailentity.getText(), Integer.parseInt(txtcelphone.getText()), txtaddressentity.getText(), txtdescriptionentity.getText()));
    }

    public void deleteEntities() {
        this.daoentity.deleteEntity(this.id);
    }

    public void selectedRowEntity(JTable tblentities, JTextField txtidnumberentity, JTextField txtnameentity,
            JTextField txtemailentity, JTextField txtcelphone, JTextField txtaddressentity, JTextField txtDescriptionentity1) {
        try {
            int row = tblentities.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tblentities.getValueAt(row, 0).toString());
                txtidnumberentity.setText(tblentities.getValueAt(row, 1).toString());
                txtnameentity.setText(tblentities.getValueAt(row, 2).toString());
                txtemailentity.setText(tblentities.getValueAt(row, 3).toString());
                txtcelphone.setText(tblentities.getValueAt(row, 4).toString());
                txtaddressentity.setText(tblentities.getValueAt(row, 5).toString());
                txtDescriptionentity1.setText(tblentities.getValueAt(row, 6).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    public void clearEntities(JTextField txtidnumberentity, JTextField txtnameentity, JTextField txtemailentity, JTextField txtcelphone, JTextField txtaddressentity, JTextField txtdescriptionentity) {
        txtidnumberentity.setText("");
        txtnameentity.setText("");
        txtemailentity.setText("");
        txtcelphone.setText("");
        txtaddressentity.setText("");
        txtdescriptionentity.setText("");
    }
}
