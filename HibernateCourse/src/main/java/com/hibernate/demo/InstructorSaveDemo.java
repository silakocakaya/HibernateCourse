package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;

public class InstructorSaveDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			InstructorDetail instructorDetail = new InstructorDetail("insta", "asd");
			Instructor instructor = new Instructor("Sıla", "Kocakaya", instructorDetail);

			//session.save(instructor);

			//Bidirectional save
			instructorDetail.setInstructor(instructor);
			session.save(instructorDetail);
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}
