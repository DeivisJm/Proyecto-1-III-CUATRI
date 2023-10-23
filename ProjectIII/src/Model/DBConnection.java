package model;
// This line specifies the package to which this class belongs.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// These import statements include necessary Java SQL and database-related classes.

/**
 * This comment is a documentation comment, which provides a brief description of the class.
 * @author fabri
 * This comment specifies the author of the class.
 */
public class DBConnection {
// This line defines the start of a class named DBConnection.

    static String bd = "bdproject";
    // This line declares a static (class-level) string variable named "bd" and assigns it the value "bdproject."

    static String port = "3306";
    // This line declares a static (class-level) string variable named "port" and assigns it the value "3306."

    static String login = "root";
    // This line declares a static (class-level) string variable named "login" and assigns it the value "root."

    static String password = "salas3107";
    // This line declares a static (class-level) string variable named "password" and assigns it the value "salas3107."

    static String url = "jdbc:mariadb://localhost:" + port + "/" + bd;
    // This line constructs a URL for connecting to the MariaDB database by combining various strings and variables.

    Connection connection = null;
    // This line declares a Connection object named "connection" and initializes it to null.

    public DBConnection() {
    // This is the constructor of the DBConnection class.
        try {
        // This line marks the beginning of a try block to handle potential exceptions.
            Class.forName("org.mariadb.jdbc.Driver");
            // This line loads the MariaDB JDBC driver class.

            connection = DriverManager.getConnection(url, login, password);
            // This line establishes a connection to the database using the provided URL, login, and password.

            System.out.println("Conexion exitosa a la base de datos");
            // This line prints a success message indicating a successful database connection.
        } catch (ClassNotFoundException e) {
        // This block handles an exception if the JDBC driver class is not found.
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
            // This line prints an error message with the details of the exception.
        } catch (SQLException e) {
        // This block handles an exception if there is an error while establishing the database connection.
            System.err.println("Error al establecer la conexion: " + e.getMessage());
            // This line prints an error message with the details of the exception.
        }
    }

    public Connection getConnection() {
    // This method returns a Connection object for the database.
        return connection;
    }

    public void disconnect(){
    // This method is used to close the database connection.
        if (connection != null) {
        // This line checks if the connection object is not null.
            try {
            // This line marks the beginning of a try block to handle potential exceptions.
                connection.close();
                // This line attempts to close the database connection.

                System.out.println("Conexion cerrada");
                // This line prints a message indicating that the connection has been closed.
            } catch (SQLException e) {
            // This block handles an exception if there is an error while closing the connection.
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
                // This line prints an error message with the details of the exception.
            }
        }
    }
}
