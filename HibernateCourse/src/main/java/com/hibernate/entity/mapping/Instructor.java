package com.hibernate.entity.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_INSTRUCTOR_DETAIL", nullable = true)
	private InstructorDetail instructorDetail;
	
	@OneToMany(mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH })
	private List<Course> courseList;

	public Instructor() {
		super();
	}

	public Instructor(String firstName, String lastName, InstructorDetail instructorDetail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.instructorDetail = instructorDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", instructorDetail="
				+ instructorDetail + "]";
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	public void addCourse(Course course) {
		
		if(courseList == null) 
			courseList = new ArrayList<Course>();
		courseList.add(course);
	}
	
}
