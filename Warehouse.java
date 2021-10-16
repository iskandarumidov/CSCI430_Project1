import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int CLIENT_NOT_FOUND = 1;
  public static final int PRODUCT_NOT_FOUND  = 2;
  public static final int SUPPLIER_NOT_FOUND = 3;
  public static final int ITEM_ADDED_TO_CART  = 4;
  public static final int ITEM_REMOVED_FROM_CART  = 5;
  public static final int OPERATION_COMPLETED= 6;
  public static final int OPERATION_FAILED= 7;
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
