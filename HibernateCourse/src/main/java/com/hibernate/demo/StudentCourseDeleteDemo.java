package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;
import com.hibernate.entity.mapping.Review;
import com.hibernate.entity.mapping.Student;

public class StudentCourseDeleteDemo {
	
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();

			Student student = session.get(Student.class, 1);
			
			//Delete also from student_course table
			session.delete(student);
			
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}

