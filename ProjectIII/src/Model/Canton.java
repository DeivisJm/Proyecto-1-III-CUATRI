package Model;

/**
 *
 * @author deivis
 */
public class Canton {

    private int id;
    private String name;
    private int province_id;

    public Canton() {
    }

    public Canton(int id, String name, int province_id) {
        this.id = id;
        this.name = name;
        this.province_id = province_id;
    }

    public Canton(String name, int province_id) {
        this.name = name;
        this.province_id = province_id;
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
     * @return the province_id
     */
    public int getProvince_id() {
        return province_id;
    }

    /**
     * @param province_id the province_id to set
     */
    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

}
