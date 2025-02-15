# Real Estate Ads

## **Overview**  
Multithreaded Java server, which supports multiple clients to view all ads, search for a particular ad form the list or add a new one.



## **Installation**
1. Navigate to the directory where you want to clone the project.  
In your terminal, type:  
```sh
cd path/to/your/directory
```
2. Clone the project using:  
```sh
git clone "https://github.com/Luisa-Elena/real-estate-ads.git"
```
3. Navigate to the cloned folder "real-estate-ads"
```sh
cd real-estate-ads
```
4. Make a directory called "bin" to store compiled classes 
```sh
mkdir -p bin
```
5. Compile all java files
```sh
javac -d bin src/main/java/org/example/*.java
```
6. Run the server first:
```sh
java -cp bin org.example.Server
```  
Then run the clients:
```sh
java -cp bin org.example.Client
```


## **Usage**  

On a client terminal, the possible commands are the following:  

#### **EXIT**  
- Closes the connection with the server.  

#### **ADD** `<description> <location> <price>`  
- Adds a new advertisement characterized by the given arguments:  
- **Important:**  
  - Arguments must be separated by spaces.  
  - Arguments **cannot** contain spaces (short, one word description and location for easy and quick testing).  
  - `price` must be a valid integer value.  

#### **GET-ALL**  
- Displays all available ads.  

#### **GET-BY-ID** `<index>`  
- Retrieves a specific ad from the list at the given index.
- Indexing starts from 0.


## **Implementation details**
## *Main idea*
- In order to allow multiple clients to access the server resources simultaneously, each client connection will run on a separate thread.
- To allow multiple programs to communicate over a network using TCP/IP we use sockets, from the java.net package:
    - ServerSocket - used by the server to listen for incoming client connections
    - Socket - to allow communication between the server and a specific client
- The server listens for connections, and the client connects to the server to exchange data.
- Communication happens via an input stream to read data from the socket and an output stream to send data through the socket.

## *Client*
1. Connect to the server
2. Set up the input and output streams for data exchange with the server
3. In a loop:
    - read data from the user
    - send it to the server
    - receive the server's response.

### Step 1
```java
// Establishing a connection
Socket socket = new Socket("localhost", 1234);
```
This line creates a socket and connects it to a server running on localhost (127.0.0.1  is the IP address of localhost) at port 1234.

### Step 2
```java
// Setting up the output stream
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
```
- out is used to send messages to the server.
- It is recommended to use PrintWriter with socket.getOutputStream().
- The true parameter in the PrintWriter constructor enables auto-flushing, ensuring that every message is immediately sent without needing to call flush() manually.
- PrintWriter also provides convenient methods like println(), which sends data with a newline (\n), ensuring the server correctly reads complete messages.

```java
// Setting up the input stream
BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
```
- in is used to read messages received from the server.
- It is recommended to use BufferedReader with socket.getInputStream().
- Since BufferedReader reads data in chunks (buffers), it improves performance compared to reading character by character.
- The readLine() method reads a full line of text, making it convenient to handle server messages.

### Step 3
```java
Scanner sc = new Scanner(System.in);
```
- This creates an object of the Scanner class which will be used to read input from the user.

```java
while (true) {
  line = sc.nextLine();
  out.println(line);

  if("EXIT".equals(line)) {
      break;
  }

  String serverResponse;
  while((serverResponse = in.readLine()) != null) {
    System.out.println(serverResponse);
    if(!in.ready()) break;
  }
}
```
- In a while loop, we read input from the user, until the user sends "EXIT".
- The user input is read in the "line" variable, then sent to the server.
- After sending a message to the server, we wait for its ressponse.
- in.readline() reads a full line from the server.
- in.ready() is used to check if there is any more data available to be read from the server (useful when the server sends multiple lines in a response, since we read only one line at a time).
- If the user sends "EXIT", then the loop finishes and the client connection is closed. All resources are closed as well. The socket, in and out will be automatically closed since the socket was created in a try-with-resources statement and the code will go out of the try block.


## *Server*
Idea: Store all ads into a ConcurrentHashMap (because it is a thread-safe collection) where the key is the index of an advertisement in the list and the value is the actual Ad object. Ad is a helper class which describes an advertisement, containing the fields description, location and price.

---

1. Create a ServerSocket object to listen for client connections.
2. For each client, create a thread to manage the socket communication with that particular client.
3. Each thread will parse the inputs from the client and respond to that client with the requested data.  

### Step 1
```java
server = new ServerSocket(1234);
server.setReuseAddress(true);
```
- This creates a ServerSocket listening on port 1234.
- setReuseAddress(true) informs the Operating System that the port can be reused immediately after the socket is closed (useful for short-lived servers or frequent restarts during development).

### Step 2
```java
while (true) {
    Socket client = server.accept();
    System.out.println("New client connected: " + client.getInetAddress().getHostAddress() + " running on port: " + client.getPort());
    ClientHandler clientHandler = new ClientHandler(client);
    Thread clientThread = new Thread(clientHandler);
    clientThread.start();
}
```

- This is the main code for the server. In an infinite loop, it waits for client connections and manages each client in a thread. The loop will run until the server process is forcibly ended.
- When a client connects, server.accept() returns a Socket object that represents the connection to that specific client.
- This Socket object will contain the client's IP, port and input/output streams to send and receive data.
- The ClientHandler class implements the Runnable interface and will contain the logic to handle the communication with that client.
- Then, we create and start the thread for that client.

### Step 3
```java
class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        // this method will contain the implementation of the thread logic
    }
}
```

#### Thread behavior for each client:
__1. Set up input and output streams:__  

```java
out = new PrintWriter(clientSocket.getOutputStream(), true);
in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
```
- With __out__ we will send data to the client
- With __in__ we will receive data from the client

__2. In a loop, read lines from the client, and parse them to interpret the command and its arguments:__  

```java
while ((line = in.readLine()) != null) {
    String parts[] = line.split(" ");
    switch (parts[0]) {
    // specific logic for each command case
    }
}
```
- parts[] will store the splitted input line based on spaces.
- parts[0] will store the command, and the rest of the elements will be the arguments for that command.

__3. Implement the logic for each command case:__  
__EXIT__ - Print a message to inform that the client has disconnected.  
__ADD__ - Construct a new Ad object with the arguments and add it to the list of ads.
__GET-BY-ID__ - Get the ad at a specific index specified as argument for this command.  
__GET-ALL__ - Get all ads from the list.  
__default__ - Send to the client an "Invalid command" message.

__4. Close the resources__
- If the client exits and closes its socket, a null will be transmitted. This will lead to the finally block to be executed, where all resources are closed (in, out and clientSocket will also be closed on the server side)
- If the client is forcibly ended (by pressing ctrl+C in the client terminal), an IOException will be caught and handled in the catch block



## **Usage example**
![Image](https://github.com/user-attachments/assets/1d88b7eb-6584-4fdd-a5bd-c3ba3c381e03)
