import java.util.*;
import java.io.*;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String ID;
  private String name;
  private String description; 
  private List<ProductSupplier> assignedSuppliers = new LinkedList<ProductSupplier>();

  public Product(String ID, String name, String description) {
    this.ID = ID;
	this.name = name;
	this.description = description;
  }
	
  public String getProductID() {
    return ID;
  }

  public String getName() {
    return name;
  }
	
  public String getDescription() {
    return description;
  }
  
  public Iterator getSuppliers()
  {
	  return assignedSuppliers.iterator();
  }
	
  public boolean assignSupplier(ProductSupplier productSupplier) {
	  return assignedSuppliers.add(productSupplier);	 
  }
  public boolean unassignSupplier(String productID, String supplierID) {
	  Iterator suppliesIterator = assignedSuppliers.iterator();

		while (suppliesIterator.hasNext())
		{
			ProductSupplier productSupplier = (ProductSupplier)(suppliesIterator.next());
			Supplier supplier = productSupplier.getSupplier();
			Product product = productSupplier.getProduct();
			if (product.getProductID().equals(productID) && supplier.getSupplierID().equals(supplierID))
			{
				return assignedSuppliers.remove(productSupplier);
			}
		}

		return false;
	 
  }
  public String toString() {
      return "Product ID " + ID + " Name " + name + " Description " + description;
  }
}