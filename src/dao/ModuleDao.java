package dao;

import org.hibernate.Session;

import entities.Module;
import util.HibernateUtil;

public class ModuleDao {

	public void addModule(Module grp) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(grp);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}
	
	public void updateModule(Module grp) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.update(grp);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

	public Module getModuleById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Module grp=(Module)session.get(Module.class,id);
	
		return grp;
	}

}
