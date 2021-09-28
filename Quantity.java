import java.io.*;
public class Quantity implements Serializable {
  private static final long serialVersionUID = 1L;
  private Product product;
  private int qty;


  public Quantity(Product product, int qty) {
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

  public String toString() {
      return "Product:  " + product.getName() + " Quantity: " + qty;
  }
}
