class ProductSupplierTest {
    public static void main(String[] args)
    {
        System.out.println("TESTING PRODUCTSUPPLIER");
        Supplier supplier1 = new Supplier("id1", "Mark", "123 6th Ave S", "111-111-1111");
        Supplier supplier5 = new Supplier("id5", "wrong name", "wrong address", "111-111-1111");

        Product pen = new Product("pid1", "Pen", "Writing tool", 100, 5.0);
        Product pencil = new Product("pid2", "Pencil", "Another writing tool", 50, 4.0);
        Product eraser = new Product("pid3", "Eraser", "Erases well", 30, 6.0);
        Product car = new Product("pid4", "Car", "Has four wheels", 2, 20000.0);
        Product glasses = new Product("pid5", "Glasses", "For your eyes", 3, 300.0);

        ProductSupplier pair1 = new ProductSupplier(supplier1, pen, 3.0);
        ProductSupplier pair2 = new ProductSupplier(supplier1, pencil, 3.5);
        ProductSupplier pair3 = new ProductSupplier(supplier1, eraser, 3.0);

        System.out.println("---------GETTERS---------");
        System.out.println(pair1.getSupplier());
        System.out.println(pair1.getProduct());
        System.out.println(pair1.getPrice());

        System.out.println("---------TO STRING---------");
        System.out.println(pair1);
        System.out.println(pair2);
        System.out.println(pair3);
    }
}