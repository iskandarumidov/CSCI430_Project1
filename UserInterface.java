/* 
 * The following Warehouse methods have been renamed to avoid naming conflicts or for brevity:
 *   - from the Add To Cart sequence diagram: addProduct() has been renamed to addToCart() 
 *   - from the Remove From Cart sequence diagram: removeProduct() has been renamed to removeFromCart()
 *   - from the Generate Transaction Report sequence diagram: generateClientTransactionReport() has been renamed to getTransactions()
 *   - from the Generate Supplier Report sequence diagram: generateSupplierReport() has been renamed to getSupplyPrices()
 *   - from the Generate Outstanding Balance Report sequence diagram: generateOutstandingBalanceReport() has been renamed to getOutstandingBalances()
 * The Warehouse method processCart() was missed on the sequence diagram (it should be placed before the
 * looping of getQuantity() which retrieves all Quantity objects)
 */

import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int ADD_SUPPLIER = 3;
  private static final int ADD_TO_CART = 4;
  private static final int REMOVE_FROM_CART = 5;
  private static final int PROCESS_CART = 6;
  private static final int GET_TRANSACTIONS = 7;
  private static final int GET_SUPPLY_PRICES = 8;
  private static final int GET_OUTSTANDING_BALANCES = 9;
  private static final int SHOW_CLIENTS = 10;
  private static final int SHOW_PRODUCTS = 11;
  private static final int SHOW_SUPPLIERS = 12;
  private static final int SAVE = 13;
  private static final int RETRIEVE = 14;
  private static final int HELP = 15;
  private UserInterface() {
    if (yesOrNo("Look for saved data and use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 15 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a new client");
    System.out.println(ADD_PRODUCT + " to add a new product");
    System.out.println(ADD_SUPPLIER + " to add a new supplier");
    System.out.println(ADD_TO_CART  + " to add products to shopping cart");
    System.out.println(REMOVE_FROM_CART  + " to remove products from shopping cart");
    System.out.println(PROCESS_CART + " to process shopping cart");
    System.out.println(GET_TRANSACTIONS  + " to print transactions for a client");
    System.out.println(GET_SUPPLY_PRICES  + " to print supply prices for a product");
    System.out.println(GET_OUTSTANDING_BALANCES  + " to print all clients with outstanding balances");
    System.out.println(SHOW_CLIENTS  + " to print clients");
    System.out.println(SHOW_PRODUCTS  + " to  print products");
    System.out.println(SHOW_SUPPLIERS  + " to  print suppliers");
    System.out.println(SAVE + " to save data");
    System.out.println(RETRIEVE + " to retrieve");
    System.out.println(HELP + " for help");
  }

  public void addClient() {
    String name = getToken("Enter client name");
    String address = getToken("Enter address");
    String phone = getToken("Enter phone");
    Client result;
    result = warehouse.addClient(name, address, phone);
    if (result == null) {
      System.out.println("Could not add client");
    }
    System.out.println(result);
  }

  public void addProduct() {
    Product result;
    do {
      String id = getToken("Enter product id");
      String name = getToken("Enter product name");
      String description = getToken("Enter product description");
      double salesPrice =  Integer.valueOf(getToken("Enter sales price"));
	    int amountInStock = Integer.valueOf(getToken("Enter amount in stock"));
      result = warehouse.addProduct(id, name, description, amountInStock, salesPrice);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Product could not be added");
      }
      if (!yesOrNo("Add more products?")) {
        break;
      }
    } while (true);
  }
  
  public void addSupplier() {
    String id = getToken("Enter supplier id");
    String name = getToken("Enter supplier name");
    String address = getToken("Enter address");
    String phone = getToken("Enter phone");
    Supplier result;
    result = warehouse.addSupplier(id, name, address, phone);
    if (result == null) {
      System.out.println("Could not add supplier");
    }
    System.out.println(result);
  }
  
  public void addToCart() {
         System.out.println("Dummy Action");
  }
  public void removeFromCart() {
      System.out.println("Dummy Action");
  }
  public void processCart() {
      System.out.println("Dummy Action");
  }
  
  public void getTransactions() {
      System.out.println("Dummy Action");   
  }
  
  public void getSupplyPrices() {
      System.out.println("Dummy Action");   
  }
  
  public void getOutstandingBalances() {
      System.out.println("Dummy Action");   
  }

  public void showClients() {
      Iterator<Client> allClients = warehouse.getClients();
      while (allClients.hasNext()){
	  Client client = (Client)(allClients.next());
          System.out.println(client.toString());
      }
  }
  
  public void showProducts() {
      Iterator<Product> allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
	  Product product = (Product)(allProducts.next());
          System.out.println(product.toString());
      }
  }

  public void showSuppliers() {
      Iterator<Supplier> allSuppliers = warehouse.getSuppliers();
      while (allSuppliers.hasNext()){
	  Supplier supplier = (Supplier)(allSuppliers.next());
          System.out.println(supplier.toString());
      }
  }

  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
    } else {
      System.out.println(" There has been an error in saving \n" );
    }
  }
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:        		addClient();
										                break;
        case ADD_PRODUCT:       		addProduct();
										                break;
        case ADD_SUPPLIER:      		addSupplier();
										                break;
        case ADD_TO_CART:       		addToCart();
										                break;
        case REMOVE_FROM_CART:  		removeFromCart();
										                break;
        case PROCESS_CART:      		processCart();
										                break;
        case GET_TRANSACTIONS:  		getTransactions();
										                break;
        case GET_SUPPLY_PRICES: 		getSupplyPrices();
										                break;
        case GET_OUTSTANDING_BALANCES:	getOutstandingBalances();
										                    break;
        case SHOW_CLIENTS:  			showClients();
										              break;
		    case SHOW_PRODUCTS:				showProducts();
										              break; 	
	    	case SHOW_SUPPLIERS:			showSuppliers();
										              break; 	
        case SAVE:              	save();
										              break;
        case RETRIEVE:         		retrieve();
										              break;		
        case HELP:             		help();
										              break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
