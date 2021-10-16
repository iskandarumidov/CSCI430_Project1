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

  public Iterator<Quantity> getWaitlistedOrders(){ 
    return waitlistedOrders.iterator();
  }  

  public int getItemAvailability(){
    if(amountInStock > 0){
      System.out.println("Product is available. In Stock: ");
      return amountInStock;
    }
    else{
      System.out.println("Product is NOT available");
      return 0;
    }
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

  public boolean addWaitlistedOrder(Quantity waitlistedOrder){
    return waitlistedOrders.add(waitlistedOrder);
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

  public String toString() {
    return "Product ID: " + id + " Name: " + name + " Description: " + description + " Amount in stock: " + amountInStock + " Sales Price: " + salesPrice;
  }
}
