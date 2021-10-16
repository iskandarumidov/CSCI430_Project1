import java.util.*;

class ClientTest {
    public static void main(String[] args)
    {
        System.out.println("TESTING ClientLIST");
        ClientList clientList = ClientList.instance();
	
        Client client1 = new Client("jose", "123 1234th Ave N", "123-456-7890");
        Client client2 = new Client("josh", "234 45th Ave S", "123-456-7891");
        Client client3 = new Client("jamal", "385 78th Ave E", "123-456-7892");
        Client client4 = new Client("kevin", "895 3th Ave W", "123-456-78903");

  Transaction transaction = new Transaction("test", 10);
	client1.setBalance(1);
	client2.setBalance(2);
	client3.setBalance(3);
	client4.setBalance(4);
  float newBalance = 9;
	String description1 = "test";
	float dollarAmount1 = 12;
	client1.createTransaction(description1, dollarAmount1);
	client1.addTransactionToList(transaction);

	String description2 = "test2";
	float dollarAmount2 = 13;
	client1.createTransaction(description2, dollarAmount2);
	client1.addTransactionToList(transaction);

	String description3 = "test3";
	float dollarAmount3 = 14;
	client2.createTransaction(description3, dollarAmount3);
	client2.addTransactionToList(transaction);

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
			newBalance++;
			System.out.println("final:" + client + client.getBalance());
      System.out.println(client.hasOutstandingBalance());
      System.out.println(client.getTransactions());
		}
    }
}