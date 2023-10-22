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
 * @author deivi
 */
public class CtrlNascent {

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

    public void loadDataNascent(JTable tblnascent) {

        DefaultTableModel model = (DefaultTableModel) tblnascent.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblnascent.setRowSorter(order);
        model.setRowCount(0);

        List<Nascent> nascents = daoNascent.readNascent();

        for (Nascent nascent : nascents) {
            String provinceName = daoProvince.getNameProvince(nascent.getProvince_id());
            String cantonName = daoCanton.getNameCanton(nascent.getCanton_id());
            String districtName = daoDistrict.getNameDistrict(nascent.getDistrict_id());
            String entityName = daoentity.nameEntity(nascent.getEntity_id());

            Object[] row = {nascent.getId(), nascent.getName(), nascent.getAddress(), nascent.getLatitude(), nascent.getLength(),
                nascent.getDescription(), provinceName, cantonName, districtName, entityName};
            model.addRow(row);
        }
    }

    

    public void deleteNascent() {
        this.daoNascent.deleteNascent(this.id);
    }

    public void selectedRowNascent(JTable tblnascent, JTextField txtnamenascent, JTextField txtaddressnascent, JTextField txtlatitudenascent,
            JTextField txtlengthnascent, JTextField txtdescriptionnascent, JComboBox<String> cbxProvince,
            JComboBox<String> cbxCanton, JComboBox<String> cbxDistrict, JComboBox<String> cbxentity) {
        try {
            int row = tblnascent.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tblnascent.getValueAt(row, 0).toString());
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
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }
    
  public void addNacents(JTable tblNascents, JTextField txtNamenascent2, JTextField txtAddressnascent2, JTextField txtLatitudenascent2, JTextField txtLengthnascent2, JTextField txtDescriptionnascent2) {
   
    this.daoNascent.createNascent(new Nascent(txtNamenascent2.getText(),txtAddressnascent2.getText(),Integer.parseInt(txtLatitudenascent2.getText()),Integer.parseInt(txtLengthnascent2.getText()),txtDescriptionnascent2.getText(), this.provinceID, this.cantonID, this.districtsID, this.entityID));
}

    
    public void getIdProvinces(JComboBox cbxProvince2) {
        this.provinceID = this.daoNascent.getIDProvince(cbxProvince2.getSelectedItem().toString());
    }
    
    public void getIdCantons(JComboBox Cantons) {
        this.cantonID = this.daoNascent.getIDCanton(Cantons.getSelectedItem().toString());
    }
     
    public void getIdDistrictis(JComboBox District) {
        this.districtsID= this.daoNascent.getIDDistrict(District.getSelectedItem().toString());
    }
      
    public void getIdEnty(JComboBox Entitys) {
        this.entityID= this.daoNascent.getIDEntity(Entitys.getSelectedItem().toString());
    }
    
    public void clearNascent(JTextField txtNamenascent2, JTextField txtAddressnascent2, JTextField txtLatitudenascent2, JTextField txtLengthnascent2, JTextField txtDescriptionnascent2) {
        txtNamenascent2.setText("");
        txtAddressnascent2.setText("");
        txtLatitudenascent2.setText("");
        txtLengthnascent2.setText("");
        txtDescriptionnascent2.setText("");
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
