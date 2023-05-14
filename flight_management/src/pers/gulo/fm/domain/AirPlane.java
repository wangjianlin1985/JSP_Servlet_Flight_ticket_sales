// 
// 
// 

package pers.gulo.fm.domain;

import java.io.Serializable;

public class AirPlane implements Serializable
{
    private int no;
    private String model;
    private int capacity;
    
    public int getNo() {
        return this.no;
    }
    
    public void setNo(final int no) {
        this.no = no;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public void setModel(final String model) {
        this.model = model;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }
    
    @Override
    public String toString() {
        return "AirPlane [no=" + this.no + ", model=" + this.model + ", capacity=" + this.capacity + "]";
    }
}
