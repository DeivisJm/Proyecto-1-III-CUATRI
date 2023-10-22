package Model;  // Declare the package where this class is located.

/**
 * A class representing an entity.
 *
 * @author Cristopher Matus  // Author's name
 */
public class Entity {

    // Private instance variables to store the attributes of the entity.
    private int id;
    private int legal_id;
    private String name;
    private String email;
    private int celphone;
    private String address;
    private String description;

    // Default constructor with no parameters.
    public Entity() {
    }

    // Constructor with parameters to initialize all attributes.
    public Entity(int id, int legal_id, String name, String email, int celphone, String address, String description) {
        this.id = id;
        this.legal_id = legal_id;
        this.name = name;
        this.email = email;
        this.celphone = celphone;
        this.address = address;
        this.description = description;
    }

    // Constructor without an 'id' parameter, used when 'id' is not known initially.
    public Entity(int legal_id, String name, String email, int celphone, String address, String description) {
        this.legal_id = legal_id;
        this.name = name;
        this.email = email;
        this.celphone = celphone;
        this.address = address;
        this.description = description;
    }

    // Getter method for 'id'.
    public int getId() {
        return id;
    }

    // Setter method for 'id'.
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for 'legal_id'.
    public int getLegal_id() {
        return legal_id;
    }

    // Setter method for 'legal_id'.
    public void setLegal_id(int legal_id) {
        this.legal_id = legal_id;
    }

    // Getter method for 'name'.
    public String getName() {
        return name;
    }

    // Setter method for 'name'.
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for 'email'.
    public String getEmail() {
        return email;
    }

    // Setter method for 'email'.
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for 'celphone'.
    public int getCelphone() {
        return celphone;
    }

    // Setter method for 'celphone'.
    public void setCelphone(int celphone) {
        this.celphone = celphone;
    }

    // Getter method for 'address'.
    public String getAddress() {
        return address;
    }

    // Setter method for 'address'.
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter method for 'description'.
    public String getDescription() {
        return description;
    }

    // Setter method for 'description'.
    public void setDescription(String description) {
        this.description = description;
    }
}
