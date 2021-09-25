import java.util.*;
import java.io.*;

public class ProductSupplier implements Serializable {
	private static final long serialVersionUID = 1L;
	private Supplier supplier; 
	private Product product;
	private float price; 
	
	public ProductSupplier(Supplier supplier, Product product, float price) {
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
	public float getPrice() {
		return price;
	}
	
	public String toString() {
	    return  supplier.toString() +  " " + product.toString() + " Price " + price;
	  }
	  
}