package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;

public class InstructorDeleteDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Instructor instructor = session.get(Instructor.class, 1);
			System.out.println(instructor);
			session.delete(instructor);
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}
