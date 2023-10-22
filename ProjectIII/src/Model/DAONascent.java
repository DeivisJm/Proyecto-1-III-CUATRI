package Model;

/**
 *
 * @author deivis
 */
import model.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAONascent {

    public DAONascent() {
    }

    public void createNascent(Nascent nascent) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO nascents ( name, address, latitude, length, description,"
                + " province_id, canton_id, district_id, entity_id) VALUES (?, ?, ?, ?, ?, ?, ? ,? ,? )";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, nascent.getName());
            ps.setString(2, nascent.getAddress());
            ps.setInt(3, nascent.getLatitude());
            ps.setInt(4, nascent.getLength());
            ps.setString(5, nascent.getDescription());
            ps.setInt(6, nascent.getProvince_id());
            ps.setInt(7, nascent.getCanton_id());
            ps.setInt(8, nascent.getDistrict_id());
            ps.setInt(9, nascent.getEntity_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se agrego correctamente la Naciente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se agrego correctamente la Naciente, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    public List<Nascent> readNascent() {

        DBConnection db = new DBConnection();
        List<Nascent> nascent = new ArrayList<>();
        String sql = "SELECT * FROM nascents";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int latitude = resultSet.getInt("latitude");
                int length = resultSet.getInt("length");
                String description = resultSet.getString("description");
                int province_id = resultSet.getInt("province_id");
                int canton_id = resultSet.getInt("canton_id");
                int district_id = resultSet.getInt("district_id");
                int entity_id = resultSet.getInt("entity_id");

                nascent.add(new Nascent(id, name, address, latitude, length, description, province_id, canton_id, district_id, entity_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return nascent;
    }

    public void updateNascent(Nascent nascent) {

        DBConnection db = new DBConnection();

        String consultaSQL = "UPDATE nascents SET name=?, address=?,latitude=?, length=?,description=?,"
                + " province_id=?, canton_id=?, district_id=?, entity_id=?,  WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, nascent.getName());
            ps.setString(2, nascent.getAddress());
            ps.setInt(3, nascent.getLatitude());
            ps.setInt(4, nascent.getLength());
            ps.setString(5, nascent.getDescription());
            ps.setInt(6, nascent.getProvince_id());
            ps.setInt(7, nascent.getCanton_id());
            ps.setInt(8, nascent.getDistrict_id());
            ps.setInt(9, nascent.getEntity_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "La modificación fue Exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }

    }

    public void deleteNascent(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM nascents WHERE id=?";

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
    
    public int getIDProvince(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM provinces WHERE name = ?";
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
    
     public int getIDCanton(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM cantons WHERE name = ?";
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
     
     
    
     public int getIDDistrict(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM districties WHERE name = ?";
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
     
     
    
    public int getIDEntity(String name) {
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
}
