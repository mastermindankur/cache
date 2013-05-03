package zaakpay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache implements Runnable {
	
	Map<String, String> cache = new HashMap<String, String>();
	Map<String, String> newcache = new HashMap<String, String>();
	
	List<Data> datas = null;
	
	void buildCachefirstTime()
	{
		Map<String, String> map = new HashMap<String, String>();
		DataManager dataManager=new DataManager();;
		datas=dataManager.list();
			for (int i=0;i<datas.size();i++) {
		     map.put(datas.get(i).getKey1(),datas.get(i).getValue1());
			}
	System.out.println("Cache created first time");
	cache=map;
	}
	
	
	public void run()
	{
		buildCache();
	}
	
	void buildCache()
	{	
		int count=0;
		DataManager dataManager=new DataManager();;
		while(true)
		{
			if(count%1000==0)
			{			System.out.println("...........Memory Cache update started...........");
						Map<String, String> map = new HashMap<String, String>();
						datas=dataManager.list();
							for (int i=0;i<datas.size();i++) {
						     map.put(datas.get(i).getKey1(),datas.get(i).getValue1());
						     
							}
					System.out.println("..............Cache updated.................");
					cache=map;
			}
			count++;
				//return map;
		}
	}



}
