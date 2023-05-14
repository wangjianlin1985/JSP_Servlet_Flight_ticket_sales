// 
// 
// 

package pers.gulo.fm.domain;

public class Statistic
{
    private int weekFlight;
    private int monthFlight;
    private int totalFlight;
    private int weekOrder;
    private int monthOrder;
    private int totalOrder;
    private float weekIncome;
    private float monthIncome;
    private float totalIncome;
    
    public int getWeekFlight() {
        return this.weekFlight;
    }
    
    public void setWeekFlight(final int weekFlight) {
        this.weekFlight = weekFlight;
    }
    
    public int getMonthFlight() {
        return this.monthFlight;
    }
    
    public void setMonthFlight(final int monthFlight) {
        this.monthFlight = monthFlight;
    }
    
    public int getTotalFlight() {
        return this.totalFlight;
    }
    
    public void setTotalFlight(final int totalFlight) {
        this.totalFlight = totalFlight;
    }
    
    public int getWeekOrder() {
        return this.weekOrder;
    }
    
    public void setWeekOrder(final int weekOrder) {
        this.weekOrder = weekOrder;
    }
    
    public int getMonthOrder() {
        return this.monthOrder;
    }
    
    public void setMonthOrder(final int monthOrder) {
        this.monthOrder = monthOrder;
    }
    
    public int getTotalOrder() {
        return this.totalOrder;
    }
    
    public void setTotalOrder(final int totalOrder) {
        this.totalOrder = totalOrder;
    }
    
    public float getWeekIncome() {
        return this.weekIncome;
    }
    
    public void setWeekIncome(final float weekIncome) {
        this.weekIncome = weekIncome;
    }
    
    public float getMonthIncome() {
        return this.monthIncome;
    }
    
    public void setMonthIncome(final float monthIncome) {
        this.monthIncome = monthIncome;
    }
    
    public float getTotalIncome() {
        return this.totalIncome;
    }
    
    public void setTotalIncome(final float totalIncome) {
        this.totalIncome = totalIncome;
    }
    
    @Override
    public String toString() {
        return "Statistic [weekFlight=" + this.weekFlight + ", monthFlight=" + this.monthFlight + ", totalFlight=" + this.totalFlight + ", weekOrder=" + this.weekOrder + ", monthOrder=" + this.monthOrder + ", totalOrder=" + this.totalOrder + ", weekIncome=" + this.weekIncome + ", monthIncome=" + this.monthIncome + ", totalIncome=" + this.totalIncome + "]";
    }
}
