package dao;

import org.hibernate.Session;

import entities.Evaluation;
import util.HibernateUtil;

public class EvaluationDao  {
	
	public void addEvaluation(Evaluation grp) {
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
	
	public void updateEvaluation(Evaluation grp) {
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

	public Evaluation getEvaluationById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Evaluation grp=(Evaluation)session.get(Evaluation.class,id);
	
		return grp;
	}

}
