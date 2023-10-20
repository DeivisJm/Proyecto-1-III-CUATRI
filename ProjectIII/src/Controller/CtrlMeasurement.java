
package Controller;

import Model.DAOEntity;
import Model.DAOmeasurement;
import Model.Entity;
import Model.Measurement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CtrlMeasurement {

    DAOmeasurement me = new DAOmeasurement();
    int id;

public void loadDataMeasurement(JTable tblCaudal) {
    DefaultTableModel model = (DefaultTableModel) tblCaudal.getModel();
    TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
    tblCaudal.setRowSorter(order);
    model.setRowCount(0);

    
    List<Measurement> measurements = me.readMeasurement();

    for (Measurement mes : measurements) {
        id = mes.getId();
        Object[] row = {mes.getId(), mes.getCapacity(), mes.getMethod(), mes.getObservation(), mes.getDate(), mes.getWeather(), mes.getNascent_id(), mes.getSamplingsite_id()};
        model.addRow(row);
    }
}


    public void addMeasurement(JTable tblCaudal, JTextField txtCapacity, JTextField txtMethod, JTextField txtOboservation, JTextField txtDate, JTextField txtWeather, JTextField txtNascent_id, JTextField txtSamplingsite_id) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato de fecha según tus necesidades
        Date date = dateFormat.parse(txtDate.getText());
        this.me.createFlow(new Measurement(
            Double.parseDouble(txtCapacity.getText()),
            txtMethod.getText(),
            txtOboservation.getText(),
            date, // Pasa el objeto Date en lugar de texto
            txtWeather.getText(),
            Integer.parseInt(txtNascent_id.getText()),
            Integer.parseInt(txtSamplingsite_id.getText())
        ));
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + e.getMessage());
    }
}

    public void updatedMeasurement(JTextField txtidnumberentity, JTextField txtCapacity, JTextField txtMethod, JTextField txtObservation, JTextField txtDate, JTextField txtWeather) {
        
    }

    public void deleteM() {
        this.me.deleteMeasurement(this.id);
    }

    public void selectedRowMeasurement(JTable tblCaudal, JTextField txtCapacity, JTextField txtMethod,
            JTextField txtObservation, JTextField txtDate, JTextField txtClima, JTextField txtRealizado) {
        try {
            int row = tblCaudal.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(tblCaudal.getValueAt(row, 0).toString());
                txtCapacity.setText(tblCaudal.getValueAt(row, 1).toString());
                txtMethod.setText(tblCaudal.getValueAt(row, 2).toString());
                txtObservation.setText(tblCaudal.getValueAt(row, 3).toString());
                txtDate.setText(tblCaudal.getValueAt(row, 4).toString());
                txtClima.setText(tblCaudal.getValueAt(row, 5).toString());
                txtRealizado.setText(tblCaudal.getValueAt(row, 5).toString());

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

    public void selectedRowMeasurement(JTable tblCaudal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
