import java.util.*;
import java.io.*;
public class Supplier implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String supplierID;
	private String name;
	private String address;
	private static final String SUPPLIER_STRING = "S";
	private List<Supplies> suppliedProducts = new LinkedList<Supplies>();

	public Supplier(String name, String address)
	{
		this.name = name;
		this.address = address;
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

	public Iterator getSuppliedProducts()
	{
		return suppliedProducts.iterator();
	}

	public boolean assignProduct(Supplies supplies)
	{
		return suppliedProducts.add(supplies);
	}

	public boolean unassignProduct(String productID, String supplierID)
	{
		Iterator suppliesIterator = suppliedProducts.iterator();

		while (suppliesIterator.hasNext())
		{
			Supplies supplies = (Supplies)(suppliesIterator.next());
			Supplier supplier = supplies.getSupplier();
			Product product = supplies.getProduct();
			if (product.getProductID().equals(productID) && supplier.getSupplierID().equals(supplierID))
			{
				return suppliedProducts.remove(supplies);
			}
		}

		return false; //Couldn't find Supplies object to unassign
	}

	public String toString() {
		return "Supplier ID: " + supplierID + " | Name: " + name + " | Address: " + address;
	}
}