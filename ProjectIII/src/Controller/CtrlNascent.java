package Controller;
// This line specifies the package where this class belongs.

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.*;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
// These lines import various Java libraries and classes needed for this controller.

/**
 *
 * @author deivi
 */
// This is a Javadoc comment providing information about the class and its author.

public class CtrlNascent {
    // This is the beginning of the class definition.

    DAONascent daoNascent = new DAONascent();
    DAOProvince daoProvince = new DAOProvince();
    DAOCanton daoCanton = new DAOCanton();
    DAODistrict daoDistrict = new DAODistrict();
    DAOEntity daoentity = new DAOEntity();
    int id;
    int provinceID;
    int cantonID;
    int districtsID;
    int entityID;
    // These lines declare class variables and initialize instances of various DAO classes and other variables.

    public void loadDataNascent(JTable tblnascent) {
        // This is the beginning of a method that loads data into a JTable.

        DefaultTableModel model = (DefaultTableModel) tblnascent.getModel();
        // Create a DefaultTableModel from the JTable's model.

        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblnascent.setRowSorter(order);
        // Create a TableRowSorter to enable sorting in the JTable.

        model.setRowCount(0);
        // Set the row count of the table model to 0, effectively clearing the table.

        List<Nascent> nascents = daoNascent.readNascent();
        // Retrieve a list of Nascent objects from the data access object.

        for (Nascent nascent : nascents) {
            // Loop through each Nascent object in the list.

            String provinceName = daoProvince.getNameProvince(nascent.getProvince_id());
            // Get the name of the province associated with the Nascent.

            String cantonName = daoCanton.getNameCanton(nascent.getCanton_id());
            // Get the name of the canton associated with the Nascent.

            String districtName = daoDistrict.getNameDistrict(nascent.getDistrict_id());
            // Get the name of the district associated with the Nascent.

            String entityName = daoentity.nameEntity(nascent.getEntity_id());
            // Get the name of the entity associated with the Nascent.

            Object[] row = {nascent.getId(), nascent.getName(), nascent.getAddress(), nascent.getLatitude(), nascent.getLength(),
                nascent.getDescription(), provinceName, cantonName, districtName, entityName};
            // Create an array with data to be displayed in a row of the JTable.

            model.addRow(row);
            // Add the row data to the table model.
        }
    }
    // This method populates the JTable with data from the database.

    public void deleteNascent() {
        this.daoNascent.deleteNascent(this.id);
    }
    // This method is used to delete a Nascent entry by calling the DAO's deleteNascent method.

