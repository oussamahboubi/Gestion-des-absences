package dao;


import org.hibernate.Session;

import entities.Student;
import util.HibernateUtil;

public class StudiantDao {
	
	public void addStudent(Student std) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(std);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}
	
	public void updateStudent(Student std) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.update(std);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}
	
	public Student getStudentById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Student std=(Student)session.get(Student.class, id);
		
		return std;
	}
	
}
