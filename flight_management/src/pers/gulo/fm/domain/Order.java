// 
// 
// 

package pers.gulo.fm.domain;

import java.sql.Timestamp;

public class Order
{
    private int no;
    private User user;
    private Flight flight;
    private boolean isPayed;
    private boolean isCanceled;
    private Timestamp time;
    
    public int getNo() {
        return this.no;
    }
    
    public void setNo(final int no) {
        this.no = no;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public Flight getFlight() {
        return this.flight;
    }
    
    public void setFlight(final Flight flight) {
        this.flight = flight;
    }
    
    public boolean getIsPayed() {
        return this.isPayed;
    }
    
    public void setIsPayed(final boolean isPayed) {
        this.isPayed = isPayed;
    }
    
    public boolean getIsCanceled() {
        return this.isCanceled;
    }
    
    public void setIsCanceled(final boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
    
    public Timestamp getTime() {
        return this.time;
    }
    
    public void setTime(final Timestamp time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "Order [no=" + this.no + ", user=" + this.user + ", flight=" + this.flight + ", isPayed=" + this.isPayed + ", isCanceled=" + this.isCanceled + ", time=" + this.time + "]";
    }
}
