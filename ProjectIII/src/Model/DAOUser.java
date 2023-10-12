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
public class DAOUser {

    public DAOUser() {
    }

    public void createUser(User user) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO user (id, name, first_name, second_name, email, password, entity_id, rol_id) VALUES (?, ?, ?, ?, ?, ?, ?,? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getFirst_lastname());
            ps.setString(4, user.getSecond_lastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getEntity_id());
            ps.setInt(8, user.getRol_id());

            ps.execute();
            JOptionPane.showMessageDialog(null, "Se agrego correctamente el usuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Se agrego correctamente el estudiante, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<User> readUser() {

        DBConnection db = new DBConnection();
        List<User> user = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String first_lastname = resultSet.getString("first_lastname");
                String second_lastname = resultSet.getString("second_lastname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int entity_id = resultSet.getInt("entity_id");
                int rol_id = resultSet.getInt("rol_id");

                user.add(new User(id, name, first_lastname, second_lastname, email, password, entity_id, rol_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return user;
    }

    public void updateUser(User user) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE user SET id=?, name=?, first_lastname=?, second_lastname=?, email=?, password=?, entity_id=?, rol_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getFirst_lastname());
            ps.setString(4, user.getSecond_lastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getEntity_id());
            ps.setInt(8, user.getRol_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "La modificación fue Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void deleteUser(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM user WHERE id=?";

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
