package Model;

/**
 * The District class represents a geographical district.
 */
public class District {

    // Private fields to store district information
    private int id;
    private String name;
    private int canton_id;

    /**
     * Constructor for creating a District object with minimal information.
     *
     * @param districtId The unique identifier for the district.
     * @param districtName The name of the district.
     */
    public District(int districtId, String districtName) {
        // Constructor does not initialize fields id and canton_id.
    }

    /**
     * Constructor for creating a District object with complete information.
     *
     * @param id The unique identifier for the district.
     * @param name The name of the district.
     * @param canton_id The identifier of the canton to which the district belongs.
     */
    public District(int id, String name, int canton_id) {
        this.id = id;
        this.name = name;
        this.canton_id = canton_id;
    }

    /**
     * Constructor for creating a District object with partial information.
     *
     * @param name The name of the district.
     * @param canton_id The identifier of the canton to which the district belongs.
     */
    public District(String name, int canton_id) {
        this.name = name;
        this.canton_id = canton_id;
    }

    /**
     * Getter for retrieving the unique identifier of the district.
     *
     * @return The unique identifier of the district.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for setting the unique identifier of the district.
     *
     * @param id The unique identifier to set for the district.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for retrieving the name of the district.
     *
     * @return The name of the district.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for setting the name of the district.
     *
     * @param name The name to set for the district.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for retrieving the identifier of the canton to which the district belongs.
     *
     * @return The identifier of the canton.
     */
    public int getCanton_id() {
        return canton_id;
    }

    /**
     * Setter for setting the identifier of the canton to which the district belongs.
     *
     * @param canton_id The identifier of the canton to set.
     */
    public void setCanton_id(int canton_id) {
        this.canton_id = canton_id;
    }
}

