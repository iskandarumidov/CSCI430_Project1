class ProductSupplierTest {
    public static void main(String[] args)
    {
        System.out.println("---------TESTING PRODUCTSUPPLIER---------");
        SupplierList supplierList = SupplierList.instance();
        ProductList productList = ProductList.instance();
        Supplier mark = new Supplier("id1", "Mark", "123 6th Ave S", "111-111-1111");
        Supplier bob = new Supplier("id2", "Bob", "123 6th Ave S", "111-111-1111");
        Supplier john = new Supplier("id3", "John", "123 6th Ave S", "111-111-1111");

        Product pen = new Product("pid1", "Pen", "Writing tool", 100, 5.0);
        Product pencil = new Product("pid2", "Pencil", "Another writing tool", 50, 4.0);
        Product eraser = new Product("pid3", "Eraser", "Erases well", 30, 6.0);
        Product car = new Product("pid4", "Car", "Has four wheels", 2, 20000.0);
        Product glasses = new Product("pid5", "Glasses", "For your eyes", 3, 300.0);

        ProductSupplier markPen = new ProductSupplier(mark, pen, 3.0);
        ProductSupplier markPencil = new ProductSupplier(mark, pencil, 3.5);
        ProductSupplier markEraser = new ProductSupplier(mark, eraser, 6.0);
        ProductSupplier bobEraser = new ProductSupplier(bob, eraser, 2.0);
        ProductSupplier johnEraser = new ProductSupplier(john, eraser, 1.5);
        ProductSupplier johnCar = new ProductSupplier(john, car, 1.0);
        ProductSupplier johnGlasses = new ProductSupplier(john, glasses, 4.0);

        supplierList.insertSupplier(mark);
        supplierList.insertSupplier(bob);
        supplierList.insertSupplier(john);

        productList.insertProduct(pen);
        productList.insertProduct(pencil);
        productList.insertProduct(eraser);
        productList.insertProduct(car);
        productList.insertProduct(glasses);

        mark.addProductSupplier(markPen);
        mark.addProductSupplier(markPencil);
        mark.addProductSupplier(markEraser);
        bob.addProductSupplier(bobEraser);
        john.addProductSupplier(johnEraser);
        john.addProductSupplier(johnCar);
        john.addProductSupplier(johnGlasses);


        pen.addProductSupplier(markPen);
        pencil.addProductSupplier(markPencil);
        eraser.addProductSupplier(markEraser);
        eraser.addProductSupplier(bobEraser);
        eraser.addProductSupplier(johnEraser);
        car.addProductSupplier(johnCar);
        glasses.addProductSupplier(johnGlasses);
        


        System.out.println("---------GETTERS---------");
        System.out.println(markPen.getSupplier());
        System.out.println(markPen.getProduct());
        System.out.println(markPen.getPrice());

        System.out.println("---------TO STRING---------");
        System.out.println(markPen);
        System.out.println(markPencil);
        System.out.println(markEraser);
        System.out.println();

        System.out.println("---------ASSIGNED PRODUCTS---------");
        mark.printAssignedProducts();
        bob.printAssignedProducts();
        john.printAssignedProducts();

        System.out.println("---------ASSIGNED SUPPLIERS---------");
        pen.printAssignedSuppliers();
        pencil.printAssignedSuppliers();
        eraser.printAssignedSuppliers();
        car.printAssignedSuppliers();
        glasses.printAssignedSuppliers();

    }
}