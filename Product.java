import java.util.*;
import java.io.*;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String id;
  private String name;
  private String description;
  private int amountInStock;
  private double salesPrice;
  private List<Quantity> waitlistedOrders = new LinkedList<>();
  private List<ProductSupplier> assignedSuppliers = new LinkedList<ProductSupplier>();

  public Product(String id, String name, String description, int amountInStock, double salesPrice) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.amountInStock = amountInStock;
    this.salesPrice = salesPrice;
  }

  public String getProductID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getAmountInStock() {
    return amountInStock;
  }

  public double getSalesPrice() {
    return salesPrice;
  }

  public Iterator<ProductSupplier> getSuppliers() {
    return assignedSuppliers.iterator();
  }

  public Iterator<Quantity> getWaitlist() {
    return waitlistedOrders.iterator();
  }

  public void setAmountInStock(int amtInStock){
    amountInStock = amtInStock;
  }

  public void setSalesPrice(double retailPrice) {
    salesPrice = retailPrice;
  }

  public boolean addProductSupplier(ProductSupplier productSupplier) {
    return assignedSuppliers.add(productSupplier);
  }

  public boolean unassignSupplier(String supplierID) {
    Iterator<ProductSupplier> productSupplierIterator = assignedSuppliers.iterator();

    while (productSupplierIterator.hasNext()) {
      ProductSupplier productSupplier = (ProductSupplier) (productSupplierIterator.next());
      Supplier supplier = productSupplier.getSupplier();
      Product product = productSupplier.getProduct();
      if (product.getProductID().equals(this.getProductID()) && supplier.getSupplierID().equals(supplierID)) {
        return assignedSuppliers.remove(productSupplier);
      }
    }
    return false;
  }
    
  public boolean addWaitlistedOrder(Quantity waitlistedOrder){
    return waitlistedOrders.add(waitlistedOrder);
  }
  
  
  // This method prints the product's waitlisted orders
  public void printWaitlist(){
	  System.out.println("Waitlist for " + name + ":");
	  Iterator<Quantity> iterator = getWaitlist();
		while(iterator.hasNext()){
			Quantity currentOrder = (Quantity)iterator.next();
			System.out.println(currentOrder.toString());
			System.out.println();
		}
		System.out.println();	  
  }
	// This method displays waitlist orders one at a time, asks if each order should be filled or not,
	// and returns the remaining quantity of received product (if any)
	public int processWaitlist(int qtyReceived){
		int remainingQty = qtyReceived;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Fill order if sufficient quantity is available, otherwise leave order on waitlist");
		Iterator<Quantity> iterator = getWaitlist();
		while(iterator.hasNext()){
			Quantity currentOrder = (Quantity)iterator.next();
      System.out.println(currentOrder.toString());
			System.out.println("Quantity available = " + remainingQty + "\nFill order? Enter Y for yes or N for no: ");
			char decision = keyboard.next().charAt(0);    
			decision = Character.toUpperCase(decision);
			if (decision == 'Y'){
				int qtyNeeded = currentOrder.getQty();
				ShoppingCart cart = currentOrder.getClient().getCart();
				cart.addToCart(currentOrder.getProduct(), qtyNeeded);
				cart.processOrder("Waitlisted Order - filled");
				remainingQty -= qtyNeeded;
        iterator.remove();
				System.out.println("Order processed\n");
			}
			else{
				System.out.println("Order has been left on waitlist\n");
			}			
		}
		keyboard.close();
		return remainingQty;		
	}


  public String toString() {
    return "Product ID: " + id + ", Name: " + name + ", Description: " + description 
	+ "\nAmount in stock: " + amountInStock + ", Sales Price: $" + String.format("%.2f", salesPrice) + "\n";
  }

    public void printAssignedSuppliers(){
	  System.out.println("Assigned Suppliers for Product " + id + " " + name + " " + salesPrice + ":");
	  Iterator<ProductSupplier> iterator = assignedSuppliers.iterator();
		while(iterator.hasNext()){
			ProductSupplier currentProductSupplier = (ProductSupplier)iterator.next();
      Supplier currentSupplier = currentProductSupplier.getSupplier();
			System.out.println(currentSupplier.getSupplierID() + " " + currentSupplier.getName());
		}
		System.out.println();	  
  }
}
