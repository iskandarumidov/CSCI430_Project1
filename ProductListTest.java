import java.util.*;

class ProductListTest {

    public static void main(String[] args)
    {

        System.out.println("TESTING PRODUCTLIST");
        ProductList productList = ProductList.instance();
        Product product1 = new Product("id1", "Item1", "description1", 100, 11.11);
        Product product2 = new Product("id2", "Item2", "description2", 200, 22.22);
        Product product3 = new Product("id3", "Item3", "description3", 300, 33.33);
        Product product4 = new Product("id4", "Item4", "description4", 400, 44.44);

        // Test insertProduct()
        productList.insertProduct(product1);
        productList.insertProduct(product2);
        productList.insertProduct(product3);
        productList.insertProduct(product4);

        // Test getProducts
        Iterator productIterator = productList.getProducts();
        while (productIterator.hasNext())
        {
            Product product = (Product)(productIterator.next());
            System.out.println(product);
        }

        // Test instance() 
        ProductList productList2 = ProductList.instance();
        Iterator productIterator2 = productList2.getProducts();
        while(productIterator2.hasNext())
        {
            Product product = (Product)(productIterator2.next());
            System.out.println(product);
        }

        // Test searchProduct()
        System.out.println(productList.searchProduct("id1"));
        System.out.println(productList.searchProduct("id5"));
        System.out.println(productList.searchProduct(null));

        System.out.println(productList);
    }

}