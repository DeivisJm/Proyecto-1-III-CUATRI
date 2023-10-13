
package Model;

/**
 *
 * @author deivis
 */
public class Entity {

    private int id;
    private int id_number;
    private String name;
    private String email;
    private int celphone;
    private String address;
    private String description;

    public Entity() {
    }

    public Entity(int id, int id_number, String name, String email, int celphone, String address, String description) {
        this.id = id;
        this.id_number = id_number;
        this.name = name;
        this.email = email;
        this.celphone = celphone;
        this.address = address;
        this.description = description;
    }

    public Entity(int id_number, String name, String email, int celphone, String address, String description) {
        this.id_number = id_number;
        this.name = name;
        this.email = email;
        this.celphone = celphone;
        this.address = address;
        this.description = description;
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
     * @return the id_number
     */
    public int getId_number() {
        return id_number;
    }

    /**
     * @param id_number the id_number to set
     */
    public void setId_number(int id_number) {
        this.id_number = id_number;
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
     * @return the celphone
     */
    public int getCelphone() {
        return celphone;
    }

    /**
     * @param celphone the celphone to set
     */
    public void setCelphone(int celphone) {
        this.celphone = celphone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
