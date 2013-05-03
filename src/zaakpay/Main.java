package zaakpay;

public class Main {
	
	
	public static void main(String args[])
	{
		System.out.println("Creating Cache for First Time from Data Base");
		
		Cache c=new Cache(); 
		c.buildCachefirstTime();
		System.out.println("Cache has been built successfully");
		Fetch f= new Fetch(c,"name"); // Thread to fetch value from present cache
		
		
		Thread buildcache= new Thread(c);
		Thread fetch= new Thread(f);
		
		buildcache.start();
		fetch.start();
		
		
	}

}
