import java.util.*;
import java.io.*;
//TODO Iskandar Does this need readobject & readobject?
public class ProductSupplier implements Serializable {
	private static final long serialVersionUID = 1L;
	private Supplier supplier; 
	private Product product;
	private double price; 
	
	public ProductSupplier(Supplier supplier, Product product, double price) {
		this.supplier = supplier;
		this.product = product;
		this.price = price;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	public Product getProduct() {
		return product;
	}
	public double getPrice() {
		return price;
	}
	
	public String toString() {
	    return supplier.toString() +  " " + product.toString() + " Price " + price;
	  }
	  
}