package Model;

/**
 *
 * @author deivis
 */
public class User {

    private int id;
    private String name;
    private String first_lastname;
    private String second_lastname;
    private String email;
    private String password;
    private int entity_id;
    private int rol_id;

    public User(int id, String name, String first_lastname, String second_lastname, String email, String password, int entity_id, int rol_id) {
        this.id = id;
        this.name = name;
        this.first_lastname = first_lastname;
        this.second_lastname = second_lastname;
        this.email = email;
        this.password = password;
        this.entity_id = entity_id;
        this.rol_id = rol_id;
    }

    public User(String name, String first_lastname, String second_lastname, String email, String password, int entity_id, int rol_id) {
        this.name = name;
        this.first_lastname = first_lastname;
        this.second_lastname = second_lastname;
        this.email = email;
        this.password = password;
        this.entity_id = entity_id;
        this.rol_id = rol_id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the first_lastname
     */
    public String getFirst_lastname() {
        return first_lastname;
    }

    /**
     * @param first_lastname the first_lastname to set
     */
    public void setFirst_lastname(String first_lastname) {
        this.first_lastname = first_lastname;
    }

    /**
     * @return the second_lastname
     */
    public String getSecond_lastname() {
        return second_lastname;
    }

    /**
     * @param second_lastname the second_lastname to set
     */
    public void setSecond_lastname(String second_lastname) {
        this.second_lastname = second_lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the entity_id
     */
    public int getEntity_id() {
        return entity_id;
    }

    /**
     * @param entity_id the entity_id to set
     */
    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    /**
     * @return the rol_id
     */
    public int getRol_id() {
        return rol_id;
    }

    /**
     * @param rol_id the rol_id to set
     */
    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

}
