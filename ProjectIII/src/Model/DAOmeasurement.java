package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.DBConnection;
import java.util.Date;



/**
 *
 * @author fabri
 */
public class DAOmeasurement {
    
      public DAOmeasurement() {
    }

    public void createFlow(Measurement measurement) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO flow_measurements (capacity, method, observation, date, weather, nascent_id, samplingsite_id) VALUES (?, ?, ?, ?, ?, ? ,?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setDouble(1, measurement.getCapacity());
            ps.setString(2, measurement.getMethod());
            ps.setString(3, measurement.getObservation());
            ps.setDate(4, new java.sql.Date(measurement.getDate().getTime()));
            ps.setString(5, measurement.getWeather());
            ps.setInt(6, measurement.getNascent_id());
            ps.setInt(7, measurement.getSamplingsite_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se agrego correctamente la medicion");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<Measurement> readMeasurement() {

        DBConnection db = new DBConnection();
        List<Measurement> measurement= new ArrayList<>();
        String sql = "SELECT * FROM flow_measurements";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double capacity = resultSet.getDouble("capacity");
                String method = resultSet.getString("method");
                String observation = resultSet.getString("observation");
                Date date = resultSet.getDate("date");
                String weather = resultSet.getString("weather");
                int nascent_id = resultSet.getInt("nascent_id");
                int samplingsite_id = resultSet.getInt("samplingsite_id");
                measurement.add(new Measurement(id, capacity, method, observation, date, weather, nascent_id, samplingsite_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return measurement;
    }

    public void updateMeasurement(Measurement measurement) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE flow_measurements SET capacity=?, method=?, observation=?, date=?, weather=?, nascent_id=?, samplingsite_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setDouble(1, measurement.getCapacity());
            ps.setString(2, measurement.getMethod());
            ps.setString(3, measurement.getObservation());
            java.util.Date utilDate = measurement.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(4, sqlDate);
            ps.setString(5, measurement.getWeather());
            ps.setInt(6, measurement.getNascent_id());
            ps.setInt(7, measurement.getSamplingsite_id());
            ps.setInt(8, measurement.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "La modificación fue Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void deleteMeasurement(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM flow_measurements WHERE id=?";

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "El metodo fue elimanado correctamente");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
    
   
    
    
    public String getNameNacent(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM nascents WHERE id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }
    
    public String getNameSampling(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM sampling_sites WHERE id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }
    
    public int getIDNacent(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM nascents WHERE name = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }
    
     public int getIDSampling(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM sampling_sites WHERE name = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }
    

   
}
