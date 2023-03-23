import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * AAA_UDP_SERVER
 */
public class UDP_Server {

  private HashMap<String, Integer> myMap;

  UDP_Server() {
    this.myMap = new HashMap<>();
  }

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
   * Returns a boolean representing whether a given string consists of all letters and digits
   * @param string - a string that we want to verify as alphanumeric
   * @return boolean representing whether a string is alphanumeric
   */
  private boolean isAlphaNumeric(String string) {
    for (char c : string.toCharArray()) {
      if (!Character.isLetter(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if a string can be converted into an integer
   * @param string - string that we want to convert to integer
   * @return
   */
  private boolean isInteger(String string) {
    long value = 0;
    for (char c : string.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
      value = value*10 + Integer.parseInt(String.valueOf(c));
    }
    if (value > 2147483647) { // integers only go up to this value
      return false;
    }
    return true;
  }

  /**
   * Prints timestamped PUT logs for the server, and returns String response for the client
   * @param clientINetAddress - client's internet address
   * @param clientPORT_NUMBER - client's port number
   * @param splitClientMessage - String array containing the PUT command sent by the client
   * @return String representing our response to the client
   */
  private String PUT(String clientINetAddress, String clientPORT_NUMBER, String[] splitClientMessage) {
    String timeStampClientINetPortNumber = "Timestamp=" + getFormattedCurrentSystemTime() + " (From " + clientINetAddress + " " + clientPORT_NUMBER;
    String responseToClient = "";
    if (splitClientMessage.length != 3) {
      System.out.println(timeStampClientINetPortNumber + ") Server received bad PUT request; PUT request does not have appropriate number of arguments");
      responseToClient = "Unsuccessful operation: PUT request does not have appropriate number of arguments";
    } else {
      String key = splitClientMessage[1];
      String stringValue = splitClientMessage[2];
      boolean isAlphaNum = isAlphaNumeric(splitClientMessage[1]);
      boolean isInt = isInteger(splitClientMessage[2]);
      if (isAlphaNum && isInt) { // successful PUT operation
        int value = Integer.valueOf(stringValue);
        myMap.put(key, value);
        System.out.println(timeStampClientINetPortNumber + ") Server successfully PUT " + key + " " + value + " into the map");
        responseToClient = "Successful PUT operation: " + key + " " + value;
      } else if (!isAlphaNum && !isInt) { //if key and value are invalid
        System.out.println(timeStampClientINetPortNumber + ") Server received bad PUT request; bad PUT key & bad PUT value");
        responseToClient = "Unsuccessful operation: PUT's key must contain only alphanumeric characters and PUT's value must be a non-negative integer";
      } else if (!isAlphaNum) { //if key is invalid
        System.out.println(timeStampClientINetPortNumber + ") Server received bad PUT request; bad PUT key");
        responseToClient = "Unsuccessful operation: PUT's key must contain only alphanumeric characters";
      } else { //if value is invalid
        System.out.println(timeStampClientINetPortNumber + ") Server received bad PUT request; bad PUT value");
        responseToClient = "Unsuccessful operation: PUT's value must be a non-negative integer";
      }
    }
    return responseToClient;
  }

  /**
   * Prints timestamped GET logs for the server, and returns String response for the client
   * @param clientINetAddress - client's internet address
   * @param clientPORT_NUMBER - client's port number
   * @param splitClientMessage - String array containing the GET command sent by the client
   * @return String representing our response to the client
   */
  private String GET(String clientINetAddress, String clientPORT_NUMBER, String[] splitClientMessage) {
    String timeStampClientINetPortNumber = "Timestamp=" + getFormattedCurrentSystemTime() + " (From " + clientINetAddress + " " + clientPORT_NUMBER;
    String responseToClient = "";
    if (splitClientMessage.length != 2) {
      System.out.println(timeStampClientINetPortNumber + ") Server received bad GET request; GET request does not have appropriate number of arguments");
      responseToClient = "Unsuccessful operation: GET request does not have appropriate number of arguments";
    } else {
      String key = splitClientMessage[1];
      boolean isAlphaNum = isAlphaNumeric(splitClientMessage[1]);
      if(isAlphaNum){
        if (myMap.containsKey(key)) { // successful GET operation
          Integer value = myMap.get(key);
          System.out.println(timeStampClientINetPortNumber + ") Server successfully retrieved GET's (" + key + ")'s value (" + value + ")");
          responseToClient = "Successful GET operation. key=" + key + " value=" + String.valueOf(value);
        }else { // Key doesn't exist
          System.out.println(timeStampClientINetPortNumber + ") Server received bad GET request; GET's key does not exist");
          responseToClient = "Unsuccessful operation: GET's key does not exist";
        }
      }else { // Key is invalid
        System.out.println(timeStampClientINetPortNumber + ") Server received bad GET request; bad GET key");
        responseToClient = "Unsuccessful operation: GET's key must contain only alphanumeric characters";
      }
    }
    return responseToClient;
  }

  /**
   * Prints timestamped DELETE logs for the server, and returns String response for the client
   * @param clientINetAddress - client's internet address
   * @param clientPORT_NUMBER - client's port number
   * @param splitClientMessage - String array containing the DELETE command sent by the client
   * @return String representing our response to the client
   */
  private String DELETE(String clientINetAddress, String clientPORT_NUMBER, String[] splitClientMessage) {
    String timeStampClientINetPortNumber = "Timestamp=" + getFormattedCurrentSystemTime() + " (From " + clientINetAddress + " " + clientPORT_NUMBER;
    String responseToClient = "";
    if (splitClientMessage.length != 2) {
      System.out.println(timeStampClientINetPortNumber + ") Server received bad DELETE request; DELETE request does not have appropriate number of arguments");
      responseToClient = "Unsuccessful operation: DELETE request does not have appropriate number of arguments";
    }  else {
      String key = splitClientMessage[1];
      boolean isAlphaNum = isAlphaNumeric(splitClientMessage[1]);
      if(isAlphaNum){
        if (myMap.containsKey(key)) { // successful DELETE operation
          Integer value = myMap.remove(key);
          System.out.println(timeStampClientINetPortNumber + ") Server successfully deleted key:" + key + " value:" + value + " from the map");
          responseToClient = "Successful DELETE operation. Key=" + key + " value=" + value + " deleted from the map";
        }else { // Key doesn't exist
          System.out.println(timeStampClientINetPortNumber + ") Server received bad DELETE request; DELETE's key does not exist");
          responseToClient = "Unsuccessful operation: DELETE's key does not exist";
        }
      }else { // Key is invalid
        System.out.println(timeStampClientINetPortNumber + ") Server received bad DELETE request; bad DELETE key");
        responseToClient = "Unsuccessful operation: DELETE's key must contain only alphanumeric characters";
      }
    }
    return responseToClient;
  }

  public static void main(String args[]){
    DatagramSocket aSocket = null;
    if (args.length < 1) {
      System.out.println("Usage: java UDPServer <Port Number>");
      System.exit(1);
    }
    String fParameter = args[0];
    if(fParameter.matches("^[a-zA-Z]*$")){
      throw new IllegalArgumentException("PORT_NUMBER has to only be numbers");
    }
    if(Integer.parseInt(args[0]) > 65536){
      throw new IllegalArgumentException("PORT_NUMBER must be a non-negative number less than 65536");
    }
    try {

      UDP_Server udpServer = new UDP_Server();

      int PORT_NUMBER = Integer.parseInt(args[0]);
      aSocket = new DatagramSocket(PORT_NUMBER);

      System.out.println("UDP Server started! Waiting for messages...");
      while(true) {
        byte[] buffer = new byte[1000];
        byte[] buffer2 = new byte[1000];
        DatagramPacket request1Packet = new DatagramPacket(buffer,
                buffer.length);
        aSocket.receive(request1Packet);

        String request1 = new String(request1Packet.getData()).trim();
        String[] request1Split = request1.split(" ");

        String clientINetAddress = request1Split[0];
        String clientPORT_NUMBER = request1Split[1];

        DatagramPacket request2Packet = new DatagramPacket(buffer2,
                buffer2.length);
        aSocket.receive(request2Packet);

        String request2 = new String(request2Packet.getData()).trim();

        String responseToClient = "";

        String[] splitClientMessage = request2.split(" "); // ????
        if (splitClientMessage[0].equals("PUT")) {
          responseToClient = udpServer.PUT(clientINetAddress, clientPORT_NUMBER, splitClientMessage);
        } else if (splitClientMessage[0].equals("GET")) {
          responseToClient = udpServer.GET(clientINetAddress, clientPORT_NUMBER, splitClientMessage);
        } else if (splitClientMessage[0].equals("DELETE")) {
          responseToClient = udpServer.DELETE(clientINetAddress, clientPORT_NUMBER, splitClientMessage);
        } else {
          System.out.println("Timestamp=" + getFormattedCurrentSystemTime() + " (From " + clientINetAddress + " " + clientPORT_NUMBER + ") Server received bad request: client did not pick from 'PUT', 'GET', 'DELETE'");
          responseToClient = "Unsuccessful operation: Please pick from 'PUT', 'GET', 'DELETE'";
        }

        byte[] responseToClientBytes = (responseToClient).getBytes();

        DatagramPacket reply = new DatagramPacket(responseToClientBytes,
                responseToClientBytes.length, request1Packet.getAddress(),
                request1Packet.getPort());
        aSocket.send(reply);
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
