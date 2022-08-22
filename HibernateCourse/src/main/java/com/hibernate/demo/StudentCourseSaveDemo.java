package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.mapping.Course;
import com.hibernate.entity.mapping.Instructor;
import com.hibernate.entity.mapping.InstructorDetail;
import com.hibernate.entity.mapping.Review;
import com.hibernate.entity.mapping.Student;

public class StudentCourseSaveDemo {
	
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

			Course course = new Course("C11");
			session.save(course);

			Student student = new Student("S11");
			Student student2 = new Student("S12");
			
			course.addStudent(student);
			course.addStudent(student2);
			
			session.save(student);
			session.save(student2);
			
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		}
	}

}

