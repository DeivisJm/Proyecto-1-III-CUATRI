package Model;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.DBConnection;

/**
 *
 * @author deivis
 */
public class DAODistrict {

    public DAODistrict() {
    }

    public void createDistrict(District district) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO district (id, name, canton_id) VALUES (?, ?, ? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, district.getId());
            ps.setString(2, district.getName());
            ps.setInt(3, district.getCanton_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se agrego correctamente el distrito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Se agrego correctamente el distrito, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<District> readDistrict() {

        DBConnection db = new DBConnection();
        List<District> district = new ArrayList<>();
        String sql = "SELECT * FROM district";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int canton_id = resultSet.getInt("canton_id");

                district.add(new District(id, name, canton_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return district;
    }

    public void updateDistrict(District district) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE district SET id=?, name=?, canton_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, district.getId());
            ps.setString(2, district.getName());
            ps.setInt(3, district.getCanton_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "La modificación fue Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void deleteDistrict(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM district WHERE id=?";

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "El distrito fue elimanado correctamente");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }

    }

}
