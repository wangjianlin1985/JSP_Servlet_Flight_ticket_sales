// 
// 
// 

package pers.gulo.fm.domain;

import java.sql.Timestamp;

public class Flight
{
    private int no;
    private AirPlane airPlane;
    private String start;
    private String dist;
    private float price;
    private Timestamp time;
    private int passengerNumber;
    
    public int getNo() {
        return this.no;
    }
    
    public void setNo(final int no) {
        this.no = no;
    }
    
    public String getStart() {
        return this.start;
    }
    
    public void setStart(final String start) {
        this.start = start;
    }
    
    public String getDist() {
        return this.dist;
    }
    
    public void setDist(final String dist) {
        this.dist = dist;
    }
    
    public float getPrice() {
        return this.price;
    }
    
    public void setPrice(final float price) {
        this.price = price;
    }
    
    public int getPassengerNumber() {
        return this.passengerNumber;
    }
    
    public void setPassengerNumber(final int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }
    
    public AirPlane getAirPlane() {
        return this.airPlane;
    }
    
    public void setAirPlane(final AirPlane airPlane) {
        this.airPlane = airPlane;
    }
    
    public Timestamp getTime() {
        return this.time;
    }
    
    public void setTime(final Timestamp time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "Flight [no=" + this.no + ", airPlane=" + this.airPlane + ", start=" + this.start + ", dist=" + this.dist + ", price=" + this.price + ", time=" + this.time + ", passengerNumber=" + this.passengerNumber + "]";
    }
}
