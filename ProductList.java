import java.util.*;
import java.io.*;

//TODO Iskandar add writeObject & readObject? For SupplierList and ProductList
public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<Product> products = new LinkedList<Product>();
  private static ProductList productList;

  private ProductList() {}
    
  public Iterator<Product> getProducts() {
    return products.iterator();
  }
  
  //Singleton
  public static ProductList instance() {
    if (productList == null) {
      return (productList = new ProductList());
    } else {
      return productList;
    }
  }
  
  public boolean insertProduct(Product product) {
    products.add(product);
    return true;
  }

  public String toString() {
    return products.toString();
  }
  
  public Product searchProduct(String productID)
	{
		Iterator<Product> productIterator = products.iterator();

		while (productIterator.hasNext())
		{
			Product product = (Product)(productIterator.next());
			if (product.getProductID().equals(productID))
			{
				return product;
			}
		}
		return null;
	}
}