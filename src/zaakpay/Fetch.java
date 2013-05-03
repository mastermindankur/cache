package zaakpay;

import java.util.HashMap;
import java.util.Map;

public class Fetch implements Runnable{
	
	Cache c;
	String key;
	
	Fetch(Cache cache,String key)
	{
		this.c=cache;
		this.key=key;
	}
	
	
	public void run()
	{
		getValueFromCache(key);
	}
	
	void getValueFromCache(String key)
	{
		while(true)
		{
		System.out.println("Fetching vaue from cache for key="+key+", value ="+ c.cache.get(key));
		}
	}

}
