package dao;

import org.hibernate.Session;

import entities.Seance;
import util.HibernateUtil;

public class SeanceDao  {

	public void addSeance(Seance grp) {
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
	
	public void updateSeance(Seance grp) {
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

	public Seance getSeanceById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Seance grp=(Seance)session.get(Seance.class,id);
	
		return grp;
	}

}
