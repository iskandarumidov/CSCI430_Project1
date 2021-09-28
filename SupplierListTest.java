import java.util.*;

class SupplierListTest {
    public static void main(String[] args)
    {
        System.out.println("TESTING SUPPLIERLIST");
        SupplierList supplierList = SupplierList.instance();
        Supplier supplier1 = new Supplier("id1", "Mark", "123 6th Ave S", "111-111-1111");
        Supplier supplier2 = new Supplier("id2", "Mark2", "234 7th Ave S", "222-222-2222");
        Supplier supplier3 = new Supplier("id3", "Mark3", "345 8th Ave S", "333-333-3333");
        Supplier supplier4 = new Supplier("id4", "Mark4", "456 9th Ave S", "333-333-3333");
        //Tests insertSupplier()
        supplierList.insertSupplier(supplier1);
        supplierList.insertSupplier(supplier2);
        supplierList.insertSupplier(supplier3);
        supplierList.insertSupplier(supplier4);
        //Tests getSuppliers()
        Iterator supplierIterator = supplierList.getSuppliers();

        while (supplierIterator.hasNext())
		{
			Supplier supplier = (Supplier)(supplierIterator.next());
			System.out.println(supplier);
		}
        //Tests instance() to make sure list is the same
        SupplierList supplierList2 = SupplierList.instance();
        Iterator supplierIterator2 = supplierList2.getSuppliers();

        while (supplierIterator2.hasNext())
		{
			Supplier supplier = (Supplier)(supplierIterator2.next());
			System.out.println(supplier);
		}
        
        //Tests searchSupplier() and toString()
        System.out.println(supplierList.searchSupplier("id1"));
        System.out.println(supplierList.searchSupplier("id5"));
        System.out.println(supplierList.searchSupplier(null));
    }
}