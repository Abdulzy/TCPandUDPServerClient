import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * AAA_UDP_CLIENT
 */
public class UDP_Client {

  /**
   * Returns a String of the current system time in "yyyy-MM-dd HH:mm:ss.SSS" format
   *
   * This code from:
   * https://stackoverflow.com/questions/1459656/how-to-get-the-current-time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
   *
   * @return String of current system time in "yyyy-MM-dd HH:mm:ss.SSS" format
   */
  private static String getFormattedCurrentSystemTime() {
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    // Get the current date
    Date currentDate = new Date();
    String currentDateTimeOutput = timeFormat.format(currentDate);
    return currentDateTimeOutput;
  }

  /**
   * Prints some statements to the console, informing the user of how to communicate with the server.
   *
   * @return void
   */
  private static void introduceClient() {
    System.out.println("Type commands to the server in the following format:");
    System.out.println("PUT (KEY) (VALUE) - puts an employee name (KEY) and an employee salary (VALUE) into the server.");
    System.out.println("GET (KEY) - GET an employee salary (KEY) from the server.");
    System.out.println("DELETE (KEY) - DELETE an employee entry (KEY) from the server");
    System.out.println();
    System.out.println("Here's an example how to use each:");
    System.out.println("'PUT Billy 100000'");
    System.out.println("'GET Billy'");
    System.out.println("'DELETE Billy'");
    System.out.println();
    System.out.println("Starting map is populated with following entries- Bill:70000, John:80000");
  }

  /**
   * Per project requirements, we are to pre-populate our map with data. This method calls 7 PUTS,
   * 5 GETS, and 5 DELETES.
   *
   * @param aSocket - socket which sends packets to server
   * @param iNetAddress - IP address or hostname, provided by the user
   * @param PORT_NUMBER - Port number, provided by the user
   * @return void
   */
  private static void fillUpServer(DatagramSocket aSocket, InetAddress iNetAddress, int PORT_NUMBER) throws IOException {
    String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    String iNetAddressPORT_NUMBER = iNetAddress + " " + PORT_NUMBER;
    byte[] bytesINetAddressPORT_NUMBER = iNetAddressPORT_NUMBER.getBytes();

    aSocket.setSoTimeout(10000);

    // populate with 2 key-value pairs (aka 2 PUTS)
    // iNetAddress + PORT_NUMBER
    DatagramPacket requestINetAddressPORT_NUMBER =
            new DatagramPacket(bytesINetAddressPORT_NUMBER, bytesINetAddressPORT_NUMBER.length, iNetAddress, PORT_NUMBER);

    // send two messages
    aSocket.send(requestINetAddressPORT_NUMBER);

    String m = "PUT Bill 70000";
    DatagramPacket requestPUTBill2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTBill2);

    // receive one response
    byte[] buffer = new byte[1000];
    DatagramPacket serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));


    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "PUT John 80000";
    DatagramPacket requestPUTJohn2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTJohn2);

    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    // 5 PUTs
    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "PUT Billy 150000";
    DatagramPacket requestPUTBilly2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTBilly2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "PUT Mandy 250000";
    DatagramPacket requestPUTMandy2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTMandy2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "PUT Jill 350000";
    DatagramPacket requestPUTJill2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTJill2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "PUT Jack 450000";
    DatagramPacket requestPUTJack2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTJack2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "PUT Elon 1050000";
    DatagramPacket requestPUTElon2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestPUTElon2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));


    // 5 GETs
    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "GET Billy";
    DatagramPacket requestGETBilly2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestGETBilly2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "GET Mandy";
    DatagramPacket requestGETMandy2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestGETMandy2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "GET Jill";
    DatagramPacket requestGETJill2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestGETJill2);
    // receive one response
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "GET Jack";
    DatagramPacket requestGETJack2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestGETJack2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "GET Elon";
    DatagramPacket requestGETElon2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestGETElon2);
    // receive one response
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));


    // 5 DELETEs
    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "DELETE Billy";
    DatagramPacket requestDELETEBilly2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestDELETEBilly2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "DELETE Mandy";
    DatagramPacket requestDELETEMandy2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestDELETEMandy2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "DELETE Jill";
    DatagramPacket requestDELETEJill2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestDELETEJill2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "DELETE Jack";
    DatagramPacket requestDELETEJack2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestDELETEJack2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));

    aSocket.send(requestINetAddressPORT_NUMBER);
    m = "DELETE Elon";
    DatagramPacket requestDELETEElon2 =
            new DatagramPacket(m.getBytes(), m.getBytes().length, iNetAddress, PORT_NUMBER);
    aSocket.send(requestDELETEElon2);
    // receive one response
    buffer = new byte[1000];
    serverResponse = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(serverResponse);
    stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
    System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));
  }

  public static void main(String args[]){
    // args give message contents and server hostname
    DatagramSocket aSocket = null;
    if (args.length < 2) {
      System.out.println(
              "Usage: java UDPClient <Host name> <Port number>");
      System.exit(1);
    }
    try {

      String IP_ADDRESS = args[0];
      String sPORT_NUMBER = args[1];
      String[] arrOfStr = IP_ADDRESS.split("\\.");

      if(arrOfStr.length != 4){
        throw new IllegalArgumentException("IP_ADDRESS has to have 3 dots");
      }

      for (String a : arrOfStr){
        if(a.matches("^[a-zA-Z]*$")){
          throw new IllegalArgumentException("IP_ADDRESS has to only be numbers");
        }
      }

      if(sPORT_NUMBER.matches("^[a-zA-Z]*$")){
        throw new IllegalArgumentException("PORT_NUMBER has to only be numbers");
      }

      if(Integer.parseInt(args[1]) > 65536){
        throw new IllegalArgumentException("PORT_NUMBER must be a non-negative number less than 65536");
      }
      InetAddress iNetAddress = InetAddress.getByName(IP_ADDRESS);
      int PORT_NUMBER = Integer.parseInt(args[1]);

      aSocket = new DatagramSocket();
      String inputChoice = "";
      Scanner scan = new Scanner(System.in);

      fillUpServer(aSocket, iNetAddress, PORT_NUMBER);
      introduceClient();

      // We send two messages: first message contains iNetAddress + PORT_NUMBER, second message contains user's input
      while (true) {

        System.out.print("Enter a command: ");
        inputChoice = scan.nextLine().strip();

        String iNetAddressPORT_NUMBER = iNetAddress + " " + PORT_NUMBER;
        byte[] bytesINetAddressPORT_NUMBER = iNetAddressPORT_NUMBER.getBytes();
        // iNetAddress + PORT_NUMBER
        DatagramPacket request1 =
                new DatagramPacket(bytesINetAddressPORT_NUMBER, bytesINetAddressPORT_NUMBER.length, iNetAddress, PORT_NUMBER);

        // send two messages
        aSocket.send(request1);

        byte[] m = inputChoice.getBytes();
        DatagramPacket request2 =
                new DatagramPacket(m, m.length, iNetAddress, PORT_NUMBER);
        aSocket.send(request2);

        String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";

        // receive one response
        byte[] buffer = new byte[1000];
        DatagramPacket serverResponse = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(serverResponse);
        System.out.println(stringCurrentDate + ": " + new String(serverResponse.getData()));
      }
    } catch (Exception e) {
      String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + " Exception: " + e);
    } finally {
      if (aSocket != null)
        aSocket.close();
    }
  }
}