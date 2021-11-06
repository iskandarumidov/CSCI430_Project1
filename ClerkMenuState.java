// NEED TO CODE WAREHOUSE CONTEXT
// NEED TO PUT A setClient() METHOD IN WAREHOUSE CONTEXT!!!
// MAKE SURE CLIENT STATE IS STATE 1 IN LOGIN STATE
// NEED TO TEST viewWaitlist() AND clientMenu() METHODS
// WHEN TESTING, CHECK IF YOU SHOULD CALL WAREHOUSE METHODS WITH Warehouse.instance() or warehouse!!!
// (might be able to change this in your two new methods to keep consistency (they might be interchangeable))

import java.util.*;
import java.text.*;
import java.io.*;
public class ClerkMenuState extends WarehouseState {
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private WarehouseContext context;
  private static ClerkMenuState instance;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int GET_OUTSTANDING_BALANCES = 2;
  private static final int ACCEPT_SHIPMENT = 3;
  private static final int SHOW_CLIENTS = 4;
  private static final int SHOW_PRODUCTS = 5;
  private static final int VIEW_WAITLIST  = 6;
  private static final int CLIENT_MENU = 7;
  private static final int HELP = 8;
  private ClerkMenuState() {
      super();
      warehouse = Warehouse.instance();
      //context = WarehouseContext.instance();
  }

  public static ClerkMenuState instance() {
    if (instance == null) {
      instance = new ClerkMenuState();
    }
    return instance;
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
        int value = Integer.parseInt(getToken("Enter command (" + HELP + " for help):"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 8 as explained below:");
    System.out.println(EXIT + " to log out\n");
    System.out.println(ADD_CLIENT + " to add a new client");
    System.out.println(GET_OUTSTANDING_BALANCES  + " to print all clients with outstanding balances");
    System.out.println(ACCEPT_SHIPMENT  + " to accept a shipment from a supplier");
    System.out.println(SHOW_CLIENTS  + " to print clients");
    System.out.println(SHOW_PRODUCTS  + " to print products");
    System.out.println(VIEW_WAITLIST  + " to view the waitlist for a product");
    System.out.println(CLIENT_MENU + " to switch to the client menu");
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

  public void getOutstandingBalances() {
    warehouse.getOutstandingBalances();  
  }
 
  public void acceptShipment() {
    String productID = getToken("Enter product id");
    int qtyReceived =  Integer.valueOf(getToken("Enter quantity received from supplier"));
    warehouse.receiveShipment(productID, qtyReceived);
  }
  
  public void showClients() {
      Iterator allClients = warehouse.getClients();
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
  
  public void viewWaitlist() {
    String productID = getToken("Please input the product id: ");
	Product currentProduct = Warehouse.instance().searchProducts(productID);
    if (currentProduct != null){
		warehouse.getWaitlist(currentProduct);
    }
    else 
      System.out.println("Invalid product ID."); 
  }

  public void clientMenu() {
    String clientID = getToken("Please input the client id: ");
    if (Warehouse.instance().searchClients(clientID) != null){
      (WarehouseContext.instance()).setClient(clientID);      
      (WarehouseContext.instance()).changeState(1);
    }
    else 
      System.out.println("Invalid client ID."); 
  }

  public void logout() {
    (WarehouseContext.instance()).changeState(0); // exit with a code 0
  }
 

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:        		addClient();
										break;
        case GET_OUTSTANDING_BALANCES:	getOutstandingBalances();
										break;
        case ACCEPT_SHIPMENT: 	  		acceptShipment();
										break;
        case SHOW_CLIENTS:  			showClients();
										break;
		case SHOW_PRODUCTS:				showProducts();
								        break; 	
	    case VIEW_WAITLIST:				viewWaitlist();
										break; 	
        case CLIENT_MENU:              	clientMenu();
										break;		
        case HELP:             			help();
										break;
      }
    }
    logout();
  }
  
  public void run() {
    process();
  }
}
