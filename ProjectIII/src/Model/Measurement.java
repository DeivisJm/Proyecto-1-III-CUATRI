package Model;

import java.util.Date;

/**
 *
 * @author fabri
 */
 
public class Measurement {

    private int id;
    private double capacity;
    private String method;
    private String observation;
    private Date date;
    private String weather;
    private int nascent_id;
    private int samplingsite_id;

    public Measurement() {
    }

    public Measurement(int id, double capacity, String method, String observation, Date date, String weather, int nascent_id, int samplingsite_id) {
        this.id = id;
        this.capacity = capacity;
        this.method = method;
        this.observation = observation;
        this.date = date;
        this.weather = weather;
        this.nascent_id = nascent_id;
        this.samplingsite_id = samplingsite_id;
    }

    public Measurement(double capacity, String method, String observation, Date date, String weather, int nascent_id, int samplingsite_id) {
        this.capacity = capacity;
        this.method = method;
        this.observation = observation;
        this.date = date;
        this.weather = weather;
        this.nascent_id = nascent_id;
        this.samplingsite_id = samplingsite_id;
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
     * @return the capacity
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the weather
     */
    public String getWeather() {
        return weather;
    }

    /**
     * @param weather the weather to set
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * @return the nascent_id
     */
    public int getNascent_id() {
        return nascent_id;
    }

    /**
     * @param nascent_id the nascent_id to set
     */
    public void setNascent_id(int nascent_id) {
        this.nascent_id = nascent_id;
    }

    /**
     * @return the samplingsite_id
     */
    public int getSamplingsite_id() {
        return samplingsite_id;
    }

    /**
     * @param samplingsite_id the samplingsite_id to set
     */
    public void setSamplingsite_id(int samplingsite_id) {
        this.samplingsite_id = samplingsite_id;
    }
}
