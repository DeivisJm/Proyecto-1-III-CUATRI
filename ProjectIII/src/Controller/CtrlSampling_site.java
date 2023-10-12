package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.Sampling_site;
import Model.DAOSampling_site;
import javax.swing.JComboBox;

/**
 *
 * @author deivis
 */
public class CtrlSampling_site {

    DAOSampling_site dao = new DAOSampling_site();
    int id;

    public void loadDataSamplingSite(JTable tblSamplingSite) {

        DefaultTableModel model = (DefaultTableModel) tblSamplingSite.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblSamplingSite.setRowSorter(order);
        model.setRowCount(0);
        List<Sampling_site> samplingSites = dao.readSampling_site();
        for (Sampling_site samplingSite : samplingSites) {
            Object[] row = {samplingSite.getId(), samplingSite.getName(), samplingSite.getProvince_id(),
                samplingSite.getCanton_id(), samplingSite.getDistrict_id(), samplingSite.getEntity_id()};
            model.addRow(row);
        }
    }

    public void addSamplingSite(JTextField txtSamplingSiteName, JComboBox cbxProvinceId, JComboBox cbxCantonId, JComboBox cbxDistrict, JComboBox cbxEntityId) {
        this.dao.createSampling_site(new Sampling_site(txtSamplingSiteName.getText(),
                Integer.parseInt(cbxProvinceId.getSelectedItem().toString()),
                Integer.parseInt(cbxCantonId.getSelectedItem().toString()),
                Integer.parseInt(cbxDistrict.getSelectedItem().toString()),
                Integer.parseInt(cbxEntityId.getSelectedItem().toString())));
    }

    public void clearFields(JTextField txtSamplingSiteName) {
        txtSamplingSiteName.setText("");
    }

    public void updateSamplingSite(JTextField txtSamplingSiteName, JComboBox cbxProvinceId, JComboBox cbxCantonId, JComboBox cbxDistrict, JComboBox cbxEntityId) {
        this.dao.updateSampling_site(new Sampling_site(txtSamplingSiteName.getText(),
                Integer.parseInt(cbxProvinceId.getSelectedItem().toString()),
                Integer.parseInt(cbxCantonId.getSelectedItem().toString()),
                Integer.parseInt(cbxDistrict.getSelectedItem().toString()),
                Integer.parseInt(cbxEntityId.getSelectedItem().toString())));
    }

    public void deleteSamplingSite() {
        this.dao.deleteSampling_site(this.id);
    }

    public void selectedRow(JTable tblSamplingSite, JTextField txtSamplingSiteName, JComboBox cbxProvinceId, JComboBox cbxCantonId, JComboBox cbxDistrict, JComboBox cbxEntityId) {
        try {
            int row = tblSamplingSite.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tblSamplingSite.getValueAt(row, 0).toString());
                txtSamplingSiteName.setText(tblSamplingSite.getValueAt(row, 1).toString());
                cbxProvinceId.setSelectedItem(tblSamplingSite.getValueAt(row, 2).toString());
                cbxCantonId.setSelectedItem(tblSamplingSite.getValueAt(row, 3).toString());
                cbxDistrict.setSelectedItem(tblSamplingSite.getValueAt(row, 4).toString());
                cbxEntityId.setSelectedItem(tblSamplingSite.getValueAt(row, 5).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }
}