    public void selectedRowNascent(JTable tblnascent, JTextField txtnamenascent, JTextField txtaddressnascent, JTextField txtlatitudenascent,
            JTextField txtlengthnascent, JTextField txtdescriptionnascent, JComboBox<String> cbxProvince,
            JComboBox<String> cbxCanton, JComboBox<String> cbxDistrict, JComboBox<String> cbxentity) {
        // This method is used to select a row in the JTable and populate text fields and combo boxes with the selected data.

        try {
            int row = tblnascent.getSelectedRow();
            // Get the currently selected row in the JTable.

            if (row >= 0) {
                // If a row is selected.

                this.id = Integer.parseInt(tblnascent.getValueAt(row, 0).toString());
                // Retrieve the ID from the selected row.

                // Populate text fields and combo boxes with data from the selected row.
                txtnamenascent.setText(tblnascent.getValueAt(row, 1).toString());
                txtaddressnascent.setText(tblnascent.getValueAt(row, 2).toString());
                txtlatitudenascent.setText(tblnascent.getValueAt(row, 3).toString());
                txtlengthnascent.setText(tblnascent.getValueAt(row, 4).toString());
                txtdescriptionnascent.setText(tblnascent.getValueAt(row, 5).toString());
                cbxProvince.setSelectedItem(tblnascent.getValueAt(row, 6).toString());
                cbxCanton.setSelectedItem(tblnascent.getValueAt(row, 7).toString());
                cbxDistrict.setSelectedItem(tblnascent.getValueAt(row, 8).toString());
                cbxentity.setSelectedItem(tblnascent.getValueAt(row, 9).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                // Display a message if no row is selected.
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
            // Handle any exceptions that occur and display an error message.
        }
    }
    // This method is used to handle row selection in the JTable and populate form fields with the selected data.

    public void addNacents(JTable tblNascents, JTextField txtNamenascent2, JTextField txtAddressnascent2, JTextField txtLatitudenascent2, JTextField txtLengthnascent2, JTextField txtDescriptionnascent2) {
        // This method is used to add a new Nascent entry.

        this.daoNascent.createNascent(new Nascent(txtNamenascent2.getText(), txtAddressnascent2.getText(), Integer.parseInt(txtLatitudenascent2.getText()), Integer.parseInt(txtLengthnascent2.getText()), txtDescriptionnascent2.getText(), this.provinceID, this.cantonID, this.districtsID, this.entityID));
        // Create a new Nascent object and pass it to the DAO to be added to the database.
    }
    // This method adds a new Nascent entry to the database.

    public void getIdProvinces(JComboBox cbxProvince2) {
        this.provinceID = this.daoNascent.getIDProvince(cbxProvince2.getSelectedItem().toString());
    }
    // This method gets the ID of the selected province from the JComboBox.

    public void getIdCantons(JComboBox Cantons) {
        this.cantonID = this.daoNascent.getIDCanton(Cantons.getSelectedItem().toString());
    }
    // This method gets the ID of the selected canton from the JComboBox.

    public void getIdDistrictis(JComboBox District) {
        this.districtsID = this.daoNascent.getIDDistrict(District.getSelectedItem().toString());
    }
    // This method gets the ID of the selected district from the JComboBox.

    public void getIdEnty(JComboBox Entitys) {
        this.entityID = this.daoNascent.getIDEntity(Entitys.getSelectedItem().toString());
    }
    // This method gets the ID of the selected entity from the JComboBox.

    public void clearNascent(JTextField txtNamenascent2, JTextField txtAddressnascent2, JTextField txtLatitudenascent2, JTextField txtLengthnascent2, JTextField txtDescriptionnascent2) {
        txtNamenascent2.setText("");
        txtAddressnascent2.setText("");
        txtLatitudenascent2.setText("");
        txtLengthnascent2.setText("");
        txtDescriptionnascent2.setText("");
    }
    // This method clears the text fields used for entering Nascent data.

    public void loadProvinces(JComboBox cbxProvince) {
        // This method populates a JComboBox with province names.

        List<Province> provinces = daoProvince.readProvince();
        // Get a list of Province objects from the database.

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        // Create a DefaultComboBoxModel for storing the province names.

        for (Province p : provinces) {
            model.addElement(p.getName());
            // Add each province name to the model.
        }

        cbxProvince.setModel(model);
        // Set the JComboBox's model to the created model containing province names.
    }

    public void loadCantons(JComboBox cbxCanton) {
        // This method populates a JComboBox with canton names.

        List<Canton> cantons = daoCanton.readCanton();
        // Get a list of Canton objects from the database.

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Canton c : cantons) {
            model.addElement(c.getName());
            // Add each canton name to the model.
        }

        cbxCanton.setModel(model);
        // Set the JComboBox's model to the created model containing canton names.
    }

    public void loadDistrict(JComboBox cbxDistrict) {
        // This method populates a JComboBox with district names.

        List<District> districties = daoDistrict.readDistrict();
        // Get a list of District objects from the database.

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (District d : districties) {
            model.addElement(d.getName());
            // Add each district name to the model.
        }

        cbxDistrict.setModel(model);
        // Set the JComboBox's model to the created model containing district names.
    }

    public void loadEntities(JComboBox cbxsentity) {
        // This method populates a JComboBox with entity names.

        List<Entity> entities = daoentity.readEntity();
        // Get a list of Entity objects from the database.

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Entity e : entities) {
            model.addElement(e.getName());
            // Add each entity name to the model.
        }

        cbxsentity.setModel(model);
        // Set the JComboBox's model to the created model containing entity names.
    }

    public void showEntity(JTable tblDatos, JComboBox<String> cbxEntityr) {
        DefaultTableModel model = (DefaultTableModel) tblDatos.getModel();
        model.setRowCount(0);
        // Clear the JTable by setting its row count to 0.

        List<Nascent> entityDetails = daoNascent.readNascent();
        // Get a list of Nascent objects from the database.

        for (Nascent report : entityDetails) {
            model.addRow(new Object[]{
                report.getId(),
                report.getName(),
                report.getAddress(),
                report.getLatitude(),
                report.getLength(),
                report.getDescription(),
                report.getProvince_id(),
                report.getCanton_id(),
                report.getDistrict_id()
            });
            // Add each Nascent object's details to the JTable.
        }
    }
}
// This method populates a JTable with data from the database, specifically for entity details.
