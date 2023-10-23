package Controller;

import java.util.List;  // Import the List class from the java.util package.
import javax.swing.JOptionPane;  // Import the JOptionPane class from the javax.swing package.
import javax.swing.JTable;  // Import the JTable class from the javax.swing package.
import javax.swing.JTextField;  // Import the JTextField class from the javax.swing package.
import javax.swing.table.DefaultTableModel;  // Import the DefaultTableModel class from the javax.swing.table package.
import javax.swing.table.TableModel;  // Import the TableModel class from the javax.swing.table package.
import javax.swing.table.TableRowSorter;  // Import the TableRowSorter class from the javax.swing.table package.
import Model.*;  // Import classes or packages under the 'Model' package.
import javax.swing.DefaultComboBoxModel;  // Import the DefaultComboBoxModel class from the javax.swing package.
import javax.swing.JComboBox;  // Import the JComboBox class from the javax.swing package.


/**
 *
 * @author deivis
 */
public class CtrlSampling_site {
    // This line defines a public class named CtrlSampling_site.
    
    DAOSampling_site daoSampling_site = new DAOSampling_site();
    // This line creates an instance of the DAOSampling_site class and initializes it.
    
    DAOProvince daoProvince = new DAOProvince();
    // This line creates an instance of the DAOProvince class and initializes it.
    
    DAOCanton daoCanton = new DAOCanton();
    // This line creates an instance of the DAOCanton class and initializes it.
    
    DAODistrict daoDistrict = new DAODistrict();
    // This line creates an instance of the DAODistrict class and initializes it.
    
    DAOEntity daoentity = new DAOEntity();
    // This line creates an instance of the DAOEntity class and initializes it.
    
    int id;
    // This line declares an integer variable named "id."

    public void loadDataSamplingSite(JTable tblSamplingSite) {
        // This line defines a public method named "loadDataSamplingSite" that takes a JTable parameter.
        
        DefaultTableModel model = (DefaultTableModel) tblSamplingSite.getModel();
        // This line retrieves the table model associated with the input JTable and casts it to DefaultTableModel.
        
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        // This line creates a TableRowSorter for the table model.
        
        tblSamplingSite.setRowSorter(order);
        // This line sets the TableRowSorter as the row sorter for the JTable.
        
        model.setRowCount(0);
        // This line sets the row count of the table model to zero, effectively clearing the table.
        
        List<Sampling_site> sampling_sites = daoSampling_site.readSampling_site();
        // This line retrieves a list of Sampling_site objects by calling the "readSampling_site" method from the "daoSampling_site" object.

        for (Sampling_site sampling_site : sampling_sites) {
            // This line starts a loop to iterate through the list of Sampling_site objects.
            
            String provinceName = daoProvince.getNameProvince(sampling_site.getProvince_id());
            // This line retrieves the name of the province associated with the current Sampling_site object.
            
            String cantonName = daoCanton.getNameCanton(sampling_site.getCanton_id());
            // This line retrieves the name of the canton associated with the current Sampling_site object.
            
            String districtName = daoDistrict.getNameDistrict(sampling_site.getDistrict_id());
            // This line retrieves the name of the district associated with the current Sampling_site object.
            
            String entityName = daoentity.nameEntity(sampling_site.getEntity_id());
            // This line retrieves the name of the entity associated with the current Sampling_site object.
            
            Object[] row = {sampling_site.getId(), sampling_site.getName(), provinceName, cantonName, districtName, entityName};
            // This line creates an array of objects representing the data for the current Sampling_site object.
            
            model.addRow(row);
            // This line adds the row of data to the table model.
        }}


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
