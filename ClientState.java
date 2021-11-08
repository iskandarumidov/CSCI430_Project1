import java.util.*;
import java.text.*;
import java.io.*;
public class ClientState extends WarehouseState{
  private static ClientState clientState;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_TO_CART = 1;
  private static final int REMOVE_FROM_CART = 2;
  private static final int UPDATE_QUANTITY = 3;
  private static final int VIEW_CART = 4;
  private static final int PROCESS_CART = 5;
  private static final int SHOW_PRODUCTS = 6;
  private static final int GET_TRANSACTIONS = 7;
  private static final int GET_OUTSTANDING_BALANCES = 8;
  private static final int ACCEPT_PAYMENT = 9;
  private static final int SAVE = 10;
  private static final int RETRIEVE = 11;
  private static final int GET_CLIENT = 12;
  private static final int GET_WAITLIST = 13;
  private static final int HELP = 14;
  private ClientState() {
    if (yesOrNo("Look for saved data and use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }
  public static ClientState instance() {
    if (clientState == null) {
      return clientState = new ClientState();
    } else {
      return clientState;
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
    System.out.println("Enter a number between 0 and 19 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_TO_CART  + " to add products to shopping cart");
    System.out.println(REMOVE_FROM_CART  + " to remove products from shopping cart");
    System.out.println(UPDATE_QUANTITY  + " to change quantity of an item in shopping cart");
    System.out.println(VIEW_CART + " to view shopping cart");
    System.out.println(PROCESS_CART + " to process shopping cart");
	System.out.println(SHOW_PRODUCTS  + " to print products");
    System.out.println(GET_TRANSACTIONS  + " to print transactions for a client");
    System.out.println(GET_OUTSTANDING_BALANCES  + " to print all clients with outstanding balances");
    System.out.println(ACCEPT_PAYMENT  + " to apply a payment to a client's account");
    System.out.println(SAVE + " to save data");
    System.out.println(RETRIEVE + " to retrieve");
	System.out.println(GET_CLIENT + " to retrieve Client data");
	System.out.println(GET_WAITLIST + " to retrieve waitlist");
    System.out.println(HELP + " for help");
  }

  public void addToCart() {
    String clientID = getToken("Enter client id");
    String productID = getToken("Enter product id");
    int quantity =  Integer.valueOf(getToken("Enter desired quantity"));
    boolean result;
    result = warehouse.addToCart(clientID, productID, quantity);
    if(result){
      System.out.println("Product added to cart");
    }
    else {
      System.out.println("Unable to add product to cart");
    }
  }

  public void removeFromCart() {
    String clientID = getToken("Enter client id");
    String productID = getToken("Enter product id");
    boolean result;
    result = warehouse.removeFromCart(clientID, productID);
    if(result){
      System.out.println("Product removed from cart");
    }
    else {
      System.out.println("Unable to remove product from cart");
    }
  }

  public void updateQuantity() {
    String clientID = getToken("Enter client id");
    String productID = getToken("Enter product id");
    int quantity =  Integer.valueOf(getToken("Enter new quantity"));
    boolean result;
    result = warehouse.updateCart(clientID, productID, quantity);
    if(result){
      System.out.println("Product quantity updated");
    }
    else {
      System.out.println("Unable to update product quantity");
    }
  }

  public void viewCart() {
    String clientID = getToken("Enter client id");
    System.out.println("CONTENTS OF CART FOR CLIENT " + clientID);
    warehouse.viewCart(clientID);
  }

  public void processCart() {
    String clientID = getToken("Enter client id");
    String description = getToken("Enter a description for this order");
    warehouse.processCart(clientID, description);
  }
  
  public void getTransactions() {
    String clientID = getToken("Enter client id");
    warehouse.getTransactions(clientID);  
  }
  
  public void getOutstandingBalances() {
    warehouse.getOutstandingBalances();  
  }

public void getClientData() {
  String clientID = getToken("Enter client id");
	System.out.println(warehouse.searchClients(clientID));
  }
  
 public void getClientsWaitlist() {
	 String clientID = getToken("Enter client id");
	 warehouse.getClientWaitlist(clientID);
  }

  public Iterator<Product> getProducts() {
      return warehouse.getProducts();
  }

  public void acceptPayment() {
    String clientID = getToken("Enter client id");
    double paymentAmt =  Double.parseDouble(getToken("Enter amount of payment"));
    boolean result;
    result = warehouse.acceptPayment(clientID, paymentAmt);
    if(result){
      System.out.println("Payment applied successfully");
    }
    else {
      System.out.println("Unable to apply payment");
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

        case ADD_TO_CART: addToCart();
		break;
        case REMOVE_FROM_CART: removeFromCart();
		break;
        case UPDATE_QUANTITY: updateQuantity();
		break;
        case VIEW_CART: viewCart();
		break;                                                      
        case PROCESS_CART: processCart();
	    break;
        case GET_TRANSACTIONS: getTransactions();
		break;
        case GET_OUTSTANDING_BALANCES: getOutstandingBalances();
		break;
        case ACCEPT_PAYMENT: acceptPayment();
		break;
		case SHOW_PRODUCTS: getProducts();
		break; 	
        case SAVE: save();
		break;
        case RETRIEVE: retrieve();
		break;	
		case GET_CLIENT: getClientData();
		break;
		case GET_WAITLIST: getClientsWaitlist();
		break;
        case HELP: help();
		break;
      }
    }
  }
  
    public void run() {
    process();
  }
  
}
