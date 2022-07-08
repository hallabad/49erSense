import java.net.*;
import java.io.*;

public class NodeSocket implements Runnable{
    private Thread c_t;
	Consol c_NodeConsol = null;
	Socket c_Socket = null;
    DataInputStream c_in = null;
    Boolean c_Run = true;
    DataInputStream c_Instream = null;

    public NodeSocket(Socket i_Socket, Consol i_Consol) throws IOException{
        this.c_NodeConsol = i_Consol;
        this.c_Socket = i_Socket;
        this.c_Instream = new DataInputStream(new BufferedInputStream(this.c_Socket.getInputStream()));
    }
    
    public void run(){
        String line;
        try{
            while(this.c_Run){
                // System.out.println("[Debug] In the NodeSocket Loop");
                line = this.c_Instream.readUTF();
                System.out.println("[Remote] A remote node sent the commeand |" + line);
                this.c_NodeConsol.exicute(line.toLowerCase());
            }
        } catch (Exception e) {
            System.out.println("[Error] There was an issue with the remote consol |" + e.getMessage());
            this.c_Run = false;
        }
    }

    public void stop() throws IOException{
        this.c_Run = false;
        System.out.println("[Debug] Run Set to false");
        this.c_Socket.close();
        System.out.println("[Debug] Socket Closed");
        this.c_Instream.close();
        System.out.println("[Debug] Instream Closed");
    }

    public void start(){
        if(this.c_t == null){
		    this.c_t = new Thread(this);
		    this.c_t.start();
		}
    }
}
