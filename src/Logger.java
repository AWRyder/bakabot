
public class Logger {
	
	private Logger(){
		
	}
	
	public static void log(Class c, String msg) {
		System.out.println("[LOG]["+c.getName()+"] "+msg);
	}
}
