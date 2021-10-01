import java.util.*;

class SupplierTest {
    public static void main(String[] args)
    {
        System.out.println("TESTING SUPPLIER");
        Supplier supplier1 = new Supplier("id1", "Mark", "123 6th Ave S", "111-111-1111");
        Supplier supplier5 = new Supplier("id5", "wrong name", "wrong address", "111-111-1111");

        Product pen = new Product("pid1", "Pen", "Writing tool", 100, 5.0);
        Product pencil = new Product("pid2", "Pencil", "Another writing tool", 50, 4.0);
        Product eraser = new Product("pid3", "Eraser", "Erases well", 30, 6.0);
        Product car = new Product("pid4", "Car", "Has four wheels", 2, 20000.0);
        Product glasses = new Product("pid5", "Glasses", "For your eyes", 3, 300.0);

        ProductSupplier pair1 = new ProductSupplier(supplier1, pen, 3.0);
        ProductSupplier pair2 = new ProductSupplier(supplier1, pencil, 3.5);
        ProductSupplier pair3 = new ProductSupplier(supplier1, eraser, 3.0);

        //Tests setters
        supplier5.setName("John");
        supplier5.setAddress("999 9th Ave S");
        //Tests getters
        System.out.println("---------PRINT NEW FIELDS---------");
        System.out.println(supplier5.getSupplierID());
        System.out.println(supplier5.getName());
        System.out.println(supplier5.getAddress());
        System.out.println(supplier5.getPhoneNumber());
        //Tests toString()
        System.out.println(supplier5);

        //Tests addProductSupplier(). Normally, we would have to assign those suppliers to each product as well but since it's
        //a test of Supplier rather than Product, we omit this
        supplier1.addProductSupplier(pair1);
        supplier1.addProductSupplier(pair2);
        supplier1.addProductSupplier(pair3);
        
        //Tests getSuppliedProducts()
        System.out.println("---------PRINT SUPPLIED PRODUCTS---------");
        Iterator suppliedProductsIterator = supplier1.getSuppliedProducts();
        while (suppliedProductsIterator.hasNext())
		{
			ProductSupplier curProductSupplier = (ProductSupplier)(suppliedProductsIterator.next());
			System.out.println(curProductSupplier);
		}

        
        //Tests unassignProduct()
        System.out.println("---------PEN WILL BE REMOVED FROM SUPPLIER1---------");
        supplier1.unassignProduct("pid1");
        Iterator suppliedProductsIterator2 = supplier1.getSuppliedProducts();
        while (suppliedProductsIterator2.hasNext())
		{
			ProductSupplier curProductSupplier = (ProductSupplier)(suppliedProductsIterator2.next());
			System.out.println(curProductSupplier);
		}
    }
}