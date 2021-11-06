import java.util.*;
import java.text.*;
import java.io.*;
public class Loginstate extends WarehouseState{
  private static final int CLIENT = 0;
  private static final int MANAGER = 1;
  private static final int SALESPERSON = 2;
  private static final int EXIT = 3;


  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
  private WarehouseContext context;
  private static Loginstate instance;
  private Loginstate() {
      super();
     // context = WarehouseContext.instance();
  }

  public static Loginstate instance() {
    if (instance == null) {
      instance = new Loginstate();
    }
    return instance;
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" ));
        if (value <= EXIT && value >= CLIENT_LOGIN) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
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
  //Wrong?
  // private void client(){
  //   (WarehouseContext.instance()).setLogin(WarehouseContext.IsClient);
  //   (WarehouseContext.instance()).changeState(0);
  // }
  private void client(){
    String userID = getToken("Please input the manager id: ");
    if (Warehouse.instance().searchMembership(userID) != null){
      (WarehouseContext.instance()).setLogin(WarehouseContext.IsUser);
      (WarehouseContext.instance()).setUser(userID);      
      (WarehouseContext.instance()).changeState(0);
    }
    else 
      System.out.println("Invalid user id.");
  } 

  private void manager(){
    String userID = getToken("Please input the manager id: ");
    if (Warehouse.instance().searchMembership(userID) != null){
      (WarehouseContext.instance()).setLogin(WarehouseContext.IsUser);
      (WarehouseContext.instance()).setUser(userID);      
      (WarehouseContext.instance()).changeState(1);
    }
    else 
      System.out.println("Invalid user id.");
  } 

  private void salesperson(){
    String userID = getToken("Please input the salesperson id: ");
    if (Warehouse.instance().searchMembership(userID) != null){
      (WarehouseContext.instance()).setLogin(WarehouseContext.IsUser);
      (WarehouseContext.instance()).setUser(userID);      
      (WarehouseContext.instance()).changeState(2);
    }
    else 
      System.out.println("Invalid user id.");
  } 

  public void process() {
    int command;
      System.out.println("Please input 0 to login as Client\n"+ 
                        "input 1 to login as Manager\n" +
                        "input 2 to login as SalesPerson\n" +
                        "input 3 to exit the system\n");  
    while ((command = getCommand()) != EXIT) {

      switch (command) {
        case CLIENT:       client();
                                break;
        case MANAGER:        manager();
                                break;
        case SALESPERSON:        salesperson();
                                break;
        default:                System.out.println("Invalid choice");

      }
      System.out.println("Please input 0 to login as Client\n"+ 
                        "input 1 to login as Manager\n" +
                        "input 2 to login as SalesPerson\n" +
                        "input 3 to exit the system\n"); 
    }
    (WarehouseContext.instance()).changeState(3);
  }

  public void run() {
    process();
  }
}