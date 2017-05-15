
import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;


public class Mail {
    final static String MX="relay.pdn.ac.lk";
    final static int PORT=25;
    
    
    public static void main (String[] args) throws IOException {
        
        String from = args[0], to = args[1], sub = args[2];
      
        Socket s = new Socket(MX, PORT);
        try(OutputStream out = s.getOutputStream();
                InputStream in = s.getInputStream() ){
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        PrintWriter pr = new PrintWriter(out);
        
        pr.write("HELO ce.pdn.ac.lk\r\n");
        pr.write("MAIL FROM:<"+from+"@ce.pdn.ac.lk>\r\n");
        pr.write("RCPT TO:<"+to+"@ce.pdn.ac.lk>\r\n");
        pr.write("DATA\r\n");
        pr.write("SUBJECT:<"+sub+">\r\n");
        pr.write(".\r\n");
        pr.flush();
        
        }catch (IOException e) {
//            out.write("400 ERROR".getBytes());
            Logger.getGlobal().severe(e.getMessage());
        }
       
    }
}

// b. if the sender address’ domain is not ce.pdn.ac.lk it will send the mail without problem.
// c. if the recepient address’ domain is not ce.pdn.ac.lk it will not send the mail. it will
//    say 'Undelivered Mail Returned to Sender. 


