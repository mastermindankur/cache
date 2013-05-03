package zaakpay;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;



public class DataManager {
	
	
	 
	    public List<Data> list() {
	    	Session session=null;
	        List<Data> datas = null;
	        try {
	        	
		        session = HibernateUtil.getSessionFactory().getCurrentSession();
		        session.beginTransaction();
	            datas = (List<Data>)session.createQuery("from Data").list();        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally{
				if(session.isOpen()){
					session.close();
				}
			}
	        return datas;
	    }
	    	
	

}