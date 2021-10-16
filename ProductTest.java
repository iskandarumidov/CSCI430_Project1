// import java.util.*;

// class ProductTest{
//     public static void main(String[] args)
//     {
//         System.out.println("TESTING PRODUCT");
//         Product product1 = new Product("id1", "Hammer", "Smash Things", 10,  11.11);
//         Product product5 = new Product("id5", "Sponge", "Wash Things", 20,  2.22);

//         Quantity quant1 = new Quantity(product1, 30);
//         Quantity quant2 = new Quantity(product1, 5);
//         Quantity quant3 = new Quantity(product1, 15);

//           //Tests getters
//         System.out.println("---------- PRINT NEW FIELDS -----------");
//         System.out.println(product1.getProductID());
//         System.out.println(product1.getName());
//         System.out.println(product1.getDescription());
//         System.out.println(product1.getAmountInStock());
//         System.out.println(product1.getSalesPrice());
//         System.out.println(product1.getItemAvailability());

//         //Test toString()
//         System.out.println(product1);

//         //Test addWaitlistedOrders()
//         product1.addWaitlistedOrder(quant1);
//         product1.addWaitlistedOrder(quant2);
//         product1.addWaitlistedOrder(quant3);

//         //Tests getWaitlistedOrders
//         System.out.println("---------- PRINT WAITLISTED ORDERS ----------");
//         Iterator waitlistedOrdersIterator = product1.getWaitlistedOrders();
//         while(waitlistedOrdersIterator.hasNext())
//         {
//             Quantity curwaitlistedOrders = (Quantity)(waitlistedOrdersIterator.next());
//             System.out.println(curwaitlistedOrders);
//         }
//     }
// }