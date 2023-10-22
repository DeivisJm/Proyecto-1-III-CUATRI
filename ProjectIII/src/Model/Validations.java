package Model;

/**
 *
 * @author Cristopher Matus
 */
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Validations {
    // Método para validar el campo de la "Cedula juridica" (txtidnumberentity)
    public static boolean validateIdNumber(String idNumber) {
        if (Pattern.matches("\\d+", idNumber)) {
            return true; // Es válido (solo números)
        } else {
            JOptionPane.showMessageDialog(null, "La cédula jurídica debe contener solo números.");
            return false; // No es válido
        }
    }

    // Método para validar el campo del "nombre" (txtnameentity)
    public static boolean validateName(String name) {
        if (Pattern.matches("[a-zA-Z]+", name)) {
            return true; // Es válido (solo letras)
        } else {
            JOptionPane.showMessageDialog(null, "El nombre debe contener solo letras.");
            return false; // No es válido
        }
    }

    // Método para validar el campo del "Email" (txtemailentity)
    public static boolean validateEmail(String email) {
        // Utilizamos una expresión regular simple para verificar el formato del correo electrónico
        if (Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            return true; // Es válido (formato de correo electrónico correcto)
        } else {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido.");
            return false; // No es válido
        }
    }

    // Método para validar el campo del "telefono" (txtcelphone)
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (Pattern.matches("[0-9\\s]+", phoneNumber)) {
            return true; // Es válido (números y espacios)
        } else {
            JOptionPane.showMessageDialog(null, "El número de teléfono debe contener solo números y espacios.");
            return false; // No es válido
        }
    }

    // Método para validar el campo de la "dirección" (txtaddressentity)
    public static boolean validateAddress(String address) {
        if (Pattern.matches("[a-zA-Z0-9,\\s]+", address)) {
            return true; // Es válido (letras, números y comas)
        } else {
            JOptionPane.showMessageDialog(null, "La dirección debe contener letras, números y comas.");
            return false; // No es válido
        }
    }

    // Método para validar el campo de la "descripción" (txtdescriptionentity)
    public static boolean validateDescription(String description) {
        // No se aplica ninguna restricción específica
        return true; // Siempre es válido
    }
}


