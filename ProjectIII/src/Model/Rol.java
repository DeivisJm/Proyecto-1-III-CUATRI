package Model;

/**
 *
 * @author deivis
 */
public class Rol {

    public int id;
    public String name;

    public Rol() {
    }

    public Rol(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Rol(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
