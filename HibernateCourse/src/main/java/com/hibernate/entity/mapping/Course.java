package com.hibernate.entity.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String title;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH })
	@JoinColumn(name = "ID_INSTRUCTOR")
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//unidirectional
	@JoinColumn(name="COURSE_ID")
	private List<Review> reviews;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
			fetch = FetchType.LAZY)
	@JoinTable( name = "student_course",
				joinColumns = @JoinColumn(name = "ID_COURSE"),
				inverseJoinColumns = @JoinColumn(name = "ID_STUDENT")
			)	
	private List<Student> studentList;

	public Course() {
		super();
	}

	public Course(String title) {
		super();
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", instructor=" + instructor + "]";
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review theReview) {
		
		if (reviews == null) {
			reviews = new ArrayList<Review>();
		}
		
		reviews.add(theReview);
	}
	
	public void addStudent(Student theStudent) {
		
		if (studentList == null) {
			studentList = new ArrayList<Student>();
		}
		
		studentList.add(theStudent);
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
}
