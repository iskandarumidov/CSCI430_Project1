import java.util.*;
import java.io.*;
public class Supplier implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String supplierID;
	private String name;
	private String address;
    private String phoneNumber;
	private List<ProductSupplier> suppliedProducts = new LinkedList<ProductSupplier>();

	public Supplier(String supplierID, String name, String address, String phoneNumber)
	{
    this.supplierID = supplierID;
		this.name = name;
		this.address = address;
    this.phoneNumber = phoneNumber;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getSupplierID()
	{
		return supplierID;
	}

	public String getName()
	{
		return name;
	}

	public String getAddress()
	{
		return address;
	}

  public String getPhoneNumber() {
    return phoneNumber;
  }

	public Iterator<ProductSupplier> getSuppliedProducts()
	{
		return suppliedProducts.iterator();
	}

	public boolean addProductSupplier(ProductSupplier productSupplier)
	{
		return suppliedProducts.add(productSupplier);
	}

	public boolean unassignProduct(String productID)
	{
		Iterator productSupplierIterator = suppliedProducts.iterator();

		while (productSupplierIterator.hasNext())
		{
			ProductSupplier supplies = (ProductSupplier)(productSupplierIterator.next());
			Supplier supplier = supplies.getSupplier();
			Product product = supplies.getProduct();
			if (product.getProductID().equals(productID) && supplier.getSupplierID().equals(this.getSupplierID()))
			{
				return suppliedProducts.remove(supplies);
			}
		}

		return false;
	}

	public String toString() {
		return "Supplier ID: " + supplierID + " | Name: " + name + " | Address: " + address + " | Phone Number: " + phoneNumber;
	}

		public void printAssignedProducts(){
	  System.out.println("Assigned Products for Supplier " + supplierID + " " + name + ":");
	  Iterator<ProductSupplier> iterator = suppliedProducts.iterator();
		while(iterator.hasNext()){
			ProductSupplier currentProductSupplier = (ProductSupplier)iterator.next();
			Product currentProduct = currentProductSupplier.getProduct();
			System.out.println(currentProduct.getProductID() + " " + currentProduct.getName() + " " + currentProduct.getSalesPrice());
		}
		System.out.println();	  
  }
}