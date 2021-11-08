import java.util.*;
import java.text.*;
import java.io.*;
public class WarehouseContext {
  
  private int currentState;
  private static Warehouse warehouse;
  private static WarehouseContext context;
  private int currentClient;
  private String clientID;
  private BufferedReader reader = new BufferedReader(new 
                                      InputStreamReader(System.in));

  public static final int IsClient = 0;
  public static final int IsManager = 1;
  public static final int IsClerk = 2;
  private WarehouseState[] states;
  private int[][] nextState;

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

  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The library has been successfully retrieved from the file LibraryData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new library" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }

  // !!! FIGURE OUT IF THESE SHOULD BE USER OR CLIENT !!!
  public void setLogin(int code)
  {currentClient = code;}

  public void setClient(String cID)
  { clientID = cID;}

  public int getLogin()
  { return currentClient;}

  public String getClient()
  { return clientID;}

  private WarehouseContext() { //constructor
    System.out.println("In Libcontext constructor");
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
    // !!! ERROR D/T MANAGER STATE NOT EXTENDED/NAMED PROPERLY !!!
    // set up the FSM and transition table;
    states = new WarehouseState[3];
    states[0] = ClientState.instance();
    states[1] = ManagerState.instance();
    states[2]=  ClerkMenuState.instance(); 
    states[3]=  Loginstate.instance();
    nextState = new int[3][3];
    nextState[0][0] = 3;nextState[0][1] = 3;nextState[0][2] = 3;nextState[0][3] = -2;
    nextState[1][0] = 3;nextState[1][1] = 3;nextState[1][2] = 2;nextState[1][3] = -2;
    nextState[2][0] = 0;nextState[2][1] = 3;nextState[2][2] = 3;nextState[2][3] = -2;
    nextState[3][0] = 0;nextState[3][1] = 1;nextState[3][2] = 2;nextState[3][3] = -1;
    currentState = 3;
  }

  public void changeState(int transition)
  {
    //System.out.println("current state " + currentState + " \n \n ");
    currentState = nextState[currentState][transition];
    if (currentState == -2) 
      {System.out.println("Error has occurred"); terminate();}
    if (currentState == -1) 
      terminate();
    //System.out.println("current state " + currentState + " \n \n ");
    states[currentState].run();
  }

  private void terminate()
  {
   if (yesOrNo("Save data?")) {
      if (warehouse.save()) {
         System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
       } else {
         System.out.println(" There has been an error in saving \n" );
       }
     }
   System.out.println(" Goodbye \n "); System.exit(0);
  }

  public static WarehouseContext instance() {
    if (context == null) {
       System.out.println("calling constructor");
      context = new WarehouseContext();
    }
    return context;
  }

  public void process(){
    states[currentState].run();
  }
  
  public static void main (String[] args){
    WarehouseContext.instance().process(); 
  }


}
