import java.util.*;
import java.io.*;
import java.io.Serializable;

public class Account implements  Serializable{
  private static final long serialVersionUID = 1L;
  float balance;

public void Account(){
    this.balance = 0;
}

public void Account(float balance){
    this.balance = balance;
}

public void setBalance(float balance){
    this.balance = balance;
}    
    
public float getBalance(){
    return balance;
}
    
public boolean hasOutstandingBalance() {
    return (balance < 0);
  }
    
public float chargeAccount(float charge){
    this.balance = balance - charge;
    return balance;
}

public float payment(float payment){
    this.balance = balance + payment;
    return balance;
}
}