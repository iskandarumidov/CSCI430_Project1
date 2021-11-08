/* The attribute productList from the sequence diagram has been renamed "items" to more clearly
 * distinguish between the collection class ProductList and the list of Quantity objects 
 * contained in this class.  It is also evident now that I should have had a better name for the 
 * association class between ShoppingCart and Product, instead of naming that class Quantity.  I
 * kept it called Quantity to be consistent with the sequence diagrams, but I may refactor as we develop 
 * the system further, and rename that class to something more descriptive
 */

import java.util.*;
import java.io.*;

public class ShoppingCart implements Serializable {
  private static final long serialVersionUID = 1L;
  private Client client;
  private double totalCost;
  private List<Quantity> items = new LinkedList<>();


  public ShoppingCart(Client client){
    this.client = client;
    this.totalCost = 0;  //initialize to 0, this will be calculated and set when items are added to the cart
  }

  public Client getClient(){
    return client;
  }
  
  public double getTotal(){
    return totalCost;
  }
  
  public Iterator<Quantity> getItems(){
	  return items.iterator();
  }
  
  public void setTotal(double amount){
	  totalCost = amount;
  }

  public String toString(){
      return "Client: " + client + " Total: " + totalCost;
  }
  
  // This method adds an item to the client's shopping cart
  public boolean addToCart(Product product, int qty){
	  Client client = this.client;
	  Quantity item = new Quantity(client, product, qty);
	  items.add(item);
	  return true;
  }
  
  // This method is set up to completely remove the item from the cart (no matter the quantity)  
  public boolean removeQuantity(Product product){
	  Iterator<Quantity> it = this.getItems(); 
	  while (it.hasNext()){
		  Quantity currentItem = (Quantity) it.next();
		  if (product.getProductID().equals(currentItem.getProduct().getProductID())){
			  it.remove(); 
			  return true;
		  }	
	  }	
	  return false;
  }
  
  public boolean updateCart(String productId, int quantityOfItems) {
    Iterator<Quantity> quantityIterator = this.getItems();
    Quantity quantity = null;
    while (quantityIterator.hasNext())
		{
			Quantity quan = (Quantity)(quantityIterator.next());
      if (quan.getProduct().getProductID() == productId){
        quantity = quan;
      }
		}	
    if (quantity == null){
	  return false;
    }else{
      //Product is in cart already. Now update quantity of items
      quantity.setQty(quantityOfItems);
      return true;
    }
  }

  public boolean clearCart(){
	Iterator<Quantity> it = this.getItems(); 
	while (it.hasNext()){
		it.remove(); 
	}	
	return true;
  }
  
	public void printCart(){
		if (this.items.isEmpty()){
			System.out.println("Shopping Cart is empty");
		}
		Iterator<Quantity> iterator = this.getItems(); 
		while(iterator.hasNext()){
			Quantity currentItem = (Quantity)iterator.next();
			System.out.println("Item: " + currentItem.getProduct().getName() + ", Quantity: " + currentItem.getQty()); 
		}
		System.out.println();
	}

  // This method processes the shopping cart (like checking out)
  public void processOrder(String description){
	double totalDue = 0;  
	List<Quantity> invoice = new LinkedList<>();
 	Iterator<Quantity> iterator = this.getItems(); 
		while(iterator.hasNext()){
			Quantity currentItem = (Quantity)iterator.next();
			Product currentProduct = currentItem.getProduct();
			double subtotal = 0;
			int amtAvailable = currentItem.checkAvailability();
			int amtRequested = currentItem.getQty();
			if (amtRequested <= amtAvailable){
				invoice.add(currentItem);
				subtotal = currentProduct.getSalesPrice() * amtRequested;
				totalDue += subtotal;
				currentProduct.setAmountInStock(amtAvailable - amtRequested);
			}
			else {
				// add to waitlist
				currentItem.getProduct().addWaitlistedOrder(currentItem);
				System.out.println(currentItem.getProduct().getName() + " added to waitlist");
			}
			iterator.remove();
		}
	double newBalance = this.client.getBalance() + totalDue;
	this.client.setBalance(newBalance);
	
	printInvoice(invoice, totalDue);
	
	createTransaction(description, totalDue);
  }
  
  // This method creates a Transaction object for the order
  private boolean createTransaction(String description, double totalDue){
	Transaction transaction = new Transaction(description, totalDue);
	this.client.addTransactionToList(transaction);
	
	return true;
  }
  
  
  // This method prints an invoice after an order has been processed
  private void printInvoice(List<Quantity> invoice, double totalDue){
	  System.out.println("INVOICE FOR THIS ORDER");
	  Iterator<Quantity> iterator = invoice.iterator();
		while(iterator.hasNext()){
			Quantity currentItem = (Quantity)iterator.next();
			int quantity = currentItem.getQty();
			double unitPrice = currentItem.getProduct().getSalesPrice();
			double subtotal = quantity * unitPrice;
			System.out.println("Item: " + currentItem.getProduct().getName() + 
			   ", Quantity: " + quantity + ", Unit Price: $" + String.format("%.2f", unitPrice) 
			   + ", Subtotal: $" + String.format("%.2f", subtotal)); 
		}
		if (totalDue !=0){
			System.out.println("Total Amount Due: $" + String.format("%.2f", totalDue));
		}
		else {
			System.out.println("No amount due at this time");
		}
		System.out.println();	  
  }
   
}
