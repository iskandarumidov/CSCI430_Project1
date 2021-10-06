import java.util.*;

class ClientListTest {
    public static void main(String[] args)
    {
        System.out.println("TESTING ClientLIST");
        ClientList clientList = ClientList.instance();
        Client client1 = new Client("jose", "123 1234th Ave N", "123-456-7890");
        Client client2 = new Client("josh", "234 45th Ave S", "123-456-7891");
        Client client3 = new Client("jamal", "385 78th Ave E", "123-456-7892");
        Client client4 = new Client("kevin", "895 3th Ave W", "123-456-78903");
        //Tests insertClient()
        clientList.insertClient(client1);
        clientList.insertClient(client2);
        clientList.insertClient(client3);
        clientList.insertClient(client4);
        //Tests getClient()
        Iterator clientIterator = clientList.getClients();

        while (clientIterator.hasNext())
		{
			 Client client = (Client)(clientIterator.next());
			System.out.println(client);
		}
        //Tests instance() to make sure list is the same
        ClientList clientList2 = ClientList.instance();
        Iterator clientIterator2 = clientList2.getClients();

        while (clientIterator2.hasNext())
		{
			 Client client = (Client)(clientIterator2.next());
			System.out.println(client);
		}
        
        //Tests searchProductList() and toString()
        System.out.println(clientList.searchClient("id1"));
        System.out.println(clientList.searchClient("id4"));
        System.out.println(clientList.searchClient(null));
    }
}