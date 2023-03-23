import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TCP Server
 */
public class TCP_Server {

  private Map<String ,Integer> myMap;

  TCP_Server() {
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
      if (!Character.isLetter(c) && !Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if a string can be converted into an integer
   * @param string - string that we want to convert to integer
   * @return boolean representing whether a string is an integer or not
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
    String responseToClient ;
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
    } else {
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

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Please pass the PORT_NUMBER as a parameter through args");
      System.exit(1);
    }
    else {
      String fParameter = args[0];
      if(fParameter.matches("^[a-zA-Z]*$")){
        throw new IllegalArgumentException("PORT_NUMBER has to only be numbers");
      }
      if(Integer.parseInt(args[0]) > 65536){
        throw new IllegalArgumentException("PORT_NUMBER must be a non-negative number less than 65536");
      }
      Socket client = null;
      try {

        TCP_Server tcpServer = new TCP_Server();

        int PORT_NUMBER = Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

        System.out.println("TCP Server started! Waiting for client connection...");

        client = serverSocket.accept(); // Wait and accept a connection

        System.out.println("Client connected! Waiting for client request...");

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        // splitClientMessage[0] = "PUT, GET, DELETE"
        // splitClientMessage[1] = key
        // splitClientMessage[2] = (only for put - value)

        while (true) {
          String clientInfo = dis.readUTF(); // read in iNetAddress and Port_Number
          String[] clientInfoSplit = clientInfo.split(" ");

          String clientINetAddress = clientInfoSplit[0];
          String clientPORT_NUMBER = clientInfoSplit[1];

          String clientMessage = dis.readUTF(); // read in clientMessage
          String responseToClient = "";

          String[] splitClientMessage = clientMessage.split(" ");
          if (splitClientMessage[0].equals("PUT")) {
            responseToClient = tcpServer.PUT(clientINetAddress, clientPORT_NUMBER, splitClientMessage);
          } else if (splitClientMessage[0].equals("GET")) {
            responseToClient = tcpServer.GET(clientINetAddress, clientPORT_NUMBER, splitClientMessage);
          } else if (splitClientMessage[0].equals("DELETE")) {
            responseToClient = tcpServer.DELETE(clientINetAddress, clientPORT_NUMBER, splitClientMessage);
          } else {
            System.out.println("Timestamp=" + getFormattedCurrentSystemTime() + " (From " + clientINetAddress + " " + clientPORT_NUMBER + ") Server received bad request: client did not pick from 'PUT', 'GET', 'DELETE'");
            responseToClient = "Unsuccessful operation: Please pick from 'PUT', 'GET', 'DELETE'";
          }

          dos.writeUTF(responseToClient);
          dos.flush();
        }
      } catch (Exception e) {
        String stringCurrentDate = "(Timestamp=" + getFormattedCurrentSystemTime() + ")";
        System.out.println(stringCurrentDate + " Exception: " + e);
      } finally {
        if (client != null)
          client.close();
      }
    }
  }
}
