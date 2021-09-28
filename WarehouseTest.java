import java.util.*;

public class WarehouseTest {
	
	public static void main(String[] args){
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
}