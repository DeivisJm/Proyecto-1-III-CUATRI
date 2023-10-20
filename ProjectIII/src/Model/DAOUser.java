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
public class DAOUser {

    public DAOUser() {
    }

    public void createUser(User user) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO users ( name, first_name, second_name, email, password, entity_id, role_id) VALUES ( ?, ?, ?, ?, ?, ?,? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getFirst_lastname());
            ps.setString(3, user.getSecond_lastname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getEntity_id());
            ps.setInt(7, user.getRol_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<User> readUser() {

        DBConnection db = new DBConnection();
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String first_lastname = resultSet.getString("first_name");
                String second_lastname = resultSet.getString("second_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int entity_id = resultSet.getInt("entity_id");
                int rol_id = resultSet.getInt("role_id");

                users.add(new User(id, name, first_lastname, second_lastname, email, password, entity_id, rol_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return users;
    }

    public void updateUser(User user) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE users SET name=?, first_name=?, second_name=?, email=?, password=?, entity_id=?, role_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getFirst_lastname());
            ps.setString(3, user.getSecond_lastname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getEntity_id());
            ps.setInt(7, user.getRol_id());
            ps.setInt(8, user.getId());
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

        String consultaSQL = "DELETE FROM users WHERE id=?";

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
