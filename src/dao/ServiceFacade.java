package dao;

import java.util.Properties;

import dao.user.UserDao;

public class ServiceFacade {
	
	private static ServiceFacade serviceFacadeInstance;
	private UserDao userDao;
	private ServiceFacade() {
		
	}
	public static ServiceFacade getInstance() {
		if(serviceFacadeInstance==null) {
			serviceFacadeInstance=new ServiceFacade();					
		}
		
		return serviceFacadeInstance;
	}
	public void start(Properties prop) throws Exception {
		userDao=new UserDao();
		userDao.start(prop);
	}
	public void stop() {
		
	}
	public UserDao getUserDao() {
		return userDao;
	}

}
