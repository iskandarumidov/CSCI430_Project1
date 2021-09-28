import java.util.*;
import java.io.*;

public class Client implements Serializable{
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private static final String CLIENT_STRING = "C";
    
  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = CLIENT_STRING + ClientIdServer.instance().getId();
  }

  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }
  //TODO Iskandar - should this accept another Client instance rather than String?
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Client name " + name + " address " + address + " id " + id + " Phone Number " + phone;
    return string;
  }
}