package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;

public class InstructorCourseGetDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Instructor instructor = session.get(Instructor.class, 2);
			
			System.out.println(instructor);
			System.out.println(instructor.getCourseList());
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}

