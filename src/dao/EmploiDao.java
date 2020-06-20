package dao;


import org.hibernate.Session;

import entities.Emploi;
import util.HibernateUtil;

public class EmploiDao {
	
	public void addEmploi(Emploi grp) {
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
	
	public void updateEmploi(Emploi grp) {
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

	public Emploi getEmploiById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Emploi grp=(Emploi)session.get(Emploi.class,id);
	
		return grp;
	}

}




