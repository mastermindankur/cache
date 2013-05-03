package zaakpay;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;



public class DataManager {
	
	
	public Data add(Data data) {
        Session session=null;
        try{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(data);
        session.getTransaction().commit();
        data.setId(getmaxId());
        }
        catch (Exception e) {
			session.getTransaction().rollback();
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
        return data;
    }
	
	
	 public Data delete(Long id) {
		 	Session session=null;
		 	Data data=null;
		 	try{
		        session = HibernateUtil.getSessionFactory().getCurrentSession();
		        session.beginTransaction();
		        data = (Data) session.load(Data.class, id);
		        if(null != data) {
		            session.delete(data);
		        }
		        session.getTransaction().commit();
		        data.setId(getmaxId());
		 	}
		 	catch (Exception e) {
				//logger.error("Error to save data"+e.getessage(),e);
				session.getTransaction().rollback();
			}finally{
				if(session.isOpen()){
					session.close();
				}
			}
	        return data;
	    }
	 
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
	    
	    public int getmaxId()
	    { 
	    	Session session=null;
	        try {
		        session = HibernateUtil.getSessionFactory().getCurrentSession();
		        session.beginTransaction();
	        	String SQL_QUERY = "select max(id)from Data data";
	        	Query query = session.createQuery(SQL_QUERY);
	        	List list = query.list();
	        	return Integer.parseInt(list.get(0)+"");  
	        } catch (Exception e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        finally{
				if(session.isOpen()){
					session.close();
				}
			}
	         return -1;	
	    }
	    
	    
	    public Data getDataIfExists(String providerid,String validateid)
	    { 
	    	Session session=null;
	        try {
		        session = HibernateUtil.getSessionFactory().getCurrentSession();
		        session.beginTransaction();
	        	String SQL_QUERY = "select data from Data data where providerid='"+providerid+"' and uniqueid='"+validateid+"'";
	        	Query query = session.createQuery(SQL_QUERY);
	        	List list = query.list();
	        	if(list.size()>0)
	        		return (Data)list.get(0);  
	        	else 
	        		return null;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally{
				if(session.isOpen()){
					session.close();
				}
			}
	         return null;	
	    }
	    
	    public void update(Data data)
	    {
	    	Session session = null;
	    	try
	    	{
		    	session=HibernateUtil.getSessionFactory().getCurrentSession();
		        session.beginTransaction();
		        Data data1 = (Data) session.load(Data.class, data.getId());
		        if(null != data1) {
		        	session.update(data);
		        }
		        session.getTransaction().commit();
	    	}
	    	 catch (Exception e) {
					System.out.println("Error to update data"+e.getMessage());
					session.getTransaction().rollback();
				}finally{
					if(session.isOpen()){
						session.close();
					}
				}
	        
	    }
	    
	   
	
	
	

}