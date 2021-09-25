import java.util.*;
import java.lang.*;
import java.io.*;

public class ProductList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<Product> products = new LinkedList<Product>();
  private static ProductList productList;

  private ProductList() {}
    
  public Iterator getProducts() {
    return products.iterator();
  }
  
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

  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(productList);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (productList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (productList == null) {
          productList = (ProductList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in ProductList readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  
  public Product searchProduct(String productID)
	{
		Iterator productIterator = products.iterator();

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