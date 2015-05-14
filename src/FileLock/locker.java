package FileLock;

public class locker {
	
	 public static void main(String[] args) 
	 {
		 read r = new read();
		 write w = new write();
		 r.start();
		 w.start();
	 }

}
