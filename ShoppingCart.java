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


  public ShoppingCart(Client client) {
    this.client = client;
    this.totalCost = 0;  //initialize to 0, this will be calculated and set when items are added to the cart
  }

  public Client getClient() {
    return client;
  }
  
  public double getTotal() {
    return totalCost;
  }
  
  public Iterator<Quantity> getItems() {
	  return items.iterator();
  }
  
  public void setTotal(double amount) {
	  totalCost = amount;
  }

  public String toString() {
      return "Client: " + client + " Total: " + totalCost;
  }
  
  public boolean addToCart(Product product, int qty) {
	  Quantity item = new Quantity(product, qty);
	  items.add(item);
	  return true;
  }
  
  /* Currently, this method is set up to completely remove the item from the cart (no matter the quantity)
   * As we further develop the system further, I may modify this method to allow the client to update the quantity
   * in the cart by setting the qty attribute of the Quantity object to a different value, or give the option to remove it 
   * completely
   */  
  public boolean removeQuantity(Product product) {
	  Iterator<Quantity> it = items.iterator();
	  while (it.hasNext()){
		  Quantity currentItem = (Quantity) it.next();
		  if (product.getProductID().equals(currentItem.getProduct().getProductID())){
			  it.remove(); 
			  return true;
		  }	
	  }	   
	  
	  /* Will also need to multiply the qty (get from the Quantity object, currentItem) by the salesPrice (get  
	   * from the Product object, product) and deduct that amount from the existing totalCost.  Then set totalCost to the
	   * new amount.  After the shopping cart is processed, the quantity of each product will also need to be deducted from 
     * the amount in stock and set in each Product object. This code for these operations will be contained in their own 
     * functions and will be written as we develop the system further.
	   */
	  return false;
  }
  
  public double calculateTotal(){
	  double total = 0;
	  //code to calculate the total cost will be written as we develop the system further
	  return total;
	  
  }
   
}
