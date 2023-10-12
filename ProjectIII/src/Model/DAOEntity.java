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
public class DAOEntity {

    public DAOEntity() {
    }

    public void createEntity(Entity entity) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO user (id, id_number, name, email, celphone, adress, description) VALUES (?, ?, ?, ?, ?, ?, ? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getId_number());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getEmail());
            ps.setInt(4, entity.getCelphone());
            ps.setString(5, entity.getAdress());
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
        String sql = "SELECT * FROM entity";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_number = resultSet.getInt("id_number");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int celphone = resultSet.getInt("celphone");
                String adress = resultSet.getString("adress");
                String description = resultSet.getString("description");
                entity.add(new Entity(id, id_number, name, email, celphone, adress, description));
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

        String consultaSQL = "UPDATE entity SET id=?, id_number=?, name=?, email=?, celphone=?, adress=?, description=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, entity.getId());
            ps.setInt(2, entity.getId_number());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getEmail());
            ps.setInt(4, entity.getCelphone());
            ps.setString(5, entity.getAdress());
            ps.setString(6, entity.getDescription());
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

        String consultaSQL = "DELETE FROM entity WHERE id=?";

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
}
