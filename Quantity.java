import java.util.*;
import java.lang.*;
import java.io.*;
public class Quantity implements Serializable {
  private static final long serialVersionUID = 1L;
  private ShoppingCart cart;
  private Product product;
  private int quantity;


  public Quantity (ShoppingCart cart, Product product, int quantity) {
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
  }

  public ShoppingCart getCart() {
    return cart;
  }
  public Product getProduct() {
    return product;
  }
  public int getQuantity() {
    return quantity;
  }

  public String toString() {
      return "Cart: " + cart + " Product:  " + product + " Quantity: " + quantity;
  }
}
