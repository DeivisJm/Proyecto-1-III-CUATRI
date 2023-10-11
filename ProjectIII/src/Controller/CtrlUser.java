package Controller;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.User;
import Model.UserDAO;
import javax.swing.JComboBox;

/**
 *
 * @author deivi
 */
public class CtrlUser {

    UserDAO dao = new UserDAO();
    int id;

    public void loadDataUser(JTable tbluser) {

        DefaultTableModel model = (DefaultTableModel) tbluser.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tbluser.setRowSorter(order);
        model.setRowCount(0);
        List<User> users = dao.read();
        for (User user : users) {
            Object[] row = {user.getId(),user.getName(),user.getFirst_lastname(),
                user.getSecond_lastname(),user.getEmail(), user.getPassword()};
            model.addRow(row);
        }
    }

    public void addUser(JTextField txtnameuser, JTextField txtlastnameuser, JTextField txtlasstnameuser, JTextField txtemailuser, JTextField txtpassworduser, JComboBox cbxentityid, JComboBox cbxrolid) {
        this.dao.create(new User(txtnameuser.getText(), txtlastnameuser.getText(), txtlasstnameuser.getText(), txtemailuser.getText(), txtpassworduser.getText(), Integer.parseInt(cbxentityid.getSelectedItem().toString()), Integer.parseInt(cbxrolid.getSelectedItem().toString())));
    }

    public void clearFields(JTextField txtnameuser, JTextField txtlastnameuser, JTextField txtlasstnameuser, JTextField txtemailuser, JTextField txtpassworduser) {
        txtnameuser.setText("");
        txtlastnameuser.setText("");
        txtlasstnameuser.setText("");
        txtemailuser.setText("");
        txtpassworduser.setText("");

    }

    public void updatedUser(JTextField txtnameuser, JTextField txtlastnameuser, JTextField txtlasstnameuser, JTextField txtemailuser, JTextField txtpassworduser, JComboBox cbxentityid, JComboBox cbxrolid) {

        this.dao.update(new User(txtnameuser.getText(), txtlastnameuser.getText(), txtlasstnameuser.getText(), txtemailuser.getText(), txtpassworduser.getText(), Integer.parseInt(cbxentityid.getSelectedItem().toString()), Integer.parseInt(cbxrolid.getSelectedItem().toString())));

    }

    public void deleteUser() {
        this.dao.delete(this.id);
    }

    public void selectedRow(JTable tbluser, JTextField txtnameuser, JTextField txtlastnameuser, JTextField txtlasstnameuser, JTextField txtemailuser, JTextField txtpassworduser, JComboBox cbxentityid, JComboBox cbxrolid) {
        try {
            int row = tbluser.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tbluser.getValueAt(row, 0).toString());
                txtnameuser.setText((tbluser.getValueAt(row, 1).toString()));
                txtlastnameuser.setText((tbluser.getValueAt(row, 2).toString()));
                txtlasstnameuser.setText((tbluser.getValueAt(row, 3).toString()));
                txtemailuser.setText((tbluser.getValueAt(row, 4).toString()));
                txtpassworduser.setText((tbluser.getValueAt(row, 5).toString()));
                cbxentityid.setSelectedItem((tbluser.getValueAt(row, 6).toString()));
                cbxrolid.setSelectedItem((tbluser.getValueAt(row, 7).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }

}
