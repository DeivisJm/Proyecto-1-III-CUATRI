package Controller;

import Model.DAOEntity;
import Model.DAONascent;
import Model.DAOSampling_site;
import Model.DAOmeasurement;
import Model.Entity;
import Model.Measurement;
import Model.Nascent;
import Model.Sampling_site;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
public class CtrlMeasurement {

    DAOmeasurement me = new DAOmeasurement();
    DAONascent na = new DAONascent();
    DAOSampling_site st = new DAOSampling_site();
    int id;
    int nascentID;
    int samplingID;

    public void loadDataMeasurement(JTable tblCaudal) {
        DefaultTableModel model = (DefaultTableModel) tblCaudal.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        tblCaudal.setRowSorter(order);
        model.setRowCount(0);

        List<Measurement> measurements = me.readMeasurement();

        for (Measurement mes : measurements) {
            id = mes.getId();
            Object[] row = {mes.getId(), mes.getCapacity(), mes.getMethod(), mes.getObservation(), mes.getDate(), mes.getWeather(), me.getNameNacent(mes.getNascent_id()), me.getNameSampling(mes.getSamplingsite_id())};
            model.addRow(row);
        }
    }

    public void updatedMeasurement(JTextField txtidnumberentity, JTextField txtCapacity, JTextField txtMethod, JTextField txtObservation, JTextField txtDate, JTextField txtWeather) {

    }

    public void deleteM() {
        this.me.deleteMeasurement(this.id);
    }

    public void selectedRowMeasurement(JTable tblCaudal, JTextField txtCapacity4, JTextField txtMethod4,
            JTextField txtObservation4, JTextField txtDate4, JTextField txtClima4, JTextField txtRealizado4,
            JComboBox cbxNascent, JComboBox cbxSites) {
        try {
            int row = tblCaudal.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tblCaudal.getValueAt(row, 0).toString());
                txtCapacity4.setText(tblCaudal.getValueAt(row, 1).toString());
                txtMethod4.setText(tblCaudal.getValueAt(row, 2).toString());
                txtObservation4.setText(tblCaudal.getValueAt(row, 3).toString());
                txtDate4.setText(tblCaudal.getValueAt(row, 4).toString());
                txtClima4.setText(tblCaudal.getValueAt(row, 5).toString());
                txtRealizado4.setText(tblCaudal.getValueAt(row, 5).toString());
                cbxNascent.setSelectedItem((tblCaudal.getValueAt(row, 6).toString()));
                cbxSites.setSelectedItem((tblCaudal.getValueAt(row, 7).toString()));
                

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    public void clearMeasurements(JTextField txtCapacity4, JTextField txtMethod4, JTextField txtObservation4, JTextField txtDate4, JTextField txtClima4, JTextField txtRealizado4) {
        txtCapacity4.setText("");
        txtMethod4.setText("");
        txtObservation4.setText("");
        txtDate4.setText("");
        txtClima4.setText("");
        txtRealizado4.setText("");
    }

    public void addMeasurement(JTable tblCaudal, JTextField txtCapacity4, JTextField txtMethod4, JTextField txtObservation4, JTextField txtDate4, JTextField txtWeather4) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formato.parse(txtDate4.getText());
            this.me.createFlow(new Measurement(Double.parseDouble(txtCapacity4.getText()), txtMethod4.getText(), txtObservation4.getText(), fecha, txtWeather4.getText(), this.nascentID, this.samplingID));
        } catch (ParseException e) {

        }
    }

    public void getIdNacents(JComboBox Nascent) {
        this.nascentID = this.me.getIDNacent(Nascent.getSelectedItem().toString());
    }
    
    public void getIdSamplings(JComboBox Sampling) {
        this.samplingID = this.me.getIDSampling(Sampling.getSelectedItem().toString());
    }

    public void loadNascents(JComboBox cbxNascent) {
        List<Nascent> nascent = na.readNascent();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Nascent n : nascent) {
            model.addElement(n.getName());
        }
        cbxNascent.setModel(model);
    }

    public void loadSampling(JComboBox cbxSites) {
        List<Sampling_site> sites = st.readSampling_site();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Sampling_site e : sites) {
            model.addElement(e.getName());
        }
        cbxSites.setModel(model);
    }

}
