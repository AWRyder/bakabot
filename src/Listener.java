import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Listener implements Runnable {

	private BufferedReader dis;
	private BufferedWriter bw;
	
	public Listener(BufferedReader dis, BufferedWriter bw){
		this.dis = dis;
		this.bw = bw;
	}
	
	@Override
	public void run() {
		
		
		try {
			String r;
			while ( (r=dis.readLine())!=null ) {
				Logger.log(this.getClass(), r);
				parseRaw(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void parseRaw(String r){		
		if ( r.split(" ")[0].equals("PING")  ){
			Handler.getHandler().sendRaw("PONG "+r.split(" ")[1]);
		}
	}

}
