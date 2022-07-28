import java.io.*;
import java.net.*;
import java.io.IOException;
import java.util.Scanner;


public class Server {
    public ServerSocket welcomeSocket;
    public Socket connectionSocket;
    public BufferedReader inFromClient;
    public  PrintWriter outToClient;
    public Server()
    {
        try {
            String clientSentence;
            String capitalizedSentence;

            welcomeSocket = new ServerSocket(6060);
            System.out.println("Hello World...");
//            int n = 2;
//            while (n > 0) {
            connectionSocket = welcomeSocket.accept();
            System.out.println("Connection successfully");
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new PrintWriter(connectionSocket.getOutputStream());
//                n = n-1;
//            }
        }
        catch (IOException e)
        {
            System.out.println("Error" +e);
        }
    }
    private String receiveMg() throws IOException{
        String clientSentence;
        clientSentence = inFromClient.readLine();

        System.out.println(clientSentence);
        return clientSentence;
    }

    private void sendMg(String MgToSend) throws  IOException{
//        Scanner mg = new Scanner(System.in);
//        System.out.println("Enter mg: ");
//        String MgToSend = mg.nextLine();
        MgToSend= inFromClient.readLine();
        System.out.println( MgToSend);
        outToClient.println(MgToSend);

        outToClient.flush();

    }
    private  void closeSendReceive() throws  IOException
    {
        inFromClient.close();
        outToClient.close();
        connectionSocket.close();
    }

    public void printAllProcess() throws IOException{
        String pr = receiveMg();
        while (pr != null)
        {
            pr = receiveMg();
            System.out.println(pr);
            //  sendMg("Received");
        }
    }
    public  static void main (String[] args) throws  IOException
    {
        Server sv = new Server();
        String check = " ";
//        while (check != "stop\n"){
//            //sv.sendMg();
//            System.out.println("Check:"+check);
//            check = sv.receiveMg();
//
//
//       }
        sv.printAllProcess();
        sv.closeSendReceive();
    }
}
