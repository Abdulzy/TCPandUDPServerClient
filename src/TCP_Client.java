// package com.company;

// SimpleClient.java: A simple client program.
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * TCP Client
 */
public class TCP_Client {

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
   * @param iNetAddress - IP address or hostname, provided by the user
   * @param PORT_NUMBER - Port number, provided by the user
   * @param dos - DataOutputStream, which we use to send messages to server
   * @param dis - DataInputStream, which we use to receive messages from the server
   * @return void
   */
  private static void fillUpServer(InetAddress iNetAddress, int PORT_NUMBER, DataOutputStream dos, DataInputStream dis) {
    try {
      String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";

      // populate with 2 key-value pairs (aka 2 PUTS)
      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT Bill 70000");
      dos.flush();
      String serverResponse = dis.readUTF();
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT John 80000");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      // 5 PUTs
      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT Billy 350000");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT Mandy 450000");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT Jill 550000");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT Jack 650000");
      dos.flush();
      serverResponse = dis.readUTF();
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("PUT Elon 1050000");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      // 5 GETs
      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("GET Billy");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("GET Mandy");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("GET Jill");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("GET Jack");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("GET Elon");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      // 5 DELETEs
      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("DELETE Billy");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("DELETE Mandy");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("DELETE Jill");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("DELETE Jack");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);

      dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
      dos.flush();
      dos.writeUTF("DELETE Elon");
      dos.flush();
      serverResponse = dis.readUTF();
      stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
      System.out.println(stringCurrentDate + ": " + serverResponse);
    } catch (IOException e) {
      System.out.println("IOException: " + e);
    } catch (Exception e) {
      System.out.println("Exception: " + e);
    }
  }

  /**
   * Main method sets up the connection with the server, and sets up our IP_ADDRESS and PORT_NUMBER
   * @param args - args[0] is IP_ADDRESS (aka localhost) and args[1] is PORT_NUMBER (aka 5000)
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Please pass IP_ADDRESS and PORT_NUMBER as arguments through args");
    }
    else {

      String fParameter = args[0];
      String sParameter = args[1];
      String[] arrOfStr = fParameter.split("\\.");

      if(arrOfStr.length != 4){
        throw new IllegalArgumentException("IP_ADDRESS has to have 3 dots");
      }

      for (String a : arrOfStr){
        if(a.matches("^[a-zA-Z]*$")){
          throw new IllegalArgumentException("IP_ADDRESS has to only be numbers");
        }
      }

      if(sParameter.matches("^[a-zA-Z]*$")){
        throw new IllegalArgumentException("PORT_NUMBER has to only be numbers");
      }

      if(Integer.parseInt(args[1]) > 65536){
        throw new IllegalArgumentException("PORT_NUMBER must be a non-negative number less than 65536");
      }
      Socket socket = null;
      try {
        String IP_ADDRESS = args[0];
        InetAddress iNetAddress = InetAddress.getByName(IP_ADDRESS);
        int PORT_NUMBER = Integer.parseInt(args[1]);

        socket = new Socket(IP_ADDRESS, PORT_NUMBER);
        socket.setSoTimeout(10000);


        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        System.out.println("Client successfully connected to server!");
        System.out.println();

        fillUpServer(iNetAddress, PORT_NUMBER, dos, dis);
        introduceClient();

        Scanner scan = new Scanner(System.in);

        // We send two messages: first message contains iNetAddress + PORT_NUMBER, second message contains user's input
        while (true) {
          System.out.print("Enter a command: ");
          String clientToServerMessage = scan.nextLine().strip();

          // send two messages
          dos.writeUTF(iNetAddress + " " + PORT_NUMBER);
          dos.flush();

          dos.writeUTF(clientToServerMessage);
          dos.flush();

          String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
          // receive one response
          String serverResponse = dis.readUTF();
          System.out.println(stringCurrentDate + ": " + serverResponse);
        }
      } catch (Exception e) {
        String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
        System.out.println(stringCurrentDate + " Exception: " + e);
      } finally {
        if (socket != null)
          socket.close();
      }
    }
  }
}