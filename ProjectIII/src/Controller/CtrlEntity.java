package Controller; // This line specifies the package to which this class belongs.

import Model.DAOEntity; // Importing the DAOEntity class from the Model package.
import Model.Entity; // Importing the Entity class from the Model package.
import java.util.List; // Importing the List class from the java.util package.
import javax.swing.JOptionPane; // Importing the JOptionPane class from the javax.swing package.
import javax.swing.JTable; // Importing the JTable class from the javax.swing package.
import javax.swing.JTextField; // Importing the JTextField class from the javax.swing package.
import javax.swing.table.DefaultTableModel; // Importing the DefaultTableModel class from the javax.swing.table package.
import javax.swing.table.TableModel; // Importing the TableModel class from the javax.swing.table package.
import javax.swing.table.TableRowSorter; // Importing the TableRowSorter class from the javax.swing.table package.

/**
 * This block is a JavaDoc comment describing the purpose of this class.
 *
 * @author fabri // Author's name.
 */
public class CtrlEntity { // Declaration of a class named CtrlEntity.

    DAOEntity daoentity = new DAOEntity(); // Creating an instance of the DAOEntity class.
    int id; // Declaring an integer variable named id.

    // This method loads data into a JTable.
    public void loadDataEntity(JTable tblentities) {
        DefaultTableModel model = (DefaultTableModel) tblentities.getModel(); // Creating a DefaultTableModel and casting it from the table's model.
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model); // Creating a TableRowSorter for the table.
        tblentities.setRowSorter(order); // Setting the row sorter for the JTable.
        model.setRowCount(0); // Setting the row count to 0, clearing any existing data.
        List<Entity> entity = daoentity.readEntity(); // Calling the readEntity method to retrieve a list of Entity objects.

        for (Entity entities : entity) { // Looping through the list of entities.
            id = entities.getId(); // Storing the ID of the current entity.
            Object[] row = {entities.getId(), entities.getLegal_id(), entities.getName(), entities.getEmail(), entities.getCelphone(), entities.getAddress(), entities.getDescription()}; // Creating an array of objects with entity data.
            model.addRow(row); // Adding the row to the table's model.
        }
    }

    // This method adds new entities to the data source and updates the table.
    public void addEntities(JTable tblentity, JTextField txtIdnumberentity, JTextField txtNameentity1, JTextField txtEmailentity1, JTextField txtCelphone1, JTextField txtAddressentity1, JTextField txtDescriptionentity1) {
        this.daoentity.createEntity(new Entity(Integer.parseInt(txtIdnumberentity.getText()), txtNameentity1.getText(), txtEmailentity1.getText(), Integer.parseInt(txtCelphone1.getText()), txtAddressentity1.getText(), txtDescriptionentity1.getText()));
    }

    // This method updates existing entities in the data source and updates the table.
    public void updatedEntities(JTextField txtidnumberentity, JTextField txtnameentity, JTextField txtemailentity, JTextField txtcelphone, JTextField txtaddressentity, JTextField txtdescriptionentity) {
        this.daoentity.updateEntity(new Entity(this.id, Integer.parseInt(txtidnumberentity.getText()), txtnameentity.getText(), txtemailentity.getText(), Integer.parseInt(txtcelphone.getText()), txtaddressentity.getText(), txtdescriptionentity.getText()));
    }

    // This method deletes an entity from the data source and updates the table.
    public void deleteEntities() {
        this.daoentity.deleteEntity(this.id);
    }

    // This method populates text fields with data from the selected row in the table.
    public void selectedRowEntity(JTable tblentities, JTextField txtidnumberentity, JTextField txtnameentity, JTextField txtemailentity, JTextField txtcelphone, JTextField txtaddressentity, JTextField txtDescriptionentity1) {
        try {
            int row = tblentities.getSelectedRow(); // Get the selected row index.
            if (row >= 0) {
                this.id = Integer.parseInt(tblentities.getValueAt(row, 0).toString()); // Store the ID from the selected row.
                txtidnumberentity.setText(tblentities.getValueAt(row, 1).toString()); // Set text fields with data from the selected row.
                txtnameentity.setText(tblentities.getValueAt(row, 2).toString());
                txtemailentity.setText(tblentities.getValueAt(row, 3).toString());
                txtcelphone.setText(tblentities.getValueAt(row, 4).toString());
                txtaddressentity.setText(tblentities.getValueAt(row, 5).toString());
                txtDescriptionentity1.setText(tblentities.getValueAt(row, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada"); // Show a message if no row is selected.
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString()); // Show an error message if an exception occurs.
        }
    }

    // This method clears text fields.
    public void clearEntities(JTextField txtidnumberentity, JTextField txtnameentity, JTextField txtemailentity, JTextField txtcelphone, JTextField txtaddressentity, JTextField txtdescriptionentity) {
        txtidnumberentity.setText(""); // Clear the text in each text field.
        txtnameentity.setText("");
        txtemailentity.setText("");
        txtcelphone.setText("");
        txtaddressentity.setText("");
        txtdescriptionentity.setText("");
    }
}
