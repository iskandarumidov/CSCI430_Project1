import java.util.*;

class ShoppingCartTest {

    public static void main(String[] args)
    {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("TESTING SHOPPING CART");
        ProductList productList = ProductList.instance();
        Product product1 = new Product("id1", "Item1", "description1", 100, 11.11);
        Product product2 = new Product("id2", "Item2", "description2", 200, 22.22);
        Product product3 = new Product("id3", "Item3", "description3", 300, 33.33);
        Product product4 = new Product("id4", "Item4", "description4", 400, 44.44);
        Client client = new Client("Bob", "123 8th Ave S", "123-456-7686");
        ShoppingCart cart = new ShoppingCart(client);
        cart.addToCart(product1, 1);
        cart.addToCart(product2, 1);
        cart.addToCart(product3, 1);

        Iterator<Quantity> iter = cart.getItems();

        while (iter.hasNext())
		{
			Quantity quan = (Quantity)(iter.next());
			System.out.println(quan);
		}

        cart.updateCart("id1", 9);
        System.out.println("Updated cart");
        iter = cart.getItems();
        while (iter.hasNext())
		{
			Quantity quan = (Quantity)(iter.next());
			System.out.println(quan);
		}
        
        System.out.println("END TESTING SHOPPING CART");
        System.out.println("=====================================");
        System.out.println();
    }

}