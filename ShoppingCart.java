import java.util.*;
import java.lang.*;
import java.io.*;
public class ShoppingCart implements Serializable {
  private static final long serialVersionUID = 1L;
  private Client client;
  private List<Product> products = new LinkedList<Product>();
  private double totalCost;


  public ShoppingCart(Client client, List products, double totalCost) {
    this.client = client;
    this.products = products;
    this.totalCost = totalCost;
  }

  public Client getClient() {
    return client;
  }
  public List getProducts() {
    return products;
  }
  public double getTotal() {
    return totalCost;
  }

  public String toString() {
      return "Client: " + client + " Products:  " + products.toString() + " Total: " + totalCost;
  }
}