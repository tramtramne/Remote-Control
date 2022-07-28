
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Optional;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client {
    Socket clientSocket;
    private   BufferedReader inFromServer;
    public PrintWriter outToServer;
    //
    public Client()
    {
        try {

            clientSocket= new Socket("127.0.0.1", 6060);


            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer = new PrintWriter(clientSocket.getOutputStream());
            //  String sentence = inFromServer.readLine();

            // String line = inFromServer.readLine();
            //  System.out.println("Client received: "+ line);


        }
        catch (IOException e)
        {
            System.out.println("Error "+e);
        }
    }
    private void sendMg(String MgToSend) throws IOException{
//        Scanner mg = new Scanner(System.in);
//        System.out.println("Enter mg: ");
//        String MgToSend = mg.nextLine();
        //inFromServer.readLine();
        System.out.println("Client sent: " + MgToSend);
        outToServer.println(MgToSend);

        outToServer.flush();
        //  return sentence;
    }
    private String receiveMg() throws IOException{
        String line = inFromServer.readLine();
        System.out.println("Client received: "+ line);
        return line;
    }

    private   void closeSendReceive() throws  IOException
    {
        inFromServer.close();
        outToServer.close();
        clientSocket.close();
    }
    public void printAllProcesses() throws Exception{
        // -e - show all processes including processes of other users
        // -o command - restrict the output to just the process name
        //String cmd ="gps | select ProcessName,Id,Description";
        String cmd = "ps aux";
        Process process = Runtime.getRuntime().exec(cmd); //("ps -e -o command");
        BufferedReader r =  new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;

        while((line=r.readLine())!=null) {
            System.out.println(line);
            sendMg(line);
            //String re = receiveMg();
        }
    }
    public static   void main(String[] args) throws  Exception
    {
        Client cl = new Client();
//        String check = "";
//        while (check !="stop\n"){
//            check = cl.receiveMg();
//            Scanner mg = new Scanner(System.in);
//            System.out.println("Enter mg: ");
//            String MgToSend = mg.nextLine();
//            cl.sendMg(MgToSend);
//        }
        cl.printAllProcesses();
        System.out.println("Stop");
        //cl.closeSendReceive();
    }
}





