/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author deivi
 */
public class District {

    private int id;
    private String name;
    private int canton_id;

    public District() {
    }

    public District(int id, String name, int canton_id) {
        this.id = id;
        this.name = name;
        this.canton_id = canton_id;
    }

    public District(String name, int canton_id) {
        this.name = name;
        this.canton_id = canton_id;
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
     * @return the canton_id
     */
    public int getCanton_id() {
        return canton_id;
    }

    /**
     * @param canton_id the canton_id to set
     */
    public void setCanton_id(int canton_id) {
        this.canton_id = canton_id;
    }

}
