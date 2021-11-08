import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerState extends WarehouseState {
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
    private static final int ADD_SUPPLIER_PRODUCT = 9;
    private static final int REMOVE_PRODUCT = 10;
    private static final int UPDATE_PRICE = 11;

    private ManagerState(){
        warehouse = Warehouse.instance();
    }

    public static ManagerState instance(){
        if (managerstate == null){
            managerstate = new ManagerState();
        }
        return managerstate;
    }

    public String getToken(String prompt){
        do{
            try{
                System.out.println(prompt);
                String line = reader.readLine();
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
                date.setTime(df.parse(item));
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
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if(value >= EXIT && value <= HELP){
                    return value;
                }
            }catch(NumberFormatException nfe){
                System.out.println("Enter a number");
            }
        }while(true);
    }

    public void help(){
        System.out.println("Enter a number between 0 and 8 as explained below:");
        System.out.println(EXIT + " 0: to Exit\n");
        System.out.println(ADD_PRODUCT + " 1: to add a product\n");
        System.out.println(ADD_SUPPLIER + " 2: to add a supplier\n");
        System.out.println(SUPPLIER_LIST + " 3: to show supplier List");
        System.out.println(PRODUCT_SUPPLIER_LIST + " 4: to show Product's List of Suppliers w/purchase prices\n");
        System.out.println(SUPPLIER_PRODUCT_LIST + " 5: to show Supplier's List of Products w/purchase prices\n");
        System.out.println(UPDATE_PRODUCT + " 6: to Update prducts and purchase prices for a supplier\n");
        System.out.println(SALESCLERK_MENU + " 7: to switch to the Sales-Clerk menu\n");
        System.out.println(HELP + " 8: for help");
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
    public void printAssignedSuppliers(){

        String productID = getToken("Enter product id");
        ProductList productList = ProductList.instance();
        Product product = productList.searchProduct(productID);
        String productName = product.getName();
        Double salesPrice = product.getSalesPrice();
        Iterator<ProductSupplier> productSuppliers = product.getSuppliers();

        System.out.println("Assigned Suppliers for Product " + productID + " " + productName + " " + salesPrice + ":");
        Iterator<ProductSupplier> iterator = productSuppliers;
          while(iterator.hasNext()){
            ProductSupplier currentProductSupplier = (ProductSupplier)iterator.next();
            Supplier currentSupplier = currentProductSupplier.getSupplier();
            System.out.println(currentSupplier.getSupplierID() + " " + currentSupplier.getName());
          }
          System.out.println();	  
    }

    // Gets the Supplier's products.
    public void printAssignedProducts(){
        
        String supplierID = getToken("Enter supplier id");
        SupplierList supplierList = SupplierList.instance();
        Supplier supplier = supplierList.searchSupplier(supplierID);
        String supplierName = supplier.getName();
        Iterator<ProductSupplier> suppliedProducts = supplier.getSuppliedProducts();

        System.out.println("Assigned Products for Supplier " + supplierID + " " + supplierName + ":");
        Iterator<ProductSupplier> iterator = suppliedProducts;
          while(iterator.hasNext()){
            ProductSupplier currentProductSupplier = (ProductSupplier)iterator.next();
            Product currentProduct = currentProductSupplier.getProduct();
            System.out.println(currentProduct.getProductID() + " " + currentProduct.getName() + " " + currentProduct.getSalesPrice());
          }
          System.out.println();	  
    }

    public void addSupplierProduct(){   //Add new product to ProductSupplier, used mainly for updateProduct
        Product result;
        String supplierID = getToken("Enter Supplier Id.");
        SupplierList supplierList = SupplierList.instance();
        Supplier supplier = supplierList.searchSupplier(supplierID);
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
            ProductSupplier productSupplier = new ProductSupplier(supplier, result, salesPrice);
            supplier.addProductSupplier(productSupplier);


        } while (true);
    }
    
    public void removeProduct(){    //Removes product from productSupplier
        SupplierList supplierList = SupplierList.instance();

        String supplierID = getToken("Enter Supplier Id.");
        Supplier supplier = supplierList.searchSupplier(supplierID);

        supplier.getSuppliedProducts();
        String productID = getToken("Enter Product id you would like to remove");
        supplier.unassignProduct(productID);
    }

    public void updatePrice(){  //Updates the purchase-price of the productSupplier
        SupplierList supplierList = SupplierList.instance();
        ProductList productList = ProductList.instance();

        String supplierID = getToken("Enter Supplier Id.");
        Supplier supplier = supplierList.searchSupplier(supplierID);
        supplier.getSuppliedProducts();

        String productId = getToken("Enter the Product Id that you would like to alter purchase-price");
        Product tempProduct = productList.searchProduct(productId);
        double purchasePrice = Double.parseDouble(getToken("Enter new purchase-price"));

        supplier.unassignProduct(productId);
        ProductSupplier productSupplier = new ProductSupplier(supplier, tempProduct, purchasePrice);
        supplier.addProductSupplier(productSupplier);
    }

    public void updateProduct(){
        do {
            SupplierList supplierList = SupplierList.instance();     
            String supplierID = getToken("Enter Supplier Id.");
            Supplier supplier = supplierList.searchSupplier(supplierID);
            supplier.getSuppliedProducts();

            System.out.println("\nEnter a number 0, 1, 9, or 10 as explained below.\n");
            System.out.println(EXIT + " 0: to Exit\n");
            System.out.println(ADD_PRODUCT + " 1: to add a product to product-supplier\n");
            System.out.println(REMOVE_PRODUCT + " 9: to remove a product from product-supplier\n");
            System.out.println(UPDATE_PRICE + " 10: to update purchase price\n");
            
            int command;
            while((command = getCommand()) != EXIT){
                switch (command){
                    case ADD_SUPPLIER_PRODUCT:  addSupplierProduct();
                                                break;
                    case REMOVE_PRODUCT:        removeProduct();
                                                break;
                    case UPDATE_PRICE:          updatePrice();
                                                break;
                }
            }

        } while(true);
    }

    public void salesClerkMenu(){
            (WarehouseContext.instance()).changeState(2);
    }

    public void process(){
        int exitcode = -1;
        help();
        boolean done = false;
        while(!done){
            switch(getCommand()){
                case ADD_PRODUCT:           addProduct();
                                            break;
                case ADD_SUPPLIER:          addSupplier();
                                            break;
                case SUPPLIER_LIST:         showSuppliers();
                                            break;
                case PRODUCT_SUPPLIER_LIST: printAssignedSuppliers();
                                            break;
                case SUPPLIER_PRODUCT_LIST: printAssignedProducts();
                                            break;
                case UPDATE_PRODUCT:        updateProduct();
                                            break;
                case SALESCLERK_MENU:       salesClerkMenu();
                                            break;
                case HELP:                  help();
                                            break;
                case EXIT:                  exitcode = 0;
                                            done = true;
                    break;
            }
        }
    }
    public void run(){
        process();
    }
}