package Model;

import model.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author deivis
 */
public class DAOSampling_site {

    public DAOSampling_site() {
    }

    public void createSampling_site(Sampling_site sampling_site) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO sampling_sites ( name, province_id, canton_id, district_id, entity_id) VALUES (?, ?, ?, ?, ? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, sampling_site.getName());
            ps.setInt(2, sampling_site.getProvince_id());
            ps.setInt(3, sampling_site.getCanton_id());
            ps.setInt(4, sampling_site.getDistrict_id());
            ps.setInt(5, sampling_site.getEntity_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se agrego correctamente el Sitio de Muestreo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se agrego correctamente el Sitio de Muestreo, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<Sampling_site> readSampling_site() {

        DBConnection db = new DBConnection();
        List<Sampling_site> sampling_site = new ArrayList<>();
        String sql = "SELECT * FROM sampling_sites";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int province_id = resultSet.getInt("province_id");
                int canton_id = resultSet.getInt("canton_id");
                int district_id = resultSet.getInt("district_id");
                int entity_id = resultSet.getInt("entity_id");

                sampling_site.add(new Sampling_site(id, name, province_id, canton_id, district_id, entity_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return sampling_site;
    }

    public void updateSampling_site(Sampling_site sampling_site) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE sampling_site SET name=?, province_id=?, canton_id=?, district_id=?, entity_id=?,  WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(2, sampling_site.getProvince_id());
            ps.setInt(3, sampling_site.getCanton_id());
            ps.setInt(4, sampling_site.getDistrict_id());
            ps.setInt(5, sampling_site.getEntity_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "La modificación fue Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void deleteSampling_site(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM sampling_sites WHERE id=?";

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "El usuario fue elimanado correctamente");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }

    }

}
