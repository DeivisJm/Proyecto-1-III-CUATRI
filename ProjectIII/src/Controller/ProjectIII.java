package Controller;

import View.*;
import model.DBConnection;
/**
 *
 * @author deivis
 */
public class ProjectIII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.disconnect();
        
        Login l = new Login();
        l.setVisible(true);
        

    }

}
