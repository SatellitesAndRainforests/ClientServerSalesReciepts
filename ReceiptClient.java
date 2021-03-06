//
//  Mark Anthony Start          180140208       C02220 CW2 
//


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ReceiptClient {
    private int port = 1983;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public String connect (String host) throws Exception {


	    System.out.println("connecting to server at: " + host + " port : " + port);
	    
	    socket = new Socket(host, port);

	    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    writer = new PrintWriter(socket.getOutputStream(), true);



        //Server sends a welcome message. return the message.
        return readServerOutput();
    }

    public void disconnect() throws Exception {
        if (!socket.isClosed()) { //only attempt to close if it hasn't already been closed
            reader.close();
            writer.close();
            socket.close();
        }
    }

    /*
     * send a command to the server and return the response
     */
    public String sendCommand(String command) throws Exception {
        writer.println(command);

        return readServerOutput();
    }

    /*
     * read the messages sent from the server
     */
    private String readServerOutput() throws Exception {
        StringBuffer sb = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) { //receiving a null here means the server has closed the connection
            if(line.equals(ServerUtils.getEOM())) { //EOM (End of Message) is defined in the Server class
                break;
            }
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        //remove trailing newline
        sb.deleteCharAt((sb.length()-1));
        return sb.toString();
    }
}



