import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int CLIENT_NOT_FOUND = 1;
  public static final int PRODUCT_NOT_FOUND  = 2;
  public static final int SUPPLIER_NOT_FOUND = 3;
  public static final int ITEM_ADDED_TO_CART  = 4;
  public static final int ITEM_REMOVED_FROM_CART  = 5;
  public static final int ITEM_QUANTITY_UPDATED = 6; 
  public static final int OPERATION_COMPLETED= 7;
  public static final int OPERATION_FAILED= 8;
  private ClientList clientList;
  private ProductList productList;
  private SupplierList supplierList;
  private static Warehouse warehouse;
  
  private Warehouse() {
    clientList = ClientList.instance();
	productList = ProductList.instance();
    supplierList = SupplierList.instance();
  }
  
  public static Warehouse instance() {
    if (warehouse == null) {
      ClientIdServer.instance(); // instantiate all singletons
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  
  public Client addClient(String name, String address, String phone) {
    Client client = new Client(name, address, phone);
    if (clientList.insertClient(client)) {
    ShoppingCart cart = new ShoppingCart(client);
    client.setCart(cart);
	  return (client);
	}
   return null;
  }
  
  public Product addProduct(String id, String name, String description, int amountInStock, double salesPrice) {
    Product product = new Product(id, name, description, amountInStock, salesPrice);
    if (productList.insertProduct(product)) {
      return (product);
    }
    return null;
  }
  
  public Supplier addSupplier(String id, String name, String address, String phone) {
    Supplier supplier = new Supplier(id, name, address, phone);
    if (supplierList.insertSupplier(supplier)) {
      return (supplier);
    }
    return null;
  }

  public boolean addToCart(String clientID, String productID, int quantity){
    Client client = clientList.searchClient(clientID);
    ShoppingCart cart = client.getCart();
    Product product = productList.searchProduct(productID);
    cart.addToCart(product, quantity);
    return true;
  }

  public boolean removeFromCart(String clientID, String productID){
    Client client = clientList.searchClient(clientID);
    ShoppingCart cart = client.getCart();
    Product product = productList.searchProduct(productID);
    cart.removeQuantity(product);
    return true;
  }

  public boolean updateCart(String clientID, String productID, int quantity){
    Client client = clientList.searchClient(clientID);
    ShoppingCart cart = client.getCart();
    boolean updated = cart.updateCart(productID, quantity);	
      if (!updated){
        // Product not in cart.  Add it to cart.
        Product product = productList.searchProduct(productID);
        cart.addToCart(product, quantity);
      }
      return true;
  }

  public void viewCart(String clientID){
    Client client = clientList.searchClient(clientID);
    ShoppingCart cart = client.getCart();
    cart.printCart();
  }

  public void processCart(String clientID, String description) {
    Client client = clientList.searchClient(clientID);
    ShoppingCart cart = client.getCart();
    cart.processOrder(description);
  }

  public void getTransactions(String clientID) {
    Client client = clientList.searchClient(clientID);
    client.printTransactions();
  }
  
  public void getClientWaitlist(String clientID) {
	  Client client = clientList.searchClient(clientID);
	  client.printClientOrder();	
	  }
 

  public void getOutstandingBalances() {
    Iterator clientIterator = clientList.getClients();
		//Tests client's remaining balance
		double finalBalance = 0;
		String finalName;
        while (clientIterator.hasNext())
		{
			 Client client = (Client)(clientIterator.next());
			 finalBalance = client.getBalance();
			 finalName = client.getName();
			 if(finalBalance > 0)
				 System.out.println(finalName + ", Outstanding Balance: " + finalBalance); 
		}
  }

  public boolean assignProductSupplier(String supplierID, String productID, double supplyPrice){
    Supplier supplier = supplierList.searchSupplier(supplierID);
    Product product = productList.searchProduct(productID);
    ProductSupplier newPair = new ProductSupplier(supplier, product, supplyPrice);
    product.addProductSupplier(newPair);
    supplier.addProductSupplier(newPair);
    product.printAssignedSuppliers();
    supplier.printAssignedProducts();
    return true;
  }

  public boolean receiveShipment(String productID, int qtyReceived){
		Product product = productList.searchProduct(productID);
    int newStockAmt = product.getAmountInStock() + qtyReceived;
		product.setAmountInStock(newStockAmt);
		int remainingQty = product.processWaitlist(newStockAmt);
		product.setAmountInStock(remainingQty);	
		return true;
	}

  public boolean acceptPayment(String clientID, double paymentAmt){
    Client client = clientList.searchClient(clientID);
    double updatedBalance = client.getBalance() - paymentAmt;
    client.setBalance(updatedBalance);
    return true;
  }

  public Iterator getClients() {
      return clientList.getClients();
  }

  public Iterator<Product> getProducts() {
      return productList.getProducts();
  }

  public Iterator<Supplier> getSuppliers() {
      return supplierList.getSuppliers();
  }
  
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
      return warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIdServer.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (warehouse == null) {
        warehouse = (Warehouse) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public String toString() {
    return clientList + "\n" + productList + "\n" + supplierList;
  }
}
