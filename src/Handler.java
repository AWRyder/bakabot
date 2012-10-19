
import java.lang.*;
import java.io.*;
import java.net.*;


public class Handler {

	private static Handler self;
	private Socket client;
	private String host = "irc.slavehack.com";
	private Integer port = 6667;
	private Thread listenerT;
	private BufferedReader dis;
	private BufferedWriter bw;
	
	private Handler () {
		
	}
	
	public static Handler getHandler(){
		if ( self == null) {
			self = new Handler();
		}
		return self;
	}
	
	public void connect () {
		try{
			self.client = new Socket(host,port);
			
			InputStreamReader is = new InputStreamReader(self.client.getInputStream());
			OutputStream os = self.client.getOutputStream();
			self.dis = new BufferedReader(is);
			self.bw = new BufferedWriter( new OutputStreamWriter(self.client.getOutputStream()));

			
			listenerT = new Thread(new Listener(dis,bw));
			listenerT.start();

			self.sendRaw("NICK BakaBot");
			self.sendRaw("USER BakaBot BakaBot BakaBot:Baaakaaaaa Bawt");
			while (listenerT.isAlive()){
				Thread.sleep(5000);
			}
			
		}catch(IOException e){
			Logger.log(this.getClass(), e.getMessage());
		}catch(Exception e ){
			Logger.log(this.getClass(), e.getMessage());
		}
	}
	
	public void sendRaw(String raw){
		if ( self.bw == null ) return;
		try {
			bw.write(raw+"\n");
			bw.flush();
			Logger.log(this.getClass(), "--> "+raw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
