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
public class DAOEntity {

    public DAOEntity() {
    }

    public void createEntity(Entity entity) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO entities (legal_id, name, email, celphone, address, description) VALUES (?, ?, ?, ?, ?, ? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, entity.getLegal_id());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getCelphone());
            ps.setString(5, entity.getAddress());
            ps.setString(6, entity.getDescription());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se agrego correctamente la entidad");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Se agrego correctamente la entidad, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<Entity> readEntity() {

        DBConnection db = new DBConnection();
        List<Entity> entity = new ArrayList<>();
        String sql = "SELECT * FROM entities";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_number = resultSet.getInt("legal_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int celphone = resultSet.getInt("celphone");
                String adress = resultSet.getString("address");
                String description = resultSet.getString("description");
                entity.add(new Entity(id,id_number, name, email, celphone, adress, description));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return entity;
    }

    public void updateEntity(Entity entity) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE entities SET legal_id=?, name=?, email=?, celphone=?, address=?, description=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, entity.getLegal_id());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getCelphone());
            ps.setString(5, entity.getAddress());
            ps.setString(6, entity.getDescription());
            ps.setInt(7, entity.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "La modificación fue Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void deleteEntity(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM entities WHERE id=?";

        try {
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "La entidad fue elimanada correctamente");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public int idEntity(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM entities WHERE name = ?";
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

    public String nameEntity(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM entities WHERE id = ?";
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
}