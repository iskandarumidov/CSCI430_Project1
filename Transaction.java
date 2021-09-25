import java.util.*;
import java.io.*;
import java.io.Serializable;

public class Transaction implements Serializable{
    private String description;
    private float dollarAmount;
    private Date date;
    private static final long serialVersionUID = 1L;
    
    public  Transaction (String description, float dollarAmount) {
    this.description = description;
    this.dollarAmount = dollarAmount;
    this.date = new Date();
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setDollarAmount(float dollarAmount){
        this.dollarAmount = dollarAmount;
    }
        
    public String getDescription(){
        return this.description;
    }
    
    public float getDollarAmount(){
        return this.dollarAmount;
    }
    
    public Date getDate(){
        return this.date;
    }

}