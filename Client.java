import java.util.*;
import java.io.*;

public class Client implements Serializable{
  private static final long serialVersionUID = 1L;
  private List<Transaction> transactions = new LinkedList<Transaction>();
  private String name;
  private String address;
  private String phone;
  private String id;
  private float balance;
  private static final String CLIENT_STRING = "C";
    
  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.balance = balance;
    id = CLIENT_STRING + ClientIdServer.instance().getId();
  }

  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }

  public void setBalance(float newBalance){
    balance = newBalance;
}    
    
public float getBalance(){
    return balance;
}
    
public boolean hasOutstandingBalance() {
    return (balance < 0);
  }

  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Client name " + name + " address " + address + " id " + id + " Phone Number " + phone;
    return string;
  }
  
  public Transaction createTransaction(String description, float dollarAmount){
      Transaction transaction = new Transaction(description, dollarAmount);
      return transaction;
  }  

   public boolean addTransactionToList(Transaction transaction){
      return transactions.add(transaction);
  }
  
  public Iterator getTransactions()
  {
      return transactions.iterator();
  }
}