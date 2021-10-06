import java.util.*;

class CombinedTest {
    
    private static void supplierTest(){
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

    private static void productSupplierTest(){
        System.out.println("TESTING PRODUCTSUPPLIER");
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

        System.out.println("---------GETTERS---------");
        System.out.println(pair1.getSupplier());
        System.out.println(pair1.getProduct());
        System.out.println(pair1.getPrice());

        System.out.println("---------TO STRING---------");
        System.out.println(pair1);
        System.out.println(pair2);
        System.out.println(pair3);
    }

    private static void supplierListTest(){
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

    private static void productListTest(){

        System.out.println("TESTING PRODUCTLIST");
        ProductList productList = ProductList.instance();
        Product product1 = new Product("id1", "Item1", "description1", 100, 11.11);
        Product product2 = new Product("id2", "Item2", "description2", 200, 22.22);
        Product product3 = new Product("id3", "Item3", "description3", 300, 33.33);
        Product product4 = new Product("id4", "Item4", "description4", 400, 44.44);

        // Test insertProduct()
        productList.insertProduct(product1);
        productList.insertProduct(product2);
        productList.insertProduct(product3);
        productList.insertProduct(product4);

        // Test getProducts
        Iterator productIterator = productList.getProducts();
        while (productIterator.hasNext())
        {
            Product product = (Product)(productIterator.next());
            System.out.println(product);
        }

        // Test instance() 
        ProductList productList2 = ProductList.instance();
        Iterator productIterator2 = productList2.getProducts();
        while(productIterator2.hasNext())
        {
            Product product = (Product)(productIterator2.next());
            System.out.println(product);
        }

        // Test searchProduct()
        System.out.println(productList.searchProduct("id1"));
        System.out.println(productList.searchProduct("id5"));
        System.out.println(productList.searchProduct(null));

    }

    private static void clientListTest(){
        System.out.println("TESTING ClientLIST");
        ClientList clientList = ClientList.instance();
        Client client1 = new Client("jose", "123 1234th Ave N", "123-456-7890");
        Client client2 = new Client("josh", "234 45th Ave S", "123-456-7891");
        Client client3 = new Client("jamal", "385 78th Ave E", "123-456-7892");
        Client client4 = new Client("kevin", "895 3th Ave W", "123-456-78903");
        //Tests insertClient()
        clientList.insertClient(client1);
        clientList.insertClient(client2);
        clientList.insertClient(client3);
        clientList.insertClient(client4);
        //Tests getClient()
        Iterator clientIterator = clientList.getClients();

        while (clientIterator.hasNext())
		{
			 Client client = (Client)(clientIterator.next());
			System.out.println(client);
		}
        //Tests instance() to make sure list is the same
        ClientList clientList2 = ClientList.instance();
        Iterator clientIterator2 = clientList2.getClients();

        while (clientIterator2.hasNext())
		{
			 Client client = (Client)(clientIterator2.next());
			System.out.println(client);
		}
        
        //Tests searchProductList() and toString()
        System.out.println(clientList.searchClient("id1"));
        System.out.println(clientList.searchClient("id4"));
        System.out.println(clientList.searchClient(null));
    }

    private static void warehouseTest(){
		Client client1 = new Client("Joe Smith", "533 Oak Drive, Littleton, CO 80127", "(303)555-2974" );
		ShoppingCart cart1 = new ShoppingCart(client1);
		Product tent = new Product("TEN47", "Tent", "Kelty 5500 Series 4 person tent", 12, 249.99);
		Product sleepBag = new Product("BAG84", "Sleeping Bag", "North Face Artic Down Sleeping Bag", 20, 129.00);
		Product pack = new Product("PAC33", "Backpack", "Osprey Cloudlite 52 liter backpack", 7, 118.00);
		
		// Print the attributes of the ShoppingCart object
		System.out.println(cart1.getClient().getName() + " (should be Joe Smith)\n");
		System.out.println(cart1.getTotal() + " (should be 0)\n");
		
		// Add items to the cart
		cart1.addToCart(tent, 1);
		cart1.addToCart(sleepBag, 2);
		cart1.addToCart(pack, 2);
		
		//Print the items in the cart
		System.out.println("Contents of cart after adding all items:");
		printCart(cart1);
		
		//Remove one item from the cart
		cart1.removeQuantity(sleepBag);
		
		//Reprint the items in the cart after item removal
		System.out.println("Contents of the cart after removing sleeping bags:");
		printCart(cart1);
		
	}	
	// Currently not getting sales prices or totaling cart, as Product is just a dummy class for my testing
	private static void printCart(ShoppingCart cart){
		Iterator<Quantity> iterator = cart.getItems(); 
		while(iterator.hasNext()){
			Quantity currentItem = (Quantity)iterator.next();
			System.out.println("Item: " + currentItem.getProduct().getName() + ", Quantity: " + currentItem.getQty()); 
		}
		System.out.println();
	}

    public static void main(String[] args)
    {
        CombinedTest.supplierTest();
        CombinedTest.productSupplierTest();
        CombinedTest.supplierListTest();
        CombinedTest.productListTest();
        CombinedTest.clientListTest();
        CombinedTest.warehouseTest();
    }
}