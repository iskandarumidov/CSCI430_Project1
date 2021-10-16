import java.util.*;
import java.io.*;
import java.io.Serializable;

public class Transaction implements Serializable{
    private String description;
    private double dollarAmount;
    private Date date;
    private static final long serialVersionUID = 1L;
    
    public Transaction(String description, double dollarAmount) {
    this.description = description;
    this.dollarAmount = dollarAmount;
    this.date = new Date();
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setDollarAmount(double dollarAmount){
        this.dollarAmount = dollarAmount;
    }
        
    public String getDescription(){
        return this.description;
    }
    
    public double getDollarAmount(){
        return this.dollarAmount;
    }
    
    public Date getDate(){
        return this.date;
    }
	
	public String toString(){
		return "Date: " + date + "\nDescription: " + description + "\nOrder Total: $" + String.format("%.2f", dollarAmount);
	}

	//public String toString() {
    //String string = "Client ID: " + client.getId() + "\n Name: " + client.getName() + "\n "+ "\n Account Balance: $" + String.format("%.2f", client.getBalance());
    //return string;
    //}
}