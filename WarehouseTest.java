import java.util.*;

public class WarehouseTest {
	
	public static void main(String[] args){

		Scanner keyboard = new Scanner(System.in);	
		
		Client client1 = new Client("Joe Smith", "533 Oak Drive, Littleton, CO 80127", "(303)555-2974" );
		ShoppingCart cart1 = new ShoppingCart(client1);
		client1.setCart(cart1);
		
		Client client2 = new Client("Abby Cole", "1400 London Rd, Duluth, MN 55805", "(218)555-9840" );
		ShoppingCart cart2 = new ShoppingCart(client2);
		client2.setCart(cart2);
		
		Client client3 = new Client("Shawn Miller", "817 Shea Blvd, Scottsdale, AZ 55805", "(480)555-3187" );
		ShoppingCart cart3 = new ShoppingCart(client3);
		client3.setCart(cart3);
		
		Product tent = new Product("TEN47", "Tent", "Kelty 5500 Series 4 person tent", 12, 249.99);
		Product sleepBag = new Product("BAG84", "Sleeping Bag", "North Face Artic Down Sleeping Bag", 20, 129.00);
		Product pack = new Product("PAC33", "Backpack", "Osprey Cloudlite 52 liter backpack", 7, 118.00);
		Product stove = new Product("STO17", "Backpacking Stove", "MSR Pocket Rocket Stove", 0, 44.95);
		
		ProductList productList = ProductList.instance();
		productList.insertProduct(tent);
		productList.insertProduct(sleepBag);
		productList.insertProduct(pack);
		productList.insertProduct(stove);
		
		// // Print the attributes of the ShoppingCart object
		// System.out.println("Showing the shopping cart for " + cart1.getClient().getName());
		// cart1.printCart();
		
		// Print the Product List
		System.out.println("Products sold by the warehouse:");
		System.out.println(productList.toString());
		System.out.println();
		
		// Add items to the cart
		cart1.addToCart(tent, 1);
		cart1.addToCart(sleepBag, 2);
		cart1.addToCart(pack, 2);
		cart1.addToCart(stove, 1);
		
		cart2.addToCart(stove, 12);
		cart3.addToCart(stove, 3); 
		
		// // Print the items in the cart
		// System.out.println("Contents of cart after adding all items:");
		// cart1.printCart();
		
		// // Remove one item from the cart
		// cart1.removeQuantity(sleepBag);
		
		// updateQuantity(cart1, "PAC33", 3, productList);	
		
		// // Reprint the items in the cart after item removal and updating pack quantity
		// System.out.println("Contents of the cart after removing sleeping bags and increasing quantity of backpack:");
		// cart1.printCart();
		
		
		// Process the order and print invoice
		String description1 = processCart(keyboard);
		cart1.processOrder(description1);
		
		// // Print the cart contents after processing order
		// System.out.println("Contents of the cart after processing order:");
		// cart1.printCart();
		
		// // Print client's updated account balance
		// System.out.println("Client's account balance after processing order:");
		// System.out.println("$" + String.format("%.2f", client1.getBalance()));
		
		// // Print client's transaction history
		// System.out.println("Client's transaction history:");
		// client1.printTransactions();
		
		// // Print product list with updated quantities
		// System.out.println("Product List showing updated quantities after processing order:");
		// System.out.println(productList.toString());
		String description2 = processCart(keyboard);
		cart2.processOrder(description2);		
		String description3 = processCart(keyboard);
		cart3.processOrder(description3);		
		
		// Print waitlist for a given product
		System.out.println("Full waitlist for stove: ");
		stove.printWaitlist();
		
		// POC for receiving a shipment
		System.out.println("SHIPMENT OF 10 BACKPACKING STOVES RECEIVED"); 
		receiveShipment(productList, "STO17", 10);
		
		// Print waitlist after receiving shipment	
		System.out.println("Waitlist after receiving stove shipment and processing waitlisted orders");
		stove.printWaitlist(); 
		
		// Verify updated inventory of stove after receiving shipment
		System.out.println("Stove inventory after receiving shipment and processing waitlisted orders: \nAmount in stock = " + stove.getAmountInStock());
		
		keyboard.close();
	}	
	
	// This code will take place in the Warehouse class when system is fully interactive
	public static boolean updateQuantity(ShoppingCart cart, String productId, int quantityOfItems, ProductList productList){
		boolean updated = cart.updateCart(productId, quantityOfItems);	
		if (!updated){
			// Product not in cart.  Add it to cart.
			Product product = productList.searchProduct(productId);
			cart.addToCart(product, quantityOfItems);
		}
		return true;
	}
	
	// This code will take place in the Warehouse class when system is fully interactive
	public static boolean receiveShipment(ProductList productList, String productId, int qtyReceived){
		Product product = productList.searchProduct(productId);
		product.setAmountInStock(qtyReceived);
		int remainingQty = product.processWaitlist(qtyReceived);
		product.setAmountInStock(remainingQty);	
		return true;
	}

	public static String processCart(Scanner keyboard){
		System.out.println("Please enter a description for this order: ");
		String description = keyboard.nextLine();	
		return description;
	}
	
}