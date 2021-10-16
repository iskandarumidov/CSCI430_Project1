import java.io.*;
public class Quantity implements Serializable {
  private static final long serialVersionUID = 1L;
  private Client client;
  private Product product;
  private int qty;
 


  public Quantity(Client client, Product product, int qty) {
	this.client = client;
    this.product = product;
    this.qty = qty;
  }

  public Product getProduct() {
    return product;
  }
  public int getQty() {
    return qty;
  }
  
  public void setQty(int updatedQty) {
	  qty = updatedQty;
  }
  
  // NEW METHOD!!!
  // This method checks the amount in stock for a given product
  public int checkAvailability(){
	  return getProduct().getAmountInStock();
  }

  public String toString() {
      return "Client: " + client.getName() + ", Product: " + product.getName() + ", Quantity: " + qty;
  }
}
