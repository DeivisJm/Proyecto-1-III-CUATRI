package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author deivis
 */
public class CtrlSampling_site {

    DAOSampling_site daoSampling_site = new DAOSampling_site();
    DAOProvince daoProvince = new DAOProvince();
    DAOCanton daoCanton = new DAOCanton();
    DAODistrict daoDistrict = new DAODistrict();
    DAOEntity daoentity = new DAOEntity();
    int id;

    public void loadDataSamplingSite(JTable tblSamplingSite) {

        DefaultTableModel model = (DefaultTableModel) tblSamplingSite.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblSamplingSite.setRowSorter(order);
        model.setRowCount(0);

        List<Sampling_site> sampling_sites = daoSampling_site.readSampling_site();

        for (Sampling_site sampling_site : sampling_sites) {
            String provinceName = daoProvince.getNameProvince(sampling_site.getProvince_id());
            String cantonName = daoCanton.getNameCanton(sampling_site.getCanton_id());
            String districtName = daoDistrict.getNameDistrict(sampling_site.getDistrict_id());
            String entityName = daoentity.nameEntity(sampling_site.getEntity_id());

            Object[] row = {sampling_site.getId(), sampling_site.getName(), provinceName, cantonName, districtName, entityName};
            model.addRow(row);
        }
    }

    public void addSamplingSite(JTextField txtSamplingSiteName, JComboBox<String> cbxProvince,
            JComboBox<String> cbxCanton, JComboBox<String> cbxDistrict, JTextField txtEntity) {
        String provinceName = cbxProvince.getSelectedItem().toString();
        int provinceId = daoProvince.getIDProvince(provinceName);

        String cantonName = cbxCanton.getSelectedItem().toString();
        int cantonId = daoCanton.getIDCanton(cantonName); // You should create a similar method in your DAO for getting Canton ID.

        String districtName = cbxDistrict.getSelectedItem().toString();
        int districtId = daoDistrict.getIDDistrict(districtName); // You should create a similar method in your DAO for getting District ID.

        String entityName = txtEntity.getText();
        int entityId = daoentity.idEntity(entityName);

        Sampling_site samplingSite = new Sampling_site(txtSamplingSiteName.getText(),
                provinceId, cantonId, districtId, entityId);
        daoSampling_site.createSampling_site(samplingSite);
    }

    public void clearFields(JTextField txtSamplingSiteName) {
        txtSamplingSiteName.setText("");

    }

    public void updateSamplingSite(JTextField txtSamplingSiteName, JComboBox<String> cbxProvince,
            JComboBox<String> cbxCanton, JComboBox<String> cbxDistrict, JComboBox<String> cbxsentity) {
        String provinceName = cbxProvince.getSelectedItem().toString();
        int provinceId = daoProvince.getIDProvince(provinceName);

        String cantonName = cbxCanton.getSelectedItem().toString();
        int cantonId = daoCanton.getIDCanton(cantonName); // You should create a similar method in your DAO for getting Canton ID.

        String districtName = cbxDistrict.getSelectedItem().toString();
        int districtId = daoDistrict.getIDDistrict(districtName); // You should create a similar method in your DAO for getting District ID.

        String entityName = cbxsentity.getSelectedItem().toString();
        int entityId = daoentity.idEntity(entityName);

        Sampling_site samplingSite = new Sampling_site(txtSamplingSiteName.getText(),
                provinceId, cantonId, districtId, entityId);
        daoSampling_site.createSampling_site(samplingSite);
    }

    public void deleteSamplingSite() {
        this.daoSampling_site.deleteSampling_site(this.id);
    }

    public void selectedRowSamplingSite(JTable tblSampling_site, JTextField txtSamplingSiteName, JComboBox<String> cbxProvince,
            JComboBox<String> cbxCanton, JComboBox<String> cbxDistrict, JComboBox<String> cbxsentity) {
        try {
            int row = tblSampling_site.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tblSampling_site.getValueAt(row, 0).toString());
                txtSamplingSiteName.setText(tblSampling_site.getValueAt(row, 1).toString());
                cbxProvince.setSelectedItem(tblSampling_site.getValueAt(row, 2).toString());
                cbxCanton.setSelectedItem(tblSampling_site.getValueAt(row, 3).toString());
                cbxDistrict.setSelectedItem(tblSampling_site.getValueAt(row, 4).toString());
                cbxsentity.setSelectedItem(tblSampling_site.getValueAt(row, 5).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    public void loadProvinces(JComboBox cbxProvince) {
        List<Province> provinces = daoProvince.readProvince();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Province p : provinces) {
            model.addElement(p.getName());
        }
        cbxProvince.setModel(model);
    }

    public void loadCantons(JComboBox cbxCanton) {
        List<Canton> cantons = daoCanton.readCanton();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Canton c : cantons) {
            model.addElement(c.getName());
        }
        cbxCanton.setModel(model);
    }

    public void loadDistrict(JComboBox cbxDistrict) {
        List<District> districties = daoDistrict.readDistrict();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (District d : districties) {
            model.addElement(d.getName());
        }
        cbxDistrict.setModel(model);
    }

    public void loadEntities(JComboBox cbxsentity) {
        List<Entity> entities = daoentity.readEntity();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Entity e : entities) {
            model.addElement(e.getName());
        }
        cbxsentity.setModel(model);
    }
}
