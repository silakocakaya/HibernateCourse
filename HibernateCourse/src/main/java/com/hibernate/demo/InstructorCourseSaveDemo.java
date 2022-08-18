package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;

public class InstructorCourseSaveDemo {
	
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
			
//			InstructorDetail instructorDetail = new InstructorDetail("insta", "asd");
//			Instructor instructor = new Instructor("Aa", "Bb", instructorDetail);
//			
//			Course course = new Course("Title1");
//			Course course2 = new Course("Title2");
//			
//			course.setInstructor(instructor);
//			course2.setInstructor(instructor);
//
//			instructor.addCourse(course);
//			instructor.addCourse(course2);
//			
//			session.save(instructor);

			
			Instructor instructor = session.get(Instructor.class, 2);
			
			Course course = new Course("Course1 For 2");
			Course course2 = new Course("Course2 For 2");
			
			course.setInstructor(instructor);
			course2.setInstructor(instructor);

			//Add etmesek de course.setInstructor'dan dolayi create ediyor.
			instructor.addCourse(course);
			instructor.addCourse(course2);
			
			session.save(course);
			session.save(course2);
			
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}

