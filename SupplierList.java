import java.util.*;
import java.io.*;

public class SupplierList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Supplier> suppliers = new LinkedList<Supplier>();
	private static SupplierList SupplierList;

	private SupplierList() { }

	public static SupplierList instance() {
		if (SupplierList == null) {
			return (SupplierList = new SupplierList());
		} else {
			return SupplierList;
		}
	}

  public Iterator<Supplier> getSuppliers() {
    return suppliers.iterator();
  }

	public boolean insertSupplier(Supplier supplier) {
		return suppliers.add(supplier);
	}

	public Supplier searchSupplier(String supplierID)
	{
		Iterator supplierIterator = suppliers.iterator();

		while (supplierIterator.hasNext())
		{
			Supplier supplier = (Supplier)(supplierIterator.next());
			if (supplier.getSupplierID().equals(supplierID))
			{
				return supplier;
			}
		}

		return null;
	}

	// public Iterator getSupplierList(){
	// 	 return suppliers.iterator();
	// }

	public String toString() {
		return suppliers.toString();
	}
}