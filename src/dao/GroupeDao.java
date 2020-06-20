package dao;



import org.hibernate.Session;

import entities.Groupe;
import util.HibernateUtil;

public class GroupeDao {
	
	
	public void addGroupe(Groupe grp) {
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
	
	public void updateGroupe(Groupe grp) {
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

	public Groupe getGroupeById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Groupe grp=(Groupe)session.get(Groupe.class,id);
	
		return grp;
	}
	
	

}
