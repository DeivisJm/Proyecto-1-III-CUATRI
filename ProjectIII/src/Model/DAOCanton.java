package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DBConnection;

/**
 *
 * @author Cristopher Matus
 */
public class DAOCanton {

    public DAOCanton() {
    }

    public List<Canton> readCanton() {
        DBConnection db = new DBConnection();
        List<Canton> cantons = new ArrayList<>();
        String sql = "SELECT * from cantons";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int province_id = resultSet.getInt("province_id");
                cantons.add(new Canton(id, name, province_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return cantons;
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

    public String getNameCanton(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM cantons WHERE id = ?";
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

    public int getIDProvince(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM cantons WHERE name = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("province_id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }

}
