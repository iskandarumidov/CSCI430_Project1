import java.util.*;
import java.io.*;

public class Client implements Serializable{
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private double balance;
  private static final String CLIENT_STRING = "C";
  private ShoppingCart cart;
  private List<Transaction> transactions = new LinkedList<Transaction>();
  private List<ShoppingCart> clientOrders = new LinkedList<>();
    
  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
	this.balance = 0;
	this.cart = null;
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
  public double getBalance(){
    return balance;
  }
  
  public ShoppingCart getCart(){
	return cart;
  }
  
   public Iterator<ShoppingCart> getClientOrder() {
    return clientOrders.iterator();
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
  
  public void setBalance(double newBalance){
    balance = newBalance;
  }    
  
  public void setCart(ShoppingCart assignedCart){
	  cart = assignedCart;
  }
    
  public boolean addClientOrder(ShoppingCart clientOrder){
    return clientOrders.add(clientOrder);
  }
  
    public void printClientOrder(){
	  System.out.println("Items Waitlisted: ");
	  Iterator<ShoppingCart> clientOrders = getClientOrder();
		while(clientOrders.hasNext()){
			ShoppingCart currentClientOrder = (ShoppingCart)clientOrders.next();
			System.out.println(currentClientOrder.toString());
			System.out.println();
		}
		System.out.println();	  
  }
  
  public boolean hasOutstandingBalance() {
  return (balance > 0);
  }
  //TODO Iskandar - should this accept another Client instance rather than String?
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public boolean addTransactionToList(Transaction transaction){
      return transactions.add(transaction);
  }  
  public Iterator getTransactions()
  {
      return transactions.iterator();
  }
  
  // This method prints the client's transaction history
  public void printTransactions(){
	  String message = new String("Transactions for " + name + ":");
    System.out.println(message.toUpperCase() );
	  Iterator<Transaction> iterator = getTransactions();
		while(iterator.hasNext()){
			Transaction currentTransaction = (Transaction)iterator.next();
			System.out.println(currentTransaction.toString());
			System.out.println();
		}
		System.out.println();	  
  }
  
  public String toString() {
    String string = "Client name " + name + " Address " + address + " id " + id + " Phone Number " + phone + " Outstanding Balance: $" + String.format("%.2f", balance);
    return string;
  }
}
