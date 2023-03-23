#Project 1
#TCP and UDP Server & Client 

##Assignment Overview
The purpose and scope of this project was to get experience with client & server architecture, communicating over both 
TCP (Transmission Control Protocol) and UDP (User Datagram Protocol) using sockets. We also had to learn about the different utilities, 
classes, objects and data structures that are associated TCP (Socket, ServerSocket, DataInputStream, DataOutputStream, etc) and 
UDP (DatagramPacket, DatagramSocket, byte[], etc). We had to utilize the Map data structure and needed associated methods with the 
data structure( PUT , GET , DELETE ) which all helped to add, remove or get the values from the Map. We also learned addresses, ports, 
and transmitting data through address and port. Part of the purpose of this assignment was to learn about the differences between 
TCP and UDP and the use cases of each. The main difference between TCP and UDP is that TCP is connection-based, 
while UDP is connection-less. Since TCP is connection-based, that means that in TCP architecture the client needs 
to establish a connection with the server before data transmission can occur. TCP is reliable, in that data sent is 
guaranteed to be delivered to its destination. Data sent is also guaranteed to arrive ordered. However, TCP is slow in comparison to UDP. 
UDP is connection-less, which means that client’s do not establish a connection with a server. Because of the lack of overhead, 
UDP is much faster than TCP. However, it is unreliable with the downside of possible lost data packets, and with a lack of data 
sequencing. With TCP it’s slow and accurate, while with UDP is fast and potentially unreliable. 

##Technical impression
I found the TCP aspect of the project not as difficult cause it was really similar to the homework but with the UDP I had to go further
 through the notes to get a better understanding but that doesn’t mean it was difficult. It was just more time consuming to understand 
how to make it work,. Surprisingly, I expected the project to be more difficult cause of my lack of networking background but JAVA is 
most prominent coding language so it wasn’t as bad as I thought it was going to be. I did get scared during the first lecture but I’m 
more confident in myself right now. Homework 1 really helped with understanding this project and laying a very good basic understanding 
of how to approach the task at hand. The only additional requirement was to find a way to implement Map and its functionality but with 
my previous experience in JAVA it wasn’t an issue. I saw a couple of questions about logging the messages from the server into a file 
and I am really pleased that it was not a necessity as I thought it was at first. Really took some time off my shoulders. All in all 
it has been a fun class so far. UI can imagine projects requiring a GUI or some requiring us to read a file or write to files and 
saving the data. Look forward to the expected fun.
##How to Run

Here are the steps to run the program:

###(TCP) How to start the client-server architecture

1. Run the TCP_Server via "java -jar TCP_Server.jar <PORT_NUMBER>"

2. Run the TCP_Client via "java -jar TCP_Client.jar <IP_ADDRESS> <PORT_NUMBER>"

3. To execute a PUT command, type "PUT <String> <Integer>"

4. To execute a GET command, type "GET <String>"

5. To execute a DELETE command, type "DELETE <String>"

###(UDP) How to start the client-server architecture

1. Run the UDP_Server via "java -jar UDP_Server.jar <PORT_NUMBER>"

2. Run the UDP_Client via "java -jar UDP_Client.jar <IP_ADDRESS> <PORT_NUMBER>"

3. To execute a PUT command, type "PUT <String> <Integer>"

4. To execute a GET command, type "GET <String>"

5. To execute a DELETE command, type "DELETE <String>"

##Examples with description
Any of the servers can be choosen for the runs
This is just a quick explanation of how the code runs, I had a test run with:
PORT_NUMBER = 32000
IP_ADDRESS = 127.0.0.1
The IP_ADDRESS was my device's address as i ran this on my device. 
With all requested functionality being met. 
GET Bill - which returns the value of Bill
PUT Abdul 5000 - Adds abdul to the map
GET Abdul - returns the value of Abdul
DELETE Bill - removes bill from the map
 
##Limitations/Notes
This project has some limitions and notes that would be listed below:
If the PORT_NUMBER isn't an Integer then there would be a flag.
If the PORT_NUMBER isn't an non-negative Integer less than 65536 then there would be a flag.
If the IP_ADDRESS isn't the traditional numbers seperated by "." then it would be flagged. 
You can only use GET, PUT, DELETE opperations followed by the required and valid inputs. 
As demonstrated above in examples with description.
Client also having timeouts

##Citation
Used https://stackoverflow.com/questions/1459656/how-to-get-the-current-time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
for a part of my code, which helped with getting date and time in the right format

used the Notes provided on canvas, used multiple of them.