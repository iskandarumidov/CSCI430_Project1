import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerState extends loginState {
    private static ManagerState managerstate;
    private BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static final int EXIT = 0;
    private static final int ADD_PRODUCT = 1;
    private static final int ADD_SUPPLIER = 2;
    private static final int SUPPLIER_LIST = 3;
    private static final int PRODUCT_SUPPLIER_LIST = 4;
    private static final int SUPPLIER_PRODUCT_LIST = 5;
    private static final int UPDATE_PRODUCT = 6;
    private static final int SALESCLERK_MENU = 7;
    private static final int HELP = 8;

    private ManagerState(){
        warehouse = Warehouse.instance();
    }

    public static ManagerState instance(){
        if (instance == null){
            instance = new ManagerState();
        }
        return instance;
    }

    public String getToken(String prompt){
        do{
            try{
                System.out.println(prompt);
                String line = reader.redLine();
                StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
                if(tokenizer.hasMoreTokens()){
                    return tokenizer.nextToken();
                }
            }
            catch (IOException ioe){
                System.exit(0);
            }
        } while(true);
    }

    private boolean yesOrNo(String prompt){
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if(more.charAt(0) != 'y' && more.charAt(0) != 'Y'){
            return false;
        }
        return true;
    }

    public int getNumber(String prompt){
        do{
            try{
                String item = getToken(prompt);
                Integer num = Integer.valueOf(item);
                return num.intValue();
            }
            catch(NumberFormatException nfe){
                System.out.println("Please input a number ");
            }
        } while(true);
    }

    public Calendar getDate(String prompt){
        do{
            try{
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                dat.setTime(df.parse(item));
                return date;
            }
            catch(Exception fe){
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while(true);
    }

    public int getCommand(){
        do{
            try{
                int value o Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if(value >= EXIT && value <= HELP){
                    return value;
                }
            }catch(NumberFormatException nfe){
                System.out.println("Enter a number");
            }
        }while(true);
    }

    public void help(){
        System.out.println("Enter a number between 0 and 7 as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_PRODUCT + " to add a product\n");
        System.out.println(ADD_SUPPLIER + " to add a supplier\n");
        System.out.println(SUPPLIER_LIST + " to show supplier List");
        System.out.println(PRODUCT_SUPPLIER_LIST + " to show Product's List of Suppliers w/purchase prices\n");
        System.out.println(SUPPLIER_PRODUCT_LIST + " to show Supplier's List of Products w/purchase prices\n");
        System.out.println(UPDATE_PRODUCT + " to Update prducts and purchase prices fo a supplier\n");
        System.out.println(SALESCLERK_MENU + " to switch to the Sales-Clerk menu\n");
        System.out.println(HELP + "for help")
    }

    public void addProduct(){
        Product result;
        do {
            String id = getToken("Enter product id");
            String name = getToken("Enter product name");
            String description = getToken("Enter product description");
            double salesPrice =  Double.parseDouble(getToken("Enter sales price"));
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

    // Gets the list of all suppliers
    public void showSuppliers() {
        Iterator<Supplier> allSuppliers = warehouse.getSuppliers();
        while (allSuppliers.hasNext()){
	        Supplier supplier = (Supplier)(allSuppliers.next());
            System.out.println(supplier.toString());
        }
    }

    // Gets the product's suppliers.
    public Iterator<ProductSupplier> getSuppliers() {
        return assignedSuppliers.iterator();
    }

    // Gets the Supplier's products.
    public Iterator<ProductSupplier> getSuppliedProducts()
	{
		return suppliedProducts.iterator();
	}



    public boolean salesClerkMenu(){
        String userID = getToken("Please input the Sales-Clerk Id: ");
        if(Warehouse.instance().searchClerk(clerkID) != null){
            return true;
        }
        else{
            System.out.println("Invalid Clerk Id.");
            return false;
        }

    }

    pubilc void terminate(int exitcode){
        (warehouseContext.instance()).changeState(exitcode);
    }

    public void process(){
        int command, exitcode = -1;
        help();
        boolean done = false;
        while(!done){
            switch(getCommand()){
                case addProduct():          addProduct;
                    break;
                case addSupplier():         addSupplier;
                    break;
                case SUPPLIER_LIST:         showSuppliers();
                    break;
                case PRODUCT_SUPPLIER_LIST:
                                            getSuppliers
                    break;
                case SUPPLIER_PRODUCT_LIST:
                                            getSuppliedProducts
                    break;
                case UPDATE_PRODUCT:

                    break;
                case SALESCLERK_MENU:       if(salesClerkMenu()){
                                                exitcode = 1;
                                                dont = true;
                                            }
                    break;
                case HELP:                  help();
                    break;
                case EXIT:                  exitcode = 0;
                                            done = true;
                    break;
            }
        }
        terminate(exitcode);
    }
}