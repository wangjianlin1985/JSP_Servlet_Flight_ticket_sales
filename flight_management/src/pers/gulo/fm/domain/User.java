// 
// 
// 

package pers.gulo.fm.domain;

public class User
{
    private int no;
    private String username;
    private String password;
    private String nickname;
    private String ID;
    private float balance;
    private int type;
    
    public float getBalance() {
        return this.balance;
    }
    
    public void setBalance(final float balance) {
        this.balance = balance;
    }
    
    public int getNo() {
        return this.no;
    }
    
    public void setNo(final int no) {
        this.no = no;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }
    
    public String getID() {
        return this.ID;
    }
    
    public void setID(final String ID) {
        this.ID = ID;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "User [no=" + this.no + ", username=" + this.username + ", password=" + this.password + ", nickname=" + this.nickname + ", ID=" + this.ID + ", balance=" + this.balance + ", type=" + this.type + "]";
    }
}
